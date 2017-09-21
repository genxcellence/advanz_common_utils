package com.advanz101.error.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 *
 */
public class ApiErrorMessage {

	@JsonProperty(value = "user_error_message")
	private final UserErrorMessage userErrorMessage;
	@JsonProperty(value = "system_error_message")
	private final SystemErrorMessage systemErrorMessage;
	@JsonProperty(value = "errors")
	private final List<?> errors;


	/**
	 * 
	 * @param systemErrorMessage
	 * @param userErrorMessage
	 */
	public ApiErrorMessage(SystemErrorMessage systemErrorMessage, UserErrorMessage userErrorMessage) {
		this(systemErrorMessage, userErrorMessage, null);
	}

	/**
	 * 
	 * @param systemErrorMessage
	 * @param userErrorMessage
	 * @param errors
	 */
	public ApiErrorMessage(SystemErrorMessage systemErrorMessage, UserErrorMessage userErrorMessage, List<?> errors) {
		this.systemErrorMessage = systemErrorMessage;
		this.userErrorMessage = userErrorMessage;
		this.errors = errors;
	}

	public SystemErrorMessage getSystemErrorMessage() {
		return systemErrorMessage;
	}

	public UserErrorMessage getUserErrorMessage() {
		return userErrorMessage;
	}

	public List<?> getErrors() {
		return errors;
	}

}
