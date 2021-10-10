package br.com.builders.apiclients.exception;

public class ClientNotFoundException extends RuntimeException implements INotFoundException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ClientNotFoundException(Long id) {
		this.message = String.format("Client with id %d not found." , id);
	}

	public String getMessage() {
		return message;
	}

}
