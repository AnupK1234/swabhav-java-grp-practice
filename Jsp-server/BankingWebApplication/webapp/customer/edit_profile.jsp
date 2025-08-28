<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
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
			<h1 class="text-2xl font-bold text-gray-900">
				Welcome,
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
			<div
				class="w-full max-w-lg mx-auto bg-white rounded-lg shadow-md p-8">
				<h2 class="text-2xl font-bold text-gray-800 mb-6">Edit Your
					Profile</h2>
                
                <!-- Status Message Display -->
                <c:if test="${not empty message}">
                    <div class="p-4 mb-4 text-sm rounded-lg ${message.startsWith('Success') ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}" role="alert">
                        <c:out value="${message}" />
                    </div>
                </c:if>

				<!-- Form to edit user profile -->
				<form
					action="${pageContext.request.contextPath}/customer/edit-profile"
					method="post">
					<div class="space-y-6">
						<!-- First Name -->
						<div>
							<label for="firstName"
								class="block text-sm font-medium text-gray-700">First
								Name</label> <input type="text" id="firstName" name="firstName" required
								value="<c:out value='${sessionScope.user.firstName}'/>"
								class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
						</div>

						<!-- Last Name -->
						<div>
							<label for="lastName"
								class="block text-sm font-medium text-gray-700">Last
								Name</label> <input type="text" id="lastName" name="lastName" required
								value="<c:out value='${sessionScope.user.lastName}'/>"
								class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
						</div>
					</div>

					<!-- Form Buttons -->
					<div class="mt-8 flex justify-end">
						<a href="${pageContext.request.contextPath}/customer/dashboard"
							class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded-md mr-4">
							Cancel </a>
						<button type="submit"
							class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-md focus:outline-none focus:shadow-outline">
							Save Changes</button>
					</div>
				</form>
                
                <div class="mt-6 border-t pt-6">
                    <a href="${pageContext.request.contextPath}/customer/change-password" class="text-sm font-medium text-blue-600 hover:text-blue-800">
                        Want to change your password?
                    </a>
                </div>
			</div>
		</div>
	</main>

</body>
</html>
