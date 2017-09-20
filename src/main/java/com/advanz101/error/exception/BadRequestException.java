/**
 *
 */
package com.advanz101.error.exception;

import com.advanz101.response.Metadata;

/**
 * @author 
 *
 */
public class BadRequestException extends ApplicationException {

    private static final long serialVersionUID = -9215466226316062049L;

    /**
     *
     * @param applicationCode
     * @param metadata
     */
    public BadRequestException(String applicationCode, Metadata metadata) {
		super(applicationCode,metadata);
	}

}
