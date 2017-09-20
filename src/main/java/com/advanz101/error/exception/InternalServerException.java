/**
 *
 */
package com.advanz101.error.exception;

import com.advanz101.response.Metadata;

/**
 * @author 
 *
 */
public class InternalServerException extends ApplicationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 5018979763055703549L;

	/**
	 *
	 * @param applicationCode
	 * @param metadata
	 */
	public InternalServerException(String applicationCode, Metadata metadata) {
		super(applicationCode,metadata);
	}


}
