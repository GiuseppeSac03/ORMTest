package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.example.entity.Apartment;
import org.example.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

//@ExtendWith(MockitoExtension.class)
public class ApartmentRaterTestMock {

	private static ApartmentDao apartmentDao;
	
	private static ApartmentRater apartmentRater;
	
	@BeforeAll
	static void init() {
		// creazione mock dao
		apartmentDao = Mockito.mock(ApartmentDao.class);
		apartmentRater = new ApartmentRater(apartmentDao);
	}
	
	@ParameterizedTest
	@CsvSource(value = {"72.0, 250000.0, 0", "48.0, 350000.0, 1", "30.0, 600000.0, 2"})
	void should_ReturnCorrectRating_When_CorrectApartment(Double area, Double price, int rating) throws EntityNotFoundException {
		Apartment apartment = new Apartment(area, new BigDecimal(price));

		// mock comportamento del metodo findApartment
		Mockito.when(apartmentDao.findApartment(Mockito.anyInt())).thenReturn(apartment);
		
		int actual = apartmentRater.rateApartment(0);
		assertEquals(rating, actual);
	}
	
	@Test
	void should_ReturnException_When_NotFoundApartment() throws EntityNotFoundException {
		Mockito.when(apartmentDao.findApartment(Mockito.anyInt())).thenReturn(null);
		
		Executable executable = () -> apartmentRater.rateApartment(0);
		
		EntityNotFoundException e = assertThrows(EntityNotFoundException.class, executable);
		assertEquals("Apartment not found", e.getMessage());
	}
	
	@Test
	void should_ReturnErrorValue_When_IncorrectApartment() throws EntityNotFoundException {
		Apartment apartment = new Apartment(0.0, new BigDecimal(350000.0));
		Mockito.when(apartmentDao.findApartment(Mockito.anyInt())).thenReturn(apartment);
		int expected = -1;
		int actual = apartmentRater.rateApartment(1234);

		assertEquals(expected, actual);		
	}
	
	@Test
	void  should_ThrowException_When_Area1000() {
		
		Apartment ap = new Apartment();
		Mockito.when(apartmentDao.updateArea(Mockito.anyInt(), 2000));
		
	}

}
