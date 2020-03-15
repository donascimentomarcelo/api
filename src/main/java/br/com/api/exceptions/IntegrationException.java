package br.com.api.exceptions;

public class IntegrationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IntegrationException(String msg) {
		super(msg);
	}
	
	public IntegrationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
