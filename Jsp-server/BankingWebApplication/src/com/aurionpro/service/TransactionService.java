package com.aurionpro.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.aurionpro.Dao.TransactionDao;
import com.aurionpro.Dao.UserDao;
import com.aurionpro.model.Transaction;
import com.aurionpro.model.TransactionType;
import com.aurionpro.model.User;

public class TransactionService {

	private UserDao userDao;
	private TransactionDao transactionDao;

	public TransactionService(UserDao userDao, TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
		this.userDao = userDao;
	}

	public String transferFunds(long senderAccontNo, long receiverAccountNo, String amountStr) throws SQLException {
		double amount;
		try {
			amount = Double.parseDouble(amountStr);
		} catch (NumberFormatException e) {
			return "Invalid transfer amount";
		}
		if (amount <= 0) {
			return "Error! Amount cannot be less then 0rs";
		}
		if (senderAccontNo == receiverAccountNo) {
			return "Cant transfer to the same person";
		}
		User sender = userDao.getUserByAccountNo(senderAccontNo);
		User receiver = userDao.getUserByAccountNo(receiverAccountNo);

		if (sender == null || receiver == null) {
			return "Fill the Empty field";
		}

		if (sender.getBalance() < amount) {
			return "Cannot transfer this amount";
		}
		double senderBalance = sender.getBalance() - amount;
		double receiverBalance = receiver.getBalance() + amount;

		boolean senderUpdateSuccess = userDao.updateUserBalance(senderAccontNo, senderBalance);
		boolean receiverUpdateSuccess = userDao.updateUserBalance(receiverAccountNo, receiverBalance);

		if (senderUpdateSuccess && receiverUpdateSuccess) {
			// Create a debit record for the sender
			Transaction senderTransaction = new Transaction(senderAccontNo, senderBalance, TransactionType.DEBIT,
					amount, LocalDateTime.now(),
					"Transfer to " + receiver.getFirstName() + " " + receiver.getLastName());
			transactionDao.createTransaction(senderTransaction);

			// Create a credit record for the receiver
			Transaction receiverTransaction = new Transaction(receiverAccountNo, receiverBalance,
					TransactionType.CREDIT, amount, LocalDateTime.now(),
					"Transfer from " + sender.getFirstName() + " " + sender.getLastName());
			transactionDao.createTransaction(receiverTransaction);

			return "Success: Transfer of $" + amount + " completed successfully!";
		} else {
			return "Error: A database error occurred during the transfer.";
		}
	}

	public List<Transaction> getTransactionHistory(long accountNo) throws SQLException {
		if (userDao.getUserByAccountNo(accountNo) == null) {
			return null;
		}
		List<Transaction> transactions = transactionDao.getTransactionsByAccountNumber(accountNo);
		return transactions;
	}

}
