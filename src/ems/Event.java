package ems;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Event {
	
	private UUID id;
	private String name;
	private Date startTime;
	private Date endTime;
	private Location location;
	private ArrayList<Ticket> tickets;
	private boolean isMature;
	private ArrayList<Review> reviews;
	
	public Event(String name, Date startTime, Date endTime, Location location, boolean isMature) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.tickets = new ArrayList<Ticket>();
		this.isMature = isMature;
		this.reviews = new ArrayList<Review>();
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getEventName() {
		return name;
	}

	public void setEventName(String eventName) {
		this.name = eventName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Location getLocation() {
		return location;
	}
	
	public boolean generateTickets(double price, double vipPriceMultiplier, int normalSize, int vipSize) {
		
		int totalSize = normalSize + vipSize;
		
		if(totalSize <= this.location.getCapacity()) {
			
			for(int i = 0; i < normalSize; i++) {
				Ticket ticket = new Ticket(this, price, i);
				this.tickets.add(ticket);
			}
			for(int i = normalSize; i < normalSize + vipSize; i++) {
				VIPTicket vipTicket = new VIPTicket(this, price, i, vipPriceMultiplier);
				this.tickets.add(vipTicket);
			}
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public ArrayList<Ticket> getTickets() {
		return this.tickets;
	}
	
	public Ticket removeTicket(Ticket ticket) {
		boolean result = this.tickets.remove(ticket);
		if(result == true) {
			return ticket;
		}
		return null;
	}
	
	public double getSales() {
		double total = 0;
		
		for(Ticket ticket: this.tickets) {
			if(ticket.getPurchaser() != null) {
				total += ticket.getPrice();
			}
		}
		return total;
	}

	public boolean getIsMature() {
		return this.isMature;
	}
	
	public void addReview(User user, double rating, String comment) {
		Review review = new Review(user, rating, comment);
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	
	public double getAverageRating() {
		double ratingSum = 0;
		for(Review r: this.reviews) {
			ratingSum += r.getRating();
		}
		return ratingSum / this.reviews.size();
	}

}
