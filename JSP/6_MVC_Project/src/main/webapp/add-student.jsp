<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
</head>
<body>
	<h2>Add Student</h2>
	<form action="student-list" method="POST">
		<input type="hidden" name="command" value="ADD" /> First Name: <input
			type="text" name="firstName" /><br />
		<br /> Last Name: <input type="text" name="lastName" /><br />
		<br /> Email: <input type="text" name="email" /><br />
		<br /> <input type="submit" value="Save" />
	</form>
</body>
</html>
