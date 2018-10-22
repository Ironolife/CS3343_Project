package ems;

import java.util.UUID;

public class Review {
	
	private UUID id;
	private User user;
	private double rating;
	private String comment;
	
	public Review(User user, double rating, String comment) {
		this.id = UUID.randomUUID();
		this.rating = rating;
		this.comment = comment;
	}
	
	public UUID getID() {
		return this.id;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public String getComment() {
		return this.comment;
	}

}
