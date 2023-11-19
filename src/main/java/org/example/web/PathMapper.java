package org.example.web;

import org.example.constant.RequestArgument;
import org.example.controller.DeveloperController;
import org.example.controller.MainController;
import org.example.model.Arguments;
import org.example.model.Response;
import org.example.constant.UrlPath;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PathMapper {

    private static final Map<UrlPath, Function<Arguments, Response>> PAGE_MAP = new HashMap<>();

    private final MainController mainController = MainController.getInstance();
    private static PathMapper instance;

    public PathMapper() {
        DeveloperController developerController = DeveloperController.getInstance();
        PAGE_MAP.put(UrlPath.JSON, developerController::getJsonType);
        PAGE_MAP.put(UrlPath.HTML, developerController::getTableType);

        MainController mainController = MainController.getInstance();
        PAGE_MAP.put(UrlPath.HELLO, mainController::getHelloType);
        PAGE_MAP.put(UrlPath.FORM, mainController::getCreationFormType);
    }

    public Response getResponse(Arguments arguments) {
        String pathWithWarName = arguments.getHashMap().get(RequestArgument.PATH);
        UrlPath finalPath = UrlPath.getBySymbol(pathWithWarName);
        if (PAGE_MAP.containsKey(finalPath)) {
            return PAGE_MAP.get(finalPath).apply(arguments);
        }
        return mainController.getNotFoundResponseType(arguments);
    }

    public static PathMapper getInstance() {
        if (instance == null) {
            instance = new PathMapper();
        }
        return instance;
    }
}
