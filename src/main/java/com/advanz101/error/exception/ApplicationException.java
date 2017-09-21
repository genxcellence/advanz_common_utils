package com.advanz101.error.exception;

import java.util.List;

import com.advanz101.response.Metadata;

/**
 *
 * @author 
 * @param <T>
 *
 */
public class ApplicationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final String applicationCode;
	private final transient Metadata metadata;
	private final List errors ;

	/**
	 *
	 * @param applicationCode
	 * @param metadata
	 */
	public ApplicationException(String applicationCode, Metadata metadata) {
		super();
		this.applicationCode = applicationCode;
		this.metadata = metadata;
		this.errors = null;
	}

	public ApplicationException(String applicationCode, Metadata metadata, List errors) {
		super();
		this.applicationCode = applicationCode;
		this.metadata = metadata;
		this.errors = errors;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public List getErrors() {
		return errors;
	}
}
