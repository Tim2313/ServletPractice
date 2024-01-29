package org.example.controller;

import org.example.constant.ContextType;
import org.example.constant.JspPage;
import org.example.constant.ResponseCode;
import org.example.constant.UrlPath;
import org.example.converter.ArgumentToDeveloperConverter;
import org.example.model.Arguments;
import org.example.model.Developer;
import org.example.model.JspAttribute;
import org.example.model.Response;
import org.example.service.DeveloperService;
import org.example.service.JsonService;
import org.example.web.PathMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class DeveloperController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeveloperController.class);
    private final JsonService jsonService;
    private final ArgumentToDeveloperConverter argumentToDeveloperConverter;
    private final DeveloperService developerService;
    private static DeveloperController instance;

    public DeveloperController(
            DeveloperService developerService,
            PathMapper pathMapper,
            ArgumentToDeveloperConverter argumentToDeveloperConverter,
            JsonService jsonService
    ) {
        this.jsonService = jsonService;
        this.argumentToDeveloperConverter = argumentToDeveloperConverter;
        this.developerService = developerService;

        pathMapper.addMapping(UrlPath.GET_DEVELOPERS_JSON, this::getJsonPage);
        pathMapper.addMapping(UrlPath.GET_ALL_DEVELOPERS_HTML, this::getTablePage);
        pathMapper.addMapping(UrlPath.POST_DEVELOPERS_HTML, this::createDeveloperHtml);
        pathMapper.addMapping(UrlPath.POST_DEVELOPERS_JSON, this::createDeveloperJson);
    }

    private DeveloperController() {
        this(DeveloperService.getInstance(),
                PathMapper.getInstance(),
                ArgumentToDeveloperConverter.getInstance(),
                JsonService.getInstance());
    }

    public void init() {
        DeveloperController.getInstance();
    }

    public Response getTablePage(Arguments arguments) {
        List<Developer> developers = developerService.getDevelopers();
        Response response = new Response();

        JspAttribute jspAttribute = new JspAttribute("developerList", developers);
        List<JspAttribute> jspAttributes = new ArrayList<>();
        jspAttributes.add(jspAttribute);
        response.setJspAttributes(jspAttributes);

        String contextType = ContextType.HTML.getContextType();
        response.setContentType(contextType);

        int responseCode = ResponseCode.HTTP_OK.getResponseCodes();
        response.setCode(responseCode);

        response.setJspPage(JspPage.DEVELOPER_TABLE_PAGE.getFilePath());
        LOGGER.info("'Developer table' page has showed!");

        return response;
    }

    public Response getJsonPage(Arguments arguments) {
        List<Developer> developers = developerService.getDevelopers();
        Response response = new Response();

        String jsonResponse = jsonService.getDevelopers(developers);

        String contextType = ContextType.JSON.getContextType();
        response.setContentType(contextType);

        response.setBody(jsonResponse);

        int responseCode = ResponseCode.HTTP_OK.getResponseCodes();
        response.setCode(responseCode);

        LOGGER.info("'Json' page has showed!");
        return response;
    }

    public Response createDeveloperHtml(Arguments arguments) {
        Response response = new Response();

        Developer developer = argumentToDeveloperConverter.convert(arguments);

        developerService.addDeveloper(developer);

        response.setCode(ResponseCode.HTTP_OK.getResponseCodes());
        response.setContentType(ContextType.HTML.getContextType());
        response.setRedirect(UrlPath.GET_DEVELOPERS_FORM_HTML);

        LOGGER.info("Developer is created!");
        return response;
    }

    public Response createDeveloperJson(Arguments arguments) {
        Response response = new Response();

        response.setCode(200);

        String contextType = ContextType.JSON.getContextType();
        response.setContentType(contextType);

        String message = "{\"message\": \"Developer is created\"}";
        response.setBody(message);

        Developer developer = argumentToDeveloperConverter.convert(arguments);
        developerService.addDeveloper(developer);

        LOGGER.info("Json is converted to developer and created");
        return response;
    }

    public static DeveloperController getInstance() {
        if (instance == null) {
            instance = new DeveloperController();
        }
        return instance;
    }
}
