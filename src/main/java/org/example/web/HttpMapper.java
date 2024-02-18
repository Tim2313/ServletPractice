package org.example.web;

import org.example.constant.*;
import org.example.model.Arguments;
import org.example.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class HttpMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpMapper.class);
    private static final Map<HttpMapping, Function<Arguments, Response>> PAGE_MAP = new EnumMap<>(HttpMapping.class);
    private static HttpMapper instance;

    private HttpMapper() {
    }

    public void clearMapping() {
        PAGE_MAP.clear();
        LOGGER.info("Clear mapping");
    }

    public void addMapping(HttpMapping url, Function<Arguments, Response> method) {
        PAGE_MAP.put(url, method);
        LOGGER.info("Add new mapping: url: {},method: {}",url, method);
    }

    public Response getResponse(Arguments arguments) {
        String pathWithWarName = arguments.getHashMap().get(RequestArgument.HTTP_PATH);
        String method = arguments.getHashMap().get(RequestArgument.HTTP_METHOD);
        HttpMapping finalPath = HttpMapping.getByFullUrl(pathWithWarName, method);
        if (PAGE_MAP.containsKey(finalPath)) {
            return PAGE_MAP.get(finalPath).apply(arguments);
        }
        LOGGER.info("The path doesn't contain the PAGE_MAP: {}", finalPath);
        return PAGE_MAP.get(HttpMapping.NOT_FOUND_HTML).apply(arguments);
    }

    public static HttpMapper getInstance() {
        if (instance == null) {
            instance = new HttpMapper();
        }
        return instance;
    }
}
