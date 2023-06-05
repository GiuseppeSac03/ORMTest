package com.academy.cic.model;

public class StudentNumCourses {
	
	private Student student;
	
	private int courseCount;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getCourseCount() {
		return courseCount;
	}

	public void setCourseCount(int courseCount) {
		this.courseCount = courseCount;
	}
	
	public String toString() {
		return String.format("Student: %s - Num courses: %d", student.toString(), courseCount);
	}
	
	public String toStringId() {
		return String.format("StudentId: %d - Num courses: %d", student.getId(), courseCount);
	}

}
