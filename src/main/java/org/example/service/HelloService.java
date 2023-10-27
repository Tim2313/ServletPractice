package org.example.service;


public class HelloService {

    private static HelloService instance;

    public String getHelloResponse() {
        StringBuilder stringBuilder = new StringBuilder();
//Start of the string
        stringBuilder
                .append("<html>")
                .append("<head></head>")
                .append("<body>")
                .append("<center>");
//String's content
        stringBuilder
                .append("<h1>Hello World!!!</h1>");
//End of the string
        stringBuilder
                .append("</center>")
                .append("</body>")
                .append("</html>");

        return stringBuilder.toString();
    }

    public static HelloService getInstance() {
        if (instance == null) {
            instance = new HelloService();
        }
        return instance;
    }
}
