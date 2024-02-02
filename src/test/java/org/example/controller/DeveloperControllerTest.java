package org.example.controller;

import org.example.constant.ContextType;
import org.example.constant.JspPage;
import org.example.converter.ArgumentToDeveloperConverter;
import org.example.model.Arguments;
import org.example.model.Developer;
import org.example.model.Response;
import org.example.service.DeveloperService;
import org.example.service.JsonService;
import org.example.web.HttpMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
    private static final String FIRST_NAME = "Tima";
    private static final String LAST_NAME = "Java";
    private static final int AGE = 27;
    private static final String PROGRAMMING_LANGUAGE = "Java";


    @Mock
    private DeveloperService developerService;
    @Mock
    private HttpMapper httpMapper;
    @Mock
    private ArgumentToDeveloperConverter argumentToDeveloperConverter;
    @Mock
    private JsonService jsonService;

    @InjectMocks
    private DeveloperController testInstance;

    private static Arguments arguments;

    private static Developer developer;

    @BeforeAll
    static void setup() {
        arguments = new Arguments();
        developer = new Developer();
    }

    @Test
    void shouldCreateDeveloperJson() {
        when(argumentToDeveloperConverter.convert(arguments)).thenReturn(developer);

        Response actual = testInstance.createDeveloperJson(arguments);

        verify(developerService).addDeveloper(developer);
        assertThat(actual.getCode()).isEqualTo(200);
        assertThat(actual.getContentType()).isEqualTo(ContextType.JSON.getContextType());
        assertThat(actual.getBody()).isEqualTo(CREATE_DEVELOPER_JSON_SUCCESS_MESSAGE);
    }

    @Test
    void shouldCreateDeveloperHtml() {
        when(argumentToDeveloperConverter.convert(arguments)).thenReturn(developer);

        Response actual = testInstance.createDeveloperHtml(arguments);

        verify(developerService).addDeveloper(developer);
        assertThat(actual.getCode()).isEqualTo(200);
        assertThat(actual.getContentType()).isEqualTo(ContextType.HTML.getContextType());
    }

    @Test
    void shouldGetTablePage() {
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
        developer.setFirstName(FIRST_NAME);
        developer.setLastName(LAST_NAME);
        developer.setAge(AGE);
        developer.setProgrammingLanguage(PROGRAMMING_LANGUAGE);

        List<Developer> developersTest = new LinkedList<>();
        developersTest.add(developer);

        when(developerService.getDevelopers()).thenReturn(developersTest);

        Response actual = testInstance.getJsonPage(arguments);

        verify(jsonService).getDevelopers(developersTest);
        assertThat(actual.getBody()).isEqualTo(jsonService.getDevelopers(developersTest));
        assertThat(actual.getCode()).isEqualTo(200);
    }
}