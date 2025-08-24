package com.aurionpro.model;

public class User {

	private int id;
	private String userName;
	private String firstName;
	private String lastName;
	private double balance;
	private int accountNo;
	private Role role;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User(String firstName, String username, String lastName, double balance, int accountNo, int id,
			String password, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.accountNo = accountNo;
		this.id = id;
		this.password = password;
		this.role = role;
		this.userName = username;
	}

	public User(String firstName, String userName, String lastName, double balance, int accountNo, String password,
			Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.accountNo = accountNo;
		this.password = password;
		this.role = role;
		this.userName = userName;

	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", balance="
				+ balance + ", accountNo=" + accountNo + ", id=" + id + ", role=" + role + ", password=" + password
				+ "]";
	}

}
