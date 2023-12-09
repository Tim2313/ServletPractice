package org.example.constant;

import java.util.HashMap;
import java.util.Map;

public enum UrlPath {
    GET_DEVELOPERS_HTML("/html/allDevelopers"),
    GET_DEVELOPERS_JSON("/api/jsonDevelopers"),
    GREETINGS_HTML("/html/greetings"),
    CREATE_DEVELOPER("/api/developerForm");

    private final String url;

    private static final String WAR_NAME = "/DeveloperApi";

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

    // Method for get UrlPattern from the line with Urls
    public static UrlPath getBySymbol(String url) {
        String path = url.replace(WAR_NAME, "");
        return URL_PATTERN_STRING_MAP.get(path);
    }
}
