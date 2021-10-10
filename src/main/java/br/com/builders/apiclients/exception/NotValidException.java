package br.com.builders.apiclients.exception;

public class NotValidException {

	private String field;
	private String message;
	private String timestamp;

	public NotValidException(String field, String message, String timestamp) {
		this.field = field;
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

	public String getTimestamp() {
		return timestamp;
	}

}
