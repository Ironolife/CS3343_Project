package ems;

import java.util.Date;

public class Event {
	String eventName;
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

	Date startTime;
	Date endTime;
	String location;
	
	public Event(String aEventName, Date aStartTime, Date aEndTime, String aLocation) {
		this.eventName = aEventName;
		this.startTime = aStartTime;
		this.endTime = aEndTime;
		this.location = location;
	}
	
	public String getEventInfo() {
		String eventInfo = this.getEventName() + ": " + this.getStartTime() + " - " + this.getEndTime() + "\n";
		eventInfo +=  "Location: " + this.getLocation() + "\n";
		return eventInfo;
		
	}

}
