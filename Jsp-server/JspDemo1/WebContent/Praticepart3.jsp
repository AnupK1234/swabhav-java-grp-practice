<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
session.invalidate();


Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie c : cookies) {
		if (c.getName().equals("username")) {
	c.setMaxAge(0);
	response.addCookie(c);

		}
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logged Out</title>
</head>
<body>
	<h2>You have been logged out.</h2>
	<a href="Praticepart1.jsp">Login again</a>
</body>
</html>