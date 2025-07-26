package com.aurionpro;

import java.sql.*;
import java.util.Scanner;

public class StudentCourseDAO {
	private final Scanner sc = new Scanner(System.in);

	public void addStudent() throws SQLException {
		System.out.print("First Name: ");
		String fname = sc.next();
		System.out.print("Last Name: ");
		String lname = sc.next();
		System.out.print("Email: ");
		String email = sc.next();

		String sql = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?)";
		try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, email);
			ps.executeUpdate();
			System.out.println("Student added successfully!");
		}
	}

	public void addCourse() throws SQLException {
		System.out.print("Course Title: ");
		String title = sc.next();
		System.out.print("Department ID: ");
		int deptId = sc.nextInt();
		System.out.print("Teacher ID: ");
		int teacherId = sc.nextInt();

		String sql = "INSERT INTO courses (title, dept_id, teacher_id) VALUES (?, ?, ?)";
		try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, title);
			ps.setInt(2, deptId);
			ps.setInt(3, teacherId);
			ps.executeUpdate();
			System.out.println("Course added successfully!");
		}
	}

	public void assignCourseToStudent() throws SQLException {
		System.out.print("Student ID: ");
		int studentId = sc.nextInt();
		System.out.print("Course ID: ");
		int courseId = sc.nextInt();

		String sql = "INSERT INTO enrollments (student_id, course_id, enroll_date) VALUES (?, ?, CURDATE())";
		try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			ps.executeUpdate();
			System.out.println("Student enrolled in course!");
		}
	}

	public void findStudentsByCourse() throws SQLException {
		System.out.print("Course ID: ");
		int courseId = sc.nextInt();

		String sql = """
				SELECT s.student_id, s.first_name, s.last_name, s.email
				FROM students s
				JOIN enrollments e ON s.student_id = e.student_id
				WHERE e.course_id = ?
				""";

		try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, courseId);
			try (ResultSet rs = ps.executeQuery()) {
				System.out.println("Enrolled students:");
				while (rs.next()) {
					System.out.printf("%d - %s %s (%s)%n", rs.getInt("student_id"), rs.getString("first_name"),
							rs.getString("last_name"), rs.getString("email"));
				}
			}
		}
	}

	public void addTeacherToDept() throws SQLException {
		System.out.print("First Name: ");
		String fname = sc.next();
		System.out.print("Last Name: ");
		String lname = sc.next();
		System.out.print("Department ID: ");
		int deptId = sc.nextInt();

		String sql = "INSERT INTO teachers (first_name, last_name, dept_id) VALUES (?, ?, ?)";
		try (Connection con = DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setInt(3, deptId);
			ps.executeUpdate();
			System.out.println("Teacher added to department!");
		}
	}
}
