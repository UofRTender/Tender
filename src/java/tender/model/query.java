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
import java.util.ArrayList;
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

            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }

            conn.close();
            return rowCount == 1;
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public String getValue(String table, String column, HashMap conditions) {
        query();
        String result = "";
        try {
            Class.forName(jdbcDriver).newInstance();
            Connection conn = DriverManager.getConnection(jdbcURL, user, DBpassword);
            Statement stmt = conn.createStatement();

            String query = "select " + column;

            query += " from " + table + " where ";
            for (int i = 0; i < conditions.size(); i++) {
                query += conditions.keySet().toArray()[i];
                query += "=";
                query += "'" + conditions.values().toArray()[i] + "'";
                if (i < conditions.size() - 1) {
                    query += " and ";
                }
            }
            query += ";";
            ResultSet rs = stmt.executeQuery(query);
            //ResultSetMetaData rsmd = rs.getMetaData();
            //int numOfCols = rsmd.getColumnCount();
            //result=rs.getString(numOfCols);
            while (rs.next()) {
                result = rs.getString(column);
            }

            conn.close();
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "fuck";
    }

    public void update(String table, String column, HashMap restraints, String new_values) {
        query();
        String execute = "UPDATE ";
        try {
            Class.forName(jdbcDriver).newInstance();
            Connection conn = DriverManager.getConnection(jdbcURL, user, DBpassword);
            Statement stmt = conn.createStatement();

            execute += table + " SET ";
            execute += column + " = ";
            execute += "'" + new_values + "'";
            execute += " WHERE ";
            for (int i = 0; i < restraints.size(); i++) {
                Object key = restraints.keySet().toArray()[i];
                execute += key + " = " + "'" + restraints.get(key) + "'";
                if (i + 1 != restraints.size()) {
                    execute += " and ";
                }
            }
            execute += ";";
            stmt.executeUpdate(execute);

            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        //return execute;
    }

    public ArrayList getManyRows(String table, String column, HashMap conditions) {
        query();
        ArrayList rows = new ArrayList();
        String query = "select * from " + table + " where ";

        for (int i = 0; i < conditions.size(); i++) {
            query += conditions.keySet().toArray()[i];
            query += "=";
            query += "'" + conditions.values().toArray()[i] + "'";
            if (i < conditions.size() - 1) {
                query += " and ";
            }
        }
        query += ";";
        try {
            Class.forName(jdbcDriver).newInstance();
            Connection conn = DriverManager.getConnection(jdbcURL, user, DBpassword);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                rows.add(rs.getArray(column));
            }
            conn.close();
            return rows;
        } catch (Exception e) {

        }
        return rows;
    }

    public void delete(String table, HashMap conditions) {
        query();
        String execute = "delete from " + table + " where ";
        try {

            Class.forName(jdbcDriver).newInstance();
            Connection conn = DriverManager.getConnection(jdbcURL, user, DBpassword);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < conditions.size(); i++) {
                execute += conditions.keySet().toArray()[i];
                execute += "=";
                execute += "'" + conditions.values().toArray()[i] + "'";
                if (i < conditions.size() - 1) {
                    execute += " and ";
                }
            }
            execute += ";";
            stmt.executeUpdate(execute);

            conn.close();
        }catch(Exception e){
            
        }
    }
}
