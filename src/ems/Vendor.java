package ems;

import java.util.Date;

public class Vendor {
	
	private String eventName;
	private Date startTime;
	private Date endTime;
	private String location;
	
	public Vendor(String eventName, Date startTime, Date endTime, String location){
		Event event = new Event(eventName, startTime, endTime, location);
	}
}
