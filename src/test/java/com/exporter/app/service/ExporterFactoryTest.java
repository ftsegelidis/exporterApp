package com.exporter.app.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.exporter.app.service.ExporterFactory;
import com.exporter.app.service.JSONExporter;
import com.exporter.app.service.XLSExporter;
import com.exporter.app.service.XMLExporter;

public class ExporterFactoryTest {
	
	private ExporterFactory exporterFactory;
	
	@Before
	public void setUp(){
		exporterFactory = new ExporterFactory();
	}
	
	@Test
	public void testGetExporterJSON(){
		
		assertTrue(exporterFactory.getExporter("JSON") instanceof JSONExporter);
		assertFalse(exporterFactory.getExporter("JSON") instanceof XMLExporter);
		assertFalse(exporterFactory.getExporter("JSON") instanceof XLSExporter);	
	}
	
	@Test
	public void getExporterXML(){
		
		assertTrue(exporterFactory.getExporter("XML") instanceof XMLExporter);
		assertFalse(exporterFactory.getExporter("XML") instanceof JSONExporter);
		assertFalse(exporterFactory.getExporter("XML") instanceof XLSExporter);
	}
	
	@Test
	public void getExporterXLS(){
		assertTrue(exporterFactory.getExporter("XLS") instanceof XLSExporter);
		assertFalse(exporterFactory.getExporter("XLS") instanceof JSONExporter);
		assertFalse(exporterFactory.getExporter("XLS") instanceof XMLExporter);
	}
}
