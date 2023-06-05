package jpa_esempio;

import org.example.Dao;
import org.example.entity.Address;
import org.example.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DaoTest {
	
	private Dao dao = new Dao();
	
	@Test
	void whenInsert_thenFindOk() {
		Person person = new Person("Paolo", "Verdi", 20);
		person.setAddress(new Address("Via Test 123", "Roma", "Italia", "11223"));
		dao.insertPerson(person);

		Person actual = dao.findPerson(person.getId());

		Assertions.assertEquals("Paolo", actual.getFirstName());
		Assertions.assertEquals("Verdi", actual.getLastName());
		Assertions.assertEquals(20, actual.getAge());
	}
	
	@Test
	void whenFindWrongId_thenNull() {
		Person actual = dao.findPerson(0);
		Assertions.assertNull(actual);
	}
	
	@Test
	void whenUpdateAge_thenFindOk() {
		Person person = new Person("Paolo", "Verdi", 30);
		person.setAddress(new Address("Via Test 123", "Roma", "Italia", "11223"));
		dao.insertPerson(person);

		int expectedAge = 40;
		dao.updatePersonAge(person.getId(), expectedAge);
		Person actual = dao.findPerson(person.getId());

		Assertions.assertEquals(expectedAge, actual.getAge());
	}

}
