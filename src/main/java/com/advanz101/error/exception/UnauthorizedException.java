package com.advanz101.error.exception;

import com.advanz101.response.Metadata;

/**
 *
 * @author 
 *
 */
public class UnauthorizedException extends ApplicationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param applicationCode
	 * @param metadata
	 */
	public UnauthorizedException(String applicationCode, Metadata metadata) {
		super(applicationCode,metadata);
	}

}
