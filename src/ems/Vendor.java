package ems;

import java.util.ArrayList;
import java.util.UUID;

public class Vendor {
	
	private UUID id;
	private String loginId;
	private String password;
	private String name;
	private ArrayList<UUID> eventIds;
	
	public Vendor(String loginId, String password, String name){
		this.id = UUID.randomUUID();
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.eventIds = new ArrayList<UUID>();
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getLoginId() {
		return this.loginId;
	}
	
	public boolean validatePassword(String inputPassword) {
		return this.password.equals(inputPassword);
	}
	
	public void changePassword(String newPassword) {
		this.password = newPassword;
	}
	
	public String getName(){
		return this.name;
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
	
	public double getAccumulateSales()  {
		double total = 0;
		
		for(Event event: this.getEvents()) {
			total += event.getSales();
		}
		return total;
	}
}
