package com.aurionpro.controller;

import java.io.IOException;

import com.aurionpro.Dao.UserDao;
import com.aurionpro.model.Role;
import com.aurionpro.model.User;
import com.aurionpro.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthService authService;

	@Override
	public void init() {
		UserDao userDao = (UserDao) getServletContext().getAttribute("userDao");
		authService = new AuthService(userDao);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			User user = authService.authenticate(username, password);

			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(5 * 60);

				// Redirect based on role
				if (user.getRole() == Role.ADMIN) {
					response.sendRedirect(request.getContextPath() + "/admin/AdminDashboard");
				} else if (user.getRole() == Role.CUSTOMER) {
					if (user.isForcePasswordChange()) {
						response.sendRedirect(request.getContextPath() + "/customer/change-password?force=true");
					} else {
						response.sendRedirect(request.getContextPath() + "/customer/dashboard");
					}

				}
			} else {
				request.setAttribute("error", "Invalid credentials!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException("Error during authentication", e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
