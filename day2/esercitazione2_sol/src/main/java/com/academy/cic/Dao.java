package com.academy.cic;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.academy.cic.util.HibernateUtil;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.academy.cic.model.Course;
import com.academy.cic.model.Module;

public class Dao {
	
	private static final Logger logger = Logger.getLogger(Dao.class.getName());
	
	public Integer insertCourse(Course course) throws SQLException {
		logger.info("insertCourse");
		Integer courseId = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
        try {
			tx = session.beginTransaction();
			courseId = (Integer) session.save(course); 
			tx.commit();
        } catch (HibernateException e) {
        	if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return courseId;
    }
	
	public Course findCourseByName(String name) throws SQLException {
		logger.info("findCourseByName");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Course course = null;
        try {
			TypedQuery<Course> query = session.createQuery("FROM Course WHERE name = :name", Course.class);
			query.setParameter("name", name);
			query.setMaxResults(1);
			course = query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return course;
    }
	
	public void addModuleToCourse(Module module, int courseId) throws SQLException {
		logger.info("addModuleToCourse");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
        try {
        	Course course = session.get(Course.class, courseId);
        	tx = session.beginTransaction();
        	course.addModule(module);
			tx.commit();
        } catch (HibernateException e) {
        	if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
        	session.close();
        }
    }
	
	public void addModuleToCourseBiDirectional(Module module) throws SQLException {
		logger.info("addModuleToCourseBiDirectional");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
        try {
        	tx = session.beginTransaction();
        	session.persist(module);
			tx.commit();
        } catch (HibernateException e) {
        	if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
        	session.close();
        }
    }
	
	public Set<Module> findCourseModules(int courseId) throws SQLException {
		logger.info("findCourseModules");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<Module> modules = null;
        try {
        	Course course = session.get(Course.class, courseId);
        	if (course != null) {
        		Hibernate.initialize(course.getModules());
        		modules = course.getModules();
        	}
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return modules;
    }
	
	public List<Course> findStartingCourseInRange(Date from, Date to) throws SQLException {
		logger.info("findStartingCourseInRange");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Course> courses = null;
        try {
			TypedQuery<Course> query = session.createQuery("FROM Course WHERE startDate BETWEEN :from AND :to", Course.class);
			query.setParameter("from", from);
			query.setParameter("to", to);
			courses = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return courses;
    }

	public Module findModuleByName(String moduleName) throws SQLException {
		logger.info("findModuleByName");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Module module = null;
        try {
			TypedQuery<Module> query = session.createQuery("FROM Module WHERE name = :moduleName", Module.class);
			query.setParameter("moduleName", moduleName);
			query.setMaxResults(1);
			module = query.getSingleResult();
			
			// for bidirectional
//			Hibernate.initialize(module.getCourse());
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return module;
    }
	
	public void updateStartDate(Date startDate, int courseId) throws SQLException {
		logger.info("updateStartDate");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
        try {
			tx = session.beginTransaction();
			Query query = session.createQuery("UPDATE Course SET startDate = ?0 WHERE id = ?1");
			query.setParameter(0, startDate);
			query.setParameter(1, courseId);
			query.executeUpdate();
			tx.commit();
        } catch (HibernateException e) {
        	if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
        	session.close();
        }
    }
	
	public void deleteCourse(int courseId) throws SQLException {
		logger.info("deleteCourse");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
        try {
        	Course course = session.get(Course.class, courseId);
        	tx = session.beginTransaction();
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
