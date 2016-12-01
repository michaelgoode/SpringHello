package com.springapp.dao;

import com.springapp.model.Contract;
import com.springapp.model.EPC;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by michaelgoode on 08/09/2016.
 */
public class ContractDAOImpl implements ContractDAO {

    private JdbcTemplate jdbcTemplate;

    public ContractDAOImpl(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }
    @Override
    public List<Contract> searchContract(String contractNo) {

        StringBuilder sb = new StringBuilder();
        sb.append("select * from MSMS_AssociationHeader h with (NOLOCK), MSMS_AssociationEPC e with (NOLOCK)");
        sb.append(String.format("where h.ID = e.FK_HeaderID and h.Contract='%s'",contractNo));
        sb.append("order by h.TransmissionId, h.Contract, h.UPC, e.EPC");
        List<Contract> listContract = jdbcTemplate.query(sb.toString(), new RowMapper<Contract>() {
            @Override
            public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {
                Contract aContract = new Contract();
                aContract.setEpc(rs.getString("EPC"));
                aContract.setLabelForm(rs.getString("LabelForm"));
                aContract.setBureau(rs.getString("Bureau"));
                aContract.setTransmissionId(rs.getString("TransmissionId"));
                aContract.setAssociationDate(rs.getString("AssociationDate"));
                aContract.setContract(rs.getString("Contract"));
                aContract.setUpc(rs.getString("UPC"));
                return aContract;
            }
        });
        return listContract;

    }
}
