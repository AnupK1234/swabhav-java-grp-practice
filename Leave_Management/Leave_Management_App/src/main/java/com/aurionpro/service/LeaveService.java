package com.aurionpro.service;

import java.time.LocalDate;
import java.util.List;
import com.aurionpro.dao.LeaveRequestDao;
import com.aurionpro.model.LeaveRequest;

public class LeaveService {

	private LeaveRequestDao leaveRequestDao;

	public LeaveService() {
		this.leaveRequestDao = new LeaveRequestDao();
	}

	public List<LeaveRequest> getPendingRequestsForManager(int managerId) {
		return leaveRequestDao.findPendingRequestsByManager(managerId);
	}

	public List<LeaveRequest> getPendingRequestsForAllManager() {
		return leaveRequestDao.findAllPendingRequestsOfManagers();
	}

	public List<LeaveRequest> getLeaveHistoryForUser(int userId) {
		return leaveRequestDao.findLeaveHistoryByUserId(userId);
	}

	public boolean applyForLeave(LeaveRequest request) {
		return leaveRequestDao.applyForLeave(request);
	}

	public boolean approveLeave(int leaveId) {
		return leaveRequestDao.approveLeaveRequest(leaveId);
	}

	public boolean rejectLeave(int leaveId, String reason) {
	    return leaveRequestDao.rejectLeaveRequest(leaveId, reason);
	}
	 public List<LeaveRequest> getRecentPendingRequestsForManager(int managerId) {
	        final int DASHBOARD_REQUEST_LIMIT = 5; // Business rule: Show max 5 requests on the dashboard
	        return leaveRequestDao.findRecentPendingRequestsByManager(managerId, DASHBOARD_REQUEST_LIMIT);
	    }
	 public boolean canApplyForLeaveThisMonth(int userId, LocalDate newLeaveStartDate, LocalDate newLeaveEndDate) {
	        final int MAX_LEAVE_DAYS_PER_MONTH = 2; // The business rule: max 2 days per month

	        // Calculate the duration of the new leave request
	        long newLeaveDuration = java.time.temporal.ChronoUnit.DAYS.between(newLeaveStartDate, newLeaveEndDate) + 1;

	        // Get the total number of leave days ALREADY taken or pending in that month
	        int daysAlreadyTaken = leaveRequestDao.getTotalLeaveDaysInMonth(userId, newLeaveStartDate.getYear(), newLeaveStartDate.getMonthValue());

	        // Check if the sum of already taken days and the new request's duration exceeds the limit
	        return (daysAlreadyTaken + newLeaveDuration) <= MAX_LEAVE_DAYS_PER_MONTH;
	    }
}
