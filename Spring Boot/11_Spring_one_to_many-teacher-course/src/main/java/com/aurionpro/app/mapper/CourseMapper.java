package com.aurionpro.app.mapper;

import com.aurionpro.app.dto.CourseResponse;
import com.aurionpro.app.dto.CourseUpdateRequest;
import com.aurionpro.app.entity.Course;
import com.aurionpro.app.entity.Teacher;

public class CourseMapper {

	private CourseMapper() {

	}

	public static CourseResponse toResponse(Course c) {
		CourseResponse r = new CourseResponse();
		r.setId(c.getId());
		r.setTitle(c.getTitle());
		r.setFees(c.getFees());
		Teacher t = c.getTeacher();
		if (t != null) {
			r.setTeacherId(t.getId());   
			r.setTeacherName(t.getName());
		}
		return r;
	}
	
	public static void applyUpdate(CourseUpdateRequest req, Course c , Teacher newTeacherIfAny) {
		if(req.getTitle()!=null) {
			c.setTitle(req.getTitle());
		}
		if(req.getFees()!=null) {
			c.setFees(req.getFees());
		}
		if(newTeacherIfAny !=null) {
			Teacher old=c.getTeacher();
			if(old!=null) {
				old.getCourses().remove(c);
				 newTeacherIfAny.addCourse(c);
			}
		}
		
	}

}
