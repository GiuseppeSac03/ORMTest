package org.example.model;

public class Address {
	
	private int id;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String zipCode;
	
	public Address() {}
	
	public Address(String street, String city, String state, String zipCode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	@Override
	public String toString() {
    	return String.format("%s, %s, %s, %s", street, city, zipCode, state);
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
	       return this.id == other.getId();
	   }
	   return false;
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + id;
	    return result;
	}

}
