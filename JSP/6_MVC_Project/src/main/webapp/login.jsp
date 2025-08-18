<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="card shadow-lg">
					<div class="card-header bg-primary text-white text-center">
						<h4>Admin Login</h4>
					</div>
					<div class="card-body">
						<form action="login" method="post">
							<div class="mb-3">
								<label class="form-label">Username</label> <input type="text"
									class="form-control" name="username" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Password</label> <input
									type="password" class="form-control" name="password" required>
							</div>
							<button type="submit" class="btn btn-primary w-100">Login</button>
						</form>
						<c:if test="${not empty errorMessage}">
							<div class="alert alert-danger mt-3">${errorMessage}</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
