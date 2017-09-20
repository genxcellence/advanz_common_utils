package com.advanz101.error.domain;

/**
 * 
 * 
 *
 */
public class ApiErrorMessage {
			
	private final UserErrorMessage userErrorMessage;
	private final SystemErrorMessage systemErrorMessage;	
	
	/**
	 * 
	 * @param systemErrorMessage
	 * @param userErrorMessage
	 */
	public ApiErrorMessage(SystemErrorMessage systemErrorMessage, UserErrorMessage userErrorMessage) {
		this.systemErrorMessage = systemErrorMessage;
		this.userErrorMessage = userErrorMessage;
	}
	
	public SystemErrorMessage getSystemErrorMessage() {
		return systemErrorMessage;
	}

	public UserErrorMessage getUserErrorMessage() {
		return userErrorMessage;
	}
		
	
			
}
