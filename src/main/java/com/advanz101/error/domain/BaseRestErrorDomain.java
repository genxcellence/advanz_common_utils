/**
 *
 */
package com.advanz101.error.domain;

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
		this.httpStatusCode = httpStatusCode;
		this.applicationCode = applicationCode;
		this.userMessage = userMessage;
		this.systemMessage = systemMessage;
		this.metadata = metadata;
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

}
