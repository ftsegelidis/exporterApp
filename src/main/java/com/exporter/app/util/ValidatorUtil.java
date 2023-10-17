package com.exporter.app.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;


import com.exporter.app.constants.Constants;
import com.exporter.app.enums.OutputFileType;
import com.exporter.app.enums.Role;
import com.exporter.app.exception.ExporterApplicationException;
import com.exporter.app.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @info Class for Validation
 *
 */
public final class ValidatorUtil {

	public final static Logger logger = LogManager.getLogger(ValidatorUtil.class);

	private ValidatorUtil() {
	}

	/**
	 * @param parameters
	 * @param size
	 * @throws ValidationException
	 * @info checking if arguments have the proper size
	 */
	public static final void validateArgumentSize(String[] parameters, int size) throws ValidationException {
		logger.debug("validating arg size:" + parameters.length + " expected:" + size);
		if (parameters.length != size) {
			logger.debug("Given parameters was:"+parameters.length +" but expected was :"+size);
			throw new ValidationException("Parameters should be three");
		}
	}

	/**
	 * @param filename
	 * @throws IOException
	 * @throws com.exporter.app.exception.ValidationException
	 *             info checks if input file is empty
	 * @throws ExporterApplicationException
	 */
	public static final void validateIfInputFileIsEmpty(String filename) throws ValidationException {
		logger.debug("Method : validateIfInputFileIsEmpty");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));

			if (br.readLine() == null) {
				br.close();
			}
		} catch (IOException e) {
			throw new ValidationException("Could not write target file:" + e.getMessage());
		}
	}

	/**
	 * @param filename
	 * @throws ValidationException
	 * @info checks if file exists
	 */
	public static final void validateIfFileExists(String filename) throws ValidationException {
		logger.debug("Method :validateIfFileExists");
		if (!new File(filename).exists()) {
			throw new ValidationException("There is no such file");
		}

	}

	/**
	 * @param roleType
	 * @throws ValidationException
	 * @info checks if role type is MANAGER or SELLER
	 */
	public static final void validateRoleType(String roleType) throws ValidationException {
		logger.debug("Method : validateRole");
		if (!roleType.equals(Role.MANAGER.name()) && !roleType.equals(Role.SELLER.name())) {
			logger.debug("Role type given : "+roleType+" but expected : MANAGER OR SELLER");
			throw new ValidationException("Role should be MANAGER or SELLER only");
		}
	}

	/**
	 * @param parameters
	 * @throws ValidationException
	 * @info checks if the exported file type are valid(JSON,XML,XML)
	 */
	public static final void validateExportFileType(String parameters) throws ValidationException {
		logger.debug("Method : validate export file type, given : "+ parameters);
		if (!parameters.equals(OutputFileType.XML.name()) && !parameters.equals(OutputFileType.XLS.name())
				&& !parameters.equals(OutputFileType.JSON.name())) {
			throw new ValidationException("Export file should be XML or JSON or XLS only");
		}
	}

	/**
	 * @param line
	 * @throws IOException
	 * @throws ValidationException
	 * @info checks if every line has expected fields
	 */
	public static final void validateCommaFieldsLength(String line) throws ValidationException {
		int commaMatch = 0;
		commaMatch = StringUtils.countMatches(line, ",");
		logger.debug("comma fields length check for given : "+line +" is: "+commaMatch);
		if (commaMatch != Constants.COMMA_FIELDS_NUM) {
			throw new ValidationException("Comma fields are not ten");
		}
	}

}
