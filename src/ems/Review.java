package ems;

import java.util.Date;
import java.util.UUID;

public class Review {
	
	private UUID id;
	private UUID memberId;
	private double rating;
	private String comment;
	private Date date;
	
	public Review(Member member, double rating, String comment) {
		this.id = UUID.randomUUID();
		this.memberId = member.getId();
		this.rating = rating;
		this.comment = comment;
		this.date = new Date();
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public Member getMember() {
		BackEnd backEnd = BackEnd.getInstance();
		for(User user: backEnd.getUsers()) {
			if(user.getId().equals(this.memberId)) {
				return (Member) user;
			}
		}
		return null;
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
