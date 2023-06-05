package com.academy.cic.model;

public class Module {
	
	private int id;
	
	private String name;
	
	private String teacher;
	
	//private Course course;
	
	public Module() {}
	
	public Module(String name, String teacher) {
		this.name = name;
		this.teacher = teacher;
	}
	
	// for bidirectional
//	public Module(String name, String teacher, Course course) {
//		this.name = name;
//		this.teacher = teacher;
//		this.course = course;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
//	public Course getCourse() {
//		return course;
//	}
//
//	public void setCourse(Course course) {
//		this.course = course;
//	}

	@Override
	public boolean equals(Object ob) {
	   if (ob == null) {
	       return false;
	   }
	   if (this == ob) {
	       return true;
	   }
	   if (ob instanceof Course) {
		   Course other = (Course) ob;
	       return this.id == other.getId();
	   }
	   return false;
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + id;
	    return result;
	}
	
	@Override
	public String toString() {
		return String.format("%d - %s - %s", id, name, teacher);
	}
	
//	@Override
//	// for bidirectional
//	public String toString() {
//		return String.format("%d - %s - %s - %s", id, name, teacher, course.toString());
//	}

}
