<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head></head>
<center>
<body>
    <h2>Developers Table</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Second Name</th>
            <th>Age</th>
            <th>Programming Language</th>
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
