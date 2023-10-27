package org.example.service;

import org.example.model.Developer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeveloperService {

    private static final String GET_DEVELOPERS = "select id, firstname, lastname, age, proglang from developer";

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
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                int age = resultSet.getInt("age");
                String proglang = resultSet.getString("proglang");

                Developer developer = new Developer();
                developer.setId(id);
                developer.setFirstName(firstname);
                developer.setSecondName(lastname);
                developer.setAge(age);
                developer.setProgLang(proglang);

                developers.add(developer);
            }
        } catch (SQLException ex) {
            System.err.println("Error occurs");
            ex.printStackTrace();
        }
        return developers;
    }

    public static DeveloperService getInstance(){
        if (instance == null) {
            instance = new DeveloperService();
        }
        return instance;
    }
}
