package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.BadRequestException;

import org.example.entity.Apartment;
import org.example.util.JpaUtil;

public class ApartmentDao {
	
	public Apartment findApartment(int apartmentId) {
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		Apartment apartment = null;
		try {
			apartment = entityManager.find(Apartment.class, apartmentId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return apartment;
	}

	public float updateArea(int apartmentId, int area) throws Exception {
	    EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
	    Apartment apartment = null;
	    ApartmentRater ar = new ApartmentRater();
	    float newRating = 0;
	    try {
	        if (area > 1000) {
	            throw new BadRequestException("Cannot update area to a value greater than 1000");
	        }
	        apartment = entityManager.find(Apartment.class, apartmentId);
	        if (apartment == null) {
	            throw new EntityNotFoundException("Apartment not found");
	        }
	        apartment.setArea(area);
	        entityManager.getTransaction().begin();
	        entityManager.persist(apartment);
	        entityManager.getTransaction().commit();
	        newRating = ar.rateApartment(apartment);
	    } catch (Exception e) {
	        entityManager.getTransaction().rollback();
	        throw e;
	    } finally {
	        entityManager.close();
	    }
	    return newRating;
	}


}
