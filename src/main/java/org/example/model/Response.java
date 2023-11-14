package org.example.model;

import org.example.service.DeveloperService;

import java.util.List;

public class Response {
    private int code;
    private String body;
    private String contentType;
    private String jspPath;

    private List<JspAttribute> attributes;


    public Response() {
    }

    public Response(int code, String body, String contentType) {
        this.code = code;
        this.body = body;
        this.contentType = contentType;
    }

    public int getStatusCode() {
        return code;
    }

    public void setStatusCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getJspPath() {
        return jspPath;
    }

    public void setJspPath(String jspPath) {
        this.jspPath = jspPath;
    }

    public List<JspAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<JspAttribute> attributes) {
        this.attributes = attributes;
    }
}
