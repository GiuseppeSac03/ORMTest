package com.academy.cic.entity;

import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.Table;

@Entity
@Table(name = "REGISTRATION")
@NamedQuery(name="Registration.findByStudentId", query = "SELECT r FROM Registration r WHERE r.student.id = :studentId")
public class Registration extends Base {
	
	private static final Logger logger = Logger.getLogger(Registration.class.getName());
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
	private Course course;
	
	@Column
	private Integer grade;
	
	public Registration() {}
	
	public Registration(Student student, Course course) {
		this.student = student;
		this.course = course;
	}
	
	@PostPersist
	public void logNewRegistration() {
		String logMessage = String.format("Registered student %d to course %d", student.getId(), course.getId());
	    logger.info(logMessage);
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

}
