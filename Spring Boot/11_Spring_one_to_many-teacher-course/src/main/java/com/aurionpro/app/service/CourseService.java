package com.aurionpro.app.service;

import java.util.List;
import com.aurionpro.app.dto.CourseCreateRequest;
import com.aurionpro.app.dto.CourseResponse;
import com.aurionpro.app.dto.CourseUpdateRequest;

public interface CourseService {
	CourseResponse create(CourseCreateRequest request);

	CourseResponse get(Long id);

	List<CourseResponse> list();

	CourseResponse update(Long id, CourseUpdateRequest request);

	void delete(Long id);

	CourseResponse moveToTeacher(Long courseId, Long newTeacherId);
}
