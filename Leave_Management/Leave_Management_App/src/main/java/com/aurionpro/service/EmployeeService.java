package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dao.EmployeeDao;
import com.aurionpro.dao.UserDao;
import com.aurionpro.model.Holiday;
import com.aurionpro.model.User;

public class EmployeeService {

	private UserDao userDao;
	private EmployeeDao empDao;

	public EmployeeService() {
		this.userDao = new UserDao();
		this.empDao = new EmployeeDao();
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
	public void updateLeaveBalance(int userId, int newBalance) {
	    empDao.updateLeaveBalance(userId, newBalance);
	}

	public boolean isUsernameTakenByOtherUser(String username, int userIdToExclude) {
	    return empDao.isUsernameTakenByOtherUser(username, userIdToExclude);
	}

	public User getEmployeeManager(int managerId) {
	    return empDao.getEmployeeManager(managerId);
	}


}
