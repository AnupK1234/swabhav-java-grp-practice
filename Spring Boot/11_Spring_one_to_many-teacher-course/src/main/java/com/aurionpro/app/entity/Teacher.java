package com.aurionpro.app.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="teachers")
public class Teacher {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,unique=true)
	private String name;
	
	//Inverse Side
	@OneToMany(mappedBy="teacher",cascade= {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval=false)
	private List<Course>courses=new ArrayList<>();
	
	public void addCourse(Course c) {
		courses.add(c);
		c.setTeacher(this);
	}
	public void removeCourse(Course c) {
		courses.remove(c);
		c.setTeacher(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
	
}
