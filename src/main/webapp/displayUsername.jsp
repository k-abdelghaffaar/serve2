<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Display Username</title>
</head>
<body>
    <h2>Welcome, <%= request.getParameter("username") %>!</h2>
</body>
</html>
