package com.aurionpro.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Get the current session, if it exists.
		HttpSession session = request.getSession(false);

		// 2. Check if a session actually exists.
		if (session != null) {
			// Invalidate the session, which removes all attributes (like the "user"
			// object).
			session.invalidate();
		}

		// 3. Redirect the user back to the login page.
		// We add a parameter to show a "Logged out successfully" message.
		response.sendRedirect(request.getContextPath() + "/login?logout=true");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
