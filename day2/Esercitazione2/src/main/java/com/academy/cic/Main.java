package com.academy.cic;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.academy.cic.model.*;
import com.academy.cic.model.Module;

public class Main {

	public static void main(String[] args) {
		
		Dao service = new Dao();
		
		Date date1 = new Date(2022-1900, 8, 20);
		Date date2 = new Date(2022-1900, 9, 15);
		
		Course course1 = new Course();
		course1.setEndDate(date2);
		course1.setName("Matematica");
		course1.setStartDate(date1);
		
		// Course course2 = new Course("Matematica", date3, date4);
		
		service.insertCourse(course1);
		
		

		Module module1 = new Module("modulo1", "prof.1");
		Module module2 = new Module("modulo2", "prof.1A");
		Module module3 = new Module("modulo1", "prof.3");
		
		Set<Module> modules1course = new HashSet<Module>();
		modules1course.add(module1);
		modules1course.add(module2);
		course1.setModules(modules1course);
		
		service.addModuleToCourse(module3, 7);
		

		
		
	}

}
