package com.practice.testCon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TestConnection")
public class TestConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/student-source")
	private DataSource dataSource;

	public TestConnection() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		try {
			conn = dataSource.getConnection();
			String sql = "select * from student";
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				String email = resultSet.getString("email");
				System.out.println(email);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}