package com.academy.cic.ibm;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.academy.cic.model.Course;
import com.academy.cic.util.DateUtil;
import com.academy.cic.util.HibernateUtil;

public class Dao {

	private static final Logger logger = Logger.getLogger(Dao.class.getName());
		
	
	public Integer insertCourse(Course course) {
		
		logger.info("insertCourse");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer result = null;
		
		try {
			tx = session.beginTransaction();
			result = (Integer) session.save(course); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		
		return result;
	}
	
	
	public Course findCourseByName(String name) {
		
		logger.info("findCourseByName");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Course course = null;
		
		try {
			TypedQuery<Course> query = session.createQuery("FROM Course c WHERE c.name = :cName", Course.class);
			query.setParameter("cName", name);
			query.setMaxResults(1);
			course = query.getSingleResult();
			if (course != null) {
				Hibernate.initialize(course.getSetModule());
			}
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		
		return course;
	}
	
	
	public void addModuleToCourse(com.academy.cic.model.Module module, int courseId) {
		
		logger.info("addModuleToCourse");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			Course course = session.get(Course.class, courseId);
			if (course != null) {
				tx = session.beginTransaction();
				Set<com.academy.cic.model.Module> s = course.getSetModule();
				s.add(module);
				session.update(course);
				tx.commit();
			}
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
	
	
	public Set<com.academy.cic.model.Module> findCourseModules(int courseId) {
		
		logger.info("findCourseModules");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<com.academy.cic.model.Module> result = null;
		
		try {
			Course course = session.get(Course.class, courseId);
			if (course != null) {
				Hibernate.initialize(course.getSetModule());
				result = course.getSetModule();
			}
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return result;
	}
	
	
	public List<Course> findStartingCourseInRange(Date from, Date to) {
		
		logger.info("findStartingCourseInRange");
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Course> result = new LinkedList<Course>();
		
		try {	
			TypedQuery<Course> query = session.createQuery("FROM Course c WHERE c.startDate >= ?0 AND c.startDate <= ?1", Course.class);
			query.setParameter(0, DateUtil.stringToDateDB(from.getYear() + "-" + from.getMonth() + "-" + from.getDate()));
			query.setParameter(1, DateUtil.stringToDateDB(to.getYear() + "-" + to.getMonth() + "-" + to.getDate()));
			result = query.getResultList();
			for (Course c: result) {
				Hibernate.initialize(c.getSetModule());
			}
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		
		return result;
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
		
		logger.info("updateStartDate");
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