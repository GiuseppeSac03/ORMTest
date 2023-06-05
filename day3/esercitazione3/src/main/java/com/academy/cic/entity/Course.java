package com.academy.cic.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "Course")
public class Course extends Base{
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Registration> registrations;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Module> modules;
	
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
	
	
	
	
	

}
