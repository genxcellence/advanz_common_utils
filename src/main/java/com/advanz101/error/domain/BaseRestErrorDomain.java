/**
 *
 */
package com.advanz101.error.domain;

import java.util.List;

import com.advanz101.error.exception.RestErrorsEnum;
import com.advanz101.response.Metadata;

/**
 * @author 
 *
 */
public class BaseRestErrorDomain {

	private final String httpStatusCode;
	private final String applicationCode;
	private final String userMessage;
	private final String systemMessage;
	private final Metadata metadata;
	private final List<?> errors;
	/**
	 *
	 * @param httpStatusCode
	 * @param applicationCode
	 * @param userMessage
	 * @param systemMessage
	 * @param metadata
	 */
	public BaseRestErrorDomain(String httpStatusCode, String applicationCode, String userMessage, String systemMessage,
			Metadata metadata) {
		super();
		if(metadata == null) {
			metadata = new Metadata();
			metadata.setHttpStatus(Integer.valueOf(RestErrorsEnum.INTERNAL_SERVER_ERROR.getHttpStatusCode()));
		}

		this.httpStatusCode = httpStatusCode;
		this.applicationCode = applicationCode;
		this.userMessage = userMessage;
		this.systemMessage = systemMessage;
		this.metadata = metadata;
		this.errors = null;
	}

	/**
	 *
	 * @param httpStatusCode
	 * @param userMessage
	 * @param metadata
	 */
	public BaseRestErrorDomain(String httpStatusCode, String userMessage,Metadata metadata) {
		this(httpStatusCode,null,userMessage,null,metadata);
	}

	/**
	 *
	 * @param applicationCode
	 * @param systemMessage
	 */
	public BaseRestErrorDomain(String applicationCode, String systemMessage) {
		this(null,applicationCode,null,systemMessage,null);
	}


	public BaseRestErrorDomain(String httpStatusCode, String applicationCode, String userMessage, String systemMessage,
			List errors, Metadata metadata) {
		super();
		if(metadata == null) {
			metadata = new Metadata();
			metadata.setHttpStatus(Integer.valueOf(RestErrorsEnum.INTERNAL_SERVER_ERROR.getHttpStatusCode()));
		}

		this.httpStatusCode = httpStatusCode;
		this.applicationCode = applicationCode;
		this.userMessage = userMessage;
		this.systemMessage = systemMessage;
		System.out.println("---------------------------- Errror ------------"+ errors);
		this.errors = errors;
		this.metadata = metadata;
	}

	/**
	 * @return the httpStatusCode
	 */
	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	/**
	 * @return the applicationCode
	 */
	public String getApplicationCode() {
		return applicationCode;
	}

	/**
	 * @return the userMessage
	 */
	public String getUserMessage() {
		return userMessage;
	}

	/**
	 * @return the systemMessage
	 */
	public String getSystemMessage() {
		return systemMessage;
	}


	public Metadata getMetadata() {
		return metadata;
	}

	public List<?> getErrors() {
		return errors;
	}

}
