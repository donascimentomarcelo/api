package br.com.api.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors extends StandardError {
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationErrors(Long timeStamp, Integer status, String error, String path) {
		super(timeStamp, status, error, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldMessage> errors) {
		this.errors = errors;
	}
	
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
}
