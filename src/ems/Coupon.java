package ems;

import java.util.Date;

public class Coupon {
  
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
  
}
