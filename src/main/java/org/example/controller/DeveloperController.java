package org.example.controller;

import org.example.constant.ContextTypes;
import org.example.constant.ResponseCodes;
import org.example.model.Arguments;
import org.example.model.Developer;
import org.example.model.Response;
import org.example.service.DeveloperService;
import org.example.service.JsonService;
import org.example.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DeveloperController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeveloperController.class);
    private final DeveloperService developerService = DeveloperService.getInstance();
    private final TableService tableService = TableService.getInstance();
    private final JsonService jsonService = JsonService.getInstance();


    private static DeveloperController instance;

    public Response getTableType(Arguments arguments) {
        List<Developer> developers = developerService.getDevelopers();
        String tableResponse = tableService.getDevelopersTable(developers);
        Response response = new Response();
        String contextType = ContextTypes.HTML.getContextType();
        response.setContentType(contextType);
        response.setBody(tableResponse);
        int responseCode = ResponseCodes.HTTP_OK.getResponseCodes();
        response.setCode(responseCode);
        LOGGER.info("'Html' page has showed!");
        return response;
    }

    public Response getJsonType(Arguments arguments) {
        List<Developer> developers = developerService.getDevelopers();
        Response response = new Response();
        String jsonResponse = jsonService.getDevelopers(developers);
        String contextType = ContextTypes.JSON.getContextType();
        response.setContentType(contextType);
        response.setBody(jsonResponse);
        int responseCode = ResponseCodes.HTTP_OK.getResponseCodes();
        response.setCode(responseCode);
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
