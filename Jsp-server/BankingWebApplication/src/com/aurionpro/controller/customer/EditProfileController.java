package com.aurionpro.controller.customer;

import java.io.IOException;
import java.sql.SQLException;

import com.aurionpro.Dao.UserDao;
import com.aurionpro.model.Role;
import com.aurionpro.model.User;
import com.aurionpro.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet controller for handling customer profile updates.
 */
@WebServlet("/customer/edit-profile")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	@Override
	public void init() {
		// Initialize the UserService
		this.userService = new UserService(new UserDao());
	}

	/**
	 * Displays the edit profile page.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		// Security Check: Only customers can edit their profile
		if (session == null || session.getAttribute("user") == null
				|| ((User) session.getAttribute("user")).getRole() != Role.CUSTOMER) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied.");
			return;
		}

		// Forward to the JSP form
		request.getRequestDispatcher("/WEB-INF/customer/edit_profile.jsp").forward(request, response);
	}

	/**
	 * Processes the submission of the edit profile form.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		// Security Check
		if (session == null || session.getAttribute("user") == null
				|| ((User) session.getAttribute("user")).getRole() != Role.CUSTOMER) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied.");
			return;
		}

		// 1. Get current user and form data
		User currentUser = (User) session.getAttribute("user");
		String newFirstName = request.getParameter("firstName");
		String newLastName = request.getParameter("lastName");

		// 2. Call the service to update the profile
		User updatedUser;
		try {
			updatedUser = userService.updateUserProfile(currentUser.getId(), newFirstName, newLastName);
			if (updatedUser != null) {
				// CRITICAL STEP: Update the user object in the session
				session.setAttribute("user", updatedUser);
				session.setAttribute("message", "Profile updated successfully!");
			} else {
				session.setAttribute("error", "Error: Could not update profile. Please ensure names are not empty.");
			}

			// 4. Redirect back to the main customer dashboard
			response.sendRedirect(request.getContextPath() + "/customer/dashboard");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 3. Check the result and update the session
}
