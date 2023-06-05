package com.academy.cic;
<<<<<<< HEAD
import java.sql.SQLException;

import com.academy.cic.model.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		Dao service = new Dao();
		
		Student student = new Student(1, "Giuseppe", "Sacco", 19);
		
		Course course = new Course(1, "Algebra");
		
		Registration registration = new Registration(1, 1, 1, 30);
		
		
		// service.insertStudent(student);
		
		// service.insertCourse(course);
		
		// service.registryStudentCourse(registration);
		
		// service.selectByNameSurname("Giuseppe", "Sacco");
		
		// service.updateCourseGradeById(1, 29);
		
		// service.findStudentCourses(1);
		
		service.corsiPerStudente();
		
	}
	

=======




import java.sql.SQLException;
import java.util.List;

import com.academy.cic.model.Course;
import com.academy.cic.model.CourseRegistration;
import com.academy.cic.model.Student;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		Dao service = new Dao();
			
		Student student = new Student(1, "Daniele", "Denza", 26);
		Course course = new Course(1, "JDBC");
		CourseRegistration registration = new CourseRegistration(1, 1, 1, 30);

		//metodo insertStudent
		//service.insertStudent(student);

		//metodo insertCourse
		//service.insertCourse(course);
			
		// metodo registryStudentCourse
		//service.registryStudentCourse(registration);
			
		// metodo selectByNameSurname
		//Student student2 = service.selectByNameSurname("Daniele", "Denza");
		//System.out.println(student2);
//			
		//metodo updateCourseGradeById
		//service.updateCourseGradeById(1, 26);
//			
		// Testo il metodo findStudentCourses
		//System.out.println(service.findStudentCourses(1));
		
		int courseCount = service.getCourseCountByStudentId(1);
		System.out.println("Il numero di corsi dello studente con id 1 è: " + courseCount);
	}
>>>>>>> refs/remotes/origin/main
}
