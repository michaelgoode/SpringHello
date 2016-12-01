package com.springapp.dao;

import com.springapp.model.Contract;
import com.springapp.model.utile.Machine;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by michaelgoode on 08/11/2016.
 */
public class UtileDAOImpl implements UtileDAO {

    private JdbcTemplate jdbcTemplate;

    public UtileDAOImpl(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }


    @Override
    public List<Machine> searchMachine(String machineName) {

        StringBuilder sb = new StringBuilder();
//        sb.append("select * from MSMS_AssociationHeader h with (NOLOCK), MSMS_AssociationEPC e with (NOLOCK)");
//        sb.append(String.format("where h.ID = e.FK_HeaderID and h.Contract='%s'",contractNo));
//        sb.append("order by h.TransmissionId, h.Contract, h.UPC, e.EPC");


        List<Machine> listMachines = jdbcTemplate.query(sb.toString(), new RowMapper<Machine>() {
            @Override
            public Machine mapRow(ResultSet rs, int rowNum) throws SQLException {
                Machine aMachine = new Machine();
                aMachine.setName("A big pump");
                aMachine.setActualMaxSpeed(100000);
                return aMachine;
            }
        });
        return listMachines;

    }



}
