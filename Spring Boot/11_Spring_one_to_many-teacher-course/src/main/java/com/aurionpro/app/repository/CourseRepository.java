package com.aurionpro.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.app.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}