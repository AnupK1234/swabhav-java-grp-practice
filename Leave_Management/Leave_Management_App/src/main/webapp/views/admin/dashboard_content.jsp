<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="container-fluid">

	<!-- Summary Cards -->
	<div class="row my-4">
		<div class="col-md-3">
			<div class="card text-bg-primary shadow-sm">
				<div class="card-body">
					<h5 class="card-title">Employees</h5>
					<h3>${employeeCount}</h3>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card text-bg-success shadow-sm">
				<div class="card-body">
					<h5 class="card-title">Managers</h5>
					<h3>${managerCount}</h3>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card text-bg-warning shadow-sm">
				<div class="card-body">
					<h5 class="card-title">Pending Requests</h5>
					<h3>${pendingCount}</h3>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card text-bg-info shadow-sm">
				<div class="card-body">
					<h5 class="card-title">Upcoming Holidays</h5>
					<h3>${holidayCount}</h3>
				</div>
			</div>
		</div>
	</div>

	<!-- Pending Requests Table -->
	<div class="row mt-4">
		<div class="col-md-8">
			<div class="card shadow-sm">
				<div class="card-header bg-light">
					<strong>Recent Pending Leave Requests</strong>
				</div>
				<div class="card-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Employee</th>
								<th>From</th>
								<th>To</th>
								<th>Reason</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="req" items="${recentPendingRequests}">
								<tr>
									<td>${req.employeeFirstName}${req.employeeLastName}</td>
									<td>${req.startDate}</td>
									<td>${req.endDate}</td>
									<td>${req.reason}</td>
									<td><span class="badge bg-warning">Pending</span></td>
								</tr>
							</c:forEach>
							<c:if test="${empty recentPendingRequests}">
								<tr>
									<td colspan="5" class="text-center">No pending requests</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- Upcoming Holidays -->
		<div class="col-md-4">
			<div class="card shadow-sm">
				<div class="card-header bg-light">
					<strong>Upcoming Holidays</strong>
				</div>
				<div class="card-body">
					<ul class="list-group">
						<c:forEach var="holiday" items="${upcomingHolidays}">
							<li
								class="list-group-item d-flex justify-content-between align-items-center">
								${holiday.title} <span class="badge bg-primary">${holiday.holidayDate}</span>
							</li>
						</c:forEach>
						<c:if test="${empty upcomingHolidays}">
							<li class="list-group-item text-center">No holidays
								scheduled</li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
