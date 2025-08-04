package com.aurionpro;

import java.sql.*;

public class BankTransaction {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bankdb";
		String user = "root";
		String password = "root";

		Connection con = null;
		PreparedStatement debitStmt = null;
		PreparedStatement creditStmt = null;

		try {
			con = DriverManager.getConnection(url, user, password);

			// Turn off auto-commit
			con.setAutoCommit(false);

			// Debit ₹200 from Anup
			String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
			debitStmt = con.prepareStatement(debitSQL);
			debitStmt.setDouble(1, 500);
			debitStmt.setInt(2, 1); // Anup's ID
			debitStmt.executeUpdate();

			// Credit ₹200 to Ravi
			String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
			creditStmt = con.prepareStatement(creditSQL);
			creditStmt.setDouble(1, 500);
			creditStmt.setInt(2, 2); // Ravi's ID
			creditStmt.executeUpdate();

			// Commit transaction
			con.commit();
			System.out.println("Transaction Successful ✅");

		} catch (SQLException e) {
			try {
				if (con != null) {
					con.rollback(); // Undo changes on error
					System.out.println("Transaction Failed ❌ - Rolled back");
				}
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (debitStmt != null)
					debitStmt.close();
				if (creditStmt != null)
					creditStmt.close();
				if (con != null)
					con.close();
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}
	}
}
