package com.aurionpro.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.Student;

public class StudentCrud1 {
	public static void main(String[] args) {
		int i;
		try {
//			i = insertStudent("Anup", "anup@gmail.com", 92.6);
//			System.out.println("Datat is : " + i);

//			Find all
//			findAll().forEach(System.out::println);

//			Find by Id
//			findById(2).forEach(System.out::println);

			// Delete by id
//			System.out.println("The result for delete by id is : " + deleteStudent(3));

			// Update student
//			System.out.println("The result for update student by id is : " + updateStudent(4, "Anup", "khismatrao@gmail.com", 99.99));

			// Total count of student
//			System.out.println("The total count of student in the class is : " + countStudents());

			// Average percentage marks of students
//			System.out.println("The average of percentage of all student in the class is : " + getAveragePercentage());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static int insertStudent(String name, String email, double percentage) throws SQLException {
		String sql = String.format("INSERT INTO student (name, email, percentage) VALUES ('%s', '%s', '%s')", name,
				email, percentage);

		try (Connection con = DbUtil.getConnection(); Statement st = con.createStatement()) {
			st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			try (ResultSet keys = st.getGeneratedKeys()) {
				return keys.next() ? keys.getInt(1) : -1; // becaz sql index start at 1
			}
		}
	}

	private static Student map(ResultSet rs) throws SQLException {
		return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getDouble("percentage"));
	}

	static List<Student> findAll() throws SQLException {
		String sql = "SELECT * FROM STUDENT";
		try (Connection con = DbUtil.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			List<Student> list = new ArrayList<>();
			while (rs.next()) {
				list.add(map(rs));
			}
			return list;
		}
	}

	static List<Student> findById(int id) throws SQLException {
		String sql = "SELECT * FROM STUDENT WHERE ID=" + id;
		try (Connection con = DbUtil.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			List<Student> list = new ArrayList<>();
			while (rs.next()) {
				list.add(map(rs));
			}
			return list;
		}
	}

	static boolean deleteStudent(int id) throws SQLException {
		String sql = "DELETE FROM STUDENT WHERE ID=" + id;
		try (Connection con = DbUtil.getConnection(); Statement st = con.createStatement()) {
			return st.executeUpdate(sql) == 1;
		}
	}

	static boolean updateStudent(int id, String name, String email, double percentage) throws SQLException {
		String sql = String.format("UPDATE STUDENT SET NAME='%s', email='%s', percentage=%s WHERE id=%d", name, email,
				percentage, id);
		try (Connection con = DbUtil.getConnection(); Statement st = con.createStatement()) {
			return st.executeUpdate(sql) == 1;
		}
	}

	static Student findByEmail(String email) throws SQLException {
		String sql = String.format("SELECT * FROM STUDENT WHERE EMAIL='%s'", email);
		try (Connection con = DbUtil.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			if (rs.next())
				return map(rs);
			return null;
		}
	}

	static int countStudents() throws SQLException {
		String sql = "SELECT COUNT(*) FROM STUDENT";
		try (Connection con = DbUtil.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			if (rs.next())
				return rs.getInt(1);
			return 0;
		}
	}

	static double getAveragePercentage() throws SQLException {
		String sql = "SELECT AVG(PERCENTAGE) FROM STUDENT";
		try (Connection con = DbUtil.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			if (rs.next())
				return rs.getDouble(1);
			return 0.0;
		}
	}

	static String escape(String s) {
		return s.replace("'", "''");
	}

}
