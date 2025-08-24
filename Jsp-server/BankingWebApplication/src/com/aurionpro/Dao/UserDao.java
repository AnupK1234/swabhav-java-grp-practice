package com.aurionpro.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.aurionpro.model.Role;
import com.aurionpro.model.User;
import com.bank.misc.PasswordHasher;

public class UserDao {

	private DataSource dataSource;

	public UserDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public UserDao()
	{
	}

	// Get user by username
	public User getUserByUserName(String username) throws SQLException {
		String sql = "SELECT * FROM users WHERE userName = ?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapRowToUser(rs);
				}
			}
		}
		return null;
	}

	public User getUserByAccountNo(long accountNo) throws SQLException {
		String sql = "select * from users where accountNo=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setLong(1, accountNo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapRowToUser(rs);
				}
			}
		}
		return null;
	}

	public User getUserById(int id) throws SQLException {
		String sql = "Select * from users where id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapRowToUser(rs);
				}
			}
		}
		return null;
	}

	// List only customer
	public List<User> getCustomer(Role role) throws SQLException {
		List<User> customers = new ArrayList<>();
		String sql = "SELECT * FROM users WHERE role = ?";

		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, role.name());

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					customers.add(mapRowToUser(rs));
				}
			}
		}
		return customers;
	}

	// Add user (store hashed password)
	public boolean addUser(User user) throws SQLException {
		String sql = "INSERT INTO users (userName, password, firstName, lastName, accountNo, balance, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			PasswordHasher hasher = new PasswordHasher();
			String hashedPassword = hasher.hashPassword(user.getPassword());

			ps.setString(1, user.getUserName());
			ps.setString(2, hashedPassword);
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setInt(5, user.getAccountNo());
			ps.setDouble(6, user.getBalance());
			ps.setString(7, user.getRole().name());
			ps.executeUpdate();
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			}
			return false;
		}
	}

	// update balance (unchanged)
	public boolean updateUserBalance(long accontNo, double newBalance) throws SQLException {
		String sql = "UPDATE users SET balance = ? WHERE accountNo = ?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setLong(1, accontNo);
			ps.setDouble(2, newBalance);
			ps.executeUpdate();
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;

		}
	}

	// delete user (unchanged)
	public void deleteUser(int id) throws SQLException {
		String sql = "DELETE FROM users WHERE id = ?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	// help in edit profile
	public boolean updateUserDetails(User user) {
		String sql = "UPDATE Users SET firstName = ?, lastName = ? WHERE id = ?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setInt(3, user.getId());
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Helper to map DB row -> User
	private User mapRowToUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String userName = rs.getString("userName");
		String password = rs.getString("password");
		String firstName = rs.getString("firstName");
		String lastName = rs.getString("lastName");
		double balance = rs.getDouble("balance");
		int accountNo = rs.getInt("accountNo");
		Role role = Role.valueOf(rs.getString("role").toUpperCase());

		return new User(firstName, userName, lastName, balance, accountNo, id, password, role);
	}

	public boolean updateUserPassword(int userId, String hashedNewPassword) throws SQLException {
		String sql = "update users set password =? where id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, hashedNewPassword);
			ps.setInt(2, userId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
