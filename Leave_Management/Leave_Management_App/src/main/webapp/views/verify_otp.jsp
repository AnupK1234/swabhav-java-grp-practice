<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Verify OTP</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-5">
				<div class="card shadow">
					<div class="card-header bg-success text-white">Verify OTP</div>
					<div class="card-body">
						<form action="${pageContext.request.contextPath}/verify-otp"
							method="post">
							<input type="hidden" name="email" value="${email}">
							<div class="mb-3">
								<label for="otp" class="form-label">Enter OTP</label> <input
									type="text" name="otp" class="form-control" required>
							</div>
							<button type="submit" class="btn btn-success w-100">Verify</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
