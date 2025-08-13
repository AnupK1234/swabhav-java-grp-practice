package com.aurionpro;

public class Student {
	String firstName;
	String lastName;
	int marks;
	boolean isPassed;

	public Student(String firstName, String lastName, int marks, boolean isPassed) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.marks = marks;
		this.isPassed = isPassed;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public boolean isPassedCheck() {
		return isPassed;
	}

	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}

}
