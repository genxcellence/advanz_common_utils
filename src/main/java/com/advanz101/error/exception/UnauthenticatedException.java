/**
 *
 */
package com.advanz101.error.exception;

import com.advanz101.response.Metadata;

/**
 * @author 
 *
 */
public class UnauthenticatedException  extends ApplicationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 4729506460942469401L;

	/**
	 *
	 * @param applicationCode
	 * @param metadata
	 */
	public UnauthenticatedException(String applicationCode, Metadata metadata) {
		super(applicationCode,metadata);
	}
}
