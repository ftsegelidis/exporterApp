package com.exporter.app.entity;

import com.exporter.app.enums.Role;

/**
 * @info Employees factory pattern
 * 
 */
public class EmployeeFactory {
	public Employee getEmployee(String roleType, String id, String firstname, String lastname, String role,
			String numOfSellers, String country, String productLine, String city, String averageSales, String active) {
		if (Role.MANAGER.name().equals(roleType))
			return new Manager(id, firstname, lastname, role, numOfSellers, country);
		else if (Role.SELLER.name().equals(roleType))
			return new Seller(id, firstname, lastname, role, productLine, city, averageSales, active);
		return null;
	}
}
