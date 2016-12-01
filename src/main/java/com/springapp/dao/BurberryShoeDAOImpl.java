package com.springapp.dao;

import com.springapp.model.BurberryShoe;
import com.springapp.model.UnassociatedTag;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

/**
 * Created by michaelgoode on 29/11/2016.
 */
public class BurberryShoeDAOImpl implements BurberryShoeDAO {
    private JdbcTemplate jdbcTemplate;

    public BurberryShoeDAOImpl(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public void saveShoe(String code, String image) {
        final String insertSql = "insert into BUBU_SHOES (Code,ImageName,rowUpdate) values (?,?,GetDate())";
        PreparedStatement preparedStatement = null;
        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.setString(1, code.trim().toUpperCase());
            preparedStatement.setString(2, image.trim().toUpperCase());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
    }

    @Override
    public List<String> findShoeReferences( String code ) {
        final String sql = "select * from BUBU_SHOES where Code=?";
        PreparedStatement preparedStatement = null;
        List<String> items = new ArrayList<String>();
        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, code.trim().toUpperCase());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                items.add(resultSet.getString("ImageName"));
            }
        } catch (SQLException ex) {
            ex.getMessage();
            return items;
        } finally {
            try {
                preparedStatement.close();
                return items;
            } catch (SQLException ex) {
                ex.getMessage();
                return items;
            }
        }
    }

    @Override
    public void postBatch(HashSet<BurberryShoe> items, String filename) {
        String sqlInsertShoe = "insert into BUBU_SHOES (Code,ImageName,rowUpdate) values (?,?,GetDate())";

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsertShoe);
            for (BurberryShoe shoe : items) {
                preparedStatement.setString(1, shoe.getImagecode());
                preparedStatement.setString(2, shoe.getImagename());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
            conn.commit();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
