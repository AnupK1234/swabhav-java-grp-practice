package com.aurionpro.app.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;

public class CourseUpdateRequest {
	private String title;

	@DecimalMin(value = "0.00", message = "fees must be >= 0")
	private BigDecimal fees;

	private Long teacherId;

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

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
}