package ems;

import java.util.Date;
import java.util.UUID;

public class Coupon {
	
	private UUID id;
	private String code;
    private double discount;
    private Date expiryDate;
    
    public Coupon(String code, double discount, Date expiryDate) {
    	this.id = UUID.randomUUID();
    	this.code = code;
        this.discount = discount;
        this.expiryDate = expiryDate;
    }
    
    public UUID getID() {
    	return this.id;
    }
    
    public String getCode() {
    	return this.code;
    }
    
    public double getDiscount() {
        return this.discount;
    }
    
    public Date getExpiryDate() {
    	return this.expiryDate;
    }
    
}
