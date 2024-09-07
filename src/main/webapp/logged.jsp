<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

    <h2>Login Page</h2>

    <h2>You are logged in, <% user.getFirstName() %>!</h2>

    <!-- Form to continue -->
    <form action="Login" method="post">
        <input type="hidden" name="csrfToken" value="<% session.getAttribute('csrfToken') %>">
        <button type="submit">Continue</button>
    </form>

    <!-- Form to logout -->
    <form action="Login" method="get">
        <input type="hidden" name="logout" value="true">
        <button type="submit">Logout</button>
    </form>

    <br><br>
    <!-- Register button -->
    <button onclick="location.href='Register'" type="button" style="width:150px;">Register</button>
    <br><br>

</body>
</html>

