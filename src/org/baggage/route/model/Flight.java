package org.baggage.route.model;

public class Flight {
	
	private String flightNumber;
	private String terminalname ;
	private String destinationCity;
	private String departureTime;
	
	
	public Flight(String flightNumber, String terminalname, String destinationCity, String departureTime) {
		this.flightNumber = flightNumber;
		this.terminalname = terminalname;
		this.destinationCity = destinationCity;
		this.departureTime = departureTime;
	}
	

	/**
	 * @return the flightNumber
	 */
	public String getFlightNumber() {
		return flightNumber;
	}


	/**
	 * @return the terminalname
	 */
	public String getTerminalname() {
		return terminalname;
	}


	/**
	 * @return the destinationCity
	 */
	public String getDestinationCity() {
		return destinationCity;
	}


	/**
	 * @return the dateTime
	 */
	public String getDateTime() {
		return departureTime;
	}



}
