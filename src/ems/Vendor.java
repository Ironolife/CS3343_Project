package ems;

import java.util.ArrayList;
import java.util.UUID;

public class Vendor {
	
	private UUID id;
	private String name;
	private ArrayList<Event> events;
	
	public Vendor(String name){
		this.id = UUID.randomUUID();
		this.name = name;
		this.events = new ArrayList<Event>();
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Event> getEvents(){
		return this.events;
	}
	
	public void addEvent(Event event) {
		this.events.add(event);
	}
	
	public Event removeEvent(Event event) {
		boolean result = this.events.remove(event);
		if(result == true) {
			return event;
		}
		return null;
	}
	
	public double getAccumulateSales()  {
		double total = 0;
		
		for(Event event: events) {
			total += event.getSales();
		}
		return total;
	}
}
