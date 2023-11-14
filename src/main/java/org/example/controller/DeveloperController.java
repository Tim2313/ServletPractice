package org.example.controller;

import org.example.constant.ContextType;
import org.example.constant.JspPage;
import org.example.constant.ResponseCode;
import org.example.model.Arguments;
import org.example.model.Developer;
import org.example.model.JspAttribute;
import org.example.model.Response;
import org.example.service.DeveloperService;
import org.example.service.JsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DeveloperController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeveloperController.class);
    private final DeveloperService developerService = DeveloperService.getInstance();
    private final JsonService jsonService = JsonService.getInstance();


    private static DeveloperController instance;

    public Response getTableType(Arguments arguments) {
        List<Developer> developers = developerService.getDevelopers();
        Response response = new Response();

        JspAttribute jspAttribute = new JspAttribute("developerList", developers);
        LOGGER.info("developers: {}", developers);
        List<JspAttribute> attributes = new ArrayList<>();
        attributes.add(jspAttribute);
        response.setAttributes(attributes);

        String contextType = ContextType.HTML.getContextType();
        response.setContentType(contextType);

        int responseCode = ResponseCode.HTTP_OK.getResponseCodes();
        response.setStatusCode(responseCode);

        response.setJspPath(JspPage.DEVELOPER_TABLE_PAGE.getJspType());

        LOGGER.info("'Html' page has showed!");
        return response;
    }

    public Response getJsonType(Arguments arguments) {
        List<Developer> developers = developerService.getDevelopers();
        Response response = new Response();

        String jsonResponse = jsonService.getDevelopers(developers);
        String contextType = ContextType.JSON.getContextType();
        response.setContentType(contextType);

        response.setBody(jsonResponse);

        int responseCode = ResponseCode.HTTP_OK.getResponseCodes();
        response.setStatusCode(responseCode);

        LOGGER.info("'Json' page has showed!");
        return response;
    }

    public static DeveloperController getInstance() {
        if (instance == null) {
            instance = new DeveloperController();
        }
        return instance;
    }

}
