package com.academy.cic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.academy.cic.model.Course;
import com.academy.cic.model.Registration;
import com.academy.cic.model.Student;
import com.academy.cic.model.StudentNumCourses;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		Dao dao = new Dao();
		
		List<Student> sList = new ArrayList<Student>();
		sList.add(new Student(1, "Mario", "Rossi", 20));
		sList.add(new Student(2, "Paolo", "Bianchi", 23));
		sList.add(new Student(3, "Paolo", "Rossi", 24));
		sList.add(new Student(4, "Mario", "Bianchi", 25));
		
		Course course1 = new Course(1, "First course");
		Course course2 = new Course(2, "Second course");
		Course course3 = new Course(3, "Third course");
		
		List<Registration> rList = new ArrayList<Registration>();
		rList.add(new Registration(1, 1, 1));
		rList.add(new Registration(2, 1, 2));
		rList.add(new Registration(3, 1, 3));
		rList.add(new Registration(4, 2, 2));
		rList.add(new Registration(5, 2, 3));
		rList.add(new Registration(6, 3, 1));
		rList.add(new Registration(7, 4, 1));
		rList.add(new Registration(8, 4, 3));

		dao.insertCourse(course1);
		dao.insertCourse(course2);
		dao.insertCourse(course3);
		
		for (Student s: sList)
			dao.insertStudent(s);
		
		for (Registration r: rList)
			dao.registryStudentCourse(r);

		System.out.println(dao.selectByNameSurname("Paolo", "Bianchi"));
		
		dao.updateCourseGradeById(1, 30);
		
		List<Course> courses = dao.findStudentCourses(1);
		if (courses != null) {
			for (Course c: courses)
				System.out.println(c);
		}
		
		List<StudentNumCourses> studentCountList = dao.findStudentIdNumCourses();
		if (studentCountList != null) {
			for (StudentNumCourses s: studentCountList)
				System.out.println(s.toStringId());
		}
		
		studentCountList = dao.findStudentDataNumCourses();
		if (studentCountList != null) {
			for (StudentNumCourses s: studentCountList)
				System.out.println(s.toString());
		}
		
	}

}
