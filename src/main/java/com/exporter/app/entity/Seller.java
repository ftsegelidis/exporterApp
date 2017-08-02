package com.exporter.app.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.exporter.app.enums.ProductLine;

/**
 * @info Seller class
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Seller extends Employee {

	private ProductLine productLine;
	private String city;
	private double averageSales;
	private boolean active;

	public Seller() {
		super();

	}

	public Seller(String id, String firstName, String lastName, String role, String productLine, String city,
			String averageSales, String active) {
		super(id, firstName, lastName, role);
		this.productLine = Enum.valueOf(ProductLine.class, productLine);
		this.city = city;
		this.averageSales = Double.valueOf(averageSales);
		this.active = Boolean.valueOf(active);
	}

	public ProductLine getProductLine() {
		return this.productLine;
	}

	public String getCity() {
		return this.city;
	}

	public double getAverageSales() {
		return this.averageSales;
	}

	public boolean getActivity() {
		return this.active;
	}

	public void setProductLine(ProductLine prodLine) {
		this.productLine = prodLine;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAverageSales(double averageSales) {
		this.averageSales = averageSales;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
