package com.aurionpro.controller;

import com.aurionpro.Dao.TransactionDao;
import com.aurionpro.Dao.UserDao;
import com.aurionpro.model.User;
import com.aurionpro.service.TransactionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/customer/transfer")
public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionService transactionService;

	@Override
	public void init() {
		UserDao userDao = (UserDao) getServletContext().getAttribute("userDao");
		TransactionDao transactionDao = (TransactionDao) getServletContext().getAttribute("transactionDao");
		this.transactionService = new TransactionService(userDao, transactionDao);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Get the current session
		HttpSession session = request.getSession(false);

		// 2. SECURITY CHECK: Is the user logged in?
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// 3. Get the sender (logged-in user)
		User sender = (User) session.getAttribute("user");
		long senderAccountNumber = sender.getAccountNo();

		// 4. Get the form data
		String receiverAccountNoStr = request.getParameter("receiverAccountNo");
		String amountStr = request.getParameter("amount");

		long receiverAccountNumber;
		try {
			receiverAccountNumber = Long.parseLong(receiverAccountNoStr);
		} catch (NumberFormatException e) {
			request.setAttribute("transferMessage", "Error: Invalid recipient account number.");
			request.setAttribute("transferSuccess", false);
			request.getRequestDispatcher("/customer/dashboard").forward(request, response);
			return;
		}

		// 5. Call TransactionService
		try {
			String message = transactionService.transferFunds(senderAccountNumber, receiverAccountNumber, amountStr);

			// 6. Put the result in request scope
			request.setAttribute("transferMessage", message);
			request.setAttribute("transferSuccess", message.startsWith("Success"));

			// 7. Forward back to dashboard (to refresh balance & transactions)
			request.getRequestDispatcher("/customer/dashboard").forward(request, response);

		} catch (SQLException e) {
			throw new ServletException("Database error during transfer", e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    request.getRequestDispatcher("/WEB-INF/views/customer-dashboard.jsp").forward(request, response);
	}


}
