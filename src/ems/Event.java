package ems;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Event {
	
	private UUID id;
	private String eventName;
	private Date startTime;
	private Date endTime;
	private String location;
	private ArrayList<Review> reviews;
	
	public Event(String aEventName, Date aStartTime, Date aEndTime, String aLocation) {
		this.id = UUID.randomUUID();
		this.eventName = aEventName;
		this.startTime = aStartTime;
		this.endTime = aEndTime;
		this.location = aLocation;
		this.reviews = new ArrayList<Review>();
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
	
	public String getEventInfo() {
		String eventInfo = this.getEventName() + ": " + this.getStartTime() + " - " + this.getEndTime() + "\n";
		eventInfo +=  "Location: " + this.getLocation() + "\n";
		return eventInfo;
	}

}
