package com.aurionpro.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.aurionpro.model.Transaction;
import com.aurionpro.model.TransactionType;

public class TransactionDao {

	private DataSource dataSource;

	public TransactionDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// Create a transaction
	public void createTransaction(Transaction transaction) throws SQLException {
		String sql = "INSERT INTO transactions (accountNo, type, amount, transaction_date, description, balance_after_transaction) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setLong(1, transaction.getAccountNo());
			ps.setString(2, transaction.getType().name());
			ps.setDouble(3, transaction.getAmount());
			ps.setTimestamp(4, Timestamp.valueOf(transaction.gettDate()));
			ps.setString(5, transaction.getDescription());
			ps.setDouble(6, transaction.getBalanceAfterTransaction());

			ps.executeUpdate();
		}
	}

	// Get all transactions for an account
	public List<Transaction> getTransactionsByAccountNumber(long accountNo) throws SQLException {
		String sql = "SELECT * FROM transactions WHERE accountNo = ?";
		List<Transaction> transactions = new ArrayList<>();

		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setLong(1, accountNo);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					transactions.add(mapRowToTransaction(rs));
				}
			}
		}
		return transactions;
	}

	private Transaction mapRowToTransaction(ResultSet rs) throws SQLException {
		long transactionId = rs.getLong("id");
		long accountNo = rs.getLong("accountNo");
		String typeStr = rs.getString("type");
		double amount = rs.getDouble("amount");
		java.sql.Timestamp ts = rs.getTimestamp("transaction_date");
		java.time.LocalDateTime transactionDate = ts.toLocalDateTime();
		String description = rs.getString("description");
		double balanceAfterTransaction = rs.getDouble("balance_after_transaction");

		return new Transaction(transactionId, balanceAfterTransaction, accountNo,
				TransactionType.valueOf(typeStr.toUpperCase()), amount, transactionDate, description);
	}

}
