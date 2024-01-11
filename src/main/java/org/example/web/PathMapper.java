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
    private static final Map<UrlPath, Function<Arguments, Response>> PAGE_MAP = new HashMap<>();
    private final Function<Arguments, Response> NOT_FOUND_RESPONSE_PAGE_PROCESSOR;
    private static PathMapper instance;

    public PathMapper() {
        DeveloperController developerController = DeveloperController.getInstance();
        PAGE_MAP.put(UrlPath.GET_DEVELOPERS_JSON, developerController::getJsonPage);
        PAGE_MAP.put(UrlPath.GET_DEVELOPERS_HTML, developerController::getTablePage);
        PAGE_MAP.put(UrlPath.POST_DEVELOPERS_HTML, developerController::createDeveloperHtml);
        PAGE_MAP.put(UrlPath.POST_DEVELOPERS_JSON, developerController::createDeveloperJson);
        MainController mainController = MainController.getInstance();
        PAGE_MAP.put(UrlPath.GREETINGS_HTML, mainController::getHelloPage);
        PAGE_MAP.put(UrlPath.POST_DEVELOPERS_HTML, mainController::getCreationFromPage);

        NOT_FOUND_RESPONSE_PAGE_PROCESSOR = mainController::getNotFoundResponsePage;
    }

    public Response getResponse(Arguments arguments) {
        String pathWithWarName = arguments.getHashMap().get(RequestArgument.HTTP_PATH);
        UrlPath finalPath = UrlPath.getByFullUrl(pathWithWarName);
        if (PAGE_MAP.containsKey(finalPath)) {
            return PAGE_MAP.get(finalPath).apply(arguments);
        }
        LOGGER.info("Unknown path: {}", finalPath.getUrl());
        return NOT_FOUND_RESPONSE_PAGE_PROCESSOR.apply(arguments);
    }

    public static PathMapper getInstance() {
        if (instance == null) {
            instance = new PathMapper();
        }
        return instance;
    }
}
