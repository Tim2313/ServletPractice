package org.example.constant;

public enum JspPage {
    HELLO_PAGE("/WEB-INF/views/helloPage.jsp"),
    DEVELOPER_TABLE_PAGE("/WEB-INF/views/developerTable.jsp"),
    NOT_FOUND_PAGE("/WEB-INF/views/notFoundPage.jsp"),
    CREATION_FORM_PAGE("/WEB-INF/views/creationForm.jsp");

    private final String value;

    JspPage(String jspPage) {
        this.value = jspPage;
    }

    public String getValue() {
        return value;
    }
}
