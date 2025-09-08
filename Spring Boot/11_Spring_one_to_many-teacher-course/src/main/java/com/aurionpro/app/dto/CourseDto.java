package com.aurionpro.app.dto;

import java.math.BigDecimal;

public class CourseDto {
	private Long id;
	private String title;
	private BigDecimal fees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getFees() {
		return fees;
	}

	public void setFees(BigDecimal fees) {
		this.fees = fees;
	}

}
