package com.exporter.app.exception;

public class ExporterApplicationException extends Exception {

	private static final long serialVersionUID = 3408002879899187219L;

	public ExporterApplicationException(String msg) {
		super(msg);
	}

	public ExporterApplicationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
