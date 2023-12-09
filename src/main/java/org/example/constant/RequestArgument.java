package org.example.constant;

public enum RequestArgument {
    HTTP_PATH("path"),
    HTTP_METHOD("method"),
    FIRST_NAME("firstname"),
    LASTNAME("lastname"),
    AGE("age"),
    PROGRAMMING_LANGUAGE("programmingLanguage");

    private final String requestUrl;

    RequestArgument(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
