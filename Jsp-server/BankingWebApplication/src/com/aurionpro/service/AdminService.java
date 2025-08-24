package com.aurionpro.service;

import java.sql.SQLException;
import java.util.List;

import com.aurionpro.Dao.TransactionDao;
import com.aurionpro.Dao.UserDao;
import com.aurionpro.model.Role;
import com.aurionpro.model.Transaction;
import com.aurionpro.model.User;
import com.bank.misc.PasswordHasher;

public class AdminService {

	private UserDao userDao;
	private TransactionDao transactionDao;

	public AdminService(UserDao userDao, TransactionDao transactionDao) {
		this.userDao = userDao;
		this.transactionDao = transactionDao;
	}

	public AdminService(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getAllCustomer() throws SQLException {
		return userDao.getCustomer(Role.CUSTOMER);
	}

	public boolean createUser(String username, String plainPassword, String firstName, String lastName,
			int accountNumber, double balance) throws SQLException {
		// 1. Validate password strength
		String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
		if (!plainPassword.matches(passwordPattern)) {
			throw new IllegalArgumentException("Password must be at least 8 characters long, "
					+ "contain one uppercase, one lowercase, one number, and one special character.");
		}
		// 1. Hash the password before saving
		PasswordHasher hasher = new PasswordHasher();
		String hashedPassword = hasher.hashPassword(plainPassword);

		// 2. Create a new User object
		User newUser = new User(firstName, username, lastName, balance, accountNumber, hashedPassword, Role.CUSTOMER);

		return userDao.addUser(newUser);
	}

	// get all the transaction in the bank
	public List<Transaction> getAllTransaction() throws SQLException {
		List<Transaction> allTransaction = transactionDao.getAllTransaction();
		return allTransaction;
	}
}
