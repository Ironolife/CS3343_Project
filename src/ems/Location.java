package ems;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Location {
	
	private UUID id;
	private String name;
	private int capacity;
	private ArrayList<UUID> eventIds;
	
	public Location(String name, int capacity) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.capacity = capacity;
		this.eventIds = new ArrayList<UUID>();
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
	
	public ArrayList<Event> getEvents() {
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> events = new ArrayList<Event>();
		for(UUID eventId: this.eventIds) {
			for(Event event: backEnd.getEvents()) {
				if(event.getId().equals(eventId)) {
					events.add(event);
				}
			}
		}
		return events;
	}
	
	public void addEvent(Event event) {
		this.eventIds.add(event.getId());
	}

	public Event removeEvent(Event event) {
		boolean result = this.eventIds.remove(event.getId());
		if(result == true) {
			return event;
		}
		return null;
	}

}
