package com.exporter.app.service;

import com.exporter.app.enums.OutputFileType;

public class ExporterFactory {

	public Exporter getExporter(String outputFileType) {
		if (outputFileType == null) {
			return null;
		}
		if (outputFileType.equals(OutputFileType.JSON.name())) {
			return new JSONExporter();
		} else if (outputFileType.equals(OutputFileType.XLS.name())) {
			return new XLSExporter();
		} else if (outputFileType.equals(OutputFileType.XML.name())) {
			return new XMLExporter();
		}
		return null;
	}
}
