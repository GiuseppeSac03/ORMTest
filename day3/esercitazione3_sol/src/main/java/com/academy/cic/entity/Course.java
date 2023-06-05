package com.academy.cic.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE")
public class Course extends Base {
	
	@Column(nullable = false, length = 64)
	private String name;
	
	@OneToMany(mappedBy = "course")
	private List<Registration> registrations;
	
	public Course() {}
	
	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}
	
	public String toString() {
		return String.format("%d - %s", getId(), name);
	}

}
