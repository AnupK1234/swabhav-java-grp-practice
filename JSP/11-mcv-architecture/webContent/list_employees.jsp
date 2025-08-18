<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LIST EMPLOYEES</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-5">
		<h2>Add New Employee</h2>
		<form action="EmployeeControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD" />
			<label>First Name:</label> 
			<input type="text" name="firstName" /><br /> <br /> 
			<label>Last Name:</label>
			<input type="text" name="lastName" /><br /> <br />
			<label>Salary:</label>
			<input type="text" name="salary" /><br /> <br /> 
			<input type="submit" value="Save" />
		</form>

		<h2 class="mb-4">Employee List</h2>
		<table class="table table-striped table-bordered">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Salary</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="employee" items="${Employee_LIST}">
					<tr>
						<td>${employee.id}</td>
						<td>${employee.firstName}</td>
						<td>${employee.lastName}</td>
						<td>${employee.salary}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>