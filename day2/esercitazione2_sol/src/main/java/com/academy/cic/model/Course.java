package com.academy.cic.model;

import java.util.Date;
import java.util.Set;

import com.academy.cic.util.DateUtil;

public class Course {
	
	private int id;
	
	private String name;
	
	private Date startDate;
	
	private Date endDate;
	
	private Set<Module> modules;
	
	public Course() {}
	
	public Course(String name) {
		this.name = name;
	}
	
	public Course(String name, Date startDate, Date endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

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

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	
	public void addModule(Module module) {
		modules.add(module);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("-");
		sb.append(name);
		if (startDate != null) {
			sb.append(" From: ");
			sb.append(DateUtil.dateToString(startDate));
		} 
		if (endDate != null) {
			sb.append(" To: ");
			sb.append(DateUtil.dateToString(endDate));
		}
		return sb.toString();
	}
	
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

}
