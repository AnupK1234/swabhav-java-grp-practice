package com.aurionpro.fooddelivery.dao;

import com.aurionpro.fooddelivery.datastore.DbUtil;
import com.aurionpro.fooddelivery.model.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {

	public void addMenuItem(MenuItem item) throws SQLException {
		String sql = "INSERT INTO menu_items (id, name, price) VALUES (?, ?, ?)";
		try (Connection conn = DbUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, item.getId());
			stmt.setString(2, item.getName());
			stmt.setDouble(3, item.getPrice());
			stmt.executeUpdate();
		}
	}

	public List<MenuItem> getAllItems() throws SQLException {
		String sql = "SELECT * FROM menu_items";
		List<MenuItem> items = new ArrayList<>();

		try (Connection conn = DbUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				items.add(new MenuItem(rs.getLong("id"), rs.getString("name"), rs.getDouble("price")));
			}
		}

		return items;
	}

	public boolean updateMenuItem(MenuItem item) throws SQLException {
		String sql = "UPDATE menu_items SET name = ?, price = ? WHERE id = ?";
		try (Connection conn = DbUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, item.getName());
			stmt.setDouble(2, item.getPrice());
			stmt.setLong(3, item.getId());
			return stmt.executeUpdate() > 0;
		}
	}

	public boolean deleteMenuItem(long id) throws SQLException {
		String sql = "DELETE FROM menu_items WHERE id = ?";
		try (Connection conn = DbUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			return stmt.executeUpdate() > 0;
		}
	}
}
