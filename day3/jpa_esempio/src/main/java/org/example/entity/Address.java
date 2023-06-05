package org.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class Address extends Base {
	
	@Column(name = "street_name", nullable = false, length = 64)
	private String street;
	
	@Column(name = "city_name", nullable = false, length = 64)
	private String city;
	
	@Column(name = "state_name", nullable = false, length = 64)
	private String state;
	
	@Column(name = "zipcode", nullable = false, length = 10)
	private String zipCode;
	
	@OneToOne(mappedBy = "address")
	private Person person;
	
	public Address() {}
	
	public Address(String street, String city, String state, String zipCode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Override
	public boolean equals(Object ob) {
	   if (ob == null) {
	       return false;
	   }
	   if (this == ob) {
	       return true;
	   }
	   if (ob instanceof Address) {
		   Address other = (Address) ob;
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
    	return String.format("%s, %s, %s, %s", street, city, zipCode, state);
	}

}
