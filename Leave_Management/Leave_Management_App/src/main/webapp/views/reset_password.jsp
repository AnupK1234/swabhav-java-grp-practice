<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-5">
				<div class="card shadow">
					<div class="card-header bg-warning text-dark">Reset Password</div>
					<div class="card-body">
						<form action="${pageContext.request.contextPath}/reset-password"
							method="post">
							<input type="hidden" name="email" value="${email}">
							<div class="mb-3">
								<label for="password" class="form-label">New Password</label> <input
									type="password" name="password" class="form-control" required>
							</div>
							<button type="submit" class="btn btn-warning w-100">Update
								Password</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
