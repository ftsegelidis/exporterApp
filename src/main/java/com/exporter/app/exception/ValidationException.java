package com.exporter.app.exception;

public class ValidationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4970743510426539442L;

	/**
	 * @param msg
	 * @info Custom exception for handling validation
	 */
	public ValidationException(String msg) {
		super(msg);
	}

	public ValidationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
