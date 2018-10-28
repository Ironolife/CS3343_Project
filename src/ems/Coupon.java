package ems;

import java.util.Date;
import java.util.UUID;

public class Coupon {
	
	private UUID id;
	private String code;
	private int discountType; //0 for flat discount, 1 for percentage discount.
    private double discount;
    private Date expiryDate;
    
    public Coupon(String code, int discountType, double discount, Date expiryDate) {
    	this.id = UUID.randomUUID();
    	this.code = code;
    	this.discountType = discountType;
        this.discount = discount;
        this.expiryDate = expiryDate;
    }
    
    public UUID getID() {
    	return this.id;
    }
    
    public String getCode() {
    	return this.code;
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
    
}
