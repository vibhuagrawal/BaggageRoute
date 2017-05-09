package org.baggage.route.service;

import java.util.HashMap;
import java.util.Map;

import org.baggage.route.model.Flight;

public class FlightCollection {

	private Map<String,Flight> flightList;
	
	public FlightCollection() {
		flightList = new HashMap<>();
	}
	
	public void addFlight(String flightNumber, String terminalname, String destinationCity, String departureTime)
	{	
		flightList.putIfAbsent(flightNumber, new Flight(flightNumber, terminalname, destinationCity, departureTime));
	}
	
	public Flight getFlight(String FlightNumber)
	{
		return flightList.get(FlightNumber);
	}
}
