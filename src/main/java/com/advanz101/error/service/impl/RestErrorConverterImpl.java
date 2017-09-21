/**
 *
 */
package com.advanz101.error.service.impl;

import com.advanz101.error.domain.ApiErrorMessage;
import com.advanz101.error.domain.BaseRestErrorDomain;
import com.advanz101.error.domain.CustomErrorResponse;
import com.advanz101.error.domain.RestErrorResponse;
import com.advanz101.error.domain.SystemErrorMessage;
import com.advanz101.error.domain.UserErrorMessage;
import com.advanz101.error.domain.impl.CustomRestErrorDomainImpl;
import com.advanz101.error.domain.impl.RestErrorDomainImpl;
import com.advanz101.error.service.RestErrorConverter;

/**
 * @author 
 *
 */
public class RestErrorConverterImpl implements RestErrorConverter {

	private RestErrorConverterImpl() {
		super();
	}

	/**
	 *
	 * @param baseRestErrorDomain
	 * @return
	 */
	public static RestErrorResponse restErrorConvertor(RestErrorDomainImpl restErrorDomainImpl) {
		return new RestErrorResponse(populateApiErrorObject(restErrorDomainImpl),restErrorDomainImpl.getMetadata(),null);
	}

	/**
	 *
	 * @param customRestErrorDomainImpl
	 * @return
	 */
	public static RestErrorResponse customExceptionConverter(CustomRestErrorDomainImpl customRestErrorDomainImpl) {
		return new RestErrorResponse(populateApiErrorObject(customRestErrorDomainImpl),customRestErrorDomainImpl.getMetadata()
				,new CustomErrorResponse(customRestErrorDomainImpl.getWarning(),customRestErrorDomainImpl.getData()));
	}

	/**
	 *
	 * @param restErrorDomain
	 * @return
	 */
	private static ApiErrorMessage populateApiErrorObject(BaseRestErrorDomain restErrorDomain) {
		SystemErrorMessage systemMessageError = new SystemErrorMessage(restErrorDomain.getSystemMessage()
				,restErrorDomain.getApplicationCode());
		UserErrorMessage userMessageError = new UserErrorMessage(restErrorDomain.getHttpStatusCode()
				, restErrorDomain.getUserMessage());
		return new ApiErrorMessage(systemMessageError,userMessageError,restErrorDomain.getErrors());
	}






}
