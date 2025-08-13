package com.practice.model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Get or create todo list
        List<String> todoList = (List<String>) session.getAttribute("todoList");
        if (todoList == null) {
            todoList = new ArrayList<>();
            session.setAttribute("todoList", todoList);
        }

        // Add new task
        String task = request.getParameter("task");
        if (task != null && !task.trim().isEmpty()) {
            todoList.add(task.trim());
        }

        // Delete task
        String deleteIndexStr = request.getParameter("deleteIndex");
        if (deleteIndexStr != null) {
            try {
                int deleteIndex = Integer.parseInt(deleteIndexStr);
                if (deleteIndex >= 0 && deleteIndex < todoList.size()) {
                    todoList.remove(deleteIndex);
                }
            } catch (NumberFormatException ignored) {}
        }

        // Refresh page
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<String> todoList = (List<String>) session.getAttribute("todoList");
        if (todoList == null) {
            todoList = new ArrayList<>();
            session.setAttribute("todoList", todoList);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>To-Do List</title></head><body>");
        out.println("<h2>My To-Do List</h2>");

        // Add Task Form
        out.println("<form method='post' action='todo'>");
        out.println("Task: <input type='text' name='task' />");
        out.println("<input type='submit' value='Add' />");
        out.println("</form>");

        // Display Tasks
        out.println("<ul>");
        for (int i = 0; i < todoList.size(); i++) {
            out.println("<li>");
            out.println(todoList.get(i) + " ");
            out.println("<form method='post' action='todo' style='display:inline;'>");
            out.println("<input type='hidden' name='deleteIndex' value='" + i + "'/>");
            out.println("<input type='submit' value='Delete' />");
            out.println("</form>");
            out.println("</li>");
        }
        out.println("</ul>");

        out.println("</body></html>");
    }
}