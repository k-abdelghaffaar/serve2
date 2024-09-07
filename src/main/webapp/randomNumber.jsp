<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.misc.Randomy" %>
<!DOCTYPE html>
<html>
<head>
    <title>Random Number Generator</title>
</head>
<body>

<form method="POST">
    <label for="range">Enter a range:</label>
    <input type="number" id="range" name="range" required>
    <button type="submit">Generate Random Number</button>
</form>

<%
    String rangeStr = request.getParameter("range");
    if (rangeStr != null) {
        int range = Integer.parseInt(rangeStr);
        int randomNum = 0;
        if(range > 0){
            Randomy rand = new Randomy();
            randomNum = rand.generateRandomNumber(range);}
%>
    <div>
        <h2>Generated Random Number: <%= randomNum %></h2>
    </div>
<%
    }
%>

</body>
</html>
