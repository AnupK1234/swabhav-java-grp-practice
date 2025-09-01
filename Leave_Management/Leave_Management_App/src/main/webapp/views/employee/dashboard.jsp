<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Employee Dashboard</title>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
<style>
body, .card, .alert, .h3 {
	font-family: 'Poppins', Arial, sans-serif !important;
}

h3 {
	font-weight: 700;
	font-size: 2.2rem;
	margin-bottom: 2rem;
	color: #232323;
}

.modern-card-row {
	display: grid;
    grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
	gap: 1.5rem;
	margin-bottom: 2rem;
}

.modern-stat-card {
	background: #fff;
	border-radius: 1.5rem;
	box-shadow: 0 4px 24px rgba(44, 62, 80, 0.12), 0 1.5px 7px
		rgba(44, 62, 80, 0.06);
	display: flex;
	align-items: center;
	padding: 1.2rem 1.6rem;
	position: relative;
	transition: all 0.2s ease-in-out;
	border: 1px solid #f2f3f7;
}

.modern-stat-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 12px 36px rgba(44, 62, 80, 0.18), 0 3px 12px
		rgba(44, 62, 80, 0.09);
	border-color: #e1e3e8;
}

.modern-stat-icon {
	width: 48px;
	height: 48px;
	border-radius: 50%;
	display: flex;
	align-items: center;
	justify-content: center;
	margin-left: 1.1rem;
	font-size: 1.7rem;
	color: #fff;
}

.modern-stat-content {
	flex: 1;
}

.modern-stat-label {
	font-size: 0.97rem;
	font-weight: 600;
	color: #555;
	letter-spacing: 0.02em;
	margin-bottom: 0.18rem;
}

.modern-stat-value {
	font-size: 1.8rem;
	font-weight: 700;
	color: #232323;
	line-height: 1.1;
}

.stretched-link::after {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 1;
    content: "";
}

.bg-success-light { background-color: #e7f5ee; }
.bg-warning-light { background-color: #fff8e1; }
.bg-primary-light { background-color: #e8f0fe; }

.icon-success { background: linear-gradient(45deg, #28a745, #218838); }
.icon-warning { background: linear-gradient(45deg, #ffc107, #e0a800); }
.icon-primary { background: linear-gradient(45deg, #007bff, #0056b3); }

.alert-info {
    background-color: #e8f0fe;
    border: 1px solid #b8d4fd;
    color: #004085;
    font-weight: 500;
    border-radius: 1.25rem;
}
</style>
</head>
<body>
	<h3>Dashboard</h3>

	<div class="alert alert-info">
		Welcome back, <c:out value="${sessionScope.user.firstName}" />! Here's
		your personal leave summary.
	</div>

	<div class="modern-card-row">
		<div class="modern-stat-card bg-success-light">
			<div class="modern-stat-content">
				<div class="modern-stat-label">My Leave Balance</div>
				<div class="modern-stat-value">${leaveBalance} Days</div>
			</div>
			<div class="modern-stat-icon icon-success">
				<i class="bi bi-calendar2-check"></i>
			</div>
			<a href="${pageContext.request.contextPath}/employee?action=leaveHistory"
				class="stretched-link"></a>
		</div>

		<div class="modern-stat-card bg-warning-light">
			<div class="modern-stat-content">
				<div class="modern-stat-label">Pending Apps</div>
				<div class="modern-stat-value">${pendingCount}</div>
			</div>
			<div class="modern-stat-icon icon-warning">
				<i class="bi bi-hourglass-split"></i>
			</div>
			<a href="${pageContext.request.contextPath}/employee?action=leaveHistory"
				class="stretched-link"></a>
		</div>

		<div class="modern-stat-card bg-primary-light">
			<div class="modern-stat-content">
				<div class="modern-stat-label">Approved This Year</div>
				<div class="modern-stat-value">${approvedCount}</div>
			</div>
			<div class="modern-stat-icon icon-primary">
				<i class="bi bi-patch-check"></i>
			</div>
			<a href="${pageContext.request.contextPath}/employee?action=leaveHistory"
				class="stretched-link"></a>
		</div>
	</div>

</body>
</html>