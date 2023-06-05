package com.academy.cic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Module")
public class Module extends Base{
	
	@Column
	private String name;
	
	@Column
	private String teacher;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	public Module(String name, String teacher) {
		this.name = name;
		this.teacher = teacher;
		
	}

	public Module() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
