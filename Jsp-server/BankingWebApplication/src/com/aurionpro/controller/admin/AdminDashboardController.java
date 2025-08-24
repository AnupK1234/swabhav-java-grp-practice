package com.aurionpro.controller.admin;

import com.aurionpro.model.Role;
import com.aurionpro.model.User;
import com.aurionpro.service.AdminService; // Using the AdminService you created
import com.aurionpro.Dao.UserDao; // Assuming AdminService needs this in its constructor

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet controller for the Admin Dashboard.
 * Handles displaying all customer accounts.
 */
@WebServlet("/admin/dashboard")
public class AdminDashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminService adminService;

    @Override
    public void init() {
        // This assumes you have a way to get a UserDao instance.
        // For simplicity, we'll create one here. In a larger app, you'd use a more robust method.
        UserDao userDao = (UserDao) getServletContext().getAttribute("userDao");
        this.adminService = new AdminService(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // --- SECURITY CHECK ---
        // 1. Is there a logged-in user?
        // 2. Is that user an ADMIN?
        if (session == null || session.getAttribute("user") == null || ((User) session.getAttribute("user")).getRole() != Role.ADMIN) {
            // If not, send an "Access Denied" error or redirect to login
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
            return;
        }
        try {
            // --- DATA FETCHING ---
            // Use the AdminService to get the list of all customers
            List<User> customerList = adminService.getAllCustomer();

            // --- FORWARD TO VIEW ---
            // Set the customer list as an attribute so the JSP can access it
            request.setAttribute("customerList", customerList);

            // Forward the request to the JSP page for display
            request.getRequestDispatcher("/WEB-INF/admin/dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            // Handle potential database errors
            throw new ServletException("Database error while fetching customers for admin dashboard.", e);
        }
    }
}
