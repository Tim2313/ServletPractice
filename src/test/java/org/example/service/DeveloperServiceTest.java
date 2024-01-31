package org.example.service;

import org.checkerframework.checker.units.qual.A;
import org.example.model.Developer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DeveloperServiceTest {

    private static final String ADD_DEVELOPER = "INSERT INTO developer(firstName," +
            " lastName," +
            " age," +
            " programmingLanguage) " +
            "VALUES (?, ?, ?, ?)";

    @Mock
    DeveloperService testInstance;

    private static Developer developer;

    @BeforeEach
    void init() {
        developer = new Developer(1,
                "Tima",
                "Kilak",
                23,
                "Java");
    }

    @Test
    void shouldGetDevelopers() {
        when(testInstance.getDevelopers()).thenReturn(List.of(developer));
        List<Developer> actual = testInstance.getDevelopers();

        assertThat(actual)
                .isNotNull()
                .hasSize(1)
                .contains(developer);
    }
}

