package com.exporter.app.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @info Manager class
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Manager extends Employee {

	private Integer numberOfSellers;
	private String country;

	public Manager() {
		super();
	}

	public Manager(String id, String firstName, String lastName, String role, String numberOfSellers, String country) {
		super(id, firstName, lastName, role);
		this.numberOfSellers = Integer.valueOf(numberOfSellers);
		this.country = country;
	}

	public Integer getNumberOfSellers() {
		return this.numberOfSellers;
	}

	public String getCountry() {
		return this.country;
	}

	public void setNumberOfSellers(Integer numOfSellers) {
		this.numberOfSellers = numOfSellers;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
