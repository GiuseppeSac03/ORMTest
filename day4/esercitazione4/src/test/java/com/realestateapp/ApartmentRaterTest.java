package com.realestateapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ApartmentRaterTest {

	private ApartmentRater ap = new ApartmentRater();
	
	@Test
	void whenArea0_thenReturnOk() {
		
		Apartment ap1 = new Apartment(0, null);
		assertEquals(-1, ap.rateApartment(ap1));
		
	}
	
	
	@Test
	void whenListApartementIsEmpty_thenException() {
		
	}

	@ParameterizedTest
	@CsvSource(value = {"100, 700000, 1","100, 1000, 0", "100, 9000000, 2"}, delimiter = ',')
	void ratioUnder0_thenReturnOk(int area, String bd,int expected) {
		BigDecimal bigDecimal = new BigDecimal(bd);
		Apartment ap1 = new Apartment(100, bigDecimal);
		
		assertEquals(expected, ap.rateApartment(ap1));
		
	}
	
		
	
}
