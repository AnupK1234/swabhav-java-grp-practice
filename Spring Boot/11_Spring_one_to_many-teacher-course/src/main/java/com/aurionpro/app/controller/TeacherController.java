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
import com.aurionpro.app.dto.TeacherCreateRequest;
import com.aurionpro.app.dto.TeacherResponse;
import com.aurionpro.app.dto.TeacherUpdateRequest;
import com.aurionpro.app.service.TeacherService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

	private final TeacherService service;

	public TeacherController(TeacherService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<TeacherResponse> create(@Valid @RequestBody TeacherCreateRequest body) {
		TeacherResponse created = service.create(body);
		return ResponseEntity.created(URI.create("/api/teachers/" + created.getId())).body(created);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TeacherResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.get(id));
	}

	@GetMapping
	public ResponseEntity<List<TeacherResponse>> list() {
		return ResponseEntity.ok(service.list());
	}

	@PutMapping("/{id}")
	public ResponseEntity<TeacherResponse> update(@PathVariable Long id,
			@Valid @RequestBody TeacherUpdateRequest body) {
		return ResponseEntity.ok(service.update(id, body));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}