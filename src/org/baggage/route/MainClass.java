package org.baggage.route;


import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.baggage.route.error.FlightException;
import org.baggage.route.error.TerminalException;
import org.baggage.route.model.Bag;
import org.baggage.route.model.ConveyorSystem;
import org.baggage.route.model.Terminal;
import org.baggage.route.service.BagCollection;
import org.baggage.route.service.FlightCollection;
import org.baggage.route.service.OptimalPath;

public class MainClass {

	public static void main(String[] args) {
	
		ConveyorSystem conveyorSystem = new ConveyorSystem();
		System.out.println("# Section: Conveyor System :  A weighted bi-directional graph describing the conveyor system.");
		System.out.println("please input in the Format of <Node 1> <Node 2> <travel_time> and to exit the section input " + "'EOF'");
		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
	    while (stdin.hasNextLine()) {
        	String str = stdin.nextLine();
        	if(!str.equalsIgnoreCase("eof")){
        		
        		if(!str.isEmpty())
        		{
        		 String input[] =	str.split(" ");
        		 conveyorSystem.addConveyorBelt(input[0], input[1], Integer.valueOf(input[2]));
        		 //System.out.println("input 1: " + input[0] + " input 2: " + input[1] + " input 3: " + input[2] );
        			}
        	}
        	else
        	{
        		break;
        	}
        	
        }


		System.out.println("# Section: Departures : ");
		System.out.println("please input departure list in Format: <flight_id> <flight_gate> <destination> <flight_time> and to exit the section input " + "'EOF'");
		FlightCollection flightInfo = new FlightCollection();
		
		while (stdin.hasNextLine()) {
        	String str = stdin.nextLine();
        	if(!str.equalsIgnoreCase("eof")){
        		
        		if(!str.isEmpty()){
        		 String input[] =	str.split(" ");
        		 flightInfo.addFlight(input[0], input[1], input[2],input[3]);
        		 }
        		}
        	else{
        		break;
        	}
        }

		System.out.println("# Section: Bags : ");
		System.out.println("please input Bag list Format: <bag_number> <entry_point> <flight_id> and to exit the section input " + "'EOF'");
		BagCollection bags = new BagCollection();
		
		while (stdin.hasNextLine()) {
        	String str = stdin.nextLine();
        	if(!str.equalsIgnoreCase("eof")){
        		
        		if(!str.isEmpty()){
        		 String input[] =	str.split(" ");
        		 bags.addBag(input[0], input[1], input[2]);
        		 }
        		}
        	else{
        		break;
        	}
        }
	
		OptimalPath path = new OptimalPath(conveyorSystem, flightInfo);
		System.out.println();
		for (Bag bag : bags.getBags()) {
		
			//System.out.println("Input:  " + bag.getBagId() + " " + bag.getTerminalName() + " " + bag.getFlghtNumber());
			try {
				System.out.println(path.getShortestRoute(bag));
			} catch (TerminalException | FlightException e) {
				System.out.println(e.getMessage());
			}
		}
				
	
	}

}
