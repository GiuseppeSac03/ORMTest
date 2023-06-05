package com.academy.cic.model;

public class Module {
	
	private int moduleId;
	private String name;
	private String teacher;
	
	public Module() {}
	
	public Module(String name, String teacher) {
		super();
		this.name = name;
		this.teacher = teacher;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
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


	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", name=" + name + ", teacher=" + teacher + "]";
	}
	

}
