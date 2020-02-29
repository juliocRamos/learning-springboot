package com.learningspring.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learningspring.services.exceptions.DatabaseException;
import com.learningspring.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardException> resourceNotFound(ResourceNotFoundException ex,
			HttpServletRequest servletRequest) {

		String error = "Resource not found";

		HttpStatus status = HttpStatus.NOT_FOUND;

		StandardException stdException = new StandardException(Instant.now(), status.value(), error, ex.getMessage(),
				servletRequest.getRequestURI());

		return ResponseEntity.status(status).body(stdException);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardException> databaseIntegrityViolation(DatabaseException ex,
			HttpServletRequest servletRequest) {

		String error = "Database integrity violation";

		HttpStatus status = HttpStatus.BAD_REQUEST;

		StandardException stdException = new StandardException(Instant.now(), status.value(), error, ex.getMessage(),
				servletRequest.getRequestURI());

		return ResponseEntity.status(status).body(stdException);
	}
}
