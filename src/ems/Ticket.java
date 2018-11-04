package ems;

import java.util.Date;
import java.util.UUID;

public class Ticket {
	
	private UUID id;
	private UUID eventId;
	private int seat;
	private Date entryTime;
	private Date exitTime;
	
	private double price;
	private UUID purchaserId;
	private Date purchaseTime;
	
	public Ticket(Event event, double price, int seat) {
		this.id = UUID.randomUUID();
		this.eventId = event.getId();
		this.price = price;
		this.seat = seat;
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public Event getEvent() {
		BackEnd backEnd = BackEnd.getInstance();
		for(Event event: backEnd.getEvents()) {
			if(event.getId() == this.eventId) {
				return event;
			}
		}
		return null;
	}
	
	public int getSeat() {
		return this.seat;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public boolean setEntryTime() {
		
		if(this.entryTime == null) {
			this.entryTime = new Date();
			return true;
		}
		return false;
		
	}
	
	public boolean setExitTime() {
		
		if(this.exitTime == null) {
			this.exitTime = new Date();
			return true;
		}
		return false;
		
	}
	
	public boolean Purchase(User purchaser) {
		
		if(this.purchaserId == null) {
			this.purchaserId = purchaser.getId();
			this.purchaseTime = new Date();
			return true;
		}
		return false;
		
	}
	
	public User getPurchaser() {
		BackEnd backEnd = BackEnd.getInstance();
		for(User user: backEnd.getUsers()) {
			if(user.getId() == this.purchaserId) {
				return user;
			}
		}
		return null;
	}
	
	public Date getPurchaseTime() {
		return this.purchaseTime;
	}
	
	public int getStatus() {
		
		if(this.purchaserId == null) {
			return 0; //Available Ticket
		}
		else if(this.entryTime == null) {
			return 1; //Purchased but Unused Ticket
		}
		else if(this.exitTime == null) {
			return 2; //Entered Ticket
		}
		else {
			return 3; //Left Ticket
		}
		
	}
	
}
