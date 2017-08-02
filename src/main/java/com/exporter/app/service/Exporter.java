package com.exporter.app.service;

import java.io.IOException;
import java.util.List;

import com.exporter.app.entity.Employee;
import com.exporter.app.exception.ExporterApplicationException;
import com.exporter.app.exception.ValidationException;

public interface Exporter {
	/**
	 * @param values
	 * @throws IOException
	 * @throws ValidationException
	 * @info Takes list of values(from Manager or Seller) and export them to the
	 *       appropriate format
	 */
	public void export(List<Employee> empValues) throws ExporterApplicationException;
}
