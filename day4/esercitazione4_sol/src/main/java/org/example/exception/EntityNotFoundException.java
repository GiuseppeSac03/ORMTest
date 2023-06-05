package org.example.exception;

public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = -4182069337338043578L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
