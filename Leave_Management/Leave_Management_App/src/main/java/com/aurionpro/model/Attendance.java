package com.aurionpro.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Attendance {

	private int id;
	private int userId;
	private LocalDate date;
	private String status;
	private String markedBy;
	private LocalDateTime createdAt;

	public Attendance() {

	}

	public Attendance(int userId, LocalDate date, String status, String markedBy, LocalDateTime createdAt) {
		this.userId = userId;
		this.date = date;
		this.status = status;
		this.markedBy = markedBy;
		this.createdAt = createdAt;
	}

	public Attendance(int id, int userId, LocalDate date, String status, String markedBy, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.date = date;
		this.status = status;
		this.markedBy = markedBy;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMarkedBy() {
		return markedBy;
	}

	public void setMarkedBy(String markedBy) {
		this.markedBy = markedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", userId=" + userId + ", date=" + date + ", status=" + status + ", markedBy="
				+ markedBy + ", createdAt=" + createdAt + "]";
	}

}
