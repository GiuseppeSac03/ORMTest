package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.example.model.Address;
import org.example.model.Document;
import org.example.model.Person;

public class Main {
	
	public static void main(String[] args) {
		Dao dao = new Dao();
		
		Address address1 = new Address("Via Test 1", "ROMA", "ITALIA", "12345");
		Address address2 = new Address("Via Prova 2", "ROMA", "ITALIA", "11223");
		
		Document doc1 = new Document("AA11223344");
		Document doc2 = new Document("BB55667788");
		Document doc3 = new Document("CC99887766");
		
		Person person1 = new Person();
		person1.setFirstName("Mario");
		person1.setLastName("Rossi");
		person1.setAge(20);
		person1.setAddress(address1);
		Set<Document> docEmp1 = new HashSet<Document>();
		docEmp1.add(doc1);
		docEmp1.add(doc2);
		person1.setDocuments(docEmp1);
		
		dao.insertPerson(person1);
		
		Person person2 = new Person();
		person2.setFirstName("Paolo");
		person2.setLastName("Bianchi");
		person2.setAge(30);
		person2.setAddress(address2);
		Set<Document> docEmp2 = new HashSet<Document>();
		docEmp2.add(doc3);
		person2.setDocuments(docEmp2);
		
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
