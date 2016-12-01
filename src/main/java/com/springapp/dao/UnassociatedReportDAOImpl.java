package com.springapp.dao;

import com.springapp.model.AssociationBatch;
import com.springapp.model.UnassociatedTag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.List;

/**
 * Created by michaelgoode on 19/09/2016.
 */
public class UnassociatedReportDAOImpl implements UnassociatedReportDAO {

    private JdbcTemplate jdbcTemplate;

    public UnassociatedReportDAOImpl(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public void postBatch(HashSet<UnassociatedTag> tags, String filename) {
        // write the batch to the db
        // write all tag data from the list to the unassociated tables

        String sqlInsertHeader = "insert into MSMS_UnassociatedTagBatch (name,dateReceived, total) values (?,GetDate(),?)";
        String sqlInsertTag = "insert into MSMS_UnassociatedTags (fk_batch,EPC,valid,message) values (?,?,?,?)";
        int Id = 0;
        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(sqlInsertHeader, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, filename);
            stmt.setInt(2, tags.size());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                Id = keys.getInt(1); //id returned after insert execution
            }

            PreparedStatement ps = conn.prepareStatement(sqlInsertTag);

            for (UnassociatedTag tag : tags) {
                ps.setInt(1, Id);
                ps.setString(2, tag.getEPC());
                ps.setBoolean(3, tag.isValid());
                ps.setString(4, "");
                ps.addBatch();
            }

            ps.executeBatch();
            ps.clearBatch();
            conn.commit();
            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<UnassociatedTag> getReport() {
        //String sql = "SELECT * FROM MSMS_UnassociatedTags with (NOLOCK) order by ID";

        //sql = "select u.valid,b.BatchID,b.[FileName],h.TransmissionId,b.Sent,h.[Contract],h.LabelForm,h.UPC,h.AssociationDate,h.Bureau,e.epc "
        //         + " from MSMS_AssociationBatchID b, MSMS_AssociationHeader h, MSMS_AssociationEPC e, MSMS_UnassociatedTags u "
        //        + " where b.BatchID = convert(int,h.TransmissionId) and h.ID = e.FK_HeaderID and u.EPC = e.EPC ";

        StringBuilder sql = new StringBuilder();
        sql.append("select ub.[name] reportname, u.epc,e.epc,u.valid,b.BatchID,b.[FileName],h.TransmissionId,b.Sent,b.IsSent,h.[Contract],h.LabelForm,h.UPC,h.AssociationDate,h.Bureau");
        sql.append(" from MSMS_UnassociatedTags u with (NOLOCK)");
        sql.append(" left join MSMS_AssociationEPC e with (NOLOCK)");
        sql.append(" on u.EPC=e.EPC");
        sql.append(" left join MSMS_AssociationHeader h with (NOLOCK)");
        sql.append(" on e.FK_HeaderID = h.ID");
        sql.append(" left join MSMS_AssociationBatchID b");
        sql.append(" on b.BatchID = convert(int,h.TransmissionId)");
        sql.append(" left join MSMS_UnassociatedTagBatch ub");
        sql.append(" on ub.ID = u.fk_batch");
        sql.append(" order by ub.[name],BatchID desc");

        List<UnassociatedTag> listTags = null;

        try {
            listTags = jdbcTemplate.query(sql.toString(), new RowMapper<UnassociatedTag>() {
                @Override
                public UnassociatedTag mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UnassociatedTag tag = new UnassociatedTag();
                    tag.setEPC(rs.getString("EPC"));
                    tag.setSent(rs.getString("Sent"));
                    tag.setBatch(rs.getInt("BatchID"));
                    tag.setFilename(rs.getString("FileName"));
                    tag.setBureau(rs.getString("Bureau"));
                    tag.setValid(rs.getBoolean("valid"));
                    tag.setAssociation(rs.getString("AssociationDate"));
                    tag.setUpc(rs.getString("UPC"));
                    tag.setContract(rs.getString("Contract"));
                    tag.setNowSent(rs.getBoolean("IsSent"));
                    tag.setReportName(rs.getString("reportname"));
                    return tag;
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return listTags;
        }
    }

}
