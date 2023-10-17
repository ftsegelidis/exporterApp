package com.exporter.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.exporter.app.constants.Constants;
import com.exporter.app.entity.Employee;
import com.exporter.app.entity.Manager;
import com.exporter.app.entity.Seller;
import com.exporter.app.exception.ExporterApplicationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @info Uses XLS POI api to write to xls file
 *
 */
public class XLSExporter implements Exporter {

	public final static Logger logger = LogManager.getLogger(XLSExporter.class);

	public XLSExporter() {
	}

	/*
	 * Convert the incoming list to xls object
	 */
	@Override
	public void export(List<Employee> empValues) throws ExporterApplicationException {
		logger.debug("xls export");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Employees");
		FileOutputStream outputStream = null;
		int rowCount = 0;

		for (int i = 0; i < empValues.size(); i++) {
			Row row = sheet.createRow(rowCount++);
			writeEmployeeInXLSFormat(empValues.get(i), row);
		}

		try {
			outputStream = new FileOutputStream(new File(Constants.XLS_OUTPUT));
			workbook.write(outputStream);
			workbook.close();
			logger.info("xls written successfully..");
		} catch (IOException e) {
			throw new ExporterApplicationException("Could not write target file:" + e.getMessage());
		}
	}

	/**
	 * @param empValue
	 * @param row
	 * @info Helping method to create cells for each employee element
	 */
	private void writeEmployeeInXLSFormat(Employee empValue, Row row) {

		Cell cell = row.createCell(0);
		cell.setCellValue(empValue.getId());

		cell = row.createCell(1);
		cell.setCellValue(empValue.getFirstname());

		cell = row.createCell(2);
		cell.setCellValue(empValue.getLastname());

		cell = row.createCell(3);
		cell.setCellValue(empValue.getRole().name());

		if (empValue instanceof Manager) {

			cell = row.createCell(4);
			cell.setCellValue(((Manager) empValue).getNumberOfSellers());

			cell = row.createCell(5);
			cell.setCellValue(((Manager) empValue).getCountry());

		} else if (empValue instanceof Seller) {

			cell = row.createCell(4);
			cell.setCellValue(((Seller) empValue).getProductLine().name());

			cell = row.createCell(5);
			cell.setCellValue(((Seller) empValue).getAverageSales());

			cell = row.createCell(6);
			cell.setCellValue(((Seller) empValue).getCity());

			cell = row.createCell(7);
			cell.setCellValue(((Seller) empValue).getActivity());
		}
	}
}
