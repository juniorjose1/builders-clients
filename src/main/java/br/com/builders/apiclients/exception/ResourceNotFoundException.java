package br.com.builders.apiclients.exception;

public class ResourceNotFoundException extends RuntimeException implements INotFoundException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ResourceNotFoundException() {
		this.message = "No records found";
	}

	public String getMessage() {
		return message;
	}

}
