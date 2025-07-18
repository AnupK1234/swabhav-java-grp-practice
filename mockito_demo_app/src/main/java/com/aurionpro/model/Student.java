package com.aurionpro.model;

public class Student {
	IStudentService studentService;

	public Student(IStudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	public double calculateAverageMarks() {
		return studentService.getFinalMarks() / studentService.getNumberOfSubjects();
	}
	
	
}
