package org.example.web;

import org.example.constant.*;
import org.example.controller.DeveloperController;
import org.example.controller.MainController;
import org.example.model.Arguments;
import org.example.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PathMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(PathMapper.class);
    private static final Map<UrlPath, Function<Arguments, Response>> GET_PAGE_MAP = new HashMap<>();
    private static final Map<UrlPath, Function<Arguments, Response>> POST_PAGE_MAP = new HashMap<>();
    private final Function<Arguments, Response> NOT_FOUND_RESPONSE_PAGE_PROCESSOR;
    private static PathMapper instance;

    public PathMapper() {
        DeveloperController developerController = DeveloperController.getInstance();
        GET_PAGE_MAP.put(UrlPath.GET_DEVELOPERS_JSON, developerController::getJsonPage);
        GET_PAGE_MAP.put(UrlPath.GET_DEVELOPERS_HTML, developerController::getTablePage);
        POST_PAGE_MAP.put(UrlPath.CREATE_DEVELOPER, developerController::create);

        MainController mainController = MainController.getInstance();
        GET_PAGE_MAP.put(UrlPath.GREETINGS_HTML, mainController::getHelloPage);
        GET_PAGE_MAP.put(UrlPath.CREATE_DEVELOPER, mainController::getCreationFromPage);

        NOT_FOUND_RESPONSE_PAGE_PROCESSOR = mainController::getNotFoundResponsePage;
    }

    public Response getResponseGET(Arguments arguments) {
        String pathWithWarName = arguments.getHashMap().get(RequestArgument.HTTP_PATH);
        UrlPath finalPath = UrlPath.getBySymbol(pathWithWarName);
        if (GET_PAGE_MAP.containsKey(finalPath)) {
            return GET_PAGE_MAP.get(finalPath).apply(arguments);
        }
        return NOT_FOUND_RESPONSE_PAGE_PROCESSOR.apply(arguments);
    }

    public Response getResponsePOST(Arguments arguments) {
        String pathWithWarName = arguments.getHashMap().get(RequestArgument.HTTP_PATH);
        UrlPath finalPath = UrlPath.getBySymbol(pathWithWarName);
        if (POST_PAGE_MAP.containsKey(finalPath)) {
            return POST_PAGE_MAP.get(finalPath).apply(arguments);
        }
        return NOT_FOUND_RESPONSE_PAGE_PROCESSOR.apply(arguments);
    }

    public static PathMapper getInstance() {
        if (instance == null) {
            instance = new PathMapper();
        }
        return instance;
    }
}
