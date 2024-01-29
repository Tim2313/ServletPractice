package org.example.controller;

import org.example.constant.ContextType;
import org.example.constant.JspPage;
import org.example.model.Arguments;
import org.example.model.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainControllerTest {

    private final MainController mainController = MainController.getInstance();

    @Test
    void shouldGetHelloPage() {
        Arguments args = new Arguments();
        Response response = mainController.getHelloPage(args);

        assertEquals(200, response.getCode());
        assertEquals(ContextType.HTML.getContextType(), response.getContentType());
        assertEquals(JspPage.HELLO_PAGE.getFilePath(), response.getJspPage());
    }

    @Test
    void shouldGetNotFoundResponsePage() {
        Arguments args = new Arguments();
        Response response = mainController.getNotFoundResponsePage(args);

        assertEquals(404, response.getCode());
        assertEquals(ContextType.HTML.getContextType(), response.getContentType());
        assertEquals(JspPage.NOT_FOUND_PAGE.getFilePath(), response.getJspPage());
    }

    @Test
    void shouldGetCreationFormPage() {
        Arguments args = new Arguments();
        Response response = mainController.getCreationFormPage(args);

        assertEquals(200, response.getCode());
        assertEquals(ContextType.HTML.getContextType(), response.getContentType());
        assertEquals(JspPage.CREATION_FORM_PAGE.getFilePath(), response.getJspPage());
    }
}
