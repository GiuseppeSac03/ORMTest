package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.example.entity.Apartment;
import org.example.exception.EntityNotFoundException;

public class ApartmentRater {
	
	private ApartmentDao apartmentDao;

	private static final BigDecimal CHEAP_THRESHOLD = new BigDecimal(6000.0);
	private static final BigDecimal MODERATE_THRESHOLD = new BigDecimal(8000.0);
	
	public ApartmentRater() {}
	
	public ApartmentRater(ApartmentDao apartmentDao) {
		this.apartmentDao = apartmentDao;
	}
	
	/**
	 * Il metodo restituisce la valutazione di un appartamento in base a prezzo e area.
	 * Se area == 0.0 restituisce -1
	 * Se prezzo/area < 6000: restituisce 0
	 * Se prezzo/area >= 6000 e < 8000: restituisce 1
	 * Altrimenti restituisce 2
	 */
	public int rateApartment(Apartment apartment) {
		if (apartment.getArea() == 0.0) {
			return -1;
		}
		BigDecimal ratio = apartment.getPrice().divide(new BigDecimal(apartment.getArea()), RoundingMode.HALF_UP);
		
		
		if (ratio.compareTo(CHEAP_THRESHOLD) < 0) {
			return 0;
		} else if (ratio.compareTo(CHEAP_THRESHOLD) >= 0 && ratio.compareTo(MODERATE_THRESHOLD) < 0) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public int rateApartment(int apartmentId) throws EntityNotFoundException {
		Apartment apartment = apartmentDao.findApartment(apartmentId);
		if (apartment != null) {
			
			if (apartment.getArea() == 0.0)
				return -1;
				
			BigDecimal ratio = apartment.getPrice().divide(new BigDecimal(apartment.getArea()), RoundingMode.HALF_UP);
			
			if (ratio.compareTo(CHEAP_THRESHOLD) < 0) {
				return 0;
			} else if (ratio.compareTo(CHEAP_THRESHOLD) >= 0 && ratio.compareTo(MODERATE_THRESHOLD) < 0) {
				return 1;
			} else {
				return 2;
			}
		} else
			throw new EntityNotFoundException("Apartment not found");
	}
	
	/**
	 * Calcola la valutazione media degli appartamenti ricevuti in input
	 */
	public double calculateAverageRating(List<Apartment> apartments) {
		if (apartments.isEmpty()) {
			throw new RuntimeException("Cannot calculate average rating for empty list");
		}
		int sumRatings = 0;
		for (Apartment apartment : apartments) {
			sumRatings += rateApartment(apartment);
		}
		return sumRatings * 1.0 / apartments.size();	
	}
	
	
	
}
