package org.example.constant;

public enum JspPage {
    HELLO_PAGE("/WEB-INF/views/helloPage.jsp"), DEVELOPER_TABLE_PAGE("/WEB-INF/views/developerTable.jsp"), NOT_FOUND_PAGE("/WEB-INF/views/notFoundPage.jsp");

    private final String jspType;


    JspPage(String jspType) {
        this.jspType = jspType;
    }

    public String getJspType() {
        return jspType;
    }
}
