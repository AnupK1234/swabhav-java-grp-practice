<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><c:out value="${pageTitle}"
		default="Login - Employee Leave Management System" /></title>

<!-- Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/app.css">

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
	position: relative;
}

.login-header::before {
	content: '';
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background:
		url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"><defs><pattern id="grain" width="100" height="100" patternUnits="userSpaceOnUse"><circle cx="50" cy="50" r="1" fill="white" opacity="0.1"/></pattern></defs><rect width="100" height="100" fill="url(%23grain)"/></svg>');
}

.login-header h2 {
	position: relative;
	z-index: 1;
	margin: 0;
	font-weight: 700;
	font-size: 1.8rem;
}

.login-header p {
	position: relative;
	z-index: 1;
	margin: 0.5rem 0 0 0;
	opacity: 0.9;
}

.login-body {
	padding: 2.5rem;
}

.form-control {
	border-radius: 12px;
	border: 2px solid #e9ecef;
	padding: 0.75rem 1rem;
	transition: all 0.3s ease;
	background: rgba(255, 255, 255, 0.9);
}

.form-control:focus {
	border-color: #667eea;
	box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
	background: white;
}

.form-label {
	font-weight: 600;
	color: #495057;
	margin-bottom: 0.5rem;
}

.btn-login {
	background: linear-gradient(45deg, #667eea, #764ba2);
	border: none;
	border-radius: 12px;
	padding: 0.75rem 1.5rem;
	font-weight: 600;
	text-transform: uppercase;
	letter-spacing: 0.5px;
	transition: all 0.3s ease;
}

.btn-login:hover {
	transform: translateY(-2px);
	box-shadow: 0 10px 25px rgba(102, 126, 234, 0.4);
	background: linear-gradient(45deg, #5a6fd8, #6a4190);
}

.btn-login:active {
	transform: translateY(0);
}

.alert {
	border-radius: 12px;
	border: none;
	font-weight: 500;
}

.alert-danger {
	background: linear-gradient(45deg, #ff6b6b, #ee5a52);
	color: white;
}

.alert-success {
	background: linear-gradient(45deg, #51cf66, #40c057);
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

.forgot-password {
	color: #667eea;
	text-decoration: none;
	font-weight: 500;
	transition: color 0.3s ease;
}

.forgot-password:hover {
	color: #764ba2;
}

.system-info {
	background: rgba(255, 255, 255, 0.1);
	border-radius: 12px;
	padding: 1rem;
	margin-top: 1.5rem;
	text-align: center;
	color: white;
}

.version-info {
	background: rgba(102, 126, 234, 0.1);
	border-radius: 8px;
	padding: 0.5rem 1rem;
	margin-top: 1rem;
	text-align: center;
	font-size: 0.85rem;
	color: #6c757d;
}

@media ( max-width : 768px) {
	.login-card {
		margin: 1rem;
		border-radius: 15px;
	}
	.login-header {
		padding: 1.5rem;
	}
	.login-body {
		padding: 1.5rem;
	}
}

.loading-spinner {
	display: none;
}

.loading .loading-spinner {
	display: inline-block;
}

.loading .btn-text {
	display: none;
}
</style>
</head>
<body>
	<div class="login-container">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-xl-4 col-lg-5 col-md-6 col-sm-8">
					<div class="login-card">
						<!-- Login Header -->
						<div class="login-header">
							<div class="mb-3">
								<i class="bi bi-calendar-check display-4"></i>
							</div>
							<h2>Employee Leave MS</h2>
							<p>Secure Access Portal</p>

							<!-- System Info -->
							<div class="system-info">
								<small> <i class="bi bi-shield-check me-1"></i> Secure
									Login System
								</small>
							</div>
						</div>

						<!-- Login Body -->
						<div class="login-body">
							<!-- Success Message -->
							<c:if test="${not empty success}">
								<div class="alert alert-success d-flex align-items-center mb-3"
									role="alert">
									<i class="bi bi-check-circle-fill me-2"></i>
									<c:out value="${success}" />
								</div>
							</c:if>

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
								method="post" id="loginForm" novalidate>
								<!-- Username Field -->
								<div class="mb-3">
									<label for="username" class="form-label"> <i
										class="bi bi-person me-1"></i>Username
									</label>
									<div class="input-group">
										<span class="input-group-text"> <i class="bi bi-person"></i>
										</span> <input type="text" id="username" name="username"
											class="form-control" placeholder="Enter your username"
											value="<c:out value='${param.username}'/>" required
											autocomplete="username">
										<div class="invalid-feedback">Please enter your
											username.</div>
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
										<button class="btn btn-outline-secondary" type="button"
											id="togglePassword" tabindex="-1">
											<i class="bi bi-eye" id="toggleIcon"></i>
										</button>
										<div class="invalid-feedback">Please enter your
											password.</div>
									</div>
								</div>

								<!-- Remember Me -->
								<div class="mb-3 form-check">
									<input type="checkbox" class="form-check-input" id="rememberMe"
										name="rememberMe"
										<c:if test="${param.rememberMe}">checked</c:if>> <label
										class="form-check-label" for="rememberMe"> Remember me
									</label>
								</div>

								<!-- Login Button -->
								<div class="d-grid mb-3">
									<button type="submit" class="btn btn-primary btn-login"
										id="loginBtn">
										<span
											class="loading-spinner spinner-border spinner-border-sm me-2"
											role="status" aria-hidden="true"></span> <span
											class="btn-text"> <i
											class="bi bi-box-arrow-in-right me-2"></i> Sign In
										</span> <span class="loading-text" style="display: none;">Signing
											in...</span>
									</button>
								</div>

								<!-- Forgot Password Link -->
								<div class="text-center">
									<a href="${pageContext.request.contextPath}/forgot-password"
										class="forgot-password"> <i
										class="bi bi-question-circle me-1"></i> Forgot your password?
									</a>
								</div>
							</form>

							<!-- Version Info -->
							<div class="version-info">
								<small> <i class="bi bi-info-circle me-1"></i> Version
									1.0.0 | <c:set var="currentYear"
										value="<%=java.time.LocalDate.now().getYear()%>" /> &copy;
									${currentYear} Leave Management System
								</small>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<!-- Custom JavaScript -->
	<script>
        document.addEventListener('DOMContentLoaded', function() {
            // Password toggle functionality
            const togglePassword = document.getElementById('togglePassword');
            const passwordField = document.getElementById('password');
            const toggleIcon = document.getElementById('toggleIcon');
            
            if (togglePassword && passwordField && toggleIco