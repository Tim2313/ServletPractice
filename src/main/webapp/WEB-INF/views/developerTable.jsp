<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.example.constant.TableContent" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Developers Table</title>
</head>
<center>
<body>
    <table>
     <h2>Developers Table</h2>
        <tr>
        <% for (TableContent content : TableContent.values()) { %>
                        <th><%= content.getTableContent() %></th>
                    <% } %>
        </tr>

        <c:forEach var="developer" items="${developerList}">
            <tr>
                <td>${developer.id}</td>
                <td>${developer.firstName}</td>
                <td>${developer.secondName}</td>
                <td>${developer.age}</td>
                <td>${developer.progLang}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</center>
</html>
