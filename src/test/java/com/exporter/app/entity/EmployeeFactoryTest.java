package com.exporter.app.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.exporter.app.entity.Employee;
import com.exporter.app.entity.EmployeeFactory;
import com.exporter.app.entity.Manager;
import com.exporter.app.entity.Seller;
import com.exporter.app.enums.ProductLine;
import com.exporter.app.enums.Role;

public class EmployeeFactoryTest {
	
	private Employee employee;
	private EmployeeFactory emplFactory;
	
	@Before
	public void setUp(){
		employee = new Employee();
		emplFactory = new EmployeeFactory();
	}

	@Test
	public void testGetEmployeeIsSeller() {
	
		// call
		employee =  emplFactory.getEmployee("SELLER", "2", "mysellername", "mysellerlastname", Role.SELLER.name(), "", "",
				ProductLine.LINE_A.name(), "Athens", "505.56", "true");
		assertTrue(employee instanceof Seller);
		assertFalse(employee instanceof Manager);
		assertEquals(employee.getId(),"2");
	}

	@Test
	public void testGetEmployeeIsManager() {
		
		// call
		employee = emplFactory.getEmployee("MANAGER", "1", "myname", "mylastname", Role.MANAGER.name(), "20", 
					"Greece", "", "", "","");

		assertTrue(employee instanceof Manager);
		assertFalse(employee instanceof Seller);
		assertEquals(employee.getId(),"1");
	}
}
