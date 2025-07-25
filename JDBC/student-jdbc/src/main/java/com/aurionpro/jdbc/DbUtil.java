package com.aurionpro.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class DbUtil {

	private static final String URL = "jdbc:mysql://localhost:3306/training_db?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
	private static final String USER = "root";
	private static final String PASS = "root";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}
}
