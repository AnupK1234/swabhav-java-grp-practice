<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>
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
				<a href="${pageContext.request.contextPath}/admin/dashboard"
					class="bg-blue-100 text-blue-700 px-3 py-2 font-medium text-sm rounded-md">View
					Customers</a> <a href="#"
					class="text-gray-500 hover:bg-gray-200 px-3 py-2 font-medium text-sm rounded-md">Add
					New Customer</a> <a href="#"
					class="text-gray-500 hover:bg-gray-200 px-3 py-2 font-medium text-sm rounded-md">View
					All Transactions</a>
			</nav>
		</div>
	</div>

	<main class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
		<div class="px-4 py-6 sm:px-0">
			<div class="bg-white shadow-md rounded-lg">
				<div class="px-6 py-4 border-b">
					<h3 class="text-lg font-medium text-gray-900">All Customer
						Accounts</h3>
				</div>
				<div class="overflow-x-auto">
					<table class="min-w-full divide-y divide-gray-200">
						<thead class="bg-gray-50">
							<tr>
								<th
									class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Name</th>
								<th
									class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Username</th>
								<th
									class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Account
									Number</th>
								<th
									class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase">Balance</th>
							</tr>
						</thead>
						<tbody class="bg-white divide-y divide-gray-200">
							<c:choose>
								<c:when test="${not empty customerList}">
									<c:forEach var="customer" items="${customerList}">
										<tr>
											<td
												class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><c:out
													value="${customer.firstName} ${customer.lastName}" /></td>
											<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><c:out
													value="${customer.userName}" /></td>
											<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><c:out
													value="${customer.accountNo}" /></td>
											<td
												class="px-6 py-4 whitespace-nowrap text-sm text-gray-800 text-right">
												<fmt:setLocale value="en_US" /> <fmt:formatNumber
													value="${customer.balance}" type="currency" />
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="4" class="px-6 py-4 text-center text-gray-500">No
											customers found.</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</main>

</body>
</html>
