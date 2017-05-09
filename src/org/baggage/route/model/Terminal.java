package org.baggage.route.model;

import java.util.ArrayList;
import java.util.List;

public class Terminal {
 
	private final String name;
	private List<Terminal> adjacentTerminals = new ArrayList<>();
	private List<ConveyorBelt> connectingConveyorBelts = new ArrayList<>();

	public Terminal(String name)
	{
		this.name= name;
	}
	public String getName() {
		return name;
	}
	
	public List<Terminal> getAdjacentTerminals() {
		return adjacentTerminals;
	}
	
	public List<ConveyorBelt> getConnectingConveyorBelts() {
		return connectingConveyorBelts;
	}
	
	public void addAdjacentTeminal(Terminal t, ConveyorBelt c)
	{
		adjacentTerminals.add(t);
		connectingConveyorBelts.add(c);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Terminal other = (Terminal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Terminal [name=" + name + "]";
	}


}
