package br.com.api.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest req) {
		
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "object not found.", e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validateFieldMessages(MethodArgumentNotValidException e, HttpServletRequest req) {
		
		ValidationErrors errors = new ValidationErrors(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "validation error.", req.getRequestURI());
		for (FieldError fe: e.getBindingResult().getFieldErrors()) {
			errors.addError(fe.getField(), fe.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
}
