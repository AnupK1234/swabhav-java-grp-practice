package com.aurionpro.controller.admin;

import com.aurionpro.model.Role;
import com.aurionpro.model.Transaction;
import com.aurionpro.model.User;
import com.aurionpro.service.AdminService;
import com.aurionpro.Dao.TransactionDao;
import com.aurionpro.Dao.UserDao;

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
 * Servlet controller for the Admin to view all transactions.
 */
@WebServlet("/admin/transactions")
public class ViewTransactionsController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminService adminService;

    @Override
    public void init() {
    	UserDao userDao = (UserDao) getServletContext().getAttribute("userDao");
		TransactionDao transactionDao = (TransactionDao) getServletContext().getAttribute("transactionDao");
        this.adminService = new AdminService(userDao, transactionDao);
    }

    /**
     * Handles GET requests to the view all transactions page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // --- SECURITY CHECK ---
        if (session == null || session.getAttribute("user") == null || ((User) session.getAttribute("user")).getRole() != Role.ADMIN) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
            return;
        }

        try {
            // --- DATA FETCHING ---
            // Use the AdminService to get the list of all transactions
            List<Transaction> transactionList = adminService.getAllTransaction();

            // --- FORWARD TO VIEW ---
            request.setAttribute("transactionList", transactionList);
            request.getRequestDispatcher("/WEB-INF/admin/view_transactions.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Database error while fetching all transactions.", e);
        }
    }
}
