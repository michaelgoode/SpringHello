package com.springapp.dao;

import com.springapp.model.AssociationBatch;
import com.springapp.model.Header;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by michaelgoode on 08/09/2016.
 */
public class AssociationBatchDAOImpl implements AssociationBatchDAO {

    private JdbcTemplate jdbcTemplate;
    public AssociationBatchDAOImpl(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public List<AssociationBatch> listBatch() {
        String sql = "SELECT * FROM MSMS_AssociationBatchID with (NOLOCK) order by BatchId desc";
        List<AssociationBatch> listBatch = jdbcTemplate.query(sql, new RowMapper<AssociationBatch>() {
            @Override
            public AssociationBatch mapRow(ResultSet rs, int rowNum) throws SQLException {
                AssociationBatch aBatch = new AssociationBatch();
                aBatch.setBatchID(rs.getInt("BatchID"));
                aBatch.setFilename(rs.getString("FileName"));
                aBatch.setDateSent(rs.getDate("Sent"));
                aBatch.setSent(rs.getBoolean("IsSent"));
                return aBatch;
            }
        });
        return listBatch;
    }
}
