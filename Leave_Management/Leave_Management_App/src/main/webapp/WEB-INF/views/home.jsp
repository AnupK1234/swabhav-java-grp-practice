<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${pageTitle}</title>
<!-- Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
.hero-section {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	min-height: 100vh;
	display: flex;
	align-items: center;
}
</style>
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container">
			<a class="navbar-brand fw-bold" href="#"> <i
				class="bi bi-calendar-check me-2"></i> Employee Leave Management
			</a>
			<div class="ms-auto">
				<a href="${pageContext.request.contextPath}/login"
					class="btn btn-outline-light"> <i
					class="bi bi-box-arrow-in-right me-2"></i> Login
				</a>
			</div>
		</div>
	</nav>

	<!-- Hero Section -->
	<section class="hero-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-8 text-center text-white">
					<h1 class="display-4 fw-bold mb-4">Employee Leave Management
						System</h1>
					<p class="lead mb-5">Streamline your organization's leave
						management process with our comprehensive system. Easy
						application, approval workflows, and real-time tracking for
						employees, managers, and administrators.</p>

					<div class="d-grid gap-2 d-md-flex justify-content-md-center">
						<a href="${pageContext.request.contextPath}/login"
							class="btn btn-light btn-lg me-md-2"> <i
							class="bi bi-box-arrow-in-right me-2"></i> Get Started
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Bootstrap 5 JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>