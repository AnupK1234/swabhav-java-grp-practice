package com.aurionpro.model;

import java.sql.Date;              // for dob
import java.time.LocalDateTime;    // for created_at

public class User {

    private int id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private Role role;
    private Integer managerId;          // nullable
    private int annualLeaveQuota;
    private int leaveBalance;
    private LocalDateTime createdAt;

    // Default constructor
    public User() {}

    // Full constructor (with id)
    public User(int id, String userName, String password, String firstName, String lastName,
                String email, Date dob, Role role, Integer managerId,
                int annualLeaveQuota, int leaveBalance, LocalDateTime createdAt) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.role = role;
        this.managerId = managerId;
        this.annualLeaveQuota = annualLeaveQuota;
        this.leaveBalance = leaveBalance;
        this.createdAt = createdAt;
    }

    // Constructor without id (for inserts)
    public User(String userName, String password, String firstName, String lastName,
                String email, Date dob, Role role, Integer managerId,
                int annualLeaveQuota, int leaveBalance, LocalDateTime createdAt) {
        this(0, userName, password, firstName, lastName, email,
             dob, role, managerId, annualLeaveQuota, leaveBalance, createdAt);
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Integer getManagerId() { return managerId; }
    public void setManagerId(Integer managerId) { this.managerId = managerId; }

    public int getAnnualLeaveQuota() { return annualLeaveQuota; }
    public void setAnnualLeaveQuota(int annualLeaveQuota) { this.annualLeaveQuota = annualLeaveQuota; }

    public int getLeaveBalance() { return leaveBalance; }
    public void setLeaveBalance(int leaveBalance) { this.leaveBalance = leaveBalance; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
