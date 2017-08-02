package com.exporter.app.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.exporter.app.enums.Role;

/**
 * @info Default common methods for Employees
 * 
 */
@XmlRootElement(name = "employee")
@XmlSeeAlso({ Seller.class, Manager.class })
@XmlAccessorType(XmlAccessType.FIELD)

public class Employee {

	private String id;
	private String firstname;
	private String lastname;
	private Role role;

	public Employee() {
	}

	public Employee(String id, String firstname, String lastname, String role) {
		this.role = Enum.valueOf(Role.class, role);
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
