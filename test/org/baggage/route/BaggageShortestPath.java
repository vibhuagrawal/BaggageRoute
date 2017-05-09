package org.baggage.route;

import static org.junit.Assert.*;

import org.baggage.route.error.FlightException;
import org.baggage.route.error.TerminalException;
import org.baggage.route.model.Bag;
import org.baggage.route.model.ConveyorSystem;
import org.baggage.route.service.BagCollection;
import org.baggage.route.service.FlightCollection;
import org.baggage.route.service.OptimalPath;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BaggageShortestPath {

	private static ConveyorSystem conveyorSystem ;
	private static FlightCollection flightInfo ;
	private static BagCollection bags ;
	
	static{
		conveyorSystem = new ConveyorSystem();
		flightInfo = new FlightCollection();
		bags = new BagCollection();
		
	}
	@BeforeClass
	public static void initialize()
	{
		  
		conveyorSystem.addConveyorBelt("Concourse_A_Ticketing", "A5", 5);
		conveyorSystem.addConveyorBelt("A5", "BaggageClaim", 5);
		conveyorSystem.addConveyorBelt("A5", "A10", 4);
		conveyorSystem.addConveyorBelt("A5", "A1", 6);
		conveyorSystem.addConveyorBelt("A1", "A2", 1);
		conveyorSystem.addConveyorBelt("A2", "A3", 1);
		conveyorSystem.addConveyorBelt("A3", "A4", 1);
		conveyorSystem.addConveyorBelt("A10", "A9", 1);
		conveyorSystem.addConveyorBelt("A9", "A8", 1);
		conveyorSystem.addConveyorBelt("A8", "A7", 1);
		conveyorSystem.addConveyorBelt("A7", "A6", 1);
		
		flightInfo.addFlight("UA10", "A1", "MIA", "08:00");
		flightInfo.addFlight("UA11", "A1", "LAX", "08:00");
		flightInfo.addFlight("UA12", "A1", "JFK", "08:00");
		flightInfo.addFlight("UA13", "A2", "JFK", "08:00");
		flightInfo.addFlight("UA14", "A2", "JFK", "08:00");
		flightInfo.addFlight("UA15", "A2", "JFK", "08:00");
		flightInfo.addFlight("UA16", "A3", "JFK", "08:00");
		flightInfo.addFlight("UA17", "A4", "MHT", "08:00");
		flightInfo.addFlight("UA18", "A5", "LAX", "08:00");
		
		bags.addBag("0001", "Concourse_A_Ticketing", "UA12");
		bags.addBag("0002", "A5", "UA17");
		bags.addBag("0003", "A2", "UA10");
		bags.addBag("0004", "A8", "UA18");
		bags.addBag("0005", "A7", "ARRIVAL");
	
	}
	
	@Test
	public void test() {
		OptimalPath path = new OptimalPath(conveyorSystem, flightInfo);
		System.out.println();
		Bag bag = bags.getBag("0001");
		
			try {
				String str = path.getShortestRoute(bag);
				assertEquals("Optimal path is wrong",str,"0001 Concourse_A_Ticketing A5 A1 : 11");
			} catch (TerminalException | FlightException e) {
				System.out.println(e.getMessage());
			}
		
	}

	@AfterClass
	public static void destroy()
	{
		conveyorSystem.getConveyorBelts().clear();
		conveyorSystem.getTerminals().clear();
		bags.getBags().clear();
	}
}
