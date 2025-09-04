package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dao.EmployeeDAO;
import com.aurionpro.dao.UserDao;
import com.aurionpro.model.Holiday;
import com.aurionpro.model.User;

public class UserService {

	private UserDao userDao;
	private EmployeeDAO empDao;

	public UserService() {
		this.userDao = new UserDao();
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
}
