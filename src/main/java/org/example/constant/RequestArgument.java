package org.example.constant;

public enum RequestArgument {
    HTTP_PATH("path"),
    HTTP_METHOD("method"),
    FIRSTNAME("firstName"),
    LASTNAME("lastName"),
    AGE("age"),
    PROGRAMMING_LANGUAGE("programmingLanguage");

    private final String requestArgument;

    RequestArgument(String requestUrl) {
        this.requestArgument = requestUrl;
    }

    public String getRequestArgument() {
        return requestArgument;
    }
}
