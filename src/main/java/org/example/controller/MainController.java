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


    public Response getHelloType(Arguments arguments) {
        Response response = new Response();

        String contextType = ContextType.HTML.getContextType();
        response.setResponseContentType(contextType);

        int responseCode = ResponseCode.HTTP_OK.getResponseCodes();
        response.setResponseCode(responseCode);

        String jspPath = JspPage.HELLO_PAGE.getJspType();
        response.setResponseJspAttributes(jspPath);

        LOGGER.info("'Hello' page has showed!");
        return response;
    }

    public Response getNotFoundResponseType(Arguments arguments) {
        Response response = new Response();

        String contextType = ContextType.HTML.getContextType();
        response.setResponseContentType(contextType);

        int responseCode = ResponseCode.HTTP_NOT_FOUND.getResponseCodes();
        response.setResponseCode(responseCode);

        String jspPath = JspPage.NOT_FOUND_PAGE.getJspType();
        response.setResponseJspAttributes(jspPath);

        LOGGER.info("'NotFoundResponse' page has showed!");
        return response;
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }
}
