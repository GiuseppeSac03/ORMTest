package com.academy.cic.model;

import java.util.Date;
import java.util.Set;

import com.academy.cic.util.DateUtil;

public class Course {
	
	private int courseId;
	private String name;
	private Date startDate;
	private Date endDate;
	private Set<Module> setModule;
	
	public Course() {}

	public Course(String name, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int course_id) {
		this.courseId = course_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

	public Set<Module> getSetModule() {
		return setModule;
	}

	public void setSetModule(Set<Module> setModule) {
		this.setModule = setModule;
	}

	@Override
	public String toString() {
		return "courseId: " + courseId + ", name: " + name + ", startDate: " + DateUtil.dateToString(startDate) + ", endDate: " + DateUtil.dateToString(endDate)
				+ ", setModule: " + setModule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseId != other.courseId)
			return false;
		return true;
	}
	
}
