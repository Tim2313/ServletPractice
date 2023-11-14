package org.example.constant;

public enum RequestArgument {
    PATH("path");

    private final String requestUrl;

    RequestArgument(String requestUrl) {
        this.requestUrl = requestUrl;
    }

}
