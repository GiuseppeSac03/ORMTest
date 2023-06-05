package com.academy.cic;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.academy.cic.model.Course;
import com.academy.cic.model.Module;
import com.academy.cic.util.DateUtil;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		Dao dao = new Dao();
		
		Course course1 = new Course("First course", DateUtil.stringToDate("01-01-2023"), DateUtil.stringToDate("31-12-2023"));
		course1.setModules(new HashSet<Module>());
		course1.addModule(new Module("Module 1", "Mario Rossi"));
		course1.addModule(new Module("Module 2", "Paolo Bianchi"));
		course1.addModule(new Module("Module 3", "Mario Rossi"));
		
		Course course2 = new Course("Second course");
		course2.setStartDate(DateUtil.stringToDate("05-01-2023"));
		course2.setModules(new HashSet<Module>());
		course2.addModule(new Module("Module 4", "Mario Gialli"));
		course2.addModule(new Module("Module 5", "Paolo Verdi"));
		
		Course course3 = new Course("Third course");

		Integer idCourse1 = dao.insertCourse(course1);
		System.out.println(String.format("Generated id: %d for course1", idCourse1));
		dao.insertCourse(course2);
		dao.insertCourse(course3);

		dao.addModuleToCourse(new Module("Module Added", "Mario Verdi"), idCourse1);
		//dao.addModuleToCourseBiDirectional(new Module("Module Added", "Mario Verdi", course1));
		
		dao.updateStartDate(DateUtil.stringToDate("21-02-2023"), course3.getId());
		System.out.println(dao.findCourseByName("Third Course"));

		List<Course> courses = dao.findStartingCourseInRange(DateUtil.stringToDate("01-01-2023"), DateUtil.stringToDate("01-03-2023"));
		if (courses != null) {
			for (Course c: courses) {
				System.out.println(c);
			}
		}
		
		Set<Module> modules = dao.findCourseModules(idCourse1);
		System.out.println(course1);
		if (modules != null) {
			for (Module m: modules)
				System.out.println(m);
		}
		
		System.out.println(dao.findModuleByName("Module 2"));
		
		//dao.deleteCourse(idCourse1);

	}

}
