package com.advanz101.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiErrorDTO implements Serializable{
	private static final long serialVersionUID = -7138626868758281941L;

	
	@JsonProperty(value="http_status")
	private String httpStatus;
	
	@JsonProperty(value="api_code")
	private String apiCode;
	
	@JsonProperty(value="user_message")
	private String userMessage;
	
	@JsonProperty(value="dev_message")
	private String devMessage;

	
	
	public ApiErrorDTO(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus.toString();
		this.userMessage = message;
		this.devMessage = httpStatus.getReasonPhrase();
	}
	
	
	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getDevMessage() {
		return devMessage;
	}

	public void setDevMessage(String devMessage) {
		this.devMessage = devMessage;
	}
	
}
