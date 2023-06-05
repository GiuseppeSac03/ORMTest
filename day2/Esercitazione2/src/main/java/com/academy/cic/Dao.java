package com.academy.cic;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;

import com.academy.cic.util.*;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.academy.cic.model.Course;
import com.academy.cic.model.Module;

public class Dao {

	private static final Logger logger = Logger.getLogger(Dao.class.getName());
	

	// il metodo restituisce l’id generato per il corso
	
	public Integer insertCourse(Course course){
		logger.info("insertCourse");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
	   
		try {
			tx = session.beginTransaction();
			Integer id_s = (Integer) session.save(course); 
			tx.commit();
			return id_s;
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
			
		} finally {
			session.close(); 
		}
		return null;
		
	}
	
	
	public Course findCourseByName(String name) {
		logger.info("findCourseByName");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Course course = null;
		try {
			Query<Course> query = session.createQuery("FROM course c WHERE c.name = :name", Course.class);
			query.setParameter("name", name);
			course = query.getSingleResult(); 

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close(); 
		}
		return course;
	}
	
	public void addModuleToCourse(Module module, int courseId) {
		logger.info("addModuleToCourse");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Module module1 = session.get(Module.class, courseId);
			session.persist(module1);
			tx.commit(); 
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		}
	}
	
	public Set<Module> findCourseModules(int courseId){
		
		logger.info("findCourseModules");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<Module> module = null;
		try {
			TypedQuery<Module> query = session.createQuery("FROM module m WHERE m.course_id = :course_id", Module.class);
			query.setParameter("course_id", courseId);
			module = (Set<Module>) query.getResultList(); 

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close(); 
		}
		return module;
		
	}
	
	
	public List<Course> findStartingCourseInRange(Date start_date, Date end_date){
		logger.info("FindStartingCourseInRange");
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Course> course = null;
		try {
			TypedQuery<Course> query = session.createQuery("FROM course c WHERE start_date BETWEEN :start_date AND :end_date", Course.class);
			query.setParameter("start_date", start_date);
			query.setParameter("end_date", end_date);
			course = query.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close(); 
		}
		return course;
		
	}
	
	public void updateStartDate(Date startDate, int courseId) {
		logger.info("updateStartDate");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Course course = session.get(Course.class, courseId);
			course.setStartDate(startDate);
			session.update(course);
			tx.commit(); 
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		}
		
		
	}
	
	public void deleteCourse(int courseId) {
		logger.info("DeleteCourse");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Course course = session.get(Course.class, courseId); 
			session.delete(course); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
	
}
