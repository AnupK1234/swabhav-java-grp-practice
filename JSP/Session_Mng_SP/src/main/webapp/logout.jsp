<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
session.invalidate(); // destroy session
%>
<!DOCTYPE html>
<html>
<head>
<title>Logged Out</title>
</head>
<body>
	<h2>You have been logged out.</h2>
	<a href="index.jsp">Go to Home</a>
</body>
</html>
