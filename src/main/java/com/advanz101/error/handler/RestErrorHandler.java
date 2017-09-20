/**
 *
 */
package com.advanz101.error.handler;

import java.io.IOException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.advanz101.error.domain.impl.CustomRestErrorDomainImpl;
import com.advanz101.error.domain.impl.RestErrorDomainImpl;
import com.advanz101.error.exception.BadRequestException;
import com.advanz101.error.exception.CustomExceptionWarning;
import com.advanz101.error.exception.InternalServerException;
import com.advanz101.error.exception.ResourceNotFoundException;
import com.advanz101.error.exception.TooManyRequestException;
import com.advanz101.error.exception.UnauthenticatedException;
import com.advanz101.error.exception.UnauthorizedException;
import com.advanz101.error.service.RestErrorResolver;
import com.advanz101.error.service.impl.RestErrorConverterImpl;
import com.advanz101.error.service.impl.RestErrorResolverImpl;

/**
 * @author 
 *
 */

@ControllerAdvice
// Name as MainErrorHandler 
public class RestErrorHandler extends ResponseEntityExceptionHandler {

//	private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);

	private RestErrorResolver restErrorResolver;


	public RestErrorHandler() {
        this.restErrorResolver = new RestErrorResolverImpl();
    }

	/**
	 *
	 * @param badRequestException
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> badRequestHandler(BadRequestException badRequestException, WebRequest request) {
		//LOGGER.error(badRequestException.getApplicationCode() + "-" + badRequestException.getMessage() , badRequestException);
		RestErrorDomainImpl restErrorDomainImpl = restErrorResolver.resolveBadRequestError(badRequestException);
        return handleExceptionInternal(badRequestException, RestErrorConverterImpl.restErrorConvertor(restErrorDomainImpl),
        		getHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	/**
	 *
	 * @param unauthenticatedException
	 * @param request
	 * @return
	 */
	@ExceptionHandler(UnauthenticatedException.class)
	public ResponseEntity<Object> unauthenticatedRequestHandler(UnauthenticatedException unauthenticatedException, WebRequest request) {
		//LOGGER.error(unauthenticatedException.getMessage(), unauthenticatedException);
		RestErrorDomainImpl restErrorDomainImpl = restErrorResolver.resolveAuthenticatedRequestError(unauthenticatedException);
        return handleExceptionInternal(unauthenticatedException, RestErrorConverterImpl.restErrorConvertor(restErrorDomainImpl),
        		getHeaders(), HttpStatus.UNAUTHORIZED, request);
	}

	/**
	 *
	 * @param unauthorizedException
	 * @param request
	 * @return
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Object> unauthorizedRequestHandler(UnauthorizedException unauthorizedException, WebRequest request) {
		//LOGGER.error(unauthorizedException.getMessage(), unauthorizedException);
		RestErrorDomainImpl restErrorDomainImpl = restErrorResolver.resolveUnauthorizedRequestError(unauthorizedException);
		return handleExceptionInternal(unauthorizedException, RestErrorConverterImpl.restErrorConvertor(restErrorDomainImpl),
	        		getHeaders(), HttpStatus.UNAUTHORIZED, request);
	}

	/**
	 *
	 * @param resourceNotFoundException
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNotFoundExceptionRequestHandler(ResourceNotFoundException resourceNotFoundException, WebRequest request) {
		//LOGGER.error(resourceNotFoundException.getMessage());
		RestErrorDomainImpl restErrorDomainImpl = restErrorResolver.resolveResourceNotFoundRequestError(resourceNotFoundException);
		return handleExceptionInternal(resourceNotFoundException, RestErrorConverterImpl.restErrorConvertor(restErrorDomainImpl),
				getHeaders(), HttpStatus.OK, request);
	}

	/**
	 *
	 * @param tooManyRequestException
	 * @param request
	 * @return
	 */
	@ExceptionHandler(TooManyRequestException.class)
	public ResponseEntity<Object> tooManyRequestExceptionRequestHandler(TooManyRequestException tooManyRequestException, WebRequest request) {
		//LOGGER.error(tooManyRequestException.getMessage(), tooManyRequestException);
		RestErrorDomainImpl restErrorDomainImpl = restErrorResolver.resolveTooManyRequestRequestError(tooManyRequestException);
		return handleExceptionInternal(tooManyRequestException, RestErrorConverterImpl.restErrorConvertor(restErrorDomainImpl),
        		getHeaders(), HttpStatus.TOO_MANY_REQUESTS, request);
	}

	/**
	 *
	 * @param internalServerException
	 * @param request
	 * @return
	 */
	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<Object> internalServerExceptionRequestHandler(InternalServerException internalServerException, WebRequest request,String systemMessage) {
		//LOGGER.error(internalServerException.getMessage(), internalServerException);
		RestErrorDomainImpl restErrorDomainImpl = restErrorResolver.resolveInternalServerExceptionRequest(internalServerException,systemMessage);
		return handleExceptionInternal(internalServerException, RestErrorConverterImpl.restErrorConvertor(restErrorDomainImpl),
        		getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	/**
	 *
	 * @param customWarningException
	 * @param request
	 * @return
	 */
	@ExceptionHandler(CustomExceptionWarning.class)
	public ResponseEntity<Object> customExceptionWarningRequestHandler(CustomExceptionWarning customWarningException , WebRequest request) {
		//LOGGER.error(customWarningException.getMessage(), customWarningException);
		CustomRestErrorDomainImpl customRestErrorDomainImpl = restErrorResolver.resolveCustomExceptionWarningRequest(customWarningException);
		return handleExceptionInternal(customWarningException, RestErrorConverterImpl.customExceptionConverter(customRestErrorDomainImpl),
				getHeaders(),HttpStatus.SEE_OTHER, request);
	}


	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Object> databaseError(SQLException e, WebRequest request) {
		//LOGGER.error(e.getMessage(), e);
		return internalServerExceptionRequestHandler(new InternalServerException("",null), request,e.toString());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> runTimeError(RuntimeException e, WebRequest request) {
		//LOGGER.error(e.getMessage(), e);
		return internalServerExceptionRequestHandler(new InternalServerException("",null), request,e.toString());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> unknowError(Exception e, WebRequest request) {
		//LOGGER.error(e.getMessage(), e);
		return internalServerExceptionRequestHandler(new InternalServerException("",null), request, e.toString());
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<Object> IOError(Exception e, WebRequest request) {
		//LOGGER.error(e.getMessage(), e);
		return internalServerExceptionRequestHandler(new InternalServerException("",null), request,e.toString());
	}

	/**
	 *
	 * @return
	 */
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}


}
