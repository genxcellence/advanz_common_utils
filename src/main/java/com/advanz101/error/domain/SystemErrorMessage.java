package com.advanz101.error.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author
 *
 */
public class SystemErrorMessage {

	@JsonProperty(value = "message")
	private final String message;
	
	@JsonProperty(value = "error_code")
	private final String errorCode;

	/**
	 * 
	 * @param message
	 * @param errorCode
	 */
	public SystemErrorMessage(String message, String errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
