package com.aurionpro;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StudentCourseDAO dao = new StudentCourseDAO();

		while (true) {
			System.out.println("\n=== Student-Course Management ===");
			System.out.println("1. Add Student");
			System.out.println("2. Add Course");
			System.out.println("3. Assign Course to Student");
			System.out.println("4. Find Students by Course");
			System.out.println("5. Add Teacher to Department");
			System.out.println("0. Exit");
			System.out.print("Choose option: ");

			int choice = sc.nextInt();

			try {
				switch (choice) {
				case 1 -> dao.addStudent();
				case 2 -> dao.addCourse();
				case 3 -> dao.assignCourseToStudent();
				case 4 -> dao.findStudentsByCourse();
				case 5 -> dao.addTeacherToDept();
				case 0 -> {
					System.out.println("Exiting...");
					return;
				}
				default -> System.out.println("Invalid choice. Try again.");
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
