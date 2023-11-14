package org.example.constant;

public enum RequestArguments {
    PATH("path");

    private final String requestUrl;

    RequestArguments(String requestUrl) {
        this.requestUrl = requestUrl;
    }

}
