package com.aurionpro.model;

public class Employee {
	private int id; // same as users.id
	private Integer managerId;
	private int annualLeaveQuota;
	private int leaveBalance;
	private String department;

	public Employee() {
	}

	public Employee(int id, Integer managerId, int annualLeaveQuota, int leaveBalance, String department) {
		this.id = id;
		this.managerId = managerId;
		this.annualLeaveQuota = annualLeaveQuota;
		this.leaveBalance = leaveBalance;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public int getAnnualLeaveQuota() {
		return annualLeaveQuota;
	}

	public void setAnnualLeaveQuota(int annualLeaveQuota) {
		this.annualLeaveQuota = annualLeaveQuota;
	}

	public int getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
