package org.example.controller;

import org.example.constant.ContextType;
import org.example.constant.JspPage;
import org.example.model.Arguments;
import org.example.model.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainControllerTest {

    private final MainController mainController = MainController.getInstance();

    private static Arguments arguments;

    @BeforeAll
    static void setup() {
        arguments = new Arguments();
    }

    @Test
    void shouldGetHelloPage() {
        Response response = mainController.getHelloPage(arguments);

        assertEquals(200, response.getCode());
        assertEquals(ContextType.HTML.getContextType(), response.getContentType());
        assertEquals(JspPage.HELLO_PAGE.getFilePath(), response.getJspPage());
    }

    @Test
    void shouldGetNotFoundResponsePage() {
        Response response = mainController.getNotFoundResponsePage(arguments);

        assertEquals(404, response.getCode());
        assertEquals(ContextType.HTML.getContextType(), response.getContentType());
        assertEquals(JspPage.NOT_FOUND_PAGE.getFilePath(), response.getJspPage());
    }

    @Test
    void shouldGetCreationFormPage() {
        Response response = mainController.getCreationFormPage(arguments);

        assertEquals(200, response.getCode());
        assertEquals(ContextType.HTML.getContextType(), response.getContentType());
        assertEquals(JspPage.CREATION_FORM_PAGE.getFilePath(), response.getJspPage());
    }
}
