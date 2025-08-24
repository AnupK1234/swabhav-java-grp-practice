// src/main/java/com/aurionpro/controller/AddCustomerController.java
package com.aurionpro.controller.admin;

import com.aurionpro.model.Role;
import com.aurionpro.model.User;
import com.aurionpro.service.AdminService;
import com.aurionpro.Dao.TransactionDao;
import com.aurionpro.Dao.UserDao; // Assuming AdminService needs this

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/add-customer")
public class AddCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService;

	@Override
	public void init() {
		UserDao userDao = (UserDao) getServletContext().getAttribute("userDao");
		TransactionDao transactionDao = (TransactionDao) getServletContext().getAttribute("transactionDao");
		this.adminService = new AdminService(userDao);
	}

	/**
	 * Displays the "Add New Customer" form.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null
				|| ((User) session.getAttribute("user")).getRole() != Role.ADMIN) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied.");
			return;
		}

		// Forward to the JSP form
		request.getRequestDispatcher("/WEB-INF/admin/add_customer.jsp").forward(request, response);
	}

	/**
	 * Processes the submission of the "Add New Customer" form.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		// Security Check: Only admins can submit this form
		if (session == null || session.getAttribute("user") == null
				|| ((User) session.getAttribute("user")).getRole() != Role.ADMIN) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied.");
			return;
		}

		// 1. Get all the form parameters
		String username = request.getParameter("username");
		String plainPassword = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String balanceStr = request.getParameter("balance");

		// For simplicity, we can generate a random account number.
		// In a real bank, this would be a more complex, sequential number.
		int accountNumber = (int) (System.currentTimeMillis() / 1000);

		try {
			double balance = Double.parseDouble(balanceStr);

			// 2. Call the AdminService to create the user
			boolean success = adminService.createUser(username, plainPassword, firstName, lastName, accountNumber,
					balance);

			// 3. Set a message to show on the dashboard
			if (success) {
				session.setAttribute("message", "Customer '" + username + "' created successfully!");
			} else {
				session.setAttribute("error", "Error: Could not create customer. Username might already exist.");
			}

		} catch (NumberFormatException e) {
			session.setAttribute("error", "Error: Invalid initial balance.");
		} catch (IllegalArgumentException | SQLException e) {
			// Catches password validation errors or DB errors
			session.setAttribute("error", "Error: " + e.getMessage());
		}

		// 4. Redirect back to the main admin dashboard
		response.sendRedirect(request.getContextPath() + "/admin/dashboard");
	}
}
