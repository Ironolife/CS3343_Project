package ems;

import java.util.Date;
import java.util.UUID;

public class Ticket {
	
	private UUID id;
	private Event event;
	private int seat;
	private Date entryTime;
	private Date exitTime;
	
	private double price;
	private User purchaser;
	private Date purchaseTime;
	
	public Ticket(Event event, double price, int seat) {
		this.id = UUID.randomUUID();
		this.event = event;
		this.price = price;
		this.seat = seat;
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public Event getEvent() {
		return this.event;
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
		
		if(this.purchaser == null) {
			this.purchaser = purchaser;
			this.purchaseTime = new Date();
			return true;
		}
		return false;
		
	}
	
	public User getPurchaser() {
		return this.purchaser;
	}
	
	public Date getPurchaseTime() {
		return this.purchaseTime;
	}
	
	public int getStatus() {
		
		if(this.purchaser == null) {
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
