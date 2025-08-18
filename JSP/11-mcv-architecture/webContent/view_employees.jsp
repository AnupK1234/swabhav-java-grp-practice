<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>MVC Architecture</title>
</head>
<body>
	<c:forEach var="tempStudent" items="${student_list}">
		${tempStudent} <br />
	</c:forEach>
</body>
</html>
