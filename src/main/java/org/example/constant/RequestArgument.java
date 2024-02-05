package org.example.constant;

public enum RequestArgument {
    HTTP_PATH_ARG("path"),

    HTTP_METHOD_ARG("method"),

    FIRSTNAME_ARG("firstName"),

    LASTNAME_ARG("lastName"),

    AGE_ARG("age"),

    PROGRAMMING_LANGUAGE_ARG("programmingLanguage");

    private final String value;

    RequestArgument(String requestUrl) {
        this.value = requestUrl;
    }

    public String getValue() {
        return value;
    }
}
