<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer Dashboard</title>
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
	<header class="bg-white shadow-sm">
		<div
			class="max-w-7xl mx-auto py-4 px-4 sm:px-6 lg:px-8 flex justify-between items-center">
			<h1 class="text-2xl font-bold text-gray-900">
				Welcome back,
				<c:out value="${sessionScope.user.firstName}" />
				!
			</h1>
			<a href="${pageContext.request.contextPath}/logout"
				class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-red-600 hover:bg-red-700">
				Logout </a>
		</div>
	</header>

	<main class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
		<div class="px-4 py-6 sm:px-0">
			<div class="grid grid-cols-1 lg:grid-cols-3 gap-6">

				<!-- Left Column: Account Summary & Transfer Form -->
				<div class="lg:col-span-1 space-y-6">
					<!-- Account Summary Card -->
					<div class="bg-white shadow-md rounded-lg p-6">
						<h3 class="text-lg font-medium text-gray-900 mb-4">Account
							Summary</h3>
						<div class="space-y-3">
							<p class="text-sm text-gray-600">
								Account Holder: <span class="font-semibold text-gray-800"><c:out
										value="${sessionScope.user.firstName}" /> <c:out
										value="${sessionScope.user.lastName}" /></span>
							</p>
							<p class="text-sm text-gray-600">
								Account Number: <span class="font-semibold text-gray-800"><c:out
										value="${sessionScope.user.accountNo}" /></span>
							</p>
							<p class="text-2xl font-bold text-gray-900">
								Current Balance: <span class="text-green-600"> <fmt:setLocale
										value="en_US" /> <fmt:formatNumber
										value="${sessionScope.user.balance}" type="currency" />
								</span>
							</p>
						</div>
					</div>

					<!-- Transfer Funds Card -->
					<div class="bg-white shadow-md rounded-lg p-6">
						<h3 class="text-lg font-medium text-gray-900 mb-4">Transfer
							Funds</h3>

						<!-- Transfer Status Message -->
						<c:if test="${not empty transferMessage}">
							<div
								class="p-4 mb-4 text-sm rounded-lg ${transferSuccess ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}"
								role="alert">
								<c:out value="${transferMessage}" />
							</div>
						</c:if>

						<form
							action="${pageContext.request.contextPath}/customer/transfer"
							method="post">
							<div class="space-y-4">
								<div>
									<label for="receiverAccountNo"
										class="block text-sm font-medium text-gray-700">Recipient's
										Account Number</label> <input type="number" name="receiverAccountNo"
										id="receiverAccountNo" required
										class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
								</div>
								<div>
									<label for="amount"
										class="block text-sm font-medium text-gray-700">Amount</label>
									<input type="number" name="amount" id="amount" step="0.01"
										required
										class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
								</div>
								<button type="submit"
									class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700">
									Transfer</button>
							</div>
						</form>
					</div>
				</div>

				<!-- Right Column: Transaction History -->
				<div class="lg:col-span-2">
					<div class="bg-white shadow-md rounded-lg">
						<div class="px-6 py-4 border-b">
							<h3 class="text-lg font-medium text-gray-900">Passbook -
								Transaction History</h3>
						</div>
						<div class="overflow-x-auto">
							<table class="min-w-full divide-y divide-gray-200">
								<thead class="bg-gray-50">
									<tr>
										<th
											class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date</th>
										<th
											class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Description</th>
										<th
											class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">Amount</th>
										<th
											class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">Balance
											After</th>
									</tr>
								</thead>
								<tbody class="bg-white divide-y divide-gray-200">
									<c:choose>
										<c:when test="${not empty transactionHistory}">
											<c:forEach var="tx" items="${transactionHistory}">
												<tr>
													<td
														class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
														<fmt:formatDate value="${tx.tDate}"
															pattern="dd MMM yyyy, hh:mm a" />
													</td>
													<td
														class="px-6 py-4 whitespace-nowrap text-sm text-gray-800"><c:out
															value="${tx.description}" /></td>
													<td
														class="px-6 py-4 whitespace-nowrap text-sm text-right font-medium">
														<c:choose>
															<c:when test="${tx.type == 'CREDIT'}">
																<span class="text-green-600">+<fmt:formatNumber
																		value="${tx.amount}" type="currency" /></span>
															</c:when>
															<c:otherwise>
																<span class="text-red-600">-<fmt:formatNumber
																		value="${tx.amount}" type="currency" /></span>
															</c:otherwise>
														</c:choose>
													</td>
													<td
														class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-right">
														<fmt:formatNumber value="${tx.balanceAfterTransaction}"
															type="currency" />
													</td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="4" class="px-6 py-4 text-center text-gray-500">No
													transactions found.</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>

			</div>
		</div>
	</main>

</body>
</html>
