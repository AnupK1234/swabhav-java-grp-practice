<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Employee</title>
</head>
<body>

<h2>Add New Employee</h2>

<form action="${pageContext.request.contextPath}/EmplopyeeControllerServlet" method="get">
    <input type="hidden" name="command" value="ADD" />
    First Name: <input type="text" name="firstName" required><br><br>
    Last Name: <input type="text" name="lastName" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    <input type="submit" value="Save">
</form>

<br>
<a href="${pageContext.request.contextPath}/EmplopyeeControllerServlet?command=LIST">
    Back to Employee List
</a>

</body>
</html>
