package com.aurionpro.fooddelivery.model;

import com.aurionpro.fooddelivery.enums.UserType;

public class User {
	private long id;
	private String name;
	private String email;
	private long phone;
	private UserType userType = UserType.CUSTOMER;

	public User(long id, String name, String email, long phone, UserType userType) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.userType = userType;
	}

	public long getId() {
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

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
