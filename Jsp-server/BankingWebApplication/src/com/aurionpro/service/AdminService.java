package com.aurionpro.service;

import java.sql.SQLException;
import java.util.List;

import com.aurionpro.Dao.TransactionDao;
import com.aurionpro.Dao.UserDao;
import com.aurionpro.misc.PasswordHasher;
import com.aurionpro.model.Role;
import com.aurionpro.model.Transaction;
import com.aurionpro.model.User;

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

	// Get all customers
	public List<User> getAllCustomer() throws SQLException {
		return userDao.getCustomer(Role.CUSTOMER);
	}

	// Create a new customer
	public String createUser(String username, String plainPassword, String firstName, String lastName,
			int accountNumber, double balance,Role role) throws SQLException {

		// 1. Validate password strength
		String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
		if (!plainPassword.matches(passwordPattern)) {
			// Return a user-friendly error message instead of throwing an exception
			return "Error: Password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a number, and a special character.";
		}

		// 2. Hash the password here in the service layer
		String hashedPassword = new PasswordHasher().hashPassword(plainPassword);

		// 3. Create a new User object using setters for clarity
		User newUser = new User();
		newUser.setUserName(username);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setAccountNo(accountNumber);
		newUser.setBalance(balance);
		newUser.setRole(role);
		newUser.setPassword(hashedPassword); // Set the hashed password

		// 4. Call DAO to save the user
		boolean success = userDao.addUser(newUser);

		if (success) {
			return "Success: Customer '" + username + "' created successfully!";
		} else {
			return "Error: Could not create customer. The username or account number might already exist.";
		}
	}

	// Get all transactions
	public List<Transaction> getAllTransaction() throws SQLException {
		return transactionDao.getAllTransactions();
	}
	// In AdminService.java
	public List<Transaction> getAllTransactions(String sortField, String sortOrder, String filterAccountNo) throws SQLException {
	    return transactionDao.getAllTransactions(sortField, sortOrder, filterAccountNo);
	}
}
