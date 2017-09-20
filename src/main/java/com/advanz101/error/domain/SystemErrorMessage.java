package com.advanz101.error.domain;
/**
 * 
 * @author 
 *
 */
public class SystemErrorMessage {

	private final String message;
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
