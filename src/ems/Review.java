package ems;

import java.util.Date;
import java.util.UUID;

public class Review {
	
	private UUID id;
	private Member member;
	private double rating;
	private String comment;
	private Date date;
	
	public Review(Member member, double rating, String comment) {
		this.id = UUID.randomUUID();
		this.member = member;
		this.rating = rating;
		this.comment = comment;
		this.date = new Date();
	}
	
	public UUID getID() {
		return this.id;
	}
	
	public Member getMember() {
		return this.member;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public String getDate() {
		return this.date.toLocaleString();
	}

}
