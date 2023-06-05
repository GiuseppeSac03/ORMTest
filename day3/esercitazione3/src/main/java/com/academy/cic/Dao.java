package com.academy.cic;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;

import com.academy.cic.entity.Course;
import com.academy.cic.entity.Registration;
import com.academy.cic.entity.Student;
import com.academy.cic.util.JpaUtil;
import com.academy.cic.entity.Module;

public class Dao {
	
	private static final Logger logger = Logger.getLogger(Dao.class.getName());
	
	public void insertStudent(Student student) {
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
	
	public void insertCourse(Course course) {
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
	
	public void registryStudentCourse(Registration registration) {
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
	
	
	public List<Student> findByNameSurname(String name, String surname){
		logger.info("findStudents");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Student> students = null;
		try {
			Query query = entityManager.createNativeQuery("SELECT * FROM student WHERE first_name = ?0 and last_name  = ?1");
			query.setParameter(0, name);
			query.setParameter(1, surname);
			students = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return students;
	}
	
	public void updateCourseGradeById(int registrationId, int grade) {
		logger.info("updateGrade");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Registration registration = entityManager.find(Registration.class, registrationId);
			registration.setGrade(grade);
			entityManager.getTransaction().begin();
			entityManager.merge(registration);
			entityManager.getTransaction().commit();
		} catch (HibernateException e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
	}
	
	public void insertModule(Module module) {
		logger.info("insertModule");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
	   
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(module); 
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(); 
		} finally {
			entityManager.close(); 
		}
	}
	
	public Course findCourse(int courseId) {
		logger.info("findCourse");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		Course course = null;
		try {
			course = entityManager.find(Course.class, courseId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return course;
	}
	
	public List<Module> findModulesByCourseId(int courseId) {
		logger.info("findModules");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Module> modules = null;
		try {
			TypedQuery<Module> query = entityManager.createQuery("SELECT m FROM Module m WHERE m.course.id = :courseId", Module.class);
			query.setParameter("courseId", courseId);
			modules = query.getResultList();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return modules;
	}
	
	

	
 
}
