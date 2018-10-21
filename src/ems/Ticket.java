package ems;

import java.util.Date;
import java.util.UUID;

public class Ticket {
	
	private UUID id;
	private Event event;
	private double price;
	private String seat;
	private Date entryTime;
	private Date exitTime;
	/**
	 * false = not be bought by anyone
	 * true = bought by someone
	 */
	private boolean isBoughtFlag; 


	/**
	 * logger is static since it does not belong to object
	 */
	private static Log logger;
	
	public Ticket(Event event, double price, String seat) {
		this.id = UUID.randomUUID();
		this.event = event;
		this.price = price;
		this.seat = seat;
		this.isBoughtFlag = false;
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public Event getEvent() {
		return this.event;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getSeat() {
		return this.seat;
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
	
	public void setBoughtStatus(boolean aIsBoughtFlag) {
		this.isBoughtFlag = aIsBoughtFlag;
	}
	
	public boolean getBoughtStatus() {
		return isBoughtFlag;
	}
	
	public int getStatus() {
		
		if(this.entryTime == null) {
			return 0; //Unused Ticket
		}
		else if(this.exitTime == null) {
			return 1; //Entered Ticket
		}
		else {
			return 2; //Left Ticket
		}
		
	}
	
	
	public void linkTheTicketToAnEvent(Event anEvent) {
		this.event = anEvent;
	}
	
	public String getEventInformation() {
		return this.getEvent().getEventInfo();
	}
}
