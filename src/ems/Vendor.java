package ems;

import java.util.Date;

public class Vendor {
	
	private String vendorName;
	private Event event;
	
	public Vendor(String vendorName, Event event){
		this.vendorName = vendorName;
		this.event = event;
	}
	
	public String getVendorName(){
		return vendorName;
	}
	
	public Event getEvent(){
		return event;
	}
}
