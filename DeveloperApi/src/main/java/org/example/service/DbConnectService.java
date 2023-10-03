package org.example.service;

import java.sql.*;

public class DbConnectService {

    public Connection connectToDb(String dataBaseName, String user, String pass) {
        Connection conn = null;

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/" + dataBaseName, user, pass);
            if (conn != null) {
                System.out.println("Connection Established!");
            } else {
                System.out.println("Connection Failed!");
            }


        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public String selectTable(Connection conn, String whichRow, String columnLabel, String nameTable) {
        Statement statement;
        ResultSet resultSet = null;
        try {

            String query = "select %s from %s";
            String finalQuery = String.format(query, whichRow, nameTable);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(finalQuery);
            System.out.println("Select Table Success!");
            while (resultSet.next()) {
                return resultSet.getString(columnLabel + " ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return "";
    }


}