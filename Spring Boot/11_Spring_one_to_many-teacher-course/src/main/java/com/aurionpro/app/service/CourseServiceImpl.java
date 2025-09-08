package com.aurionpro.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurionpro.app.dto.CourseCreateRequest;
import com.aurionpro.app.dto.CourseResponse;
import com.aurionpro.app.dto.CourseUpdateRequest;
import com.aurionpro.app.entity.Course;
import com.aurionpro.app.entity.Course;
import com.aurionpro.app.entity.Teacher;
import com.aurionpro.app.exception.NotFoundException;
import com.aurionpro.app.mapper.CourseMapper;
import com.aurionpro.app.repository.CourseRepository;
import com.aurionpro.app.repository.TeacherRepository;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	private final CourseRepository courses;
	private final TeacherRepository teachers;

	public CourseServiceImpl(CourseRepository courses, TeacherRepository teachers) {
		this.courses = courses;
		this.teachers = teachers;
	}

	@Override
	public CourseResponse create(CourseCreateRequest req) {
		Teacher teacher = teachers.findById(req.getTeacherId())
				.orElseThrow(() -> new NotFoundException("Teacher not found: " + req.getTeacherId()));
		Course c = new Course();
		c.setTitle(req.getTitle());
		c.setFees(req.getFees());

		teacher.addCourse(c);

		Course saved = courses.save(c);
		return CourseMapper.toResponse(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public CourseResponse get(Long id) {
		Course c = courses.findById(id).orElseThrow(() -> new NotFoundException("Course not found: " + id));
		return CourseMapper.toResponse(c);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseResponse> list() {
		return courses.findAll().stream().map(CourseMapper::toResponse).toList();
	}

	@Override
	public CourseResponse update(Long id, CourseUpdateRequest req) {
		Course c = courses.findById(id).orElseThrow(() -> new NotFoundException("Course not found: " + id));

		Teacher newTeacher = null;
		if (req.getTeacherId() != null) {
			newTeacher = teachers.findById(req.getTeacherId())
					.orElseThrow(() -> new NotFoundException("Teacher not found: " + req.getTeacherId()));
		}

		CourseMapper.applyUpdate(req, c, newTeacher);
		return CourseMapper.toResponse(courses.save(c));
	}

	@Override
	public void delete(Long id) {
		if (!courses.existsById(id))
			throw new NotFoundException("Course not found: " + id);
		courses.deleteById(id);
	}

	@Override
	public CourseResponse moveToTeacher(Long courseId, Long newTeacherId) {
		Course c = courses.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found: " + courseId));
		Teacher newTeacher = teachers.findById(newTeacherId)
				.orElseThrow(() -> new NotFoundException("Teacher not found: " + newTeacherId));

		Teacher old = c.getTeacher();
		if (old != null)
			old.getCourses().remove(c);
		newTeacher.addCourse(c);

		return CourseMapper.toResponse(courses.save(c));
	}

}
