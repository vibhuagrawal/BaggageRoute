package org.baggage.route.model;

public class Bag {

	private String bagId;
	private String terminalName;
	private String FlghtNumber;
	
	public Bag(String bagId, String terminalName, String flghtNumber) {
		super();
		this.bagId = bagId;
		this.terminalName = terminalName;
		FlghtNumber = flghtNumber;
	}

	/**
	 * @return the bagId
	 */
	public String getBagId() {
		return bagId;
	}

	/**
	 * @return the terminalName
	 */
	public String getTerminalName() {
		return terminalName;
	}

	/**
	 * @return the flghtNumber
	 */
	public String getFlghtNumber() {
		return FlghtNumber;
	}
	
}
