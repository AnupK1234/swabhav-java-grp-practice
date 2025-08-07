<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*, javax.servlet.*, javax.servlet.http.*" %>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if ("admin".equals(username) && "admin123".equals(password)) {
        session.setAttribute("user", username);
        session.setMaxInactiveInterval(300);
        System.out.println("Session created with ID: " + session.getId());
    } else {
        response.sendRedirect("error.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("user") %>!</h2>
    <p>This is a protected page visible only to logged-in users.</p>
    <p><strong>Session ID:</strong> <%= session.getId() %></p>
    <p><strong>Session Timeout:</strong> <%= session.getMaxInactiveInterval() %> seconds</p>
    <a href="logout.jsp">Logout</a>
</body>
</html>
