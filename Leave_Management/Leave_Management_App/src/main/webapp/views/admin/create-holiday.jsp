<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List, com.aurionpro.model.Holiday"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${pageTitle}</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body class="bg-light">

	<div class="container py-4">
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h4>Holidays</h4>
			<form class="d-flex gap-2" method="post"
				action="${pageContext.request.contextPath}/app/admin/holiday/save">
				<input type="date" class="form-control" name="holidayDate" required>
				<input class="form-control" name="title" placeholder="Title"
					required>
				<button class="btn btn-primary">Add</button>
			</form>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Date</th>
					<th>Title</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Holiday> holidays = (List<Holiday>) request.getAttribute("holidays");
				if (holidays != null)
					for (var h : holidays) {
				%>
				<tr>
					<td><%=h.getHolidayDate()%></td>
					<td><%=h.getTitle()%></td>
					<td>
						<form method="post"
							action="${pageContext.request.contextPath}/app/admin/holiday/delete">
							<input type="hidden" name="id" value="<%=h.getId()%>">
							<button class="btn btn-sm btn-danger">Delete</button>
						</form>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
</body>
</html>