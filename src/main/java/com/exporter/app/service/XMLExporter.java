package com.exporter.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.exporter.app.constants.Constants;
import com.exporter.app.entity.Employee;
import com.exporter.app.entity.Manager;
import com.exporter.app.entity.Seller;
import com.exporter.app.enums.Role;
import com.exporter.app.exception.ExporterApplicationException;

/**
 * @info Uses xml JAXB api to write to xml file
 *
 */
public class XMLExporter implements Exporter {
	public final static Logger logger = Logger.getLogger(XMLExporter.class);

	public XMLExporter() {
	}

	/*
	 * Convert the incoming list to xml object
	 * 
	 */
	@Override
	public void export(List<Employee> empValues) throws ExporterApplicationException {

		logger.debug("Xml export");
		File file = new File(Constants.XML_OUTPUT);
		Document doc = new Document();
		doc.setRootElement(new Element("employees"));
		Element employee = null;
		for (Employee emp : empValues) {
			if (emp instanceof Manager) {
				employee = new Element(Role.MANAGER.name().toLowerCase());
				employee.setAttribute("id", "" + emp.getId());
				employee.addContent(new Element("firstname").setText(emp.getFirstname()));
				employee.addContent(new Element("lastname").setText(emp.getLastname()));
				employee.addContent(new Element("role").setText(emp.getRole().name()));
				employee.addContent(
						new Element("numberOfSellers").setText(((Manager) emp).getNumberOfSellers().toString()));
				employee.addContent(new Element("country").setText(((Manager) emp).getCountry()));
			} else if (emp instanceof Seller) {
				employee = new Element(Role.SELLER.name().toLowerCase());
				employee.setAttribute("id", "" + emp.getId());
				employee.addContent(new Element("firstname").setText(emp.getFirstname()));
				employee.addContent(new Element("lastname").setText(emp.getLastname()));
				employee.addContent(new Element("role").setText(emp.getRole().name()));
				employee.addContent(new Element("productLine").setText(((Seller) emp).getProductLine().name()));
				employee.addContent(new Element("city").setText(((Seller) emp).getCity()));
				employee.addContent(
						new Element("averagesales").setText(String.valueOf(((Seller) emp).getAverageSales())));
				employee.addContent(new Element("active").setText(String.valueOf(((Seller) emp).getActivity())));
			}
			doc.getRootElement().addContent(employee);
		}
		XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
		try {
			xmlOutputter.output(doc, new FileOutputStream(file));
			logger.info("xml written successfully..");
		} catch (IOException e) {
			throw new ExporterApplicationException("Could not write target file:" + e.getMessage());
		}
	}
}
