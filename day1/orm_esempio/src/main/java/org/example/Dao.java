package org.example;

import org.example.entity.Book;
import org.example.entity.Publisher;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Dao {
	
	public void addPublisher(Publisher publisher){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
	   
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(publisher); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
	
	public void addBook(Book book){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
	   
		try {
			tx = session.beginTransaction();
			session.persist(book); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
	
	public Book findBook(String isbn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Book book = null;
		try {
			book = (Book)session.get(Book.class, isbn);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close(); 
		}
		return book;
	}
	
	public void deletePublisher(String code){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
	   
		try {
			tx = session.beginTransaction();
			Publisher publisher = session.get(Publisher.class, code);
			session.delete(publisher);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

}
