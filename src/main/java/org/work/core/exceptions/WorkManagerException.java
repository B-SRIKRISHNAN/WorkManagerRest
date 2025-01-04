package org.work.core.exceptions;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This exception handles all work manager related exceptions
 */
@SuppressWarnings("serial")
public class WorkManagerException extends Exception {

	public WorkManagerException(String message) {
		super(message);
	}
	
}
