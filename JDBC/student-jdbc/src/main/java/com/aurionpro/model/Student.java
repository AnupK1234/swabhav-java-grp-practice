package com.aurionpro.model;

public class Student {
	private int id;
	private String name;
	private String email;
	private double percentage;

	public Student(int id, String name, String email, double percentage) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.percentage = percentage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", percentage=" + percentage + "]";
	}

}
