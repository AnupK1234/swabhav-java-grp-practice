<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
String name = request.getParameter("username");
if (name != null && !name.trim().isEmpty()) {
	session.setAttribute("user", name);
}
Cookie userCookie = new Cookie("user", name);
userCookie.setMaxAge(60 * 60 * 24);
response.addCookie(userCookie);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>dashboard</title>
</head>
<body>
	<h2>
		Welcome,
		<%=session.getAttribute("user")%></h2>
	<p>This is your dashboard.</p>
	<%
	java.util.Enumeration<String> keys = session.getAttributeNames();
	while (keys.hasMoreElements()) {
		String key = keys.nextElement();
		Object value = session.getAttribute(key);
	%>
	<p>
		<b><%=key%></b>:
		<%=value%></p>
	<%
	}
	// session.setMaxInactiveInterval(30);
	%>
	<h3>Cookies :</h3>
	<%
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie c : cookies) {
	%>
	<p>
		<b><%=c.getName()%></b>:
		<%=c.getValue()%></p>

	<%
	}
	} else {
	%>
	<p>No cookies found</p>
	<%
	}
	%>
	<br>
		<a href="Praticepart3.jsp">Logout</a>
	
</body>
</html>