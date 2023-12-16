package org.example.constant;

public enum RequestArgument {
    HTTP_PATH("path"),
    HTTP_METHOD("method"),
    FIRSTNAME("FirstName"),
    LASTNAME("LastName"),
    AGE("Age"),
    PROGRAMMING_LANGUAGE("ProgrammingLanguage");

    private final String requestArgument;

    RequestArgument(String requestUrl) {
        this.requestArgument = requestUrl;
    }

    public String getRequestArgument() {
        return requestArgument;
    }
}
