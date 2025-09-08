package com.aurionpro.app.service;

import java.util.List;

import com.aurionpro.app.dto.TeacherCreateRequest;
import com.aurionpro.app.dto.TeacherResponse;
import com.aurionpro.app.dto.TeacherUpdateRequest;

public interface TeacherService {

	TeacherResponse create(TeacherCreateRequest request);
	TeacherResponse get(Long id);
	List<TeacherResponse>list();
	TeacherResponse update(Long id, TeacherUpdateRequest request);
	void delete(Long id);
	
}
