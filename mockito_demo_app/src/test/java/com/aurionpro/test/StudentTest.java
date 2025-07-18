package com.aurionpro.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.aurionpro.model.IStudentService;
import com.aurionpro.model.Student;

class StudentTest {
	private Student student;
	private IStudentService studentService;
	
	@BeforeEach
	void init() {
		studentService = Mockito.mock(IStudentService.class);
		student = new Student(studentService);
	}
	
	@AfterEach
	void display() {
		System.out.println("Testing done.");
	}
	
	@Test
	void testCalculateAverageMarks() {
		Mockito.when(studentService.getFinalMarks()).thenReturn(450);
		Mockito.when(studentService.getNumberOfSubjects()).thenReturn(5);
		assertEquals(90, student.calculateAverageMarks());
		
	}
}
