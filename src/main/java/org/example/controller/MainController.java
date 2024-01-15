package org.example.controller;

import org.example.constant.ContextType;
import org.example.constant.JspPage;
import org.example.constant.ResponseCode;
import org.example.constant.UrlPath;
import org.example.model.Arguments;
import org.example.model.Response;
import org.example.web.PathMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private static MainController instance;

    public MainController() {
        PathMapper pathMapper = PathMapper.getInstance();

        pathMapper.addMapping(UrlPath.GET_DEVELOPERS_FORM_HTML, this::getCreationFormPage);
        pathMapper.addMapping(UrlPath.GET_GREETINGS_HTML, this::getHelloPage);
        pathMapper.addMapping(UrlPath.NOT_FOUND_HTML, this::getNotFoundResponsePage);
    }

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

    public Response getCreationFormPage(Arguments arguments) {
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
