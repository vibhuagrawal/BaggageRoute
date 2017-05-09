package org.baggage.route.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.baggage.route.model.Bag;

public class BagCollection {

	private Map<String,Bag> bags;
	
	public BagCollection() {
		bags = new HashMap<>();
	}
	
	public void addBag(String bagId, String terminalname, String flghtNumber)
	{	
		bags.putIfAbsent( bagId, new Bag(bagId, terminalname, flghtNumber));
	}
	
	public Bag getBag(String bagId)
	{
		return bags.get(bagId);
	}
	
	public List<Bag> getBags()
	{
		List<Bag> list = new ArrayList<Bag>(bags.values());
		Collections.sort(list,new Comparator<Bag>() {

			@Override
			public int compare(Bag o1, Bag o2) {
			return o1.getBagId().compareTo(o2.getBagId());
			
			}
			
		});
		return  list;
	}
}
