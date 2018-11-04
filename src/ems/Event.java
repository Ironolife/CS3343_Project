package ems;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Event {
	
	private UUID id;
	private String name;
	private Date startTime;
	private Date endTime;
	private UUID locationId;
	private ArrayList<UUID> ticketIds;
	private boolean isMature;
	private ArrayList<UUID> reviewIds;
	
	public Event(String name, Date startTime, Date endTime, Location location, boolean isMature) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.locationId = location.getId();
		this.ticketIds = new ArrayList<UUID>();
		this.isMature = isMature;
		this.reviewIds = new ArrayList<UUID>();
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Location getLocation() {
		BackEnd backEnd = BackEnd.getInstance();
		for(Location location: backEnd.getLocations()) {
			if(location.getId().equals(this.locationId)) {
				return location;
			}
		}
		return null;
	}
	
	public boolean generateTickets(double price, double vipPriceMultiplier, int normalSize, int vipSize) {
		
		int totalSize = normalSize + vipSize;
		
		BackEnd backEnd = BackEnd.getInstance();
		
		Location location = this.getLocation();
		
		if(totalSize <= location.getCapacity()) {
			
			for(int i = 0; i < normalSize; i++) {
				Ticket ticket = new Ticket(this, price, i);
				this.ticketIds.add(ticket.getId());
			}
			for(int i = normalSize; i < normalSize + vipSize; i++) {
				VIPTicket vipTicket = new VIPTicket(this, price, i, vipPriceMultiplier);
				this.ticketIds.add(vipTicket.getId());
			}
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public ArrayList<Ticket> getTickets() {
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		for(UUID ticketId: this.ticketIds) {
			for(Ticket ticket: backEnd.getTickets()) {
				if(ticket.getId().equals(ticketId)) {
					tickets.add(ticket);
				}
			}
		}
		return tickets;
	}
	
	public Ticket removeTicket(Ticket ticket) {
		boolean result = this.ticketIds.remove(ticket.getId());
		if(result == true) {
			return ticket;
		}
		return null;
	}
	
	public double getSales() {
		double total = 0;
		
		for(Ticket ticket: this.getTickets()) {
			if(ticket.getPurchaser() != null) {
				total += ticket.getPrice();
			}
		}
		return total;
	}

	public boolean getIsMature() {
		return this.isMature;
	}
	
	public void addReview(Review review) {
		this.reviewIds.add(review.getId());
	}
	
	public void removeReview(Review review) {
		this.reviewIds.remove(review.getId());
	}
	
	public ArrayList<Review> getReviews() {
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Review> reviews = new ArrayList<Review>();
		for(UUID reviewId: this.reviewIds) {
			for(Review review: backEnd.getReviews()) {
				if(review.getId().equals(reviewId)) {
					reviews.add(review);
				}
			}
		}
		return reviews;
	}
	
	public double getAverageRating() {
		double ratingSum = 0;
		for(Review r: this.getReviews()) {
			ratingSum += r.getRating();
		}
		return ratingSum / this.reviewIds.size();
	}

}
