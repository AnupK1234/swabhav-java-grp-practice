package com.aurionpro.service;

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

	public boolean rejectLeave(int leaveId) {
		return leaveRequestDao.rejectLeaveRequest(leaveId);
	}
}
