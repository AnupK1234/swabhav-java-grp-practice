package com.aurionpro.model;

public class Employee {

	private String firstName;
	private String lastName;
	private int id;
	private double salary;
	
	public Employee(String firstName, String lastName, int id, double salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.salary = salary;
	}
	
	public Employee(String firstName, String lastName, double salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}
	

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getId() {
		return id;
	}
	public double getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", salary=" + salary
				+ "]";
	}
	
	
	
}
