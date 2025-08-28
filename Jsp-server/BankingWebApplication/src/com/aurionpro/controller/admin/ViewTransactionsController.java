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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null
				|| ((User) session.getAttribute("user")).getRole() != Role.ADMIN) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied.");
			return;
		}

		try {
			String sortField = request.getParameter("sortField");
			String sortOrder = request.getParameter("sortOrder");
			String filterAccountNo = request.getParameter("filterAccountNo");

			List<Transaction> transactionList = adminService.getAllTransactions(sortField, sortOrder, filterAccountNo);

			// --- Set attributes for the JSP ---
			request.setAttribute("transactionList", transactionList);
			request.setAttribute("sortField", sortField);
			request.setAttribute("sortOrder", sortOrder);
			request.setAttribute("filterAccountNo", filterAccountNo);
			request.setAttribute("activePage", "transactions");

			request.getRequestDispatcher("/admin/view_transaction.jsp").forward(request, response);

		} catch (SQLException e) {
			throw new ServletException("Database error while fetching transactions.", e);
		}
	}
}
