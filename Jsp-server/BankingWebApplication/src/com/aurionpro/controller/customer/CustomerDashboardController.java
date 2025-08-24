package com.aurionpro.controller.customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.aurionpro.model.Transaction;
import com.aurionpro.model.User;
import com.aurionpro.service.TransactionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Customer/DashBoard")
public class CustomerDashboardController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TransactionService transactionService;

	public void init() {
		this.transactionService = transactionService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);

		if (session == null || req.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		User user = (User) session.getAttribute("user");

		List<Transaction> transactions;
		try {
			transactions = transactionService.getTransactionHistory(user.getAccountNo());
			req.setAttribute("transactionHistory", transactions);
		    req.getRequestDispatcher("/WEB-INF/views/customer_dashboard.jsp").forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		super.doGet(req, resp);
	}

}
