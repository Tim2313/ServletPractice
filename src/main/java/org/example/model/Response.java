package org.example.model;

public class Response {
    private int code;
    private String body;
    private String contentType;

    public Response() {
    }

    public Response(int code, String body, String contentType) {
        this.code = code;
        this.body = body;
        this.contentType = contentType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
