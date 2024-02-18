package org.example.controller;

import org.example.constant.ContextType;
import org.example.constant.JspPage;
import org.example.constant.ResponseCode;
import org.example.constant.HttpMapping;
import org.example.model.Arguments;
import org.example.model.Response;
import org.example.web.HttpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private static MainController instance;

    private MainController() {
        HttpMapper httpMapper = HttpMapper.getInstance();

        httpMapper.addMapping(HttpMapping.GET_DEVELOPERS_FORM_HTML, this::getCreationFormPage);
        httpMapper.addMapping(HttpMapping.GET_GREETINGS_HTML, this::getHelloPage);
        httpMapper.addMapping(HttpMapping.NOT_FOUND_HTML, this::getNotFoundResponsePage);
    }

    public void init(){
         MainController.getInstance();
    }

    public Response getHelloPage(Arguments arguments) {
        Response response = new Response();

        String contextType = ContextType.HTML.getValue();
        response.setContentType(contextType);

        int responseCode = ResponseCode.HTTP_OK.getValue();
        response.setCode(responseCode);

        String jspPage = JspPage.HELLO_PAGE.getValue();
        response.setJspPage(jspPage);

        LOGGER.info("'Hello' page has showed!");
        return response;
    }

    public Response getNotFoundResponsePage(Arguments arguments) {
        Response response = new Response();

        String contextType = ContextType.HTML.getValue();
        response.setContentType(contextType);

        int responseCode = ResponseCode.HTTP_NOT_FOUND.getValue();
        response.setCode(responseCode);

        String jspPage = JspPage.NOT_FOUND_PAGE.getValue();
        response.setJspPage(jspPage);

        LOGGER.info("'NotFoundResponse' page has showed!");
        return response;
    }

    public Response getCreationFormPage(Arguments arguments) {
        Response response = new Response();

        String contextType = ContextType.HTML.getValue();
        response.setContentType(contextType);

        int responseCode = ResponseCode.HTTP_OK.getValue();
        response.setCode(responseCode);

        String jspPage = JspPage.CREATION_FORM_PAGE.getValue();
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
