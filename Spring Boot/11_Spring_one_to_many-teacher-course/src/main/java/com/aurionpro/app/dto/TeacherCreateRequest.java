package com.aurionpro.app.dto;

import jakarta.validation.constraints.NotBlank;

public class TeacherCreateRequest {

	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
