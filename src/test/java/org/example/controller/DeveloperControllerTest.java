package org.example.controller;

import org.example.constant.ContextType;
import org.example.constant.JspPage;
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

import java.util.LinkedList;
import java.util.List;

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

    @Test
    void shouldCreateDeveloperHtml() {
        Arguments arguments = new Arguments();
        Developer developer = new Developer();
        when(argumentToDeveloperConverter.convert(arguments)).thenReturn(developer);

        Response actual = testInstance.createDeveloperHtml(arguments);

        verify(developerService).addDeveloper(developer);
        assertThat(actual.getCode()).isEqualTo(200);
        assertThat(actual.getContentType()).isEqualTo(ContextType.HTML.getContextType());
    }

    @Test
    void shouldGetTablePage() {
        Arguments arguments = new Arguments();
        Developer developer = new Developer();

        List<Developer> developersTest = new LinkedList<>();
        developersTest.add(developer);

        when(developerService.getDevelopers()).thenReturn(developersTest);

        Response actual = testInstance.getTablePage(arguments);

        assertThat(actual.getJspAttributes()).isNotEmpty();
        assertThat(actual.getCode()).isEqualTo(200);
        assertThat(actual.getJspPage()).isEqualTo(JspPage.DEVELOPER_TABLE_PAGE.getFilePath());
    }

    @Test
    void shouldGetJsonPage() {
        Arguments arguments = new Arguments();
        Developer developer = new Developer();
        developer.setFirstName("Vlad");
        developer.setSecondName("Kikul");
        developer.setAge(23);
        developer.setProgrammingLanguage("Java");

        List<Developer> developersTest = new LinkedList<>();
        developersTest.add(developer);

        when(developerService.getDevelopers()).thenReturn(developersTest);

        Response actual = testInstance.getJsonPage(arguments);

        verify(jsonService).getDevelopers(developersTest);
        assertThat(actual.getBody()).isEqualTo(jsonService.getDevelopers(developersTest));
        assertThat(actual.getCode()).isEqualTo(200);
    }
}