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
	private boolean forcePasswordChange;

	public boolean isForcePasswordChange() {
		return forcePasswordChange;
	}

	public void setForcePasswordChange(boolean forcePasswordChange) {
		this.forcePasswordChange = forcePasswordChange;
	}

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
	
    public User() {
    }


	public User(int id, String userName, String firstName, String lastName, double balance, int accountNo, Role role,
			String password, boolean forcePasswordChange) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.accountNo = accountNo;
		this.role = role;
		this.password = password;
		this.forcePasswordChange = forcePasswordChange;
	}

	public User(String userName, String firstName, String lastName, double balance, int accountNo, Role role,
			String password, boolean forcePasswordChange) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.accountNo = accountNo;
		this.role = role;
		this.password = password;
		this.forcePasswordChange = forcePasswordChange;

	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", balance=" + balance + ", accountNo=" + accountNo + ", role=" + role + ", password=" + password
				+ ", forcePasswordChange=" + forcePasswordChange + "]";
	}

}
