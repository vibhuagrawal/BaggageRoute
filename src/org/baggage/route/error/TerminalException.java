package org.baggage.route.error;

public class TerminalException extends Exception {
	
	private String errorMsg;

	public TerminalException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	
}
