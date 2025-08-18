package com.aurionpro.model;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/demo")
public class MvcDemoServlet extends HttpServlet {

	public MvcDemoServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Step 0: Add data
		String[] employees = { "Nilesh", "Rohit", "Virat", "Dhoni" };
		request.setAttribute("employee_list", employees);
		// Step 1: get request dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view_employees.jsp");
		// Step 2: forward the request to JSP
		dispatcher.forward(request, response);
	}
}
