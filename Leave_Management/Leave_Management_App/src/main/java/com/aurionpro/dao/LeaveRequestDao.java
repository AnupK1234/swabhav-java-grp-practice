package com.aurionpro.dao;

import com.aurionpro.model.LeaveRequest;
import com.aurionpro.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	public boolean rejectLeaveRequest(int leaveId, String reason) {
	    String sql = "UPDATE leave_requests SET status = 'REJECTED', rejection_reason = ? WHERE id = ?";
	    try (Connection conn = DatabaseUtil.getConnection(); 
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, reason);
	        stmt.setInt(2, leaveId);
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
				req.setRejectionReason(rs.getString("rejection_reason"));
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
	
	public int getTotalLeaveDaysInMonth(int userId, int year, int month) {
        String sql = "SELECT SUM(DATEDIFF(end_date, start_date) + 1) FROM leave_requests " +
                     "WHERE user_id = ? AND (status = 'APPROVED' OR status = 'PENDING') " +
                     "AND YEAR(start_date) = ? AND MONTH(start_date) = ?";
        int totalDays = 0;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            stmt.setInt(2, year);
            stmt.setInt(3, month);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalDays = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalDays;
    }

	public int countPendingRequests() throws SQLException {
		String sql = "SELECT COUNT(*) FROM leave_requests WHERE status = 'PENDING'";
		try (Connection conn = DatabaseUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			if (rs.next())
				return rs.getInt(1);
		}
		return 0;
	}

	public List<LeaveRequest> findRecentPendingRequests(int limit) throws SQLException {
		List<LeaveRequest> list = new ArrayList<>();
		String sql = "SELECT lr.*, u.first_name, u.last_name "
				+ "FROM leave_requests lr JOIN users u ON lr.user_id = u.id "
				+ "WHERE lr.status = 'PENDING' ORDER BY lr.created_at DESC LIMIT ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, limit);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					LeaveRequest req = new LeaveRequest();
					req.setId(rs.getInt("id"));
					req.setEmployeeFirstName(rs.getString("first_name"));
					req.setEmployeeLastName(rs.getString("last_name"));
					req.setStartDate(rs.getDate("start_date"));
					req.setEndDate(rs.getDate("end_date"));
					req.setReason(rs.getString("reason"));
					req.setStatus(rs.getString("status"));
					list.add(req);
				}
			}
		}
		return list;
	}
	public List<LeaveRequest> findRecentPendingRequestsByManager(int managerId, int limit) {
        List<LeaveRequest> requests = new ArrayList<>();
        String sql = "SELECT lr.*, u.first_name, u.last_name FROM leave_requests lr " +
                     "JOIN users u ON lr.user_id = u.id " +
                     "WHERE u.manager_id = ? AND lr.status = 'PENDING' " +
                     "ORDER BY lr.created_at DESC LIMIT ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, managerId);
            stmt.setInt(2, limit);
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
