package org.baggage.route.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConveyorSystem {
	private List<ConveyorBelt> conveyorBelts = new ArrayList<>();
	private Map<String,Terminal> terminals = new HashMap<>();

	public void addConveyorBelt(String strSourceTerminal, String strDestinationTerminal , int travelTime)
	{
		Terminal sourceTerminal = null;
		if(terminals.containsKey(strSourceTerminal))
		{
			sourceTerminal = terminals.get(strSourceTerminal);
		}
		else
		{
			sourceTerminal = new Terminal(strSourceTerminal);
			terminals.put(strSourceTerminal, sourceTerminal);
		}
		
		Terminal destinationTerminal = null;
		if(terminals.containsKey(strDestinationTerminal))
		{
			destinationTerminal = terminals.get(strDestinationTerminal);
		}
		else
		{
			destinationTerminal = new Terminal(strDestinationTerminal);
			terminals.put(strDestinationTerminal, destinationTerminal);
		}
		
		ConveyorBelt belt = new ConveyorBelt(sourceTerminal, destinationTerminal, travelTime);
		conveyorBelts.add(belt);
		sourceTerminal.addAdjacentTeminal(destinationTerminal, belt);
		destinationTerminal.addAdjacentTeminal(sourceTerminal, belt);
	}
	
	public Terminal getTerminal(String strTerminalName)
	{
		return terminals.get(strTerminalName);
	}

	/**
	 * @return the conveyorBelts
	 */
	public List<ConveyorBelt> getConveyorBelts() {
		return conveyorBelts;
	}

	/**
	 * @return the terminals
	 */
	public List<Terminal> getTerminals() {
		return new ArrayList<Terminal>(terminals.values());
	}
	

	
	
}
