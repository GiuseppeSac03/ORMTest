package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.entity.Address;
import org.example.entity.Document;
import org.example.entity.Person;

public class Main {
	
	public static void main(String[] args) {
		Dao dao = new Dao();
		
		Address address1 = new Address("Via Test 1", "ROMA", "ITALIA", "12345");
		Address address2 = new Address("Via Prova 2", "ROMA", "ITALIA", "11223");
		
		Person person1 = new Person();
		person1.setFirstName("Mario");
		person1.setLastName("Rossi");
		person1.setAge(20);
		person1.setAddress(address1);
		
		Person person2 = new Person();
		person2.setFirstName("Paolo");
		person2.setLastName("Bianchi");
		person2.setAge(30);
		person2.setAddress(address2);
		
		Document doc1 = new Document("AA11223344", person1);
		Document doc2 = new Document("BB55667788", person1);
		Document doc3 = new Document("CC99887766", person2);

		List<Document> docPerson1 = new ArrayList<Document>();
		docPerson1.add(doc1);
		docPerson1.add(doc2);
		person1.setDocuments(docPerson1);
		
		dao.insertPerson(person1);

		List<Document> docPerson2 = new ArrayList<Document>();
		docPerson2.add(doc3);
		person2.setDocuments(docPerson2);
		
		dao.insertPerson(person2);
		
		dao.updatePersonAge(person1.getId(), 21);
		
		Person person = dao.findPerson(person1.getId());
		System.out.println(String.format("%s - %s", person, person.getAddress()));

		System.out.println("delete person with id " + person2.getId());
		dao.deletePerson(person2.getId());
		
		List<Person> persons = dao.findPersons();
		if (persons != null) {
			for (Person p: persons) {
				System.out.println(p);
			}
		}
	}

}
