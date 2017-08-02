package com.exporter.app;

import java.util.List;

import org.apache.log4j.Logger;

import com.exporter.app.constants.Constants;
import com.exporter.app.entity.Employee;
import com.exporter.app.exception.ExporterApplicationException;
import com.exporter.app.exception.ValidationException;
import com.exporter.app.service.Exporter;
import com.exporter.app.service.ExporterFactory;
import com.exporter.app.service.FileManager;
import com.exporter.app.util.ValidatorUtil;

/**
 * @info Exporter app takes as input a text (comma separated file) and export
 *       values into xml,json,xls
 *
 */
public class ExporterApp {
	public final static Logger logger = Logger.getLogger(ExporterApp.class);

	/**
	 * @param args // e.g input.txt , JSON , MANAGER
	 *  App entry point. Performs validations ,then reading from file and finally exports to desired file
	 */
	public static void main(String[] args) {

		logger.info("File exporter program");

		try {
			// make validations
			performInputValidations(args, Constants.ARGS_SIZE);

			// read file and load to list
			FileManager fileManager = new FileManager();
			List<Employee> employeeList = fileManager.readFromFile(args[0], args[2]);

			// export to file
			ExporterFactory exporterFactory = new ExporterFactory();
			Exporter exporter = exporterFactory.getExporter(args[1]);
			exporter.export(employeeList);

			logger.info("Export successfully finished");
		} catch (ValidationException ve) {
			logger.error(ve.getMessage());
		} catch (ExporterApplicationException ee) {
			logger.error(ee.getMessage());
		}
	}

	/**
	 * @param args
	 * @param size
	 * @param filename
	 * @throws ValidationException
	 * @throws ExporterApplicationException
	 * @info make validations for each case
	 */
	private static void performInputValidations(String[] args, int size) throws ValidationException {
		logger.debug("starting validations");
		ValidatorUtil.validateArgumentSize(args, size);
		ValidatorUtil.validateIfFileExists(args[0]);
		ValidatorUtil.validateIfInputFileIsEmpty(args[0]);
		ValidatorUtil.validateExportFileType(args[1]);
		ValidatorUtil.validateRoleType(args[2]);
	}
}
