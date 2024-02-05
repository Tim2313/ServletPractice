package org.example.constant;

public enum ContextType {
    HTML("text/html"), JSON("application/json");

    private final String value;

    ContextType(String contextType) {
        this.value = contextType;
    }

    public String getValue() {
        return value;
    }
}
