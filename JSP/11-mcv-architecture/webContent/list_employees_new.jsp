<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Employee List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container py-4">
		<div class="d-flex align-items-center justify-content-between mb-3">
			<h1 class="h3 m-0">Employee Directory</h1>
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/add_employee_form_new.jsp">Add
				Employee</a>  <a class="btn btn-secondary"
				href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
		</div>

		<div class="card">
			<div class="card-body p-0">
				<div class="table-responsive">
					<table class="table table-striped table-hover m-0">
						<thead class="table-light">
							<tr>
								<th style="width: 20%">ID</th>
								<th style="width: 25%">First Name</th>
								<th style="width: 25%">Last Name</th>
								<th style="width: 30%">Salary</th>
								<th style="width: 15%">Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="employee" items="${Employee_LIST}">
								<tr>
									<td><c:out value="${employee.id}" /></td>
									<td><c:out value="${employee.firstName}" /></td>
									<td><c:out value="${employee.lastName}" /></td>
									<td><c:out value="${employee.salary}" /></td>
									<td><a class="btn btn-outline-secondary btn-sm me-1"
										href="${pageContext.request.contextPath}/EmployeeControllerServlet?command=LOAD&EmployeeId=${employee.id}">
											Edit </a>

										<form class="d-inline" method="get"
											action="${pageContext.request.contextPath}/EmployeeControllerServlet"<%-- 											onsubmit="return confi<!--  -->rm('Delete this employee?');
 --%>											">
											<input type="hidden" name="command" value="DELETE" /> <input
												type="hidden" name="EmployeeId" value="${employee.id}" />
											<button class="btn btn-outline-danger btn-sm" type="submit">Delete</button>
										</form></td>
								</tr>
							</c:forEach>

							<c:if test="${empty Employee_LIST}">
								<tr>
									<td colspan="5" class="text-center text-muted py-4">No
										employees found.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>