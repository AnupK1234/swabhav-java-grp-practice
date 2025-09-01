<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Dashboard Overview</title>
<!-- Poppins Font Import -->
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap"
	rel="stylesheet">
<style>
body, .card, .alert, .sidebar, .h2, .fw-bold, .text-uppercase {
	font-family: 'Poppins', Arial, sans-serif !important;
}

h3 {
	font-weight: 700;
	font-size: 2.2rem;
	margin-bottom: 2rem;
	color: #232323;
}

.modern-card-row {
	display: flex;
	flex-wrap: wrap;
	gap: 1.5rem;
	margin-bottom: 2rem;
}

.modern-stat-card {
	flex: 1 1 200px;
	min-width: 220px;
	background: #fff;
	border-radius: 1.5rem;
	box-shadow: 0 4px 24px rgba(44, 62, 80, 0.16), 0 1.5px 7px
		rgba(44, 62, 80, 0.08);
	display: flex;
	align-items: center;
	padding: 1.2rem 1.6rem;
	position: relative;
	transition: box-shadow 0.2s, border-color 0.2s;
	border: 1px solid #f2f3f7;
}

.modern-stat-card:hover {
	box-shadow: 0 12px 36px rgba(44, 62, 80, 0.28), 0 3px 12px
		rgba(44, 62, 80, 0.14);
	border-color: #e1e3e8;
	z-index: 2;
}

.modern-stat-icon {
	width: 46px;
	height: 46px;
	border-radius: 50%;
	background: #f7fafd;
	display: flex;
	align-items: center;
	justify-content: center;
	margin-left: 1.1rem;
	font-size: 1.65rem;
	color: #adb5bd;
	transition: background 0.2s;
}

.modern-stat-card:hover .modern-stat-icon {
	background: #f0f4f8;
}

.modern-stat-content {
	flex: 1;
}

.modern-stat-label {
	font-size: 0.97rem;
	font-weight: 600;
	letter-spacing: 0.02em;
	margin-bottom: 0.18rem;
}

.modern-stat-value {
	font-size: 1.8rem;
	font-weight: 700;
	color: #232323;
	line-height: 1.1;
}

.text-warning {
	color: #f6b402 !important;
}

.text-primary {
	color: #1c62f6 !important;
}

.text-success {
	color: #18794e !important;
}

.text-info {
	color: #1dcaff !important;
}

.alert {
	font-size: 1.12rem;
	font-weight: 500;
	letter-spacing: 0.01em;
}

@media ( max-width : 1200px) {
	.modern-card-row {
		gap: 1rem;
	}
	.modern-stat-card {
		min-width: 180px;
		padding: 1rem 1.2rem;
	}
}

@media ( max-width : 991.98px) {
	.modern-card-row {
		flex-direction: column;
		gap: 1rem;
	}
	.modern-stat-card {
		min-width: 0;
		width: 100%;
	}
}
</style>
<!-- Bootstrap Icons (if not already loaded) -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
	<h3>Dashboard Overview</h3>
	<div class="modern-card-row">
		<!-- Stat Card: Pending Requests -->
		<div class="modern-stat-card">
			<div class="modern-stat-content">
				<div class="text-warning modern-stat-label">Pending Requests</div>
				<div class="modern-stat-value">${pendingRequestsCount}</div>
			</div>
			<div class="modern-stat-icon text-warning">
				<i class="bi bi-card-list"></i>
			</div>
			<a
				href="${pageContext.request.contextPath}/manager?action=showPendingRequests"
				class="stretched-link"></a>
		</div>

		<!-- Stat Card: Team Members -->
		<div class="modern-stat-card">
			<div class="modern-stat-content">
				<div class="text-primary modern-stat-label">Team Members</div>
				<div class="modern-stat-value">${teamMembersCount}</div>
			</div>
			<div class="modern-stat-icon text-primary">
				<i class="bi bi-people"></i>
			</div>
			<a
				href="${pageContext.request.contextPath}/manager?action=manageEmployees"
				class="stretched-link"></a>
		</div>

		<!-- Stat Card: My Leave Balance -->
		<div class="modern-stat-card">
			<div class="modern-stat-content">
				<div class="text-success modern-stat-label">My Leave Balance</div>
				<div class="modern-stat-value">${myLeaveBalance} Days</div>
			</div>
			<div class="modern-stat-icon text-success">
				<i class="bi bi-calendar2-check"></i>
			</div>
			<a
				href="${pageContext.request.contextPath}/manager?action=showApplyLeave"
				class="stretched-link"></a>
		</div>

		<!-- Stat Card: My Leave History -->
		<div class="modern-stat-card">
			<div class="modern-stat-content">
				<div class="text-info modern-stat-label">My Leave History</div>
				<div class="modern-stat-value">${myLeavesCount} Apps</div>
			</div>
			<div class="modern-stat-icon text-info">
				<i class="bi bi-clock-history"></i>
			</div>
			<a
				href="${pageContext.request.contextPath}/manager?action=showLeaveHistory"
				class="stretched-link"></a>
		</div>
	</div>

	<div class="alert alert-info mt-4">
		Welcome to your dashboard,
		<c:out value="${sessionScope.user.firstName}" />
		! You can manage your team's leave requests and view employee details
		from the sidebar.
	</div>
</body>
</html>