package com.advanz101.error.domain;

import com.advanz101.response.Metadata;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 
 *
 */
public class RestErrorResponse {

	private final Metadata metadata;
	@JsonProperty(value="data")
	private Object[] data = {};
	@JsonProperty(value="error")
	private final ApiErrorMessage apiErrorMessage;


	/**
	 *
	 * @param apiErrorMessage
	 * @param metadata
	 * @param data
	 */
	public RestErrorResponse(ApiErrorMessage apiErrorMessage, Metadata metadata, CustomErrorResponse data) {
		super();
		this.apiErrorMessage = apiErrorMessage;
		this.metadata = metadata;
		if(data != null) {
			this.data = data.getData();
		} 
	}

	public ApiErrorMessage getApiErrorMessage() {
		return apiErrorMessage;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public Object getData() {
		return data;
	}




}
