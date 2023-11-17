package org.example.constant;

public enum TableContent {
    COLUMN_NAME_ID("Id"),
    COLUMN_NAME_FIRST_NAME("First Name"),
    COLUMN_NAME_SECOND_NAME("Second Name"),
    COLUMN_NAME_AGE("Age"),
    COLUMN_NAME_PROGRAMMING_LANG("Programming Language");

    private final String tableContent;

    TableContent(String tableContent) {
        this.tableContent = tableContent;
    }

    public String getTableContent() {
        return tableContent;
    }

}
