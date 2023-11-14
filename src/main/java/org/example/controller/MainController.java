package org.example.controller;

import org.example.constant.ContextTypes;
import org.example.constant.ResponseCodes;
import org.example.model.Arguments;
import org.example.model.Response;
import org.example.service.HelloService;
import org.example.service.NotFoundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private final HelloService helloService = HelloService.getInstance();
    private final NotFoundService notFoundService = NotFoundService.getInstance();

    private static MainController instance;


    public Response getHelloType(Arguments arguments) {
        Response response = new Response();
        String contextType = ContextTypes.HTML.getContextType();
        response.setContentType(contextType);
        String helloResponse = helloService.getHelloResponse();
        response.setBody(helloResponse);
        int responseCode = ResponseCodes.HTTP_OK.getResponseCodes();
        response.setCode(responseCode);
        LOGGER.info("'Hello' page has showed!");
        return response;
    }

    public Response getNotFoundResponseType(Arguments arguments) {
        Response response = new Response();
        String contextType = ContextTypes.HTML.getContextType();
        response.setContentType(contextType);
        String notFoundMessage = notFoundService.getNotFoundResponse();
        response.setBody(notFoundMessage);
        int responseCode = ResponseCodes.HTTP_NOT_FOUND.getResponseCodes();
        response.setCode(responseCode);
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
