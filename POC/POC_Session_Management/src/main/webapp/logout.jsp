<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    out.println("Session ID before invalidate: " + session.getId());
    out.println("<br>Username before invalidate: " + session.getAttribute("user"));

    session.invalidate(); // destroy session

    out.println("<br>Session destroyed.");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Logged Out</title>
</head>
<body>
    <h2>You have been logged out successfully.</h2>
    <a href="login.jsp">Login Again</a>
</body>
</html>
