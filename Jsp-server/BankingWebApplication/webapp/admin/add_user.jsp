<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add New User</title>
<script src="https://cdn.tailwindcss.com"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap"
	rel="stylesheet">
<style>
body {
	font-family: 'Inter', sans-serif;
}
</style>
</head>
<body class="bg-gray-100">

	<!-- Header -->
	<header class="bg-white shadow-sm">
		<div
			class="max-w-7xl mx-auto py-4 px-4 sm:px-6 lg:px-8 flex justify-between items-center">
			<h1 class="text-2xl font-bold text-gray-900">Admin Dashboard</h1>
			<a href="${pageContext.request.contextPath}/logout"
				class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-red-600 hover:bg-red-700">
				Logout </a>
		</div>
	</header>

	<!-- Sub Navigation -->
	<div class="bg-white shadow-sm">
		<div class="max-w-7xl mx-auto py-3 px-4 sm:px-6 lg:px-8">
			<nav class="flex space-x-4">
				<a href="${pageContext.request.contextPath}/admin/AdminDashboard"
					class="${activePage == 'customers' ? 'bg-blue-100 text-blue-700' : 'text-gray-500 hover:bg-gray-200'} px-3 py-2 font-medium text-sm rounded-md">
					View Customers </a> <a
					href="${pageContext.request.contextPath}/admin/add-user"
					class="${activePage == 'addUser' ? 'bg-blue-100 text-blue-700' : 'text-gray-500 hover:bg-gray-200'} px-3 py-2 font-medium text-sm rounded-md">
					Add New User </a> <a
					href="${pageContext.request.contextPath}/admin/transactions"
					class="${activePage == 'transactions' ? 'bg-blue-100 text-blue-700' : 'text-gray-500 hover:bg-gray-200'} px-3 py-2 font-medium text-sm rounded-md">
					View All Transactions </a>
			</nav>
		</div>
	</div>

	<main class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
		<div class="px-4 py-6 sm:px-0">
			<div
				class="w-full max-w-2xl mx-auto bg-white rounded-lg shadow-md p-8">
				<h2 class="text-2xl font-bold text-gray-800 mb-6">Create New
					User</h2>

				<c:if test="${not empty error}">
					<div class="p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-100"
						role="alert">
						<span class="font-medium"><c:out value="${error}" /></span>
					</div>
				</c:if>

				<!-- Form to add a new user -->
				<form action="${pageContext.request.contextPath}/admin/add-user"
					method="post">
					<div class="grid grid-cols-1 md:grid-cols-2 gap-6">
						<!-- First Name -->
						<div>
							<label for="firstName"
								class="block text-sm font-medium text-gray-700">First
								Name</label> <input type="text" id="firstName" name="firstName" required
								class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
						</div>
						<!-- Last Name -->
						<div>
							<label for="lastName"
								class="block text-sm font-medium text-gray-700">Last
								Name</label> <input type="text" id="lastName" name="lastName" required
								class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
						</div>
						<!-- Username -->
						<div class="md:col-span-2">
							<label for="username"
								class="block text-sm font-medium text-gray-700">Username</label>
							<input type="text" id="username" name="username" required
								class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
						</div>
						<div class="relative">
							<label for="password"
								class="block text-sm font-medium text-gray-700">Password</label>
							<input type="password" id="password" name="password" required
								class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">

							<button type="button" id="togglePassword"
								class="absolute inset-y-0 right-0 top-6 pr-3 flex items-center text-sm leading-5">
								<span id="eye-icon">👁️</span>
							</button>
						</div>


						<!-- Initial Balance -->
						<div>
							<label for="balance"
								class="block text-sm font-medium text-gray-700">Initial
								Balance</label> <input type="number" id="balance" name="balance"
								step="0.01" required value="0.00"
								class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
						</div>
						<!-- Role Dropdown -->
						<div class="md:col-span-2">
							<label for="role" class="block text-sm font-medium text-gray-700">Role</label>
							<select id="role" name="role" required
								class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
								<option value="">-- Select Role --</option>
								<option value="admin">Admin</option>
								<option value="customer">Customer</option>
							</select>
						</div>
					</div>

					<!-- Hidden accountNo (generated in servlet) -->
					<input type="hidden" name="accountNo" value="0">

					<!-- Submit Button -->
					<div class="mt-8 flex justify-end">
						<a href="${pageContext.request.contextPath}/admin/AdminDashboard"
							class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded-md mr-4">
							Cancel </a>
						<button type="submit"
							class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-md focus:outline-none focus:shadow-outline">
							Create User</button>
					</div>
				</form>
			</div>
		</div>
	</main>
<script>
    const passwordInput = document.getElementById("password");
    const togglePassword = document.getElementById("togglePassword");
    const eyeIcon = document.getElementById("eye-icon");

    togglePassword.addEventListener("click", function (e) {
        e.preventDefault();
        const isPassword = passwordInput.type === "password";
        passwordInput.type = isPassword ? "text" : "password";
        eyeIcon.textContent = isPassword ? "🙈" : "👁️"; // change icon too
    });
</script>

</body>
</html>
