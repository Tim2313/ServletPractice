package org.example.controller;

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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.constant.ResponseCode.*;
import static org.example.constant.ContextType.*;
import static org.example.constant.JspPage.*;
import static org.example.constant.HttpMapping.*;
import static org.mockito.Mockito.*;

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
        developer.setFirstName(FIRST_NAME);
        developer.setLastName(LAST_NAME);
        developer.setAge(AGE);
        developer.setProgrammingLanguage(PROGRAMMING_LANGUAGE);
    }

    @Test
    void shouldCreateDeveloperJson() {
        when(argumentToDeveloperConverter.convert(arguments)).thenReturn(developer);

        Response actual = testInstance.createDeveloperJson(arguments);

        verify(developerService).addDeveloper(developer);
        assertThat(actual.getCode()).isEqualTo(HTTP_OK.getValue());
        assertThat(actual.getContentType()).isEqualTo(JSON.getValue());
        assertThat(actual.getBody()).isEqualTo(CREATE_DEVELOPER_JSON_SUCCESS_MESSAGE);
    }

    @Test
    void shouldCreateDeveloperHtml() {
        when(argumentToDeveloperConverter.convert(arguments)).thenReturn(developer);

        Response actual = testInstance.createDeveloperHtml(arguments);

        verify(developerService).addDeveloper(developer);
        assertThat(actual.getRedirect()).isEqualTo(GET_DEVELOPERS_FORM_HTML);
        assertThat(actual.getCode()).isEqualTo(HTTP_OK.getValue());
        assertThat(actual.getContentType()).isEqualTo(HTML.getValue());
    }

    @Test
    void shouldGetTablePage() {
        when(developerService.getDevelopers()).thenReturn(List.of(developer));

        Response actual = testInstance.getTablePage(arguments);

        assertThat(actual)
                .extracting(Response::getJspAttributes)
                .isNotNull();

        assertThat(actual.getCode()).isEqualTo(HTTP_OK.getValue());
        assertThat(actual.getJspPage()).isEqualTo(DEVELOPER_TABLE_PAGE.getValue());
    }

    @Test
    void shouldGetJsonPage() {
        when(developerService.getDevelopers()).thenReturn(List.of(developer));

        Response actual = testInstance.getJsonPage(arguments);

        String testBody = jsonService.getDevelopers(List.of(developer));

        verify(jsonService, times(2)).getDevelopers(List.of(developer));
        assertThat(actual.getBody()).isEqualTo(testBody);
        assertThat(actual.getCode()).isEqualTo(HTTP_OK.getValue());
    }
}
