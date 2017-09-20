package com.advanz101.error.domain;

public class CustomErrorResponse {
	
	private final String warning;
	private final Object[] data;
	
	public CustomErrorResponse(String warning, Object[] data) {
		super();
		this.warning = warning;
		this.data = data;
	}

	public String getWarning() {
		return warning;
	}

	public Object[] getData() {
		return data;
	}	

}
