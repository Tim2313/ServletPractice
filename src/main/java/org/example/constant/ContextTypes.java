package org.example.constant;

public enum ContextTypes {
    HTML("text/html"), JSON("application/json");

    private final String contextType;


    ContextTypes(String contextType) {
        this.contextType = contextType;
    }

    public String getContextType() {
        return contextType;
    }
}
