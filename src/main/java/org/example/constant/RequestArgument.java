package org.example.constant;

public enum RequestArgument {
    HTTP_PATH("path"),
    HTTP_METHOD("method"),
    FIRSTNAME("firstName"),
    LASTNAME("lastName"),
    AGE("age"),
    PROGRAMMING_LANGUAGE("programmingLanguage");

    private final String value;

    RequestArgument(String requestUrl) {
        this.value = requestUrl;
    }

    public String getValue() {
        return value;
    }
}
