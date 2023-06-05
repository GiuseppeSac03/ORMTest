package com.academy.cic;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.academy.cic.entity.Base;
import com.academy.cic.entity.Course;
import com.academy.cic.entity.Module;

public class Service {
	
	Dao dao = new Dao();
	
	public Service (Dao dao) {
		this.dao=dao;
	}
	
	
	public int addModule(Module module, int courseId) throws ApplicationException {
		Course course = dao.findCourse(courseId);
		List<Module> modules = dao.findModulesByCourseId(courseId);
		
		if(course == null) {
			throw new EntityNotFoundException("Course not found");
		
		}
		
		if(modules.size()>9) {
			throw new ApplicationException("No more than 10 modules can be added to a course");
		}
		
		module.setCourse(course);
		dao.insertModule(module);
		
		return modules.size()+1;
		
	}

}
