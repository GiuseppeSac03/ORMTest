package org.example.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="APARTMENT")
public class Apartment {
	
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@Column
	private double area;
	
	@Column
	private BigDecimal price;
	
	public Apartment() {}
	
	public Apartment(double area, BigDecimal price) {
		this.area = area;
		this.price = price;
	}
	
	public Apartment(int id, double area, BigDecimal price) {
		this.id = id;
		this.area = area;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Apartment [area=" + area + ", price=" + price + "]";
	}
	
}
