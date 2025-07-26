package com.aurionpro.fooddelivery.dao;

import com.aurionpro.fooddelivery.datastore.DbUtil;
import com.aurionpro.fooddelivery.model.MenuItem;
import com.aurionpro.fooddelivery.model.Order;
import com.aurionpro.fooddelivery.model.OrderItem;

import java.sql.*;
import java.util.List;

public class OrderDAO {
	public long insertOrder(Order order) throws SQLException {
		String sql = "INSERT INTO orders (final_amount, payment_mode, delivery_address) VALUES (?, ?, ?)";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setDouble(1, order.getFinalAmount());
			stmt.setString(2, order.getPaymentMode().toString());
			stmt.setString(3, order.getDeliveryAddress());
			// stmt.setString(4, order.getDeliveryPartner().toString());
			stmt.executeUpdate();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getLong(1);
				}
			}
		}
		return -1;
	}

	public void insertOrderItems(long orderId, List<OrderItem> items) throws SQLException {
		String sql = "INSERT INTO order_items (order_id, menu_item_id, quantity) VALUES (?, ?, ?)";
		try (Connection conn = DbUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			for (OrderItem item : items) {
				stmt.setLong(1, orderId);
				stmt.setLong(2, item.getItem().getId());
				stmt.setInt(3, item.getQuantity());
				stmt.addBatch();
			}
			stmt.executeBatch();
		}
	}
}