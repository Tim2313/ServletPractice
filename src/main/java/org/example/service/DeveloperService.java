package org.example.service;

import org.example.model.Developer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeveloperService {

    private static final String GET_DEVELOPERS = "select id, firstname, lastname, age, proglang from developer";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_PROG_LANG = "proglang";

    private static final Logger LOGGER = LoggerFactory.getLogger(DeveloperService.class);
    private final ConnectionService connectionService = ConnectionService.getInstance();

    private static DeveloperService instance;

    public List<Developer> getDevelopers() {
        Connection connection = connectionService.getConnection();
        List<Developer> developers = new ArrayList<>();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(GET_DEVELOPERS)
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_ID);
                String firstname = resultSet.getString(COLUMN_FIRST_NAME);
                String lastname = resultSet.getString(COLUMN_LASTNAME);
                int age = resultSet.getInt(COLUMN_AGE);
                String proglang = resultSet.getString(COLUMN_PROG_LANG);

                Developer developer = new Developer();
                developer.setId(id);
                developer.setFirstName(firstname);
                developer.setSecondName(lastname);
                developer.setAge(age);
                developer.setProgLang(proglang);

                developers.add(developer);
            }
        } catch (SQLException ex) {
            LOGGER.error("Check database's names of columns!");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return developers;
    }

    public static DeveloperService getInstance() {
        if (instance == null) {
            instance = new DeveloperService();
        }
        return instance;
    }
}
