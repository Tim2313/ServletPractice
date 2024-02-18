package org.example.service;

import org.example.model.Developer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.example.model.Developer.*;


@ExtendWith(MockitoExtension.class)
class DeveloperServiceTest {
    private static final String ADD_DEVELOPER = "INSERT INTO developer(firstName," +
            " lastName," +
            " age," +
            " programmingLanguage) " +
            "VALUES (?, ?, ?, ?)";

    private static final String GET_DEVELOPERS = "select id," +
            " firstName," +
            " lastName," +
            " age," +
            " programmingLanguage from developer";

    private static final int ID = 1;
    private static final String FIRST_NAME = "Tima";
    private static final String LAST_NAME = "Kilkas";
    private static final int AGE = 23;
    private static final String PROGRAMMING_LANGUAGE = "Java";

    @Mock
    private ConnectionService connectionService;

    @Mock
    private Connection connection;

    @Mock
    private ResultSet resultSet;

    @Mock
    private Statement statement;

    @Mock
    PreparedStatement preparedStatement;

    @InjectMocks
    private DeveloperService testInstance;

    private static Developer developer;

    @BeforeAll
    static void init() {
        developer = new Developer();
        developer.setId(ID);
        developer.setFirstName(FIRST_NAME);
        developer.setLastName(LAST_NAME);
        developer.setAge(AGE);
        developer.setProgrammingLanguage(PROGRAMMING_LANGUAGE);

    }

    @Test
    void shouldGetDevelopers() throws SQLException {
        when(connectionService.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(GET_DEVELOPERS)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true)
                .thenReturn(false);

        when(resultSet.getInt(COLUMN_ID)).thenReturn(ID);
        when(resultSet.getString(COLUMN_FIRST_NAME)).thenReturn(FIRST_NAME);
        when(resultSet.getString(COLUMN_LAST_NAME)).thenReturn(LAST_NAME);
        when(resultSet.getInt(COLUMN_AGE)).thenReturn(AGE);
        when(resultSet.getString(COLUMN_PROGRAMMING_LANGUAGE)).thenReturn(PROGRAMMING_LANGUAGE);


        List<Developer> actual = testInstance.getDevelopers();

        assertThat(actual)
                .isNotNull()
                .hasSize(1)
                .contains(developer);
    }

    @Test
    void shouldAddDeveloper() throws SQLException {
        when(connectionService.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(ADD_DEVELOPER)).thenReturn(preparedStatement);

        testInstance.createDeveloper(developer);

        verify(preparedStatement).setString(1, FIRST_NAME);
        verify(preparedStatement).setString(2, LAST_NAME);
        verify(preparedStatement).setInt(3, AGE);
        verify(preparedStatement).setString(4, PROGRAMMING_LANGUAGE);

        verify(preparedStatement).executeUpdate();

    }
}
