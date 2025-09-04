package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.Attendance;
import com.aurionpro.util.DatabaseUtil;

public class AttendanceDAO {

	public boolean markAttendance(Attendance attendance) {
		String sql = "INSERT INTO attendance (user_id, date, status, marked_by) VALUES (?, ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE status = VALUES(status), marked_by = VALUES(marked_by)";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, attendance.getUserId());
			stmt.setDate(2, java.sql.Date.valueOf(attendance.getDate()));
			stmt.setString(3, attendance.getStatus());
			stmt.setString(4, attendance.getMarkedBy());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Attendance> findAttendanceForUser(int userId) {
		List<Attendance> records = new ArrayList<>();
		String sql = "SELECT * FROM attendance WHERE user_id = ?";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Attendance record = new Attendance();
					record.setId(rs.getInt("id"));
					record.setUserId(rs.getInt("user_id"));
					record.setDate(rs.getDate("date").toLocalDate());
					record.setStatus(rs.getString("status"));
					records.add(record);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

	public void markAllEmployeesPresent(Date holidayDate) throws SQLException {
		String getUsers = "SELECT id FROM users WHERE role IN ('EMPLOYEE', 'MANAGER')";
		String insertAttendance = "INSERT INTO attendance (user_id, date, status, marked_by) VALUES (?, ?, 'PRESENT', 'ADMIN')";

		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement psUsers = conn.prepareStatement(getUsers);
				ResultSet rs = psUsers.executeQuery()) {

			while (rs.next()) {
				int userId = rs.getInt("id");

				// Check if already marked
				if (!isAttendanceAlreadyMarked(conn, userId, holidayDate)) {
					try (PreparedStatement psInsert = conn.prepareStatement(insertAttendance)) {
						psInsert.setInt(1, userId);
						psInsert.setDate(2, holidayDate);
						psInsert.executeUpdate();
					}
				}
			}
		}
	}

	private boolean isAttendanceAlreadyMarked(Connection conn, int userId, Date date) throws SQLException {
		String sql = "SELECT 1 FROM attendance WHERE user_id = ? AND date = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setDate(2, date);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next(); // true if already exists
			}
		}
	}

}