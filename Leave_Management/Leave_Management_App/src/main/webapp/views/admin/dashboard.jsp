<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap"
	rel="stylesheet">
<style>
html, body {
	height: 100vh;
	font-family: 'Poppins', Arial, sans-serif !important;
	background: #f6fafd;
}

body {
	overflow: hidden;
}

.sidebar {
	width: 270px;
	height: 100vh;
	background: linear-gradient(160deg, #232526 0%, #414345 100%);
	color: #fff;
	border-right: 1.5px solid #232526;
	box-shadow: 2px 0 20px rgba(44, 62, 80, 0.07);
}

.sidebar a, .sidebar .nav-link {
	color: #c9d1d9;
	font-weight: 500;
	font-size: 1.07rem;
	letter-spacing: 0.01em;
	border-radius: 0.8rem;
	transition: background 0.18s, color 0.18s;
	padding: 0.65rem 1.1rem;
	margin: 0.13rem 0;
	display: flex;
	align-items: center;
}

.sidebar .nav-link.active, .sidebar .nav-link:focus, .sidebar .nav-link:hover
	{
	color: #fff !important;
	background: linear-gradient(90deg, #1c62f6 0%, #40e0d0 100%);
	box-shadow: 0 2px 18px rgba(44, 62, 80, 0.13);
}

.sidebar .fs-5 {
	font-family: 'Poppins', Arial, sans-serif;
	font-weight: 700;
	letter-spacing: 0.02em;
}

.sidebar hr {
	border-top: 1.5px solid #33373a;
}

.sidebar .text-light, .sidebar .small {
	color: #e5e8ec !important;
	font-size: 1rem;
	font-weight: 500;
}

.sidebar .nav-link.text-white {
	color: #c9d1d9 !important;
}

.sidebar .nav-link.text-white:hover {
	color: #fff !important;
	background: rgba(64, 224, 208, 0.09);
}

.sidebar .bi {
	font-size: 1.25rem;
	color: #40e0d0;
	margin-right: 0.85rem;
	transition: color 0.15s;
}

.sidebar .nav-link.active .bi, .sidebar .nav-link:hover .bi {
	color: #fff;
}

.sidebar .logout-link {
	margin-top: 1rem;
	background: none;
	color: #ff7675;
	border-radius: 0.7rem;
	font-weight: 600;
	transition: background 0.15s, color 0.15s;
}

.sidebar .logout-link:hover {
	background: rgba(255, 118, 117, 0.09);
	color: #fff;
}

.content {
	flex-grow: 1;
	overflow-y: auto;
	background: #f6fafd;
}

.toast-notification {
	position: fixed;
	top: 20px;
	right: 20px;
	z-index: 1050;
	min-width: 250px;
}
/* Responsive adjustments */
@media ( max-width : 900px) {
	.sidebar {
		width: 100vw;
		height: auto;
		flex-direction: row;
	}
	.sidebar .nav {
		flex-direction: row !important;
	}
}

@media ( max-width : 600px) {
	.sidebar {
		min-width: 100vw;
	}
}

.content {
	flex-grow: 1;
	overflow-y: auto;
	background: #f6fafd;
}
/* Style for toast notifications */
.toast-notification {
	position: fixed;
	top: 20px;
	right: 20px;
	z-index: 1050;
	min-width: 250px;
}
</style>
</head>
<body class="d-flex">
	<!-- Toast Container -->
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

	<!-- Sidebar -->
	<div class="d-flex flex-column flex-shrink-0 p-3 sidebar">
		<a href="${pageContext.request.contextPath}/admin?action=dashboard"
			class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
			<i class="bi bi-person-workspace fs-4"></i> <span class="fs-5 ms-2">Admin
				Panel</span>
		</a>
		<hr>
		<ul class="nav nav-pills flex-column mb-auto">
			<li><a
				href="${pageContext.request.contextPath}/admin?action=dashboard"
				class="nav-link ${requestScope.view == 'dashboard' ? 'active' : 'text-white'}">
					<i class="bi bi-speedometer2"></i>Dashboard
			</a></li>
			<li><a
				href="${pageContext.request.contextPath}/admin?action=showPendingRequests"
				class="nav-link ${requestScope.view == 'pending_requests' ? 'active' : 'text-white'}">
					<i class="bi bi-card-list"></i>Pending Requests
			</a></li>
			<li><a
				href="${pageContext.request.contextPath}/admin?action=manageEmployees"
				class="nav-link ${requestScope.view == 'manage_employees' ? 'active' : 'text-white'}">
					<i class="bi bi-people"></i>View Employees
			</a></li>
			<li><a
				href="${pageContext.request.contextPath}/admin?action=showHoliday"
				class="nav-link ${requestScope.view == 'showHoliday' ? 'active' : 'text-white'}">
					<i class="bi bi-people"></i>Create Holiday
			</a></li>
			<li><a
				href="${pageContext.request.contextPath}/admin?action=createEmployee"
				class="nav-link ${requestScope.view == 'create_employee' ? 'active' : 'text-white'}">
					<i class="bi bi-people"></i>Create Employee
			</a></li>
			<!--
            <li>
                <a href="${pageContext.request.contextPath}/admin?action=showEditProfile" class="nav-link ${requestScope.view == 'edit_profile' ? 'active' : 'text-white'}">
                    <i class="bi bi-person-circle"></i>Edit Profile
                </a>
            </li>
            -->
		</ul>

		<div>
			<span class="text-light small">Welcome,
				${sessionScope.user.firstName}</span><br> <a
				href="${pageContext.request.contextPath}/logout"
				class="nav-link logout-link"> <i class="bi bi-box-arrow-right"></i>Logout
			</a>
		</div>
	</div>

	<!-- Main Content Area -->
	<div class="flex-grow-1 p-4 content">
		<%-- <c:if test="${requestScope.view == 'dashboard'}">
            <jsp:include page="dashboard_content.jsp" />
        </c:if> --%>
		<c:if test="${requestScope.view == 'pending_requests'}">
			<jsp:include page="pending_requests.jsp" />
		</c:if>
		<c:if test="${requestScope.view == 'manage_employees'}">
            <jsp:include page="employees.jsp" />
        </c:if>
		<c:if test="${requestScope.view == 'showHoliday'}">
            <jsp:include page="create_holiday.jsp" />
        </c:if>
		<%--
        <c:if test="${requestScope.view == 'edit_profile'}">
            <jsp:include page="edit_profile.jsp" />
        </c:if> --%>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		// Wait for the document to be fully loaded
		document.addEventListener('DOMContentLoaded', function() {
			// Find any toast element that is currently being shown
			var toastEl = document.querySelector('.toast.show');

			// If a visible toast is found
			if (toastEl) {
				// Use setTimeout to hide it after 3 seconds (3000 milliseconds)
				setTimeout(function() {
					// Create a Bootstrap Toast instance from the element and hide it
					var toastInstance = new bootstrap.Toast(toastEl);
					toastInstance.hide();
				}, 3000);
			}
		});
	</script>
</body>
</html>