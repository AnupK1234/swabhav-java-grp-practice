	package com.aurionpro.service;
	
	import java.sql.SQLException;
	
	import com.aurionpro.Dao.UserDao;
	import com.aurionpro.misc.PasswordHasher;
	import com.aurionpro.model.User;
	
	public class UserService {
	
		private UserDao userDao;
		private PasswordHasher passwordHasher;
	
		public UserService(UserDao userDao) {
			this.userDao = userDao;
			this.passwordHasher = new PasswordHasher();
	
		}
	
		public User updateUserProfile(int userId, String newFirstName, String newLastName) throws SQLException {
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
	
		public String updateUserPassword(int userId, String oldPassword, String newPassword) throws SQLException {
			User user = userDao.getUserById(userId);
			if (user == null) {
				return "Error: User not found.";
			}
	
			// 1. Verify the old password
			if (!passwordHasher.verifyPassword(oldPassword, user.getPassword())) {
				return "Error: The old password you entered is incorrect.";
			}
	
			// 2. Validate the new password's complexity
			String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
			if (!newPassword.matches(passwordPattern)) {
				return "Error: New password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a number, and a special character.";
			}
	
			// 3. Hash and update the new password
			String hashedNewPassword = passwordHasher.hashPassword(newPassword);
			boolean passwordUpdated = userDao.updateUserPassword(userId, hashedNewPassword);
	
			if (passwordUpdated) {
				userDao.clearPasswordChangeFlag(userId);
				return "Success: Your password has been updated.";
			} else {
				return "Error: A database error occurred. Could not update password.";
			}
		}
	}
