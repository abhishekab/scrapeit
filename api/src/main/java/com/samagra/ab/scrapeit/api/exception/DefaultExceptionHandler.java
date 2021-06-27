package com.samagra.ab.scrapeit.api.exception;

import com.samagra.ab.scrapeit.api.dto.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Default error message in case of uncaught/unexpected exception.
	 */
	public static final String DEFAULT_ERROR_MESSAGE = "Something went wrong unexpectedly";

	/**
	 * Error message in case of IllegalArgumentException exception.
	 */
	public static final String ILLEGAL_ARGUMENTS_ERROR_MESSAGE = "Invalid Arguments Passed";

	private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ResponseData<String>> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
		logger.error("RuntimeException", ex);
		ResponseData<String> responseData = new ResponseData<>(false, null, ILLEGAL_ARGUMENTS_ERROR_MESSAGE);
		return new ResponseEntity<>(responseData, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseData<String>> genericExceptionHandler(RuntimeException ex) {
		logger.error("RuntimeException", ex);
		ResponseData<String> responseData = new ResponseData<>(false, null, DEFAULT_ERROR_MESSAGE);
		return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
