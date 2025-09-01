package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.Holiday;
import com.aurionpro.model.User;
import com.aurionpro.util.DatabaseUtil;

public class EmployeeDao {

	public int getLeaveBalance(int userId) {
		String sql = "SELECT leave_balance FROM users WHERE id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("leave_balance");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
		// return -1 if user not found or error
	}

	public void updateLeaveBalance(int userId, int newBalance) {
		String sql = "UPDATE users SET leave_balance = ? WHERE id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, newBalance);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean updateUserProfile(User user) {
		String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmail());
			stmt.setInt(4, user.getId());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isEmailTakenByOtherUser(String email, int userIdToExclude) {
		String sql = "SELECT 1 FROM users WHERE email = ? AND id != ? LIMIT 1";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, email);
			stmt.setInt(2, userIdToExclude);

			try (ResultSet rs = stmt.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public boolean isUsernameTakenByOtherUser(String username, int userIdToExclude) {
		String sql = "SELECT 1 FROM users WHERE username = ? AND id != ? LIMIT 1";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, username);
			stmt.setInt(2, userIdToExclude);

			try (ResultSet rs = stmt.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public User getEmployeeManager(int managerId) {
		String sql = "SELECT * FROM users WHERE id = ?";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, managerId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return mapRowToUser(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Holiday> getHolidaysForYear(int year) {
		List<Holiday> holidays = new ArrayList<>();
		String sql = "SELECT * FROM holidays WHERE YEAR(holiday_date) = ? ORDER BY holiday_date";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, year);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Holiday holiday = new Holiday();
					holiday.setId(rs.getInt("id"));
					holiday.setHolidayDate(rs.getDate("holiday_date").toLocalDate());
					holiday.setTitle(rs.getString("title"));

					// filter out Saturdays and Sundays
					DayOfWeek day = holiday.getHolidayDate().getDayOfWeek();
					if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
						holidays.add(holiday);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return holidays;
	}

	private User mapRowToUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("email"));
		user.setDob(rs.getDate("dob"));
		user.setRole(rs.getString("role"));
		user.setManagerId((Integer) rs.getObject("manager_id"));
		user.setLeaveBalance(rs.getInt("leave_balance"));
		return user;
	}

}
