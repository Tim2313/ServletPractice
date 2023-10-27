package org.example.service;

public class NotFoundService {

    private static NotFoundService instance;

    public String getNotFoundResponse() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("<html>")
                .append("<head></head>")
                .append("<body>");

        stringBuilder
                .append("<centre>")
                .append("Not existing source;(")
                .append("</centre>");

        stringBuilder
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
