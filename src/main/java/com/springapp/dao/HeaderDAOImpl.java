package com.springapp.dao;

/**
 * Created by michaelgoode on 06/08/2016.
 */

import com.springapp.model.Header;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HeaderDAOImpl implements HeaderDAO {

    private JdbcTemplate jdbcTemplate;

    public HeaderDAOImpl(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }
    @Override
    public List<Header> list() {

        String sql = "SELECT * FROM MSMS_AssociationHeader order by TransmissionId desc";
        List<Header> listHeader = jdbcTemplate.query(sql, new RowMapper<Header>() {
            @Override
            public Header mapRow(ResultSet rs, int rowNum) throws SQLException {
                Header aHeader = new Header();
                aHeader.setContract(rs.getString("Contract"));
                aHeader.setUpc(rs.getString("UPC"));
                aHeader.setTransmissionId(rs.getString("TransmissionId"));
                aHeader.setLabelForm(rs.getString("LabelForm"));
                aHeader.setBureau(rs.getString("Bureau"));
                aHeader.setAssocDate(rs.getString("AssociationDate"));
                return aHeader;
            }
        });
        return listHeader;
    }
}


