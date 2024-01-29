<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Developer Form</title>
  <%@ page import="org.example.constant.UrlPath" %>
  <%@ page import="org.example.constant.RequestArgument" %>
  <style>
  body {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
    margin: 0;
  }

  form {
    width: 300px;
  }

  section {
    display: flex;
    flex-direction: column;
  }

  h2 {
    text-align: center;
  }

  label {
    margin-bottom: 5px;
  }

  input {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    box-sizing: border-box;
  }

  button {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
  }
  </style>
</head>

<body>

  <form action="<%= UrlPath.POST_DEVELOPERS_HTML.getWarUrl() %>" method="POST">
      <section>
        <h2>New developer information</h2>
          <label for="<%= RequestArgument.FIRSTNAME.getRequestArgument() %>">
            <span>First Name: </span>
          </label>
          <input type="text" name="<%= RequestArgument.FIRSTNAME.getRequestArgument() %>" required />


          <label for="<%= RequestArgument.LASTNAME.getRequestArgument() %>">
            <span>Second Name: </span>
          </label>
          <input type="text" name="<%= RequestArgument.LASTNAME.getRequestArgument() %>" required />


          <label for="<%= RequestArgument.AGE.getRequestArgument() %>">
            <span>Age: </span>
          </label>
          <input type="text" name="<%= RequestArgument.AGE.getRequestArgument() %>" required />


          <label for="<%= RequestArgument.PROGRAMMING_LANGUAGE.getRequestArgument() %>">
            <span>Programming Language: </span>
          </label>
          <input type="text" name="<%= RequestArgument.PROGRAMMING_LANGUAGE.getRequestArgument() %>" required />

      </section>

      <section>
          <button type="submit">Add new developer</button>
      </section>
  </form>

</body>

</html>
