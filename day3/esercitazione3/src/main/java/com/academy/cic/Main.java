package com.academy.cic;

import com.academy.cic.entity.Course;
import com.academy.cic.entity.Registration;
import com.academy.cic.entity.Student;
import com.academy.cic.entity.Module;

public class Main {

	public static void main(String[] args) throws ApplicationException {
		Dao dao = new Dao();
		Service service = new Service();
		
		Student student1 = new Student();
		student1.setFirstName("Giuseppe");
		student1.setLastName("Sacco");
		student1.setAge(19);
		student1.setRegistrations(null);
		
		Registration registration1 = new Registration();
		registration1.setStudent(student1);
		registration1.setGrade(30);
		
		
		Course course1 = new Course();
		course1.setName("Algebra");
		course1.setRegistrations(null);
		Course course2 = new Course();
		course2.setName("Programmazione WEB");
		
		registration1.setCourse(course1);
		
		Module module1 = new Module();
		module1.setName("parte 1");
		module1.setTeacher("prof rossi");
		
		dao.insertStudent(student1);
		dao.insertCourse(course1);
		dao.insertCourse(course2);
		dao.findByNameSurname("Giuseppe", "Sacco");
	
		service.addModule(module1, 1);
		
		
		

	}

}
