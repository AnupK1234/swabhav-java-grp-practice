package com.aurionpro.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Static authentication check
		if ("admin".equals(username) && "admin123".equals(password)) {
			// Get the session object
			HttpSession session = request.getSession();

			// Set a session attribute to indicate the user is logged in
			session.setAttribute("loggedInUser", username);

			// Redirect to the main employee list page
			response.sendRedirect("EmployeeControllerServlet?command=LIST");
		} else {
			// Authentication failed
			request.setAttribute("error", "Invalid username or password.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
