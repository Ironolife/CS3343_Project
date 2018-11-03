package ems;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Location {
	
	private UUID id;
	private String name;
	private int capacity;
	private ArrayList<Event> eventList = new ArrayList<>(); //  a list of events taken place there
	
	
	public Location() {
		
	}
	
	public Location(String name, int capacity) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.capacity = capacity;
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public List<Event> getEventList() {
		return this.eventList;
	}
	
	public void addEventToTheEventList(Event event) {
		this.eventList.add(event);
	}

	public void setEventList(ArrayList<Event> eventList) {
		this.eventList = eventList;
	}

}
