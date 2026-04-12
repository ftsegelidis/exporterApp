package com.exporter.app.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exporter.app.entity.Employee;
import com.exporter.app.entity.EmployeeFactory;
import com.exporter.app.entity.Manager;
import com.exporter.app.entity.Seller;
import com.exporter.app.enums.Role;
import com.exporter.app.exception.ExporterApplicationException;
import com.exporter.app.exception.ValidationException;
import com.exporter.app.util.ValidatorUtil;

/**
 * @info Helper class for file basic functions
 *
 */
public class FileManager {

	private final static Logger logger = LogManager.getLogger(FileManager.class);

	public FileManager() {
	}

	/**
	 * @param filename
	 * @param roleType
	 * @return List<Employee>
	 * @throws ExporterApplicationException
	 * @throws ValidationException
	 * @info Reads from txt file. Check if is manager or seller and fills each
	 *       list
	 */
	public List<Employee> readFromFile(String filename, String roleType)
			throws ExporterApplicationException, ValidationException {
		logger.debug("Reading from txt file....");
		Path filePath = Paths.get(filename);
		List<Employee> employeeList = new ArrayList<Employee>();
		EmployeeFactory empFactory = new EmployeeFactory();
		try (Scanner scanner = new Scanner(Files.newInputStream(filePath), StandardCharsets.UTF_8.name())) {
			while (scanner.hasNextLine()) {
				String currentLine = scanner.nextLine();
				String[] elements = currentLine.split(",", -1);
				ValidatorUtil.validateCommaFieldsLength(currentLine);
				if (elements[0].equals("")) {
					elements[0] = UUID.randomUUID().toString().split("-")[0];
				}
				try {
					if (elements[3].equals(Role.MANAGER.name()) && roleType.equals(Role.MANAGER.name())) {
						Manager manager = (Manager) empFactory.getEmployee(Role.MANAGER.name(), elements[0],
								elements[1], elements[2], elements[3], elements[4], elements[5], null, null, null,
								null);
						employeeList.add(manager);
					} else if (elements[3].equals(Role.SELLER.name()) && roleType.equals(Role.SELLER.name())) {
						Seller seller = (Seller) empFactory.getEmployee(Role.SELLER.name(), elements[0], elements[1],
								elements[2], elements[3], null, null, elements[6], elements[7], elements[8],
								elements[9]);
						employeeList.add(seller);
					}
				} catch (IllegalArgumentException e) {
					throw new ExporterApplicationException(
							"Invalid employee data in line: " + currentLine + " - " + e.getMessage());
				}
			}
		} catch (IOException e) {
			throw new ExporterApplicationException("Could not read from file: " + e.getMessage());
		}
		return employeeList;
	}
}
