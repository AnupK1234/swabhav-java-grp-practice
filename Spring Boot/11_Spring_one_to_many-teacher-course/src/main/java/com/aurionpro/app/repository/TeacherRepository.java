package com.aurionpro.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.app.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}