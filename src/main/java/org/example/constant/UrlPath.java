package org.example.constant;

import java.util.HashMap;
import java.util.Map;

public enum UrlPath {
    HTML("/html"), JSON("/json"), HELLO("/hello"), FORM("/form");

    private final String url;

    private static final String WAR_NAME = "/DeveloperApi";

    private static final Map<String, UrlPath> URL_PATTERN_STRING_MAP = new HashMap<>();

    static {
        URL_PATTERN_STRING_MAP.put(HELLO.getUrl(), HELLO);
        URL_PATTERN_STRING_MAP.put(JSON.getUrl(), JSON);
        URL_PATTERN_STRING_MAP.put(HTML.getUrl(), HTML);
        URL_PATTERN_STRING_MAP.put(FORM.getUrl(), FORM);
    }

    UrlPath(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    // Method for get UrlPattern from the line with Urls
    public static UrlPath getBySymbol(String url) {
        String path = url.replace(WAR_NAME, "");
        return URL_PATTERN_STRING_MAP.get(path);
    }
}
