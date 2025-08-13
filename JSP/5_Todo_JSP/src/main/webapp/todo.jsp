<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
HttpSession sessionObj = request.getSession();
List<String> tasks = (List<String>) sessionObj.getAttribute("tasks");

if (tasks == null) {
	tasks = new ArrayList<>();
	sessionObj.setAttribute("tasks", tasks);
}

String newTask = request.getParameter("task");
if (newTask != null && !newTask.trim().isEmpty()) {
	tasks.add(newTask.trim());
}

String deleteIndexStr = request.getParameter("delete");
if (deleteIndexStr != null) {
	try {
		int deleteIndex = Integer.parseInt(deleteIndexStr);
		if (deleteIndex >= 0 && deleteIndex < tasks.size()) {
	tasks.remove(deleteIndex);
		}
	} catch (NumberFormatException e) {
	}
}

request.setAttribute("tasks", tasks);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP To-Do List with JSTL</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f7fc;
	display: flex;
	justify-content: center;
	margin-top: 50px;
}

.todo-container {
	background: #fff;
	padding: 20px 30px;
	border-radius: 12px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
	width: 350px;
}

h2 {
	text-align: center;
	color: #333;
}

form {
	display: flex;
	margin-bottom: 15px;
}

input[type="text"] {
	flex: 1;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 6px;
}

button {
	margin-left: 8px;
	padding: 8px 12px;
	background: #4CAF50;
	color: white;
	border: none;
	border-radius: 6px;
	cursor: pointer;
}

button:hover {
	background: #45a049;
}

ul {
	list-style: none;
	padding-left: 0;
}

li {
	background: #f9f9f9;
	margin-bottom: 8px;
	padding: 8px 12px;
	border-radius: 6px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

a.delete-btn {
	text-decoration: none;
	color: #ff4d4d;
	font-weight: bold;
}

a.delete-btn:hover {
	color: #d93636;
}
</style>
</head>
<body>
	<div class="todo-container">
		<h2>My To-Do List</h2>

		<!-- Add Task Form -->
		<form method="post">
			<input type="text" name="task" placeholder="Enter new task" required>
			<button type="submit">Add</button>
		</form>

		<!-- Task List -->
		<c:choose>
			<c:when test="${empty tasks}">
				<p>No tasks yet.</p>
			</c:when>
			<c:otherwise>
				<ul>
					<c:forEach var="task" items="${tasks}" varStatus="status">
						<li><span>${task}</span> <a class="delete-btn"
							href="?delete=${status.index}">Delete</a></li>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
