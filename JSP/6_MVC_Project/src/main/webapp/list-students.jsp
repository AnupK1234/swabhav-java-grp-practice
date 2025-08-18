<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Student List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
	crossorigin="anonymous">
</head>
<body class="bg-light">

	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand fw-bold" href="#">Student Management</a>
			<div class="d-flex">
				<a href="add-student.jsp" class="btn btn-success">+ Add Student</a>
			</div>
			<a href="logout" class="btn btn-outline-light ms-2">Logout</a>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container my-4">

		<div class="card shadow-lg">
			<div class="card-header bg-primary text-white">
				<h4 class="mb-0">Student List</h4>
			</div>
			<div class="card-body p-0">
				<table class="table table-hover table-striped mb-0">
					<thead class="table-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tempStudent" items="${STUDENT_LIST}">
							<tr>
								<td>${tempStudent.id}</td>
								<td>${tempStudent.firstName}</td>
								<td>${tempStudent.lastName}</td>
								<td><a
									href="student-list?command=LOAD&studentId=${tempStudent.id}"
									class="btn btn-sm btn-warning me-2">Update</a> <a
									href="student-list?command=DELETE&studentId=${tempStudent.id}"
									class="btn btn-sm btn-danger"
									onclick="return confirm('Are you sure you want to delete this student?');">
										Delete </a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
		crossorigin="anonymous"></script>
</body>
</html>
