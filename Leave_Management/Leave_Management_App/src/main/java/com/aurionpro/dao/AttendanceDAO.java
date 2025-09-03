package com.aurionpro.dao;

import java.sql.Connection;
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
}