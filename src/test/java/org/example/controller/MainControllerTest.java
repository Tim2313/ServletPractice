package org.example.controller;

import org.example.constant.ContextType;
import org.example.constant.JspPage;
import org.example.model.Arguments;
import org.example.model.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.constant.ResponseCode.*;

class MainControllerTest {

    private final MainController testInstance = MainController.getInstance();

    private static Arguments arguments;

    @BeforeAll
    static void setup() {
        arguments = new Arguments();
    }

    @Test
    void shouldGetHelloPage() {
        Response actual = testInstance.getHelloPage(arguments);

        assertThat(actual.getCode()).isEqualTo(HTTP_OK.getResponseCodes());
        assertThat(actual.getContentType()).isEqualTo(ContextType.HTML.getContextType());
        assertThat(actual.getJspPage()).isEqualTo(JspPage.HELLO_PAGE.getFilePath());
    }

    @Test
    void shouldGetNotFoundResponsePage() {
        Response actual = testInstance.getNotFoundResponsePage(arguments);

        assertThat(actual.getCode()).isEqualTo(HTTP_NOT_FOUND.getResponseCodes());
    }

    @Test
    void shouldGetCreationFormPage() {
        Response actual = testInstance.getCreationFormPage(arguments);

        assertThat(actual.getCode()).isEqualTo(HTTP_OK.getResponseCodes());
        assertThat(ContextType.HTML.getContextType()).isEqualTo(actual.getContentType());
        assertThat(JspPage.CREATION_FORM_PAGE.getFilePath()).isEqualTo(actual.getJspPage());
    }
}
