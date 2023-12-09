<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.example.model.Developer" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Developers Table</title>
</head>

<body>
  <center>
    <table>
      <h2>Developers Table</h2>
      <tr>
        <th><%= Developer.COLUMN_NAME_ID %></th>
        <th><%= Developer.COLUMN_NAME_FIRST_NAME %></th>
        <th><%= Developer.COLUMN_NAME_SECOND_NAME %></th>
        <th><%= Developer.COLUMN_NAME_AGE %></th>
        <th><%= Developer.COLUMN_NAME_PROGRAMMING_LANGUAGE %></th>
      </tr>
      <c:forEach var="developer" items="${developerList}">
        <tr>
          <td>${developer.id}</td>
          <td>${developer.firstName}</td>
          <td>${developer.secondName}</td>
          <td>${developer.age}</td>
          <td>${developer.programmingLanguage}</td>
        </tr>
      </c:forEach>
    </table>
  </center>
</body>

</html>
