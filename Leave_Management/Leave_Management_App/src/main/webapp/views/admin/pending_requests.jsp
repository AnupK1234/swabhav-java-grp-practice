<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Pending Employee Leave Requests</title>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap"
	rel="stylesheet">
<style>
/* Your CSS styles remain the same... */
body, .card, .table, .btn, .form-control {
	font-family: 'Poppins', Arial, sans-serif !important;
}

h3 {
	font-weight: 700;
	font-size: 2rem;
	color: #232323;
}

.modern-card {
	background: #fff;
	border-radius: 1.25rem;
	box-shadow: 0 6px 28px rgba(44, 62, 80, 0.14), 0 1.5px 7px
		rgba(44, 62, 80, 0.08);
	padding: 2rem 2.1rem;
	margin-bottom: 2rem;
	border: 1px solid #f1f4f6;
}

.table thead th {
	background: #f8fafb !important;
	border-bottom: 2px solid #e8ecef;
	font-weight: 600;
	color: #495057;
	text-transform: uppercase;
}

.table-hover tbody tr:hover {
	background-color: #e8f0fa;
}

.form-control {
	border-radius: 0.8rem;
	border: 2px solid #e8ecef;
}

.form-control:focus {
	border-color: #1c62f6;
	box-shadow: none;
}
</style>
</head>
<body>
	<h3 class="mb-4">Pending Employee Leave Requests</h3>

	<!-- Search Bar (No form tag needed) -->
	<div class="modern-card">
		<div class="input-group">
			<span class="input-group-text bg-transparent border-end-0"><i
				class="bi bi-search"></i></span> <input type="text"
				class="form-control border-start-0" id="searchInput"
				onkeyup="filterTable()"
				placeholder="Type to search by employee name...">
		</div>
	</div>

	<div class="modern-card">
		<div class="table-responsive">
			<table class="table table-hover align-middle" id="requestsTable">
				<thead>
					<tr>
						<th>Employee Name</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Reason</th>
						<th class="text-center">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="req" items="${pendingRequests}">
						<tr>
							<td><c:out
									value="${req.employeeFirstName} ${req.employeeLastName}" /></td>
							<td><c:out value="${req.startDate}" /></td>
							<td><c:out value="${req.endDate}" /></td>
							<td><c:out value="${req.reason}" /></td>
							<td class="text-center">
								<form action="${pageContext.request.contextPath}/manager"
									method="post" class="d-inline">
									<input type="hidden" name="action" value="processLeave">
									<input type="hidden" name="leaveId" value="${req.id}">
									<button type="submit" name="decision" value="approve"
										class="btn btn-success btn-sm">Approve</button>
									<button type="submit" name="decision" value="reject"
										class="btn btn-danger btn-sm">Reject</button>
								</form>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty pendingRequests}">
						<tr>
							<td colspan="5" class="text-center text-muted p-4">There are
								no pending leave requests from your team.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>

	<!-- *** NEW: JavaScript for Live Search *** -->
	<script>
		function filterTable() {
			// Get the input field, filter value, table, and all rows
			var input = document.getElementById("searchInput");
			var filter = input.value.toLowerCase();
			var table = document.getElementById("requestsTable");
			var tr = table.getElementsByTagName("tr");

			// Loop through all table rows (skipping the header row, which is tr[0])
			for (var i = 1; i < tr.length; i++) {
				// Get the first cell (Employee Name) in the current row
				var td = tr[i].getElementsByTagName("td")[0];
				if (td) {
					var txtValue = td.textContent || td.innerText;
					// Check if the row's text contains the filter text
					if (txtValue.toLowerCase().indexOf(filter) > -1) {
						// If it matches, show the row
						tr[i].style.display = "";
					} else {
						// If it doesn't match, hide the row
						tr[i].style.display = "none";
					}
				}
			}
		}
	</script>
</body>
</html>