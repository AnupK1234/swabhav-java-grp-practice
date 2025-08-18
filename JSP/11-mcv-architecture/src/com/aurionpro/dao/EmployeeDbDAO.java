package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.aurionpro.model.Employee;

public class EmployeeDbDAO {

	private DataSource dataSource;

	public EmployeeDbDAO(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Employee> getEmployees() throws Exception {

		List<Employee> Employees = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from Employee";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("firstName");
				String lastName = myRs.getString("lastName");
				double salary = myRs.getDouble("salary");

				// create new Employee object
				Employee tempEmployee = new Employee(firstName, lastName, id, salary);

				// add it to the list of Employees
				Employees.add(tempEmployee);
			}

			return Employees;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void addEmployee(Employee theEmployee) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into Employee (firstName, lastName, salary) values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the employee
			myStmt.setString(1, theEmployee.getFirstName());
			myStmt.setString(2, theEmployee.getLastName());
			myStmt.setDouble(3, theEmployee.getSalary());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	// Inside EmployeeDbDAO.java
	public Employee getEmployee(String EmployeeId) throws Exception {
		String sql = "SELECT * FROM Employee WHERE id = ?";

		// Safely parse the EmployeeId
		int id = parseIntSafe(EmployeeId, -1);

		// Handle invalid ID gracefully
		if (id == -1) {
			throw new Exception("Invalid Employee ID provided: " + EmployeeId);
		}

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					String first = rs.getString("firstName");
					String last = rs.getString("lastName");
					Double salary = rs.getDouble("salary");
					return new Employee(first, last, id, salary);
				} else {
					throw new Exception("Could not find Employee id: " + EmployeeId);
				}
			}
		}
	}

	public void updateEmployee(Employee Employee) throws Exception {
		String sql = "UPDATE Employee SET firstName = ?, lastName = ?, salary = ? WHERE id = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, Employee.getFirstName());
			ps.setString(2, Employee.getLastName());
			ps.setDouble(3, Employee.getSalary());
			ps.setInt(4, Employee.getId());

			ps.executeUpdate();
		}
	}

	public void deleteEmployee(String employeeId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			int id = Integer.parseInt(employeeId);
			myConn = dataSource.getConnection();
			String sql = "delete from Employee where id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, id);
			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
	}

	private int parseIntSafe(String str, int defaultValue) {
		if (str == null || str.trim().isEmpty()) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close(); // doesn't really close it ... just puts back in connection pool
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}