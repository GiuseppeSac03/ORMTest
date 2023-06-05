package org.example;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;

import org.example.model.Person;
import org.example.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Dao {
	
	private static final Logger logger = Logger.getLogger(Dao.class.getName());
	
	public void insertPerson(Person person){
		logger.info("insertPerson");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
	   
		try {
			tx = session.beginTransaction();
			session.persist(person); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
	
	public Person findPerson(int personId) {
		logger.info("findPerson");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Person person = null;
		try {
			person = session.get(Person.class, personId);
			if (person != null) {
				Hibernate.initialize(person.getAddress());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close(); 
		}
		return person;
	}

	public List<Person> findPersons() {
		logger.info("findPersons");
		List<Person> persons = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			persons = session.createQuery("FROM Person", Person.class).getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close(); 
		}
		return persons;
	}

	public void updatePersonAge(int personId, int age) {
		logger.info("updatePersonAge");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Person person = session.get(Person.class, personId);
			person.setAge(age);
			session.update(person);
			tx.commit(); 
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		}
	}
	
	public List<Person> findPersonsBySurnameNamedParam(String surname) {
		logger.info("findPersonsBySurnameNamedParam");
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Person> persons = null;
		try {
			TypedQuery<Person> query = session.createQuery("FROM Person p WHERE p.lastName = :surname", Person.class);
			query.setParameter("surname", surname);
			persons = query.getResultList(); 

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close(); 
		}
		return persons;
	}
	
	public List<Person> findPersonsBySurnamePosParam(String surname) {
		logger.info("findPersonsBySurnamePosParam");
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Person> persons = null;
		try {
			TypedQuery<Person> query = session.createQuery("FROM Person p WHERE p.lastName = ?0", Person.class);
			query.setParameter(0, surname);
			persons = query.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close(); 
		}
		return persons;
	}
	
	public void deletePerson(int personId){
		logger.info("deletePerson");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Person person = session.get(Person.class, personId); 
			session.delete(person); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
	
	public void deleteAllPersons(){
		logger.info("deleteAllPersons");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.createQuery("DELETE FROM Person").executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

}
