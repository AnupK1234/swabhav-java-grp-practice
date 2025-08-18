package com.aurionpro.EmployeeDbUtil;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.aurionpro.Employee.Employee;

public class EmployeeDbUtil {

	private DataSource dataSource;

	public EmployeeDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Employee> getEmp() throws Exception {

		List<Employee> emps = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from employee order by lastName";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("emp_id");
				String firstName = myRs.getString("firstName");
				String lastName = myRs.getString("lastName");
				String email = myRs.getString("email");

				// create new student object
				Employee tempStudent = new Employee(lastName, firstName, id, email);

				// add it to the list of students
				emps.add(tempStudent);
			}

			return emps;
		} finally {
			// close JDBC objects
			close(myConn,myStmt, myRs);
		}
	}
	
	public void addEmployee(Employee emp) throws Exception {
	    Connection myConn = null;
	    java.sql.PreparedStatement myStmt = null;

	    try {
	        // get db connection
	        myConn = dataSource.getConnection();

	        // create sql insert
	        String sql = "insert into employee (firstName, lastName,  email) values ( ?, ?, ?)";

	        myStmt = myConn.prepareStatement(sql);

	        // set param values
	        myStmt.setString(1, emp.getFirstName());
	        myStmt.setString(2, emp.getLastName());
	        myStmt.setString(3, emp.getEmail());

	        // execute
	        myStmt.execute();
	    } finally {
	        // clean up JDBC
	        close(myConn, myStmt, null);
	    }
	}


	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				((Connection) myStmt).close();
			}

			if (myConn != null) {
				myConn.close(); // doesn't really close it ... just puts back in connection pool
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}