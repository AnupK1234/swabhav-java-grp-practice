<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Forgot Password</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-5">
				<div class="card shadow">
					<div class="card-header bg-primary text-white">Forgot
						Password</div>
					<div class="card-body">
						<c:if test="${not empty error}">
							<div class="alert alert-danger">${error}</div>
						</c:if>
						<form action="${pageContext.request.contextPath}/forgot-password"
							method="post">
							<div class="mb-3">
								<label for="email" class="form-label">Enter your email</label> <input
									type="email" name="email" class="form-control" required>
							</div>
							<button type="submit" class="btn btn-primary w-100">Send
								OTP</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
