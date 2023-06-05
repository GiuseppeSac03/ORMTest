package org.example.model;

public class Document {
	
	private int id;
	
	private String code;
	
	public Document() {}
	
	public Document(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
    	return String.format("%d - %s", id, code);
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
