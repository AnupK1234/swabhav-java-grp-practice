package com.aurionpro.app.service;

import java.util.List;

import com.aurionpro.app.dto.TeacherCreateRequest;
import com.aurionpro.app.dto.TeacherResponse;
import com.aurionpro.app.dto.TeacherUpdateRequest;
import com.aurionpro.app.entity.Teacher;
import com.aurionpro.app.exception.NotFoundException;
import com.aurionpro.app.mapper.TeacherMapper;
import com.aurionpro.app.repository.TeacherRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

	private final TeacherRepository teachers;

	public TeacherServiceImpl(TeacherRepository teachers) {
		this.teachers = teachers;
	}

	@Override
	public TeacherResponse create(TeacherCreateRequest request) {
		Teacher t = TeacherMapper.toNewEntity(request);
		return TeacherMapper.toResponse(teachers.save(t));
	}

	@Override
	@Transactional(readOnly = true)
	public TeacherResponse get(Long id) {
		Teacher t = teachers.findById(id).orElseThrow(() -> new NotFoundException("Teacher not found: " + id));
		return TeacherMapper.toResponse(t);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TeacherResponse> list() {
		return teachers.findAll().stream().map(TeacherMapper::toResponse).toList();
	}

	@Override
	public TeacherResponse update(Long id, TeacherUpdateRequest req) {
		Teacher t = teachers.findById(id).orElseThrow(() -> new NotFoundException("Teacher not found: " + id));
		TeacherMapper.applyUpdate(req, t);
		return TeacherMapper.toResponse(teachers.save(t));
	}

	@Override
	public void delete(Long id) {
		Teacher t = teachers.findById(id).orElseThrow(() -> new NotFoundException("Teacher not found: " + id));
		if (!t.getCourses().isEmpty())
			throw new IllegalStateException("Cannot delete teacher with assigned courses");
		teachers.deleteById(id);
	}

}
