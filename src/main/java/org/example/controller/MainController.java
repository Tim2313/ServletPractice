package org.example.controller;

import org.example.constant.ContextType;
import org.example.constant.JspPage;
import org.example.constant.ResponseCode;
import org.example.model.Arguments;
import org.example.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private static MainController instance;


    public Response getHelloPage(Arguments arguments) {
        Response response = new Response();

        String contextType = ContextType.HTML.getContextType();
        response.setContentType(contextType);

        int responseCode = ResponseCode.HTTP_OK.getResponseCodes();
        response.setCode(responseCode);

        String jspPage = JspPage.HELLO_PAGE.getFilePath();
        response.setJspPage(jspPage);

        LOGGER.info("'Hello' page has showed!");
        return response;
    }

    public Response getNotFoundResponsePage(Arguments arguments) {
        Response response = new Response();

        String contextType = ContextType.HTML.getContextType();
        response.setContentType(contextType);

        int responseCode = ResponseCode.HTTP_NOT_FOUND.getResponseCodes();
        response.setCode(responseCode);

        String jspPage = JspPage.NOT_FOUND_PAGE.getFilePath();
        response.setJspPage(jspPage);

        LOGGER.info("'NotFoundResponse' page has showed!");
        return response;
    }

    public Response getCreationFromPage(Arguments arguments) {
        Response response = new Response();

        String contextType = ContextType.HTML.getContextType();
        response.setContentType(contextType);

        int responseCode = ResponseCode.HTTP_OK.getResponseCodes();
        response.setCode(responseCode);

        String jspPage = JspPage.CREATION_FORM_PAGE.getFilePath();
        response.setJspPage(jspPage);

        LOGGER.info("'CreationFormPage' page has showed!");
        return response;
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }
}
