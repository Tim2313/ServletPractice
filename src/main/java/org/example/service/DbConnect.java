package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnect {

    public static Connection connectToDb(String dataaBaseName, String user, String pass) {
        Connection conn = null;

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5434/" + dataaBaseName, user, pass);
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

    public void insertTable(Connection conn, String tableName, String customerID, String firstName, String lastName, String email) {
        Statement statement;
        try {

            String query = "insert into %s(customerid, firstName, lastName, email) values ('%s','%s','%s','%s')";
            String insertCommand = String.format(query, tableName, customerID, firstName, lastName, email);
            statement = conn.createStatement();
            statement.executeUpdate(insertCommand);
            System.out.println("Row Inserted!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void selectTable(Connection conn, String whichRow, String nameTable, int limit) {
        Statement statement;
        ResultSet resultSet = null;
        try {

            String query = "select %s from %s limit %d";
            String finalQuery = String.format(query, whichRow, nameTable, limit);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(finalQuery);
            System.out.println("Select Table Success!");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(whichRow) + " ");

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
