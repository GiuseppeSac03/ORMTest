package org.example.entity;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
@NamedQuery(name="Person.findBySurname", query = "SELECT p FROM Person p WHERE p.lastName = :surname")
@NamedQuery(name="Person.deleteAllPersons", query = "DELETE FROM Person")
public class Person extends Base {
	
	private static final Logger logger = Logger.getLogger(Person.class.getName());
	
	@Column(name = "first_name", nullable = false, length = 32)
	private String firstName; 
	
	@Column(name = "last_name", nullable = false, length = 32)
	private String lastName;
	
	@Column(nullable = false)
	private int age;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Document> documents;
	
	@PrePersist
	public void logNewUserAttempt() {
	    logger.info("Attempting to add new person with name: " + firstName + lastName);
	}
	    
	@PostPersist
	public void logNewUserAdded() {
	    logger.info("Added person " + firstName + lastName + " with id: " + getId());
	}
	
	public Person() {}
	
	public Person(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
	@Override
	public boolean equals(Object ob) {
	   if (ob == null) {
	       return false;
	   }
	   if (this == ob) {
	       return true;
	   }
	   if (ob instanceof Person) {
		   Person other = (Person) ob;
	       return this.getId() == other.getId();
	   }
	   return false;
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + getId();
	    return result;
	}
	
	@Override
	public String toString() {
    	return String.format("%d - %s - %s - %d", super.getId(), firstName, lastName, age);
	}

}
