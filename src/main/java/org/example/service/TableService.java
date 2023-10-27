package org.example.service;

import org.example.model.Developer;

import java.util.List;

public class TableService {
    private static TableService instance;

    public String getDevelopersTable(List<Developer> developerList) {

        StringBuilder stringBuilder = new StringBuilder();
//      Start of developers table
        stringBuilder
                .append("<html>")
                .append("<head></head>")
                .append("<center><body>");

//      Title of the table
        stringBuilder.append("<h2>Developers Table</h2>");

        stringBuilder.append("<table>");
//      Table's headers
        stringBuilder.append("<tr>")
                .append("<th>ID</th>")
                .append("<th>First Name</th>")
                .append("<th>Second Name</th>")
                .append("<th>Age</th>")
                .append("<th>Programing Language</th>")
                .append("</tr>");
//      Table's data
        for (Developer developer : developerList) {
            stringBuilder
                    .append("<tr>")
                    .append("<td>").append(developer.getId()).append("</td>")
                    .append("<td>").append(developer.getFirstName()).append("</td>")
                    .append("<td>").append(developer.getSecondName()).append("</td>")
                    .append("<td>").append(developer.getAge()).append("</td>")
                    .append("<td>").append(developer.getProgLang()).append("</td>")
                    .append("</tr>");
        }
//      End of the developers table
        stringBuilder.append("</table></body></center>")
                .append("</html>");
// return complete string for response
        return stringBuilder.toString();
    }

    public static TableService getInstance() {
        if (instance == null) {
            instance = new TableService();
        }
        return instance;
    }

}
