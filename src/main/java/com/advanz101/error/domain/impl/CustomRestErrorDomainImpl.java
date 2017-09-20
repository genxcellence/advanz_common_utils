package com.advanz101.error.domain.impl;

import com.advanz101.error.domain.BaseRestErrorDomain;
import com.advanz101.response.Metadata;

public class CustomRestErrorDomainImpl extends BaseRestErrorDomain {

	private final String warning;
	private final Object[] data;

	/**
	 *
	 * @param httpStatusCode
	 * @param applicationCode
	 * @param userMessage
	 * @param systemMessage
	 * @param metadata
	 * @param warning
	 */
	public CustomRestErrorDomainImpl(String httpStatusCode, String applicationCode, String userMessage,
			String systemMessage, Metadata metadata, String warning, Object[] data) {
		super(httpStatusCode, applicationCode, userMessage, systemMessage, metadata);
		this.warning = warning;
		this.data = data;
	}

	public CustomRestErrorDomainImpl(String warning, Object[] data) {
		super(null,null,null,null,null);
		this.warning = warning;
		this.data = data;
	}

	/**
	 *
	 * @return
	 */
	public String getWarning() {
		return warning;
	}

	public Object[] getData() {
		return data;
	}


}
