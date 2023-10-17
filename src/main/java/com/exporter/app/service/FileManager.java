package com.exporter.app.service;

import java.io.IOException;
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
	private Scanner scanner;

	public FileManager() {
	}

	/**
	 * @param filename
	 * @param roleType
	 * @return List<Employee>
	 * @throws ExporterApplicationException
	 * @info Reads from txt file. Check if is manager or seller and fills each
	 *       list
	 */
	public List<Employee> readFromFile(String filename, String roleType) throws ExporterApplicationException {
		logger.debug("Reading from txt file....");
		Path filePath = Paths.get(filename);
		String currentLine = null;
		List<Employee> employeeList = new ArrayList<Employee>();
		EmployeeFactory empFactory = new EmployeeFactory();
		try {
			scanner = new Scanner(filePath);
			while (scanner.hasNext()) {
				if (scanner.hasNextLine()) {
					currentLine = scanner.nextLine();
					String[] elements = currentLine.split(",");
					ValidatorUtil.validateCommaFieldsLength(currentLine);
					if (elements[0].equals("")) {
						elements[0] = String.valueOf(UUID.randomUUID().toString().split("-")[0]);
					}
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
				} else {
					scanner.next();
				}
			}
		} catch (IOException e) {
			throw new ExporterApplicationException("Could not read from file" + e.getMessage());
		} catch (ValidationException ve) {
			logger.error(ve.getMessage());
		}
		return employeeList;
	}
}
