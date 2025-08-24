package com.aurionpro.service;

import java.sql.SQLException;

import com.aurionpro.Dao.UserDao;
import com.aurionpro.model.User;
import com.bank.misc.PasswordHasher;

public class UserService {

	private UserDao userDao;
	private PasswordHasher passwordHasher;

	// Constructor injection (better for testing)
	public UserService(UserDao userDao) {
		this.userDao = userDao;
		this.passwordHasher = new PasswordHasher();

	}

	public User updateUserProfile(int userId, String newFirstName, String newLastName) throws SQLException {
		// 1. Basic validation
		if (newFirstName == null || newFirstName.trim().isEmpty() || newLastName == null
				|| newLastName.trim().isEmpty()) {
			return null; // Invalid input
		}

		// 2. Fetch the existing user
		User existingUser = userDao.getUserById(userId);
		if (existingUser == null) {
			return null;
		}

		// 3. Update fields
		existingUser.setFirstName(newFirstName);
		existingUser.setLastName(newLastName);

		// 4. Save changes via DAO
		boolean success = userDao.updateUserDetails(existingUser);

		return success ? existingUser : null;
	}

	public boolean updateUserPassword(int userId, String oldPassword, String newPassword) throws SQLException {
		User user = userDao.getUserById(userId);
		if (user == null)
			return false;

		if (!passwordHasher.verifyPassword(oldPassword, user.getPassword())) {
			return false; // Wrong old password
		}

		String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
		if (!newPassword.matches(passwordPattern)) {
			throw new IllegalArgumentException("Password must be at least 8 characters long, "
					+ "contain one uppercase, one lowercase, one number, and one special character.");
		}
		String hashedNewPassword = passwordHasher.hashPassword(newPassword);

		return userDao.updateUserPassword(userId, hashedNewPassword);
	}
}
