package org.baggage.route.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.baggage.route.error.FlightException;
import org.baggage.route.error.TerminalException;
import org.baggage.route.model.Bag;
import org.baggage.route.model.ConveyorBelt;
import org.baggage.route.model.ConveyorSystem;
import org.baggage.route.model.Terminal;


public class OptimalPath {
		
	private ConveyorSystem conveyorSystem ;
	private FlightCollection flightInfo;
	private Bag bag;
	
	//stores shortest distance from source terminal to every other terminal in conveyor system
    private Map<Terminal,Integer> shortestTravelTime;
    
    //stores parent of every terminal in shortest travel time
    private Map<Terminal, Terminal> parent ;
	
	public OptimalPath(ConveyorSystem conveyorSystem, FlightCollection flightInfo) {
		this.conveyorSystem = conveyorSystem;
		this.flightInfo = flightInfo;
	}

    private Terminal getTerminalfromConveyorBelt(Terminal v, ConveyorBelt e){
        return e.getSourceTerminal().equals(v) ? e.getDestinationTerminal() : e.getSourceTerminal();
    }

	public void calculateShortestRoute(String strSourceTerminal){

	  	//stores shortest distance from source terminal to every other terminal in conveyor system
		shortestTravelTime = new HashMap<>();

        //stores parent of every terminal in shortest travel time
        parent = new HashMap<>();
        
        //heap + map data structure
        BinaryMinHeap<Terminal> minHeap = new BinaryMinHeap<>();

        //initialize all terminal with infinite distance from source terminal
        for(Terminal terminal : conveyorSystem.getTerminals()){
            minHeap.add(Integer.MAX_VALUE, terminal);
        }
        
        Terminal sourceTerminal = conveyorSystem.getTerminal(strSourceTerminal);
        
        //set distance of source terminal to itself 0
        minHeap.decrease(sourceTerminal, 0);

        //put it in map
        shortestTravelTime.put(sourceTerminal, 0);

        //source terminal parent is null
        parent.put(sourceTerminal, null);

        //iterate till heap is not empty
        while(!minHeap.empty()){
            //get the min value from heap node which has vertex and distance of that vertex from source vertex.
            BinaryMinHeap<Terminal>.Node heapNode = minHeap.extractMinNode();
            Terminal currentTerminal = heapNode.key;

            //update shortest distance of current vertex from source vertex
            shortestTravelTime.put(currentTerminal, heapNode.weight);

            //iterate through all edges of current vertex
            for(ConveyorBelt conveyorBelt : currentTerminal.getConnectingConveyorBelts()){

                //get the adjacent vertex
                Terminal adjacent = getTerminalfromConveyorBelt(currentTerminal, conveyorBelt);

                //if heap does not contain adjacent vertex means adjacent vertex already has shortest distance from source vertex
                if(!minHeap.containsData(adjacent)){
                    continue;
                }

                //add distance of current vertex to edge weight to get distance of adjacent vertex from source vertex
                //when it goes through current vertex
                int newDistance = shortestTravelTime.get(currentTerminal) + conveyorBelt.getWeight();

                //see if this above calculated distance is less than current distance stored for adjacent vertex from source vertex
                if(minHeap.getWeight(adjacent) > newDistance) {
                    minHeap.decrease(adjacent, newDistance);
                    parent.put(adjacent, currentTerminal);
                }
            }
        }
        return;
    }

	public String getShortestRoute (Bag bag) throws TerminalException, FlightException
	{
		String SourceTerminal = bag.getTerminalName();
		if(conveyorSystem.getTerminal(SourceTerminal) == null)
		{
			throw new TerminalException("The terminal " + SourceTerminal + " is not present in the conveyor system" );
		}
		
		if(flightInfo.getFlight(bag.getFlghtNumber()) == null && (! bag.getFlghtNumber().equals("ARRIVAL")))
		{
			throw new FlightException("The flight " + bag.getFlghtNumber() + " is not present in the conveyor system" );
		}
		String destinationTerminal = "BaggageClaim";
		
		if(!bag.getFlghtNumber().equals("ARRIVAL"))
			destinationTerminal = flightInfo.getFlight(bag.getFlghtNumber()).getTerminalname();
		
		String shortestRoute ;
	    Stack<String> Path = new Stack<>();

		this.calculateShortestRoute(SourceTerminal);

		String lookupTerminal = destinationTerminal;
	    Path.push(lookupTerminal);
	    while(!lookupTerminal.equals(SourceTerminal))
	    {
	    	
	    	Terminal parentTerminal = parent.get(conveyorSystem.getTerminal(lookupTerminal));
	    	lookupTerminal = parentTerminal.getName();
	    	Path.push(lookupTerminal);
	    }
	    
	    String route = bag.getBagId();
	    
	    while(!Path.empty())
	    {
	    	route = route +  " " + Path.pop();
	    }
	    
	    route =  route +  " : " + shortestTravelTime.get(conveyorSystem.getTerminal(destinationTerminal)).toString();
		return route;
	}

}
