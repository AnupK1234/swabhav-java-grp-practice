package com.aurionpro.controller.customer;

import com.aurionpro.model.User;
import com.aurionpro.service.UserService;
import com.aurionpro.Dao.UserDao; 

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/customer/change-password")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
    private UserDao userDao; // Add UserDao to fetch the updated user

	@Override
	public void init() {
		this.userDao = (UserDao) getServletContext().getAttribute("userDao");
		this.userService = new UserService(userDao);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		request.getRequestDispatcher("/customer/change_password.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		User currentUser = (User) session.getAttribute("user");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("message", "Error: New passwords do not match.");
			request.getRequestDispatcher("/customer/change_password.jsp").forward(request, response);
			return;
		}

		try {
			String resultMessage = userService.updateUserPassword(currentUser.getId(), oldPassword, newPassword);

			if (resultMessage.startsWith("Success")) {
                // 1. Fetch the updated user object from the database.
                User updatedUser = userDao.getUserById(currentUser.getId());
                
                // 2. Replace the old user object in the session with the new one.
                session.setAttribute("user", updatedUser); 
				
                session.setAttribute("message", "Password updated successfully!");
				response.sendRedirect(request.getContextPath() + "/customer/dashboard");
			} else {
				request.setAttribute("message", resultMessage);
				request.getRequestDispatcher("/customer/change_password.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			throw new ServletException("Database error during password update.", e);
		}
	}
}
