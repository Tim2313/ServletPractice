package org.example.web;

import org.example.constant.*;
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
    private static PathMapper instance;

    public PathMapper() {
    }

    public void addMapping(UrlPath url, Function<Arguments, Response> method) {
        PAGE_MAP.put(url, method);
    }

    public Response getResponse(Arguments arguments) {
        String pathWithWarName = arguments.getHashMap().get(RequestArgument.HTTP_PATH);
        String method = arguments.getHashMap().get(RequestArgument.HTTP_METHOD);
        UrlPath finalPath = UrlPath.getByFullUrl(pathWithWarName, method);
        if (PAGE_MAP.containsKey(finalPath)) {
            return PAGE_MAP.get(finalPath).apply(arguments);
        }
        LOGGER.info("The path doesn't contain the PAGE_MAP: {}", finalPath);
        return PAGE_MAP.get(UrlPath.NOT_FOUND_HTML).apply(arguments);
    }

    public static PathMapper getInstance() {
        if (instance == null) {
            instance = new PathMapper();
        }
        return instance;
    }
}
