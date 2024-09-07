<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

    <h2>Login Page</h2>
    <form action="Login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="pass">Password:</label>
        <input type="password" id="pass" name="pass" required><br><br>
        <input type="hidden" name="csrfToken" value="<%= session.getAttribute("csrfToken") %>" />
        <button type="submit">Submit</button>
    </form>
    <br><br>
    <button onclick="location.href='Register'" type="button" style="width:150px;">Register</button>
    <br><br>

    <%
        String color = request.getParameter("color");
        if (color != null) {
            out.println("<p style='color:" + color + ";'>Wrong credentials</p>");
        }
    %>
</body>
</html>
