package com.exporter.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.junit.Test;

import com.exporter.app.entity.Employee;
import com.exporter.app.entity.Manager;
import com.exporter.app.enums.Role;
import com.exporter.app.exception.ExporterApplicationException;
import com.exporter.app.exception.ValidationException;

public class ExporterServiceTest {

	@Test
	public void testReadFileProcess() throws ExporterApplicationException, ValidationException, IOException {
		File temp = File.createTempFile("exporter-test", ".txt");
		temp.deleteOnExit();
		String line = "4,Marge,Simpson,MANAGER,4,Spain,,,,";
		Files.write(temp.toPath(), line.getBytes(StandardCharsets.UTF_8));

		FileManager fm = new FileManager();
		List<Employee> employeeList = fm.readFromFile(temp.getAbsolutePath(), Role.MANAGER.name());

		assertEquals(1, employeeList.size());
		assertTrue(employeeList.get(0) instanceof Manager);
		assertEquals("4", employeeList.get(0).getId());
	}
}
