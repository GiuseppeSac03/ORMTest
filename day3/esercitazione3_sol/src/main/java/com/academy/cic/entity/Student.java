package com.academy.cic.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
@NamedQuery(name="Student.findByNameSurname", query = "SELECT s FROM Student s WHERE s.name = :name AND s.surname = :surname")
public class Student extends Base {
	
	@Column(name = "first_name", nullable = false, length = 32)
	private String name;
	
	@Column(name = "last_name", nullable = false, length = 32)
	private String surname;
	
	@Column
	private Integer age;
	
	@OneToMany(mappedBy = "student")
	private List<Registration> registrations;
	
	public Student() {}
	
	public Student(String name, String surname, Integer age) {
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}
	
	public String toString() {
		return String.format("%d - %s %s - %d", getId(), name, surname, age);
	}

}
