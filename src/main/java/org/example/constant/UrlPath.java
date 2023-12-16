package org.example.constant;

import org.example.web.MainServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public enum UrlPath {
    GET_DEVELOPERS_HTML("/html/allDevelopers"),
    GET_DEVELOPERS_JSON("/api/jsonDevelopers"),
    GREETINGS_HTML("/html/greetings"),
    NOT_FOUND_HTML("/html/notFound"),
    CREATE_DEVELOPER("/api/developerForm");
    private final String url;

    private static final String WAR_NAME = "/DeveloperApi";
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlPath.class);
    private static final Map<String, UrlPath> URL_PATTERN_STRING_MAP = new HashMap<>();

    static {
        URL_PATTERN_STRING_MAP.put(GREETINGS_HTML.getUrl(), GREETINGS_HTML);
        URL_PATTERN_STRING_MAP.put(GET_DEVELOPERS_JSON.getUrl(), GET_DEVELOPERS_JSON);
        URL_PATTERN_STRING_MAP.put(GET_DEVELOPERS_HTML.getUrl(), GET_DEVELOPERS_HTML);
        URL_PATTERN_STRING_MAP.put(CREATE_DEVELOPER.getUrl(), CREATE_DEVELOPER);
    }

    UrlPath(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getFullUrl() {
        String path = "%s%s";
        return String.format(path, WAR_NAME, url);
    }

    /**
     * Gets UrlPath enum value by url path string.
     * Remove or Add the WAR_NAME and finds corresponding UrlPath.
     *
     * @param url - url string to parse
     * @return UrlPath enum value
     */
    public static UrlPath getBySymbol(String url) {
        String path = url.replace(WAR_NAME, "");
        if (URL_PATTERN_STRING_MAP.get(path) == null){
            LOGGER.info("The page: {}. Does not exist", path);
            return NOT_FOUND_HTML;
        }
        return URL_PATTERN_STRING_MAP.get(path);
    }
}
