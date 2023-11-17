package org.example.model;

import java.util.List;

public class Response {
    private int code;
    private String body;
    private String contentType;
    private String jspAttributes;

    private List<JspAttribute> attributes;


    public Response() {
    }

    public Response(int code, String body, String contentType) {
        this.code = code;
        this.body = body;
        this.contentType = contentType;
    }

    public int getResponseCode() {
        return code;
    }

    public void setResponseCode(int code) {
        this.code = code;
    }

    public String getResponseBody() {
        return body;
    }

    public void setResponseBody(String body) {
        this.body = body;
    }

    public String getResponseContentType() {
        return contentType;
    }

    public void setResponseContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getResponseJspAttributes() {
        return jspAttributes;
    }

    public void setResponseJspAttributes(String jspAttributes) {
        this.jspAttributes = jspAttributes;
    }

    public List<JspAttribute> getResponseAttributes() {
        return attributes;
    }

    public void setResponseAttributes(List<JspAttribute> attributes) {
        this.attributes = attributes;
    }
}
