<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Apply for Leave</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap"
	rel="stylesheet">
<style>
body, .card, .form-control, .btn, label, h3 {
	font-family: 'Poppins', Arial, sans-serif !important;
}

h3 {
	font-weight: 700;
	font-size: 2rem;
	color: #232323;
	margin-bottom: 1.5rem;
}

.modern-card {
	background: #fff;
	border-radius: 1.5rem;
	box-shadow: 0 6px 28px rgba(44, 62, 80, 0.18), 0 1.5px 7px
		rgba(44, 62, 80, 0.09);
	padding: 2.2rem 2.3rem;
	border: 1px solid #f1f4f6;
	max-width: 550px;
}

.modern-form {
	display: flex;
	flex-direction: column;
	gap: 1.3rem;
}

.form-label {
	font-weight: 600;
	font-size: 1rem;
	color: #232323;
	margin-bottom: 0.4rem;
}

.form-control {
	border-radius: 0.8rem;
	border: 1px solid #e3e6ea;
	background: #f7fbfc;
	font-size: 1.08rem;
	padding: 0.7rem 1.1rem;
	transition: border 0.2s;
	box-shadow: none;
}

.form-control:focus {
	border-color: #1c62f6;
	background: #fff;
}

.btn-primary {
	background: linear-gradient(90deg, #1c62f6 0%, #40e0d0 100%);
	border: none;
	border-radius: 0.8rem;
	font-weight: 600;
	font-size: 1.1rem;
	padding: 0.7rem 1.9rem;
	transition: all 0.2s;
}

.btn-primary:hover {
	box-shadow: 0 8px 32px rgba(44, 62, 80, 0.13);
	transform: translateY(-2px);
}

.btn-secondary {
	border-radius: 0.8rem;
	font-weight: 600;
}

.modal-header {
	background: linear-gradient(90deg, #6c757d 0%, #343a40 100%);
	color: white;
}

.modal-header .btn-close {
	filter: invert(1) grayscale(100%) brightness(200%);
}
</style>
</head>
<body>
	<h3>Apply for Leave</h3>
	<div class="modern-card">
		<div class="d-grid mb-4">
			<button type="button" class="btn btn-secondary"
				data-bs-toggle="modal" data-bs-target="#holidayModal">
				<i class="bi bi-calendar-heart me-2"></i>View Company Holidays
			</button>
		</div>
		<form action="${pageContext.request.contextPath}/employee"
			method="post" class="modern-form">
			<input type="hidden" name="action" value="applyLeave">
			<div>
				<label for="startDate" class="form-label">Start Date</label> <input
					type="date" class="form-control" id="startDate" name="startDate"
					required>
			</div>
			<div>
				<label for="endDate" class="form-label">End Date</label> <input
					type="date" class="form-control" id="endDate" name="endDate"
					required>
			</div>
			<div>
				<label for="reason" class="form-label">Reason for Leave</label>
				<textarea class="form-control" id="reason" name="reason" rows="4"
					required placeholder="Please provide a brief reason..."></textarea>
			</div>
			<button type="submit" class="btn btn-primary mt-2">Submit
				Application</button>
		</form>
	</div>

	<div class="modal fade" id="holidayModal" tabindex="-1"
		aria-labelledby="holidayModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="holidayModalLabel">
						<i class="bi bi-calendar-heart me-2"></i>Company Holidays - 2025
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<thead class="table-light">
							<tr>
								<th>Date</th>
								<th>Holiday Name</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="holiday" items="${holidayList}">
								<tr>
									<td><c:out value="${holiday.holidayDate}" /></td>
									<td><c:out value="${holiday.title}" /></td>
								</tr>
							</c:forEach>

							<c:if test="${empty holidayList}">
								<tr>
									<td colspan="2" class="text-center text-muted">Holiday
										list is not available at the moment.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>