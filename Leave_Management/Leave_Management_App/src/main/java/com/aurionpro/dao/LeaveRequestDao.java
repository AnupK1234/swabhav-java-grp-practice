package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.aurionpro.model.LeaveRequest;
import com.aurionpro.util.DatabaseUtil;

public class LeaveRequestDao {

	public List<LeaveRequest> findPendingRequestsByManager(int managerId) {
		List<LeaveRequest> requests = new ArrayList<>();
		String sql = "SELECT lr.*, u.first_name, u.last_name FROM leave_requests lr "
				+ "JOIN users u ON lr.user_id = u.id " + "WHERE u.manager_id = ? AND lr.status = 'PENDING'";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, managerId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				LeaveRequest req = new LeaveRequest();
				req.setId(rs.getInt("id"));
				req.setUserId(rs.getInt("user_id"));
				req.setStartDate(rs.getDate("start_date"));
				req.setEndDate(rs.getDate("end_date"));
				req.setReason(rs.getString("reason"));
				req.setStatus(rs.getString("status"));
				req.setEmployeeFirstName(rs.getString("first_name"));
				req.setEmployeeLastName(rs.getString("last_name"));
				requests.add(req);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	public boolean approveLeaveRequest(int leaveId) {
		String updateStatusSql = "UPDATE leave_requests SET status = 'APPROVED' WHERE id = ?";
		String deductBalanceSql = "UPDATE users u " + "JOIN leave_requests lr ON u.id = lr.user_id "
				+ "SET u.leave_balance = u.leave_balance - (DATEDIFF(lr.end_date, lr.start_date) + 1) "
				+ "WHERE lr.id = ?";

		Connection conn = null;
		try {
			conn = DatabaseUtil.getConnection();
			conn.setAutoCommit(false);

			try (PreparedStatement stmt1 = conn.prepareStatement(updateStatusSql)) {
				stmt1.setInt(1, leaveId);
				stmt1.executeUpdate();
			}

			try (PreparedStatement stmt2 = conn.prepareStatement(deductBalanceSql)) {
				stmt2.setInt(1, leaveId);
				stmt2.executeUpdate();
			}

			conn.commit();
			return true;

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean rejectLeaveRequest(int leaveId) {
		String sql = "UPDATE leave_requests SET status = 'REJECTED' WHERE id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, leaveId);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<LeaveRequest> findLeaveHistoryByUserId(int userId) {
		List<LeaveRequest> history = new ArrayList<>();
		String sql = "SELECT * FROM leave_requests WHERE user_id = ? ORDER BY start_date DESC";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				LeaveRequest req = new LeaveRequest();
				req.setId(rs.getInt("id"));
				req.setStartDate(rs.getDate("start_date"));
				req.setEndDate(rs.getDate("end_date"));
				req.setReason(rs.getString("reason"));
				req.setStatus(rs.getString("status"));
				history.add(req);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return history;
	}

	public boolean applyForLeave(LeaveRequest request) {
		String sql = "INSERT INTO leave_requests (user_id, start_date, end_date, reason) VALUES (?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, request.getUserId());
			stmt.setDate(2, request.getStartDate());
			stmt.setDate(3, request.getEndDate());
			stmt.setString(4, request.getReason());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<LeaveRequest> findAllPendingRequestsOfManagers() {
		List<LeaveRequest> requests = new ArrayList<>();
		String sql = "SELECT lr.*, u.first_name, u.last_name FROM leave_requests lr "
				+ "JOIN users u ON lr.user_id = u.id " + "WHERE u.role='MANAGER' AND lr.status = 'PENDING'";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				LeaveRequest req = new LeaveRequest();
				req.setId(rs.getInt("id"));
				req.setUserId(rs.getInt("user_id"));
				req.setStartDate(rs.getDate("start_date"));
				req.setEndDate(rs.getDate("end_date"));
				req.setReason(rs.getString("reason"));
				req.setStatus(rs.getString("status"));
				req.setEmployeeFirstName(rs.getString("first_name"));
				req.setEmployeeLastName(rs.getString("last_name"));
				requests.add(req);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requests;
	}
}
