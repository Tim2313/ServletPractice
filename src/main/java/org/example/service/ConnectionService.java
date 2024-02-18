package org.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class ConnectionService {

    // All Uppercase for Constants: Constants, which are typically declared using the final keyword,
    // should be written in all uppercase letters with words separated by underscores. This is known as "SCREAMING_SNAKE_CASE." 
    private static final String DATABASE_NAME = "postgres";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "postgres";
    private static final String DRIVER_POSTGRESQL = "org.postgresql.Driver";
    private static final String SCHEMA = "jdbc:postgresql";
    private static final String HOST = "developers-db";
    private static final String PORT = "5432";
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionService.class);
    private final Connection connection;
    private static ConnectionService instance;

    public ConnectionService() {
        connection = connectToDb();
    }

    public Connection getConnection() {
        return connection;
    }

    public Connection connectToDb() {
        Connection connectionLocal;
        try {
            Class.forName(DRIVER_POSTGRESQL);
            connectionLocal = DriverManager.getConnection(getDatabaseUrl(), DATABASE_USER, DATABASE_PASSWORD);
            LOGGER.info("Connection Established!");
        } catch (Exception e) {
            LOGGER.error("Check connection methods: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        return connectionLocal;
    }

    public String getDatabaseUrl() {
        String url = "%s://%s:%s/%s";
        return String.format(url, SCHEMA, HOST, PORT, DATABASE_NAME);
    }

    public static ConnectionService getInstance() {
        if (instance == null) {
            instance = new ConnectionService();
        }
        return instance;
    }

}
