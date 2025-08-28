<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Change Password</title>
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
<body class="bg-gray-100 flex items-center justify-center h-screen">

	<div class="w-full max-w-md bg-white rounded-lg shadow-md p-8">
		<h2 class="text-2xl font-bold text-center text-gray-800 mb-2">Update
			Your Password</h2>
		<p class="text-center text-gray-500 mb-8">
			<c:choose>
				<c:when test="${param.force == 'true'}">
                    For security, you must change your temporary password before proceeding.
                </c:when>
				<c:otherwise>
                    Enter your old and new password below.
                </c:otherwise>
			</c:choose>
		</p>

		<!-- Change Password Form -->
		<form
			action="${pageContext.request.contextPath}/customer/change-password"
			method="post">

			<!-- Error/Success Message Display -->
			<c:if test="${not empty message}">
				<div
					class="p-4 mb-4 text-sm rounded-lg ${message.startsWith('Success') ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}"
					role="alert">
					<span class="font-medium"><c:out value="${message}" /></span>
				</div>
			</c:if>

			<!-- Old Password Input -->
			<div class="mb-4">
				<label for="oldPassword"
					class="block text-gray-700 text-sm font-bold mb-2">Old
					Password</label> <input type="password" id="oldPassword" name="oldPassword"
					required
					class="shadow-sm appearance-none border rounded-md w-full py-3 px-4 text-gray-700 leading-tight focus:outline-none focus:ring-2 focus:ring-blue-500">
			</div>

			<!-- New Password Input -->
			<div class="mb-4">
				<label for="newPassword"
					class="block text-gray-700 text-sm font-bold mb-2">New
					Password</label> <input type="password" id="newPassword" name="newPassword"
					required
					class="shadow-sm appearance-none border rounded-md w-full py-3 px-4 text-gray-700 leading-tight focus:outline-none focus:ring-2 focus:ring-blue-500">
			</div>

			<!-- Confirm New Password Input -->
			<div class="mb-6">
				<label for="confirmPassword"
					class="block text-gray-700 text-sm font-bold mb-2">Confirm
					New Password</label> <input type="password" id="confirmPassword"
					name="confirmPassword" required
					class="shadow-sm appearance-none border rounded-md w-full py-3 px-4 text-gray-700 leading-tight focus:outline-none focus:ring-2 focus:ring-blue-500">
			</div>

			<!-- Submit Button -->
			<div class="flex items-center justify-between">
				<button type="submit"
					class="w-full bg-blue-600 hover:bg-blue-700 text-white font-bold py-3 px-4 rounded-md focus:outline-none focus:shadow-outline transition duration-150 ease-in-out">
					Update Password</button>
			</div>
		</form>
	</div>

</body>
</html>
