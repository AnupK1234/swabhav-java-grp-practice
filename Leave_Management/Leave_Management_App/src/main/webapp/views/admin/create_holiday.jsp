<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Holiday</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="container mt-5">

	<h3>Create Holiday</h3>
	<form
		action="${pageContext.request.contextPath}/admin?action=createHoliday"
		method="post" class="mt-3">
		<div class="mb-3">
			<label for="holiday_date" class="form-label">Holiday Date</label> <input
				type="date" class="form-control" id="holiday_date"
				name="holiday_date" required>
		</div>
		<div class="mb-3">
			<label for="description" class="form-label">Description</label> <input
				type="text" class="form-control" id="description" name="description"
				placeholder="e.g. Independence Day" required>
		</div>
		<button type="submit" class="btn btn-primary">Add Holiday</button>
	</form>

</body>
</html>
