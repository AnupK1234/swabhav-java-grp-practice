package com.aurionpro.app.mapper;

import java.util.List;

import com.aurionpro.app.dto.CourseDto;
import com.aurionpro.app.dto.TeacherCreateRequest;
import com.aurionpro.app.dto.TeacherResponse;
import com.aurionpro.app.dto.TeacherUpdateRequest;
import com.aurionpro.app.entity.Course;
import com.aurionpro.app.entity.Teacher;

public class TeacherMapper {

	private TeacherMapper() {

	}

	public static TeacherResponse toResponse(Teacher t) {
		TeacherResponse r = new TeacherResponse();
		r.setId(t.getId());
		r.setName(t.getName());
		r.setCourses(toCourseDtos(t.getCourses()));
		return r;
	}

	private static List<CourseDto> toCourseDtos(List<Course> list) {
		if (list == null || list.isEmpty())
			return List.of();
		return list.stream().map(TeacherMapper::toDto).toList();
	}

	private static CourseDto toDto(Course c) {
		CourseDto d = new CourseDto();
		d.setId(c.getId());
		d.setTitle(c.getTitle());
		d.setFees(c.getFees());
		return d;

	}

	public static Teacher toNewEntity(TeacherCreateRequest req) {
		Teacher t = new Teacher();
		t.setName(req.getName());
		return t;
	}

	public static void applyUpdate(TeacherUpdateRequest req, Teacher t) {
		if (req.getName() != null) {
			t.setName(req.getName());
		}
	}
}
