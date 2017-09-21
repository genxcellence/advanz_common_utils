package com.advanz101.error.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author 
 *
 */
public class UserErrorMessage {
	
	@JsonProperty(value="http_status")
	private final String httpStatus;
	
	@JsonProperty(value="user_message")
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
