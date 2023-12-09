<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Developer Form</title>
  <style>
  body {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
    margin: 0;
  }

  form {
    width: 300px; /* Set a width for the form */
  }

  section {
    display: flex;
    flex-direction: column;
  }

  h2 {
    text-align: center;
  }

  label {
    margin-bottom: 5px; /* Adjust the margin as needed */
  }

  input {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px; /* Adjust the margin as needed */
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

  <form action="/DeveloperApi/api/developerForm" method="POST">
      <section>
        <h2>New developer information</h2>
          <label for="firstName">
            <span>First Name: </span>
          </label>
          <input type="text" name="First Name" required />


          <label for="secondName">
            <span>Second Name: </span>
          </label>
          <input type="text" name="Second Name" required />


          <label for="age">
            <span>Age: </span>
          </label>
          <input type="text" name="Age" required />


          <label for="programmingLanguage">
            <span>Programming Language: </span>
          </label>
          <input type="text" name="Programming Language" required />

      </section>

      <section>
          <button type="submit">Add new developer</button>
      </section>
  </form>

</body>

</html>
