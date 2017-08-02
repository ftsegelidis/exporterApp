package com.exporter.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.exporter.app.entity.Employee;
import com.exporter.app.entity.EmployeeFactory;
import com.exporter.app.entity.Manager;
import com.exporter.app.enums.Role;
import com.exporter.app.exception.ExporterApplicationException;
import com.exporter.app.service.FileManager;

public class ExporterServiceTest {

	private String[] args = { "input.txt", "JSON", "MANAGER" };
	

	@Before
	public void setUp() {	
	
	}

	@Test
	public void testReadFileProcess() throws ExporterApplicationException {
		
		//build
		FileManager fm = Mockito.mock(FileManager.class) ;
		List<Employee> employeeList = new ArrayList<Employee>();
		EmployeeFactory empFactory = new EmployeeFactory();
		Scanner scanner = Mockito.mock(Scanner.class);
		String currentLine = "4,Marge,Simpson,MANAGER,4,Spain,,,,";
		String[] elements = currentLine.split(",");
		
		//check
		Mockito.when(scanner.hasNext()).thenReturn(true);
		Mockito.when(scanner.nextLine()).thenReturn(currentLine);
		
		//call
		Manager manager = (Manager) empFactory.getEmployee(Role.MANAGER.name(), elements[0],
				elements[1], elements[2], elements[3], elements[4], elements[5], null, null, null,
				null);
		employeeList.add(manager);
		
		Mockito.when(fm.readFromFile(Mockito.anyString(), Mockito.anyString())).thenReturn(employeeList);
		fm.readFromFile(args[0], args[2]);
		
		//verify
		Mockito.verify(fm).readFromFile(ArgumentMatchers.eq("input.txt"), ArgumentMatchers.eq(Role.MANAGER.name()));

	}
}
