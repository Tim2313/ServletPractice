package org.example.service;

public class NotFoundService {

    private static NotFoundService instance;

    public String getNotFoundResponse() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("<html>")
                .append("<head></head>")
                .append("<body>")
                .append("<center>");

        stringBuilder
                .append("Not existing source;(");

        stringBuilder
                .append("</center>")
                .append("</body>")
                .append("</html>");
        return stringBuilder.toString();
    }

    public static NotFoundService getInstance(){
        if (instance == null) {
            instance = new NotFoundService();
        }
        return instance;
    }
}
