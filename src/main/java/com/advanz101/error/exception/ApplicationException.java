package com.advanz101.error.exception;

import com.advanz101.response.Metadata;

/**
 *
 * @author 
 *
 */
public class ApplicationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final String applicationCode;
	private final transient Metadata metadata;

	/**
	 *
	 * @param applicationCode
	 * @param metadata
	 */
	public ApplicationException(String applicationCode, Metadata metadata) {
		super();
		this.applicationCode = applicationCode;
		this.metadata = metadata;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public Metadata getMetadata() {
		return metadata;
	}


}
