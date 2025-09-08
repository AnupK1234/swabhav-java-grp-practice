package com.aurionpro.app.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aurionpro.app.dto.CourseCreateRequest;
import com.aurionpro.app.dto.CourseResponse;
import com.aurionpro.app.dto.CourseUpdateRequest;
import com.aurionpro.app.service.CourseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService service;

	public CourseController(CourseService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseCreateRequest body) {
		CourseResponse created = service.create(body);
		return ResponseEntity.created(URI.create("/api/courses/" + created.getId())).body(created);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CourseResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.get(id));
	}

	@GetMapping
	public ResponseEntity<List<CourseResponse>> list() {
		return ResponseEntity.ok(service.list());
	}

	@PutMapping("/{id}")
	public ResponseEntity<CourseResponse> update(@PathVariable Long id, @Valid @RequestBody CourseUpdateRequest body) {
		return ResponseEntity.ok(service.update(id, body));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}/teacher/{teacherId}")
	public ResponseEntity<CourseResponse> move(@PathVariable Long id, @PathVariable Long teacherId) {
		return ResponseEntity.ok(service.moveToTeacher(id, teacherId));
	}
}