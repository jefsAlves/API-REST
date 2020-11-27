package com.ibm.application.spb.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ibm.application.spb.services.exceptions.DataIntegrityException;
import com.ibm.application.spb.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<StandardError> resquestError(ObjectNotFoundException e, HttpServletRequest request) {
		String error = "Resource Not Found!";
		StandardError err = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), error, e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler
	public ResponseEntity<StandardError> integrityViolate(DataIntegrityException e, HttpServletRequest request) {
		String error = "Bad Request!";
		StandardError err = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), error, e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler
	public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		String error = "Validation in Error!";
		ValidationError err = new ValidationError(Instant.now(), HttpStatus.BAD_REQUEST.value(), error, "BAD_REQUEST!!",
				request.getRequestURI());

		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
