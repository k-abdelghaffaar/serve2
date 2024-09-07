<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.UserCountListener" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="refresh" content="5">
    <title>Online Users</title>
</head>
<body>
    <h2>Number of Online Users: <%= UserCountListener.getActiveUsers() %></h2>
</body>
</html>
