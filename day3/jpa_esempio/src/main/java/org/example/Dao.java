package org.example;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.example.entity.Person;
import org.example.util.JpaUtil;
import org.hibernate.HibernateException;

public class Dao {
	
	private static final Logger logger = Logger.getLogger(Dao.class.getName());
	
	public void insertPerson(Person person){
		logger.info("insertPerson");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
	   
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(person); 
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(); 
		} finally {
			entityManager.close(); 
		}
	}
	
	public Person findPerson(int personId) {
		logger.info("findPerson");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		Person person = null;
		try {
			person = entityManager.find(Person.class, personId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return person;
	}

	public List<Person> findPersons() {
		logger.info("findPersons");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Person> persons = null;
		try {
			persons = entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return persons;
	}

	public void updatePersonAge(int personID, int age) {
		logger.info("updatePersonAge");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();

		try {
			Person person = entityManager.find(Person.class, personID);
			person.setAge(age);
			entityManager.getTransaction().begin();
			//metodo merge
			entityManager.merge(person);
			entityManager.getTransaction().commit();
		} catch (HibernateException e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
	}

	public List<Person> findPersonsBySurnameNamedParam(String surname) {
		logger.info("findPersonsBySurnameNamedParam");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Person> persons = null;
		try {
			TypedQuery<Person> query = entityManager.createNamedQuery("Person.findBySurname", Person.class);
			query.setParameter("surname", surname);
			persons = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return persons;
	}
	
	public List<Person> findPersonsBySurnamePosParam(String surname) {
		logger.info("findPersonsBySurnamePosParam");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Person> persons = null;
		try {
			TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p WHERE p.lastName = ?0", Person.class);
			query.setParameter(0, surname);
			persons = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return persons;
	}
	
	public List<Person> findPersonsBySurnameNative(String surname) {
		logger.info("findPersonsBySurnameNative");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		List<Person> persons = null;
		try {
			Query query = entityManager.createNativeQuery("SELECT * FROM PERSON WHERE last_name = ?0");
			query.setParameter(0, surname);
			persons = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return persons;
	}
	
	public void deletePerson(int personId){
		logger.info("deletePerson");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		
		try {
			Person person = entityManager.find(Person.class, personId);
			entityManager.getTransaction().begin();
			entityManager.remove(person); 
			entityManager.getTransaction().commit();
		} catch (HibernateException e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(); 
		} finally {
			entityManager.close(); 
		}
	}
	
	public void deleteAllPersons(){
		logger.info("deleteAllPersons");
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			entityManager.createNamedQuery("Person.deleteAllPersons").executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(); 
		} finally {
			entityManager.close(); 
		}
	}

}
