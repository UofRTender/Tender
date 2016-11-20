/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tender.model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author marlon
 */
public class query {

    private String jdbcDriver;
    private String jdbcURL;
    private String user;
    private String DBpassword;

    public void query() {
        jdbcDriver = "org.postgresql.Driver";
        jdbcURL = "jdbc:postgresql:tender";
        user = "postgres";
        DBpassword = "T3nder123";
    }

    public int insert(String table, HashMap values) {

        query();
        int pk = 0;
        try {

            Class.forName(jdbcDriver).newInstance();
            Connection conn = DriverManager.getConnection(jdbcURL, user, DBpassword);
            Statement stmt = conn.createStatement();

            String insert = "insert into " + table + " (";

            for (int i = 0; i < values.size(); i++) {
                insert += values.keySet().toArray()[i];
                if (i < values.size() - 1) {
                    insert += ",";
                }
            }

            insert += ") values (";
            for (int i = 0; i < values.size(); i++) {
                insert += "'" + values.values().toArray()[i] + "'";
                if (i < values.size() - 1) {
                    insert += ",";
                }
            }
            insert += ");";

            stmt.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();

            while (rs.next()) {
                pk = rs.getInt(1);
            }
            conn.close();
            return pk;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public Boolean exists(String table, HashMap values) {
        Boolean result = false;
        query();
        try {
            Class.forName(jdbcDriver).newInstance();
            Connection conn = DriverManager.getConnection(jdbcURL, user, DBpassword);
            Statement stmt = conn.createStatement();

            String query = "select ";
            for (int i = 0; i < values.size(); i++) {
                query += values.keySet().toArray()[i];
                if (i < values.size() - 1) {
                    query += ",";
                }
            }
            query += " from " + table + " where ";
            for (int i = 0; i < values.size(); i++) {
                query += values.keySet().toArray()[i];
                query += "=";
                query += "'" + values.values().toArray()[i] + "'";
                if (i < values.size() - 1) {
                    query += " and ";
                }
            }
            query += ";";
            ResultSet rs = stmt.executeQuery(query);

            int rowCount=0;
            while(rs.next()){
                rowCount++;
            }
            

            conn.close();
            return rowCount==1;
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }
}
