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
	private UserDao userDao; 

	@Override
	public void init() {
		
		this.userDao = (UserDao) getServletContext().getAttribute("userDao");

		TransactionDao transactionDao = (TransactionDao) getServletContext().getAttribute("transactionDao");

		this.transactionService = new TransactionService(userDao, transactionDao);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		User sender = (User) session.getAttribute("user");
		long senderAccountNumber = sender.getAccountNo();
		String receiverAccountNoStr = request.getParameter("receiverAccountNo");
		String amountStr = request.getParameter("amount");

		try {
			long receiverAccountNumber = Long.parseLong(receiverAccountNoStr);
			String message = transactionService.transferFunds(senderAccountNumber, receiverAccountNumber, amountStr);

			request.setAttribute("transferMessage", message);
			request.setAttribute("transferSuccess", message.startsWith("Success"));

			if (message.startsWith("Success")) {

				User updatedSender = this.userDao.getUserByAccountNo(senderAccountNumber);
				session.setAttribute("user", updatedSender);
			}

		} catch (NumberFormatException e) {
			request.setAttribute("transferMessage", "Error: Invalid recipient account number.");
			request.setAttribute("transferSuccess", false);
		} catch (SQLException e) {
			request.setAttribute("transferMessage", "Error: A database error occurred during the transfer.");
			request.setAttribute("transferSuccess", false);
			e.printStackTrace();
		}

		// Forward to the dashboard controller to refresh all data
		request.getRequestDispatcher("/customer/dashboard").forward(request, response);
	}
}
