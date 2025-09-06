package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dao.EmployeeDAO;
import com.aurionpro.dao.UserDao;
import com.aurionpro.model.Holiday;
import com.aurionpro.model.User;

public class UserService {

	private UserDao userDao;
	private EmployeeDAO empDao;
	private AuthService authService;

	public UserService() {
		this.userDao = new UserDao();
		this.authService = new AuthService();
	}

	public List<User> getEmployeesByManager(int managerId) {
		return userDao.findEmployeesByManagerId(managerId);
	}

	public boolean updateUserProfile(User userToUpdate, String originalEmail) {
		if (!userToUpdate.getEmail().equalsIgnoreCase(originalEmail)) {
			if (userDao.isEmailTakenByOtherUser(userToUpdate.getEmail(), userToUpdate.getId())) {
				return false;
			}
		}
		return userDao.updateUserProfile(userToUpdate);
	}

	public List<Holiday> getEmpHoliday() {
		return empDao.getHolidaysForYear(2025);
	}

	public int getLeaveBalance(int id) {
		return userDao.getLeaveBalance(id);
	}

	public List<User> getAllEmployees() {
		return userDao.findAllEmployees();
	}

	public User findByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	public void updatePassword(String email, String password) {
		String hashedPassword = authService.hashPassword(password);
		userDao.updatePassword(email, hashedPassword);
	}
}
