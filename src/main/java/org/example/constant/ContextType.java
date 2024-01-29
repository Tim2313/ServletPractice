package org.example.constant;

public enum ContextType {
    HTML("text/html"), JSON("application/json");

    private final String contextType;

    ContextType(String contextType) {
        this.contextType = contextType;
    }

    public String getContextType() {
        return contextType;
    }
}
