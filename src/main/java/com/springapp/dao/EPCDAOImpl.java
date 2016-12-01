package com.springapp.dao;

import com.springapp.model.EPC;
import com.springapp.model.Header;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by michaelgoode on 24/08/2016.
 */
public class EPCDAOImpl implements EPCDAO {

    private JdbcTemplate jdbcTemplate;
    private boolean isValid;

    public EPCDAOImpl(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }
    @Override
    public List<EPC> searchEPC(String epcNo) {

        StringBuilder sb = new StringBuilder();
        sb.append("select * from MSMS_AssociationHeader h with (NOLOCK), MSMS_AssociationEPC e with (NOLOCK)");
        sb.append(String.format("where h.ID = e.FK_HeaderID and EPC='%s'",epcNo));
        sb.append("order by h.TransmissionId, h.Contract, h.UPC, e.EPC");
        List<EPC> listEPC = jdbcTemplate.query(sb.toString(), new RowMapper<EPC>() {
            @Override
            public EPC mapRow(ResultSet rs, int rowNum) throws SQLException {
                EPC aEPC = new EPC();
                aEPC.setEpc(rs.getString("EPC"));
                aEPC.setLabelForm(rs.getString("LabelForm"));
                aEPC.setBureau(rs.getString("Bureau"));
                aEPC.setTransmissionId(rs.getString("TransmissionId"));
                aEPC.setAssociationDate(rs.getString("AssociationDate"));
                aEPC.setContract(rs.getString("Contract"));
                aEPC.setUpc(rs.getString("UPC"));
                return aEPC;
            }
        });
        return listEPC;

    }

    @Override
    public EPC checkEPC( String epcNo ) {
        EPC aEPC = new EPC();
        aEPC.setValuesMap(this.getValuesMap(epcNo.trim()));
        aEPC.setMessages(this.validate(aEPC.getValuesMap()));
        aEPC.setSmlValid(this.isValid);
        return aEPC;
    }

    public Hashtable<String,String> getValuesMap(String hex ) {
        String binary = hexToBinary(hex.substring(0,2),8)
                + hexToBinary(hex.substring(2,4),8)
                + hexToBinary(hex.substring(4,6),8)
                + hexToBinary(hex.substring(6,8),8)
                + hexToBinary(hex.substring(8,10),8)
                + hexToBinary(hex.substring(10,12),8)
                + hexToBinary(hex.substring(12,14),8)
                + hexToBinary(hex.substring(14,16),8)
                + hexToBinary(hex.substring(16,18),8)
                + hexToBinary(hex.substring(18,20),8)
                + hexToBinary(hex.substring(20,22),8)
                + hexToBinary(hex.substring(22,24),8);

        StringBuilder sb = new StringBuilder();

        Hashtable<String,String> results = new Hashtable<String, String>();

        String header = binaryToDecimal(binary.substring(0,8));
        results.put("header", header);
        String filter = binaryToDecimal(binary.substring(8,11));
        results.put("filter",filter);
        String partition = binaryToDecimal(binary.substring(11,14));
        results.put("partition",partition);
        String company = binaryToDecimal(binary.substring(14,34));
        results.put("company",company);
        String item = binaryToDecimal(binary.substring(34,58));
        results.put("item",item);
        String serial = binaryToDecimal(binary.substring(59,96));
        long lserial = Long.parseLong(serial);
        results.put("serial",String.format("%,d", lserial));
        return results;
    }

    private Hashtable<String,String> validate( Hashtable<String,String> values ) {
        isValid = true;
        Hashtable<String,String> messages = new Hashtable();
        String header = values.get("header").toString();
        if (header.equals("48")) {
            messages.put("header","Header is valid (SGTIN-96)");
        } else {
            messages.put("header","Header is not valid (SGTIN-96) should be 48");
            isValid = false;
        }
        String filter = values.get("filter").toString();
        if (filter.equals("1")) {
            messages.put("filter","Filter is valid");
        } else {
            messages.put("filter","Filter is not valid, should be 1");
            isValid = false;
        }
        String partition = values.get("partition").toString();
        if (partition.equals("6")) {
            messages.put("partition","Partition is valid");
        } else {
            messages.put("partition","Partition is not valid, should be 6");
            isValid = false;
        }
        String company = values.get("company").toString();
        if ((company.equals("504492")) || (company.equals("504560"))) {
            messages.put("company","M&S valid company prefix");
        } else {
            messages.put("company","Company prefix is not valid, should be 504492 or 504560");
            isValid = false;
        }

        String serial = values.get("serial").toString();
        serial = serial.replaceAll(",","");
        if ((Long.parseLong(serial) >= 10000000000L) && (Long.parseLong(serial) <= 15000000000L)) {
            messages.put("serial", "Serial is in range (SML)");
        } else {
            messages.put("serial", "Serial is not in range (SML), should be between 10,000,000,000 and 15,000,000,000");
            isValid = false;
        }
        return messages;
    }

    private String binaryToDecimal(String binary) {
        Long l = Long.parseLong(binary,2);
        return l.toString();
    }

    private String hexToBinary(String hexadecimalString, int digits) {
        int i = Integer.parseInt(hexadecimalString, 16);
        String binaryString = Integer.toBinaryString(i);
        while (binaryString .length() < digits) {
            binaryString  = "0" + binaryString ;
        }
        return binaryString;
    }
}
