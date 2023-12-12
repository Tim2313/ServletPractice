package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Developer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperService {

    private static final String GET_DEVELOPERS = "select id, firstname, lastname, age, programmingLanguage from developer";
    private static final String ADD_DEVELOPER = "INSERT INTO developer(firstname, lastname, age, programmingLanguage) VALUES (?, ?, ?, ?)";
    private static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_PROGRAMMING_LANGUAGE = "programmingLanguage";
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
                String programmingLanguage = resultSet.getString(COLUMN_PROGRAMMING_LANGUAGE);

                Developer developer = new Developer();
                developer.setId(id);
                developer.setFirstName(firstname);
                developer.setSecondName(lastname);
                developer.setAge(age);
                developer.setProgrammingLanguage(programmingLanguage);

                developers.add(developer);
            }
            LOGGER.info("{} developers have been retrieved from the database.", developers.size());
        } catch (SQLException ex) {
            LOGGER.error("Unable to retrieve developers from DB");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return developers;
    }

    public void addDeveloper(String jsonQuery) {
        Connection connection = connectionService.getConnection();

        PreparedStatement preparedStatement;
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            Developer developer = objectMapper.readValue(jsonQuery, Developer.class);
            preparedStatement = connection.prepareStatement(ADD_DEVELOPER);

            preparedStatement.setString(1, developer.getFirstName());
            preparedStatement.setString(2, developer.getSecondName());
            preparedStatement.setInt(3, developer.getAge());
            preparedStatement.setString(4, developer.getProgrammingLanguage());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error occurs in sql query: {}", e.getErrorCode());
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public static DeveloperService getInstance() {
        if (instance == null) {
            instance = new DeveloperService();
        }
        return instance;
    }
}
