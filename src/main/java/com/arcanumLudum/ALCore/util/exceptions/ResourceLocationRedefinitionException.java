package com.arcanumLudum.ALCore.util.exceptions;

public class ResourceLocationRedefinitionException extends Exception {
	// Parameterless Constructor
	public ResourceLocationRedefinitionException() {
		
	}

	// Constructor that accepts a message
	public ResourceLocationRedefinitionException(String message) {
		super(message);
	}

	public ResourceLocationRedefinitionException(Throwable cause) {
		super(cause);
	}

	public ResourceLocationRedefinitionException(String message, Throwable cause) {
		super(message, cause);
	}
}