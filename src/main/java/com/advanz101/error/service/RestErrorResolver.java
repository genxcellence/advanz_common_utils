/**
 *
 */
package com.advanz101.error.service;


import com.advanz101.error.domain.impl.CustomRestErrorDomainImpl;
import com.advanz101.error.domain.impl.RestErrorDomainImpl;
import com.advanz101.error.exception.BadRequestException;
import com.advanz101.error.exception.CustomExceptionWarning;
import com.advanz101.error.exception.InternalServerException;
import com.advanz101.error.exception.ResourceNotFoundException;
import com.advanz101.error.exception.TooManyRequestException;
import com.advanz101.error.exception.UnauthenticatedException;
import com.advanz101.error.exception.UnauthorizedException;

/**
 * @author 
 *
 */
public interface RestErrorResolver {

	/**
	 *
	 * @param ex
	 * @return
	 */
	RestErrorDomainImpl resolveBadRequestError(BadRequestException ex);

	/**
	 *
	 * @param unauthenticatedException
	 * @return
	 */
	RestErrorDomainImpl resolveAuthenticatedRequestError(UnauthenticatedException unauthenticatedException);

	/**
	 *
	 * @param unauthorizedException
	 * @return
	 */
	RestErrorDomainImpl resolveUnauthorizedRequestError(UnauthorizedException unauthorizedException);

	/**
	 *
	 * @param resourceNotFoundException
	 * @return
	 */
	RestErrorDomainImpl resolveResourceNotFoundRequestError(ResourceNotFoundException resourceNotFoundException);

	/**
	 *
	 * @param tooManyRequestException
	 * @return
	 */
	RestErrorDomainImpl resolveTooManyRequestRequestError(TooManyRequestException tooManyRequestException);

	/**
	 *
	 * @param internalServerException
	 * @return
	 */
	RestErrorDomainImpl resolveInternalServerExceptionRequest(InternalServerException internalServerException,String systemMessage);

	/**
	 *
	 * @param customExceptionWarning
	 * @return
	 */
	CustomRestErrorDomainImpl resolveCustomExceptionWarningRequest(CustomExceptionWarning customExceptionWarning);
}
