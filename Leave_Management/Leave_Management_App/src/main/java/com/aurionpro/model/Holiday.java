package com.aurionpro.model;

import java.time.LocalDate;

public class Holiday {
	private int id;
	private LocalDate holidayDate;
	private String title;

	public Holiday() {

	}

	public Holiday(LocalDate holidayDate, String title) {

		this.holidayDate = holidayDate;
		this.title = title;
	}

	public Holiday(int id, LocalDate holidayDate, String title) {
		super();
		this.id = id;
		this.holidayDate = holidayDate;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(LocalDate holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
