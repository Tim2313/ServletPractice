package org.example.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public enum UrlPath {
    GET_ALL_DEVELOPERS_HTML("/html/allDevelopers", "GET"),

    GET_GREETINGS_HTML("/html/greetings", "GET"),

    NOT_FOUND_HTML("/html/notFound", "GET"),

    GET_DEVELOPERS_FORM_HTML("/html/developersForm", "GET"),

    GET_DEVELOPERS_JSON("/api/developersJson", "GET"),

    POST_DEVELOPERS_HTML("/html/developers", "POST"),

    POST_DEVELOPERS_JSON("/api/developers", "POST");
    private final String url;
    private final String method;

    private static final String WAR_NAME = "/DeveloperApi";
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlPath.class);
    private static final Map<String, UrlPath> URL_PATTERN_STRING_MAP = new HashMap<>();

    static {
        URL_PATTERN_STRING_MAP.put(GET_GREETINGS_HTML.getMethodUrl(), GET_GREETINGS_HTML);
        URL_PATTERN_STRING_MAP.put(GET_ALL_DEVELOPERS_HTML.getMethodUrl(), GET_ALL_DEVELOPERS_HTML);
        URL_PATTERN_STRING_MAP.put(GET_DEVELOPERS_FORM_HTML.getMethodUrl(), GET_DEVELOPERS_FORM_HTML);
        URL_PATTERN_STRING_MAP.put(POST_DEVELOPERS_JSON.getMethodUrl(), POST_DEVELOPERS_JSON);
        URL_PATTERN_STRING_MAP.put(GET_DEVELOPERS_JSON.getMethodUrl(), GET_DEVELOPERS_JSON);
        URL_PATTERN_STRING_MAP.put(POST_DEVELOPERS_HTML.getMethodUrl(), POST_DEVELOPERS_HTML);
    }

    UrlPath(String url, String method) {
        this.method = method;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getWarUrl() {
        String path = "%s%s";
        return String.format(path, WAR_NAME, url);
    }

    public String getMethodUrl() {
        String pathWithUrl = "%s%s";
        return String.format(pathWithUrl, url, method);
    }

    public String getMethod() {
        return method;
    }

    /**
     * Gets UrlPath enum value by url path string.
     * Remove or Add the WAR_NAME and finds corresponding UrlPath.
     *
     * @param url - url string to parse
     * @return UrlPath enum value
     */
    public static UrlPath getByFullUrl(String url, String method) {
        String path = url.replace(WAR_NAME, "");

        String format = "%s%s";
        String urlMethod = String.format(format, path, method);

        if (URL_PATTERN_STRING_MAP.get(urlMethod) == null) {
            LOGGER.info("The key: '{}'. Does not exist", urlMethod);
            return NOT_FOUND_HTML;
        }
        return URL_PATTERN_STRING_MAP.get(urlMethod);
    }
}
