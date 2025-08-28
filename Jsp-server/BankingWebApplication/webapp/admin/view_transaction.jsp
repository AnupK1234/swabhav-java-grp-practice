<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin - All Transactions</title>
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
				<%-- CORRECTED: Links now point to the correct controller URLs --%>
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
			<div class="bg-white shadow-md rounded-lg">
				<div class="px-6 py-4 border-b">
					<h3 class="text-lg font-medium text-gray-900">Complete
						Transaction Log</h3>
				</div>

				<!-- Search and Filter Form -->
				<div class="p-4 bg-gray-50 border-b">
					<%-- CORRECTED: Form action now points to the consistent plural URL --%>
					<form
						action="${pageContext.request.contextPath}/admin/transactions"
						method="get" class="flex items-center space-x-4">
						<div>
							<label for="filterAccountNo" class="sr-only">Account
								Number</label> <input type="number" name="filterAccountNo"
								id="filterAccountNo" value="<c:out value='${filterAccountNo}'/>"
								placeholder="Search by Account No..."
								class="block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
						</div>
						<button type="submit"
							class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700">
							Search</button>
						<a href="${pageContext.request.contextPath}/admin/transactions"
							class="text-sm font-medium text-gray-600 hover:text-gray-900">Clear</a>
					</form>
				</div>


				<div class="overflow-x-auto">
					<table class="min-w-full divide-y divide-gray-200">
						<thead class="bg-gray-50">
							<tr>
								<%-- Sortable Date Header --%>
								<th
									class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
									<a
									href="${pageContext.request.contextPath}/admin/transactions?sortField=transaction_date&sortOrder=${sortOrder == 'ASC' ? 'DESC' : 'ASC'}&filterAccountNo=${filterAccountNo}">Date</a>
								</th>

								<%-- Sortable Account No Header --%>
								<th
									class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
									<a
									href="${pageContext.request.contextPath}/admin/transactions?sortField=accountNo&sortOrder=${sortOrder == 'ASC' ? 'DESC' : 'ASC'}&filterAccountNo=${filterAccountNo}">Account
										No</a>
								</th>

								<th
									class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Type</th>
								<th
									class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Description</th>

								<%-- Sortable Amount Header --%>
								<th
									class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase">
									<a
									href="${pageContext.request.contextPath}/admin/transactions?sortField=amount&sortOrder=${sortOrder == 'ASC' ? 'DESC' : 'ASC'}&filterAccountNo=${filterAccountNo}">Amount</a>
								</th>
							</tr>
						</thead>
						<tbody class="bg-white divide-y divide-gray-200">
							<c:choose>
								<c:when test="${not empty transactionList}">
									<c:forEach var="tx" items="${transactionList}">
										<tr>
											<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><fmt:formatDate
													value="${tx.getTransactionDateAsDate()}"
													pattern="dd MMM yyyy, hh:mm a" /></td>
											<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><c:out
													value="${tx.accountNo}" /></td>
											<td class="px-6 py-4 whitespace-nowrap text-sm"><c:choose>
													<c:when test="${tx.type == 'CREDIT'}">
														<span
															class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">Credit</span>
													</c:when>
													<c:otherwise>
														<span
															class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-red-100 text-red-800">Debit</span>
													</c:otherwise>
												</c:choose></td>
											<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-800"><c:out
													value="${tx.description}" /></td>
											<td
												class="px-6 py-4 whitespace-nowrap text-sm text-gray-800 text-right font-medium">
												<fmt:setLocale value="en_US" /> <fmt:formatNumber
													value="${tx.amount}" type="currency" />
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="5" class="px-6 py-4 text-center text-gray-500">No
											transactions found.</td>
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
