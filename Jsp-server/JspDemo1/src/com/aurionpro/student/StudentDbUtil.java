package com.aurionpro.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private final DataSource dataSource;

	public StudentDbUtil(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}

	public List<Student> getStudents() throws Exception {
		String sql = "SELECT * FROM student ORDER BY last_name, first_name";
		List<Student> students = new ArrayList<>();
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				String email = rs.getString("email");
				students.add(new Student(id, first, last, email));
			}
		}
		return students;
	}

	public void deleteStudent(String studentId) throws Exception {
		String sql = "DELETE FROM student WHERE id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, Integer.parseInt(studentId));
			ps.executeUpdate();
		}
	}

	public void updateStudent(Student theStudent) throws Exception {
		String sql = "UPDATE student SET first_name=?, last_name=?, email=? WHERE id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, theStudent.getFirstName());
			ps.setString(2, theStudent.getLastName());
			ps.setString(3, theStudent.getEmail());
			ps.setInt(4, theStudent.getId());
			ps.executeUpdate();
		}
	}

	public void addStudent(Student student) throws Exception {
		String sql = "INSERT INTO student (first_name, last_name, email) VALUES (?, ?, ?)";
		try (Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.setString(3, student.getEmail());
			ps.executeUpdate();
		}
	}

	public Student getStudent(String studentId) throws Exception {
		Student theStudent = null;
		String sql = "SELECT * FROM student WHERE id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, Integer.parseInt(studentId));
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					String first = rs.getString("first_name");
					String last = rs.getString("last_name");
					String email = rs.getString("email");
					theStudent = new Student(id, first, last, email);
				}
			}
		}
		return theStudent;
	}
}
