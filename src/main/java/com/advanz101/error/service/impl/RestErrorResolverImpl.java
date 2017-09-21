/**
 *
 */
package com.advanz101.error.service.impl;


import com.advanz101.error.domain.impl.CustomRestErrorDomainImpl;
import com.advanz101.error.domain.impl.RestErrorDomainImpl;
import com.advanz101.error.exception.ApplicationException;
import com.advanz101.error.exception.BadRequestException;
import com.advanz101.error.exception.BusinessException;
import com.advanz101.error.exception.CustomExceptionWarning;
import com.advanz101.error.exception.InternalServerException;
import com.advanz101.error.exception.ResourceNotFoundException;
import com.advanz101.error.exception.RestErrorsEnum;
import com.advanz101.error.exception.TooManyRequestException;
import com.advanz101.error.exception.UnauthenticatedException;
import com.advanz101.error.exception.UnauthorizedException;
import com.advanz101.error.service.RestErrorResolver;
import com.advanz101.response.Metadata;
import com.advanz101.response.ValidationErrorDto;

/**
 * A {@code RestErrorResolver} resolves an exception and produces a
 * {@code RestErrorDomainImpl} instance that can be used to render a Rest error
 * representation to the response body.
 *
 * @author 
 *
 */
public class RestErrorResolverImpl implements RestErrorResolver {

	public RestErrorResolverImpl(){
	}
	/*
	 * (non-Javadoc)
	 * @see com.cga.api.error.service.RestErrorResolver#resolveBadRequestError(com.cga.api.error.exception.BadRequestException)
	 */
	@Override
	public RestErrorDomainImpl resolveBadRequestError(BadRequestException badRequestException) {
		return populateRestErrorObject(RestErrorsEnum.BAD_REQUEST,
				badRequestException);
	}

	/*
	 * (non-Javadoc)
	 * @see com.cga.api.error.service.RestErrorResolver#resolveAuthenticatedRequestError(com.cga.api.error.exception.UnauthenticatedException)
	 */
	@Override
	public RestErrorDomainImpl resolveAuthenticatedRequestError(UnauthenticatedException
			unauthenticatedException) {
		return populateRestErrorObject(RestErrorsEnum.AUTHENTICATION_REQUIRED,
				unauthenticatedException);
	}

	/*
	 * (non-Javadoc)
	 * @see com.cga.api.error.service.RestErrorResolver#resolveUnauthorizedRequestError(com.cga.api.error.exception.UnauthorizedException)
	 */
	@Override
	public RestErrorDomainImpl resolveUnauthorizedRequestError(UnauthorizedException unauthorizedException) {
		 return populateRestErrorObject(RestErrorsEnum.AUTHENTICATION_REQUIRED,
				unauthorizedException);

	}

	/*
	 * (non-Javadoc)
	 * @see com.cga.api.error.service.RestErrorResolver#resolveResourceNotFoundRequestError(com.cga.api.error.exception.ResourceNotFoundException)
	 */
	@Override
	public RestErrorDomainImpl resolveResourceNotFoundRequestError(
			ResourceNotFoundException resourceNotFoundException) {
		return populateRestErrorObject(RestErrorsEnum.NOT_FOUND,
				resourceNotFoundException);
	}

	/*
	 * (non-Javadoc)
	 * @see com.cga.api.error.service.RestErrorResolver#resolveTooManyRequestRequestError(com.cga.api.error.exception.TooManyRequestException)
	 */
	@Override
	public RestErrorDomainImpl resolveTooManyRequestRequestError(TooManyRequestException tooManyRequestException) {
		return populateRestErrorObject(RestErrorsEnum.TOO_MANY_REQUESTS,
				tooManyRequestException);
	}

	/*
	 * (non-Javadoc)
	 * @see com.cga.api.error.service.RestErrorResolver#resolveInternalServerExceptionRequest(com.cga.api.error.exception.InternalServerException)
	 */
	@Override
	public RestErrorDomainImpl resolveInternalServerExceptionRequest(InternalServerException internalServerException,String systemMessage) {
		return new RestErrorDomainImpl(RestErrorsEnum.INTERNAL_SERVER_ERROR.getHttpStatusCode(),internalServerException.getApplicationCode()
				,RestErrorsEnum.INTERNAL_SERVER_ERROR.getUserMessage(),systemMessage,internalServerException.getMetadata());

	}

	/*
	 * (non-Javadoc)
	 * @see com.cga.api.error.service.RestErrorResolver#resolveInternalServerExceptionRequest(com.cga.api.error.exception.BusinessException)
	 */
	@Override
	public RestErrorDomainImpl resolveBusinessExceptionRequest(BusinessException businessException,String systemMessage) {
		Metadata metadata = new Metadata();
		metadata.setHttpStatus(businessException.getStatusCode());
		return new RestErrorDomainImpl(businessException.getStatusCode().toString(),businessException.getStatusCode().toString()
				,businessException.getMessage(),systemMessage,metadata);

	}

	/*
	 * (non-Javadoc)
	 * @see com.cga.api.error.service.RestErrorResolver#resolveCustomExceptionWarningRequest(com.cga.api.error.exception.CustomWarningException)
	 */
	@Override
	public CustomRestErrorDomainImpl resolveCustomExceptionWarningRequest(
			CustomExceptionWarning customExceptionWarning) {
		return populateCustomRestErrorObject(customExceptionWarning);
	}



	/**
	 *
	 * @param restError
	 * @param ex
	 * @return
	 */
	private RestErrorDomainImpl populateRestErrorObject(RestErrorsEnum restError, ApplicationException ex) {
		return new RestErrorDomainImpl(restError.getHttpStatusCode(),ex.getApplicationCode()
													,restError.getUserMessage(),ex.getMessage(),ex.getMetadata());
	}
	
	private RestErrorDomainImpl populateRestErrorObject(RestErrorsEnum restError,
			ValidationErrorDto ex) {
		return new RestErrorDomainImpl(restError.getHttpStatusCode(),null
				,restError.getUserMessage(),null,ex.getFieldErrors(),new Metadata());
	}


	/**
	 *
	 * @param customException
	 * @return
	 */
	private CustomRestErrorDomainImpl populateCustomRestErrorObject(CustomExceptionWarning customException) {
		return new CustomRestErrorDomainImpl("", customException.getApplicationCode(), "", ""
						, customException.getMetadata(), customException.getWarningMessage(), customException.getData());
	}
	
	
//	@Override
//	public RestErrorDomainImpl resolveMethodArgumentNotValidExceptionRequest(ValidationErrorDto processFieldErrors,
//			String systemMessage) {
//		//Metadata metadata = new Metadata();
//		//metadata.setHttpStatus(businessException.getStatusCode().toString());
//		return populateRestErrorObject(RestErrorsEnum.BAD_REQUEST,
//				processFieldErrors);
//
////		return new RestErrorDomainImpl(businessException.getStatusCode().toString(),businessException.getStatusCode().toString()
////				,businessException.getMessage(),systemMessage,metadata);
//	}
	@Override
	public RestErrorDomainImpl resolveMethodArgumentNotValidExceptionRequest(ApplicationException applicationException,
			String systemMessage) {
		return new RestErrorDomainImpl(RestErrorsEnum.INTERNAL_SERVER_ERROR.getHttpStatusCode(),applicationException.getApplicationCode()
				,RestErrorsEnum.INTERNAL_SERVER_ERROR.getUserMessage(),systemMessage,applicationException.getErrors(), applicationException.getMetadata());
	}








}
