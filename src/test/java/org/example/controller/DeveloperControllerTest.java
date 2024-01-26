package org.example.controller;

import org.example.constant.ContextType;
import org.example.converter.ArgumentToDeveloperConverter;
import org.example.model.Arguments;
import org.example.model.Developer;
import org.example.model.Response;
import org.example.service.DeveloperService;
import org.example.service.JsonService;
import org.example.web.PathMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeveloperControllerTest {

    private static final String CREATE_DEVELOPER_JSON_SUCCESS_MESSAGE = "{\"message\": \"Developer is created\"}";

    @Mock
    private DeveloperService developerService;
    @Mock
    private PathMapper pathMapper;
    @Mock
    private ArgumentToDeveloperConverter argumentToDeveloperConverter;
    @Mock
    private JsonService jsonService;

    private DeveloperController testInstance;

    @BeforeEach
    void init() {
        testInstance = new DeveloperController(developerService, pathMapper, argumentToDeveloperConverter, jsonService);
    }

    @Test
    void shouldCreateDeveloperJson() {
        Arguments arguments = new Arguments();
        Developer developer = new Developer();
        when(argumentToDeveloperConverter.convert(arguments)).thenReturn(developer);

        Response actual = testInstance.createDeveloperJson(arguments);

        verify(developerService).addDeveloper(developer);
        assertThat(actual.getCode()).isEqualTo(200);
        assertThat(actual.getContentType()).isEqualTo(ContextType.JSON.getContextType());
        assertThat(actual.getBody()).isEqualTo(CREATE_DEVELOPER_JSON_SUCCESS_MESSAGE);
    }

}
