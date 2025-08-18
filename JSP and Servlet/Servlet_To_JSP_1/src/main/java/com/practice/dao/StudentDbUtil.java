package com.practice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.practice.model.Student;

public class StudentDbUtil {

    private DataSource dataSource;

    public StudentDbUtil(DataSource theDataSource) {
        this.dataSource = theDataSource;
    }

    // Get all students
    public List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM student ORDER BY last_name";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");

                students.add(new Student(id, firstName, lastName, email));
            }
        }

        return students;
    }

    // Add a student
    public void addStudent(Student theStudent) throws Exception {
        String sql = "INSERT INTO student (first_name, last_name, email) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, theStudent.getFirstName());
            ps.setString(2, theStudent.getLastName());
            ps.setString(3, theStudent.getEmail());

            ps.executeUpdate();
        }
    }

    // Delete a student
    public void deleteStudent(String theStudentId) throws Exception {
        String sql = "DELETE FROM student WHERE id = ?";

        int studentId = Integer.parseInt(theStudentId);

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.executeUpdate();
        }
    }

    // Update a student
    public void updateStudent(Student theStudent) throws Exception {
        String sql = "UPDATE student SET first_name=?, last_name=?, email=? WHERE id=?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, theStudent.getFirstName());
            ps.setString(2, theStudent.getLastName());
            ps.setString(3, theStudent.getEmail());
            ps.setInt(4, theStudent.getId());

            ps.executeUpdate();
        }
    }

    // Get student by ID
    public Student getStudent(String studentId) throws Exception {
        String sql = "SELECT * FROM student WHERE id = ?";

        int id = Integer.parseInt(studentId);

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");

                    return new Student(id, firstName, lastName, email);
                } else {
                    throw new Exception("Could not find student id: " + studentId);
                }
            }
        }
    }
}
