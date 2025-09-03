package com.aurionpro.controller;

import java.io.IOException;
import com.aurionpro.model.User;
import com.aurionpro.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthService authService;

	@Override
	public void init() {
		authService = new AuthService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = authService.authenticate(email, password);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("success_toast", "Login successful. Welcome back!");

			switch (user.getRole().toUpperCase()) {
			case "ADMIN":
				response.sendRedirect(request.getContextPath() + "/admin/dashboard");
				break;
			case "MANAGER":
				response.sendRedirect(request.getContextPath() + "/manager/dashboard");
				break;
			case "EMPLOYEE":
				response.sendRedirect(request.getContextPath() + "/employee/dashboard");
				break;
			default:
				response.sendRedirect(request.getContextPath() + "/home");
				break;
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("error_toast", "Invalid email or password. Please try again.");
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}
}
