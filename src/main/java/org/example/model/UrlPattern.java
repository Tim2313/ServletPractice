package org.example.model;

public enum UrlPattern {
    HTML("/html"), JSON("/json"), HELLO("/hello");

    private static final String WAR_NAME = "/DeveloperApi";

    private final String url;

    UrlPattern(String url) {
        this.url = url;
    }

    public String getUrl() {
        return WAR_NAME + url;
    }

    // Method for get UrlPattern from the line with Urls
    public static UrlPattern fromUrl(String url) {
        for (UrlPattern pattern : values()) {
            if (pattern.url.equals(url)) {
                return pattern;
            }
        }
        return null;
    }
}
