package com.arcanumLudum.ALCore.util.exceptions;

public class IconRedefinitionException extends Exception {
	// Parameterless Constructor
	public IconRedefinitionException() {
		
	}

	// Constructor that accepts a message
	public IconRedefinitionException(String message) {
		super(message);
	}

	public IconRedefinitionException(Throwable cause) {
		super(cause);
	}

	public IconRedefinitionException(String message, Throwable cause) {
		super(message, cause);
	}
}