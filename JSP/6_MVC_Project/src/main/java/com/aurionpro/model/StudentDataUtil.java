package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {
	
	// ONLY USED FOR STATIC FOR TESTING PURPOSE NOT USING JDBC
	
	public static List<Student> getStudent() {
		List<Student> students = new ArrayList<Student>();

//		students.add(new Student("Anup", 1));
//		students.add(new Student("John", 2));
//		students.add(new Student("Alex", 3));
		return students;
	}
}
