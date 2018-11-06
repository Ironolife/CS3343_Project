package ems;

import java.util.Date;
import java.util.UUID;

public class Coupon {
	
	private UUID id;
	private String code;
	private UUID eventId;
	private int discountType; //0 for flat discount, 1 for percentage discount, 2 for free purchase
    private double discount;
    private Date expiryDate;
    
    public Coupon(String code, Event event, int discountType, double discount, Date expiryDate) {
    	this.id = UUID.randomUUID();
    	this.code = code;
    	this.eventId = event.getId();
    	this.discountType = discountType;
        this.discount = discount;
        this.expiryDate = expiryDate;
    }
    
    public UUID getId() {
    	return this.id;
    }
    
    public String getCode() {
    	return this.code;
    }
    
    public Event getEvent() {
		BackEnd backEnd = BackEnd.getInstance();
		for(Event event: backEnd.getEvents()) {
			if(event.getId().equals(this.eventId)) {
				return event;
			}
		}
		return null;
	}
    
    public int getDiscountType() {
    	return this.discountType;
    }
    
    public double getDiscount() {
        return this.discount;
    }
    
    public Date getExpiryDate() {
    	return this.expiryDate;
    }
    
    public double getDiscountedAmount(double price) {
    	
    	switch(this.discountType) {
	    	case 0: {
	    		return (price - this.discount);
	    	}
	    	case 1: {
	    		return (price * (1 - this.discount));
	    	}
	    	case 2: {
	    		return 0;
	    	}
	    	default: {
	    		return -1;
	    	}
    	}
    	
    }
    
}
