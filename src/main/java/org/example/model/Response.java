package org.example.model;
import org.example.constant.HttpMapping;

import java.util.List;

public class Response {
    private int code;
    private String body;
    private String contentType;
    private String jspPage;
    private HttpMapping redirect;
    private List<JspAttribute> jspAttributes;

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

    public List<JspAttribute> getJspAttributes() {
        return jspAttributes;
    }

    public void setJspAttributes(List<JspAttribute> attributes) {
        this.jspAttributes = attributes;
    }

    public String getJspPage() {
        return jspPage;
    }

    public void setJspPage(String jspPage) {
        this.jspPage = jspPage;
    }

    public HttpMapping getRedirect() {
        return redirect;
    }

    public void setRedirect(HttpMapping redirect) {
        this.redirect = redirect;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", body='" + body + '\'' +
                ", contentType='" + contentType + '\'' +
                ", jspPage='" + jspPage + '\'' +
                ", redirect=" + redirect +
                ", jspAttributes=" + jspAttributes +
                '}';
    }
}

