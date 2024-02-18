package org.example.controller;

import org.example.model.Arguments;
import org.example.model.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.constant.ResponseCode.*;
import static org.example.constant.ContextType.*;
import static org.example.constant.JspPage.*;

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

        assertThat(actual.getCode()).isEqualTo(HTTP_OK.getValue());
        assertThat(actual.getContentType()).isEqualTo(HTML.getValue());
        assertThat(actual.getJspPage()).isEqualTo(HELLO_PAGE.getValue());
    }

    @Test
    void shouldGetNotFoundResponsePage() {
        Response actual = testInstance.getNotFoundResponsePage(arguments);

        assertThat(actual.getCode()).isEqualTo(HTTP_NOT_FOUND.getValue());
    }

    @Test
    void shouldGetCreationFormPage() {
        Response actual = testInstance.getCreationFormPage(arguments);

        assertThat(actual.getCode()).isEqualTo(HTTP_OK.getValue());
        assertThat(actual.getContentType()).isEqualTo(HTML.getValue());
        assertThat(actual.getJspPage()).isEqualTo(CREATION_FORM_PAGE.getValue());
    }
}
