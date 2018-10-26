package ems;

import java.util.Date;
import java.util.UUID;

public class Coupon {
<<<<<<< HEAD
	
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
    
  
  private String couponCode;
  private double discount;
  private Date expiryDate;
  
  public Coupon(String couponCode, double discount, Date expiryDate) {
    this.couponCode = couponCode
    this.discount = discount;
    this.expiryDate = expiryDate;
  }
  
  public double useCoupon() {
    return discount;
  }
  
>>>>>>> 7e40e3b7002d580633c9be0e52f4a14e56404fcc
}
