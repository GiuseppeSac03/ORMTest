package org.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENT")
public class Document extends Base {
	
	@Column(nullable = false, length = 30)
	private String code;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	public Document() {}
	
	public Document(String code, Person person) {
		this.code = code;
		this.person = person;
	}
	
	public Document(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public boolean equals(Object ob) {
	   if (ob == null) {
	       return false;
	   }
	   if (this == ob) {
	       return true;
	   }
	   if (ob instanceof Document) {
		   Document other = (Document) ob;
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
    	return String.format("%d - %s", super.getId(), code);
	}

}
