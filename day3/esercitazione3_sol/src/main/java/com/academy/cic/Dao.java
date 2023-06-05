package com.academy.cic;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import com.academy.cic.entity.Course;
import com.academy.cic.entity.Registration;
import com.academy.cic.entity.Student;
import com.academy.cic.util.JpaUtil;

public class Dao {
	
	private static final Logger logger = Logger.getLogger(Dao.class.getName());
	
	public void insertStudent(Student student){
		logger.info("insertStudent");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
	   
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(student); 
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(); 
		} finally {
			entityManager.close(); 
		}
	}
	
	public void insertCourse(Course course){
		logger.info("insertCourse");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
	   
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(course); 
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(); 
		} finally {
			entityManager.close(); 
		}
	}
	
	public void registryStudentCourse(Registration registration){
		logger.info("registryStudentCourse");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
	   
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(registration); 
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(); 
		} finally {
			entityManager.close(); 
		}
	}
	
	public List<Student> findByNameSurname(String name, String surname) {
		logger.info("findByNameSurname");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Student> students = null;
		try {
			TypedQuery<Student> query = entityManager.createNamedQuery("Student.findByNameSurname", Student.class);
			query.setParameter("name", name);
			query.setParameter("surname", surname);
			students = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return students;
	}
	
	public void updateCourseGradeById(int registrationId, int grade) {
		logger.info("updatePersonAge");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Registration r = entityManager.find(Registration.class, registrationId);
			r.setGrade(grade);
			entityManager.merge(r);
			entityManager.getTransaction().commit();
		} catch (HibernateException e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
	}
	
	public Student findStudent(int studentId) {
		logger.info("findStudent");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		Student student = null;
		try {
			student = entityManager.find(Student.class, studentId);
			if (student != null)
				Hibernate.initialize(student.getRegistrations());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return student;
	}
	
	public List<Registration> findStudentRegistrations(int studentId) {
		logger.info("findStudentCourses");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Registration> registrations = null;
		try {
			TypedQuery<Registration> query = entityManager.createNamedQuery("Registration.findByStudentId", Registration.class);
			query.setParameter("studentId", studentId);
			registrations = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return registrations;
	}
	
	
}
