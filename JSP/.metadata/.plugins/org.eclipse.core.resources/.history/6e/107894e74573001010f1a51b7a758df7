<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String name = request.getParameter("username");

if (name != null && !name.trim().isEmpty()) {
	session.setAttribute("username", name);
}

String sessionName = (String) session.getAttribute("username");

if (sessionName == null) {
	response.sendRedirect("index.jsp"); // redirect if session expired or not set
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<h2>
		Welcome,
		<%=sessionName%>!
	</h2>
	<p>
		Session ID:
		<%=session.getId()%></p>
	<a href="logout.jsp">Logout</a>
</body>
</html>
