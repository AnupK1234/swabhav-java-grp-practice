<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Data 2</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
		</tr>
		<c:forEach var="tempStudent" items="${student_list}">

			<tr>
				<td>${tempStudent.getId()}</td>
				<td>${tempStudent.getName()}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>