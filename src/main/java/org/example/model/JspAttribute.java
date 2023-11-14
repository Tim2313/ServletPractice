package org.example.model;

public class JspAttribute {

    private final String name;

    private final Object value;

    public JspAttribute(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
