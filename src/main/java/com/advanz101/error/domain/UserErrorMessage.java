package com.advanz101.error.domain;
/**
 * 
 * @author 
 *
 */
public class UserErrorMessage {
	
	private final String httpStatus;
	private final String userMessage;
	
	/**
	 * 
	 * @param httpStatus
	 * @param userMessage
	 */
	public UserErrorMessage(String httpStatus, String userMessage) {
		this.httpStatus = httpStatus;
		this.userMessage = userMessage;
	}
		
	public String getHttpStatus() {
		return httpStatus;
	}
	
	public String getUserMessage() {
		return userMessage;
	}
	
}
