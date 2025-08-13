<%@ page import="java.util.*" %>
<%
    // Retrieve the to-do list from session
    List<String> todoList = (List<String>) session.getAttribute("todoList");
    if (todoList == null) {
        todoList = new ArrayList<>();
        session.setAttribute("todoList", todoList);
    }

    // Handle adding new task
    String newTask = request.getParameter("task");
    if (newTask != null && !newTask.trim().isEmpty()) {
        todoList.add(newTask.trim());
    }

    // Handle deleting task
    String deleteIndexStr = request.getParameter("deleteIndex");
    if (deleteIndexStr != null) {
        try {
            int deleteIndex = Integer.parseInt(deleteIndexStr);
            if (deleteIndex >= 0 && deleteIndex < todoList.size()) {
                todoList.remove(deleteIndex);
            }
        } catch (NumberFormatException e) {
            // ignore invalid index
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>To-Do List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 500px;
            margin: auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 2px 6px rgba(0,0,0,0.2);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        input[type=text] {
            width: 75%;
            padding: 10px;
            margin-right: 5px;
        }
        button {
            padding: 10px 15px;
            cursor: pointer;
            background: #28a745;
            border: none;
            color: white;
            border-radius: 5px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            background: #eee;
            margin: 5px 0;
            padding: 8px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .delete-btn {
            background: #dc3545;
            padding: 5px 10px;
            border-radius: 5px;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>My To-Do List</h2>

    
    <form method="post" action="Todo.jsp">
        <input type="text" name="task" placeholder="Enter new task" />
        <button type="submit">Add</button>
    </form>

    <ul>
        <%
            for (int i = 0; i < todoList.size(); i++) {
        %>
            <li>
                <span><%= todoList.get(i) %></span>
                <form method="post" action="Todo.jsp" style="margin:0;">
                    <input type="hidden" name="deleteIndex" value="<%= i %>"/>
                    <button type="submit" class="delete-btn">Delete</button>
                </form>
            </li>
        <%
            }
        %>
    </ul>
</div>
</body>
</html>
