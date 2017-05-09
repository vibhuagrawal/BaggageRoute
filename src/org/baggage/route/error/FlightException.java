package org.baggage.route.error;

public class FlightException extends Exception {
	
	private String errorMsg;

	public FlightException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	
}
