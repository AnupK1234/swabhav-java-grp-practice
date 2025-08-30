package com.aurionpro.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * HomeController - Main entry point for the Employee Leave Management System
 * Handles the home page rendering and basic navigation
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		// Check if user is already logged in and redirect to appropriate dashboard
		if (session != null && session.getAttribute("userType") != null) {
			String userType = (String) session.getAttribute("userType");

			switch (userType.toLowerCase()) {
			case "admin":
				response.sendRedirect(request.getContextPath() + "/admin/dashboard");
				return;
			case "manager":
				response.sendRedirect(request.getContextPath() + "/manager/dashboard");
				return;
			case "employee":
				response.sendRedirect(request.getContextPath() + "/employee/dashboard");
				return;
			}
		}

		// If not logged in, show the home page
		request.setAttribute("pageTitle", "Employee Leave Management System");
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests (for login form submission)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("login".equals(action)) {
			// Redirect to login controller for authentication
			response.sendRedirect(request.getContextPath() + "/auth/login");
		} else {
			// Default behavior - show home page
			doGet(request, response);
		}
	}
}