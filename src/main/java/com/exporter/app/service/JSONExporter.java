package com.exporter.app.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.exporter.app.constants.Constants;
import com.exporter.app.entity.Employee;
import com.exporter.app.exception.ExporterApplicationException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * @info Uses JSON api to write to json file
 *
 */
public class JSONExporter implements Exporter {

	public final static Logger logger = Logger.getLogger(JSONExporter.class);

	public JSONExporter() {
	}

	/*
	 * Convert the incoming list to json object
	 */
	@Override
	public void export(List<Employee> empValues) throws ExporterApplicationException {
		logger.debug("Json export");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(Constants.JSON_OUTPUT)) {
			String data = gson.toJson(empValues);
			JsonArray jsonArray = new JsonParser().parse(data).getAsJsonArray();
			gson.toJson(jsonArray, writer);
			logger.info("json written successfully..");
		} catch (IOException e) {
			throw new ExporterApplicationException("Could not write target file:" + e.getMessage());
		}
	}
}
