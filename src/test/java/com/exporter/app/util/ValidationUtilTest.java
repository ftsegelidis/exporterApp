package com.exporter.app.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.exporter.app.constants.Constants;
import com.exporter.app.enums.Role;
import com.exporter.app.exception.ValidationException;
import com.exporter.app.util.ValidatorUtil;

public class ValidationUtilTest {

	String[] args = { "input.txt", "JSON", "MANAGER" };
	
	@Test(expected = ValidationException.class )
	public void testValidateArgumentSizeException() throws ValidationException {
		String[] invalidArgs = { "input.txt", "JSON" };
		ValidatorUtil.validateArgumentSize(invalidArgs, Constants.ARGS_SIZE);
	}

	@Test
	public void testValidateRoleType() throws ValidationException  {
		ValidatorUtil.validateRoleType(args[2]);
		assertEquals(args[2], Role.MANAGER.name());
		assertFalse(Role.SELLER.name().equals(args[2]));
	}
	
	@Test(expected = ValidationException.class )
	public void testValidateRoleTypeException() throws ValidationException {
		ValidatorUtil.validateRoleType("OTHER");
		assertEquals(args[2], Role.MANAGER.name());
	}

	@Test
	public void testValidateCommaFieldsLength() throws ValidationException {
		String line = "2,Maggie,Simpson,SELLER,,,LINE_A,Madrid,0.4,TRUE";
		ValidatorUtil.validateCommaFieldsLength(line);
	}
	
	@Test(expected = ValidationException.class )
	public void testValidateCommaFieldsWithWrongLengthException() throws ValidationException {
		String line = "2,,,,,,";
		ValidatorUtil.validateCommaFieldsLength(line);
	}
	
	
	@Test
	public void testValidateExportFileType() throws ValidationException {
		ValidatorUtil.validateExportFileType(args[1]);
		assertEquals("JSON", args[1]);
	}
	

	@Test(expected = ValidationException.class )
	public void testValidateExportFileTypeException() throws ValidationException {
		ValidatorUtil.validateExportFileType("OTHER");

	}	
}
