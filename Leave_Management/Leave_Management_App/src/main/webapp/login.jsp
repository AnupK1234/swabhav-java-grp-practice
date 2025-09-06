<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login - Employee Leave Management System</title>
<!-- Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	min-height: 100vh;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.login-container {
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}

.login-card {
	background: rgba(255, 255, 255, 0.95);
	backdrop-filter: blur(10px);
	border-radius: 20px;
	border: 1px solid rgba(255, 255, 255, 0.2);
	box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
	overflow: hidden;
}

.login-header {
	background: linear-gradient(45deg, #667eea, #764ba2);
	color: white;
	padding: 2rem;
	text-align: center;
}

.login-header h2 {
	margin: 0;
	font-weight: 700;
	font-size: 1.8rem;
}

.login-body {
	padding: 2.5rem;
}

.form-control {
	border-radius: 12px;
	border: 2px solid #e9ecef;
	padding: 0.75rem 1rem;
}

.form-control:focus {
	border-color: #667eea;
	box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
}

.btn-login {
	background: linear-gradient(45deg, #667eea, #764ba2);
	border: none;
	border-radius: 12px;
	padding: 0.75rem 1.5rem;
	font-weight: 600;
}

.alert-danger {
	background: linear-gradient(45deg, #ff6b6b, #ee5a52);
	color: white;
}

.input-group-text {
	background: transparent;
	border: 2px solid #e9ecef;
	border-right: none;
	border-radius: 12px 0 0 12px;
}

.input-group .form-control {
	border-left: none;
	border-radius: 0 12px 12px 0;
}

.input-group:focus-within .input-group-text {
	border-color: #667eea;
}

.toast-notification {
	position: fixed;
	top: 20px;
	right: 20px;
	z-index: 1050;
	min-width: 250px;
}
</style>
</head>
<body>
	<div class="toast-container toast-notification">
		<c:if test="${not empty sessionScope.success_toast}">
			<div
				class="toast show align-items-center text-white bg-success border-0"
				role="alert" aria-live="assertive" aria-atomic="true">
				<div class="d-flex">
					<div class="toast-body">
						<i class="bi bi-check-circle-fill me-2"></i>
						${sessionScope.success_toast}
					</div>
					<button type="button" class="btn-close btn-close-white me-2 m-auto"
						data-bs-dismiss="toast" aria-label="Close"></button>
				</div>
			</div>
			<c:remove var="success_toast" scope="session" />
		</c:if>
		<c:if test="${not empty sessionScope.error_toast}">
			<div
				class="toast show align-items-center text-white bg-danger border-0"
				role="alert" aria-live="assertive" aria-atomic="true">
				<div class="d-flex">
					<div class="toast-body">
						<i class="bi bi-exclamation-triangle-fill me-2"></i>
						${sessionScope.error_toast}
					</div>
					<button type="button" class="btn-close btn-close-white me-2 m-auto"
						data-bs-dismiss="toast" aria-label="Close"></button>
				</div>
			</div>
			<c:remove var="error_toast" scope="session" />
		</c:if>
	</div>
	<div class="login-container">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-xl-4 col-lg-5 col-md-6 col-sm-8">
					<div class="login-card">
						<div class="login-header">
							<div class="mb-3">
								<i class="bi bi-calendar-check display-4"></i>
							</div>
							<h2>Employee Leave MS</h2>
							<p>Secure Access Portal</p>
						</div>
						<div class="login-body">
							<!-- Error Message -->
							<c:if test="${not empty error}">
								<div class="alert alert-danger d-flex align-items-center mb-3"
									role="alert">
									<i class="bi bi-exclamation-triangle-fill me-2"></i>
									<c:out value="${error}" />
								</div>
							</c:if>

							<!-- Login Form -->
							<form action="${pageContext.request.contextPath}/login"
								method="post" id="loginForm">

								<!-- *** Email Field (CHANGED FROM USERNAME) *** -->
								<div class="mb-3">
									<label for="email" class="form-label"> <i
										class="bi bi-envelope me-1"></i>Email Address
									</label>
									<div class="input-group">
										<span class="input-group-text"> <i
											class="bi bi-envelope"></i>
										</span> <input type="email" id="email" name="email"
											class="form-control" placeholder="Enter your email"
											value="<c:out value='${param.email}'/>" required
											autocomplete="email">
									</div>
								</div>

								<!-- Password Field -->
								<div class="mb-3">
									<label for="password" class="form-label"> <i
										class="bi bi-lock me-1"></i>Password
									</label>
									<div class="input-group">
										<span class="input-group-text"> <i class="bi bi-lock"></i>
										</span> <input type="password" id="password" name="password"
											class="form-control" placeholder="Enter your password"
											required autocomplete="current-password">
									</div>
								</div>

								<!-- Login Button -->
								<div class="d-grid mb-3">
									<button type="submit" class="btn btn-primary btn-login"
										id="loginBtn">
										<i class="bi bi-box-arrow-in-right me-2"></i> Sign In
									</button>
								</div>
							</form>
						</div>
						<!-- Forgot Password Link -->
						<div class="text-center mt-3">
							<a href="${pageContext.request.contextPath}/forgot-password"
								class="text-decoration-none"> <i
								class="bi bi-question-circle me-1"></i> Forgot Password?
							</a>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var toastEl = document.querySelector('.toast.show');
			if (toastEl) {
				setTimeout(function() {
					new bootstrap.Toast(toastEl).hide();
				}, 3000);
			}
		});
	</script>
</body>
</html>