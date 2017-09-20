package com.advanz101.error.exception;

import com.advanz101.response.Metadata;

/**
 *
 * @author SharmD01
 *
 */
public class ResourceNotFoundException extends ApplicationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param applicationCode
	 * @param metadata
	 */
	public ResourceNotFoundException(String applicationCode, Metadata metadata) {
		super(applicationCode,metadata);
	}

}
