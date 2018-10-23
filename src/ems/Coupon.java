package ems;

public class Coupon {
  
  private String couponCode;
  private double discount;
  private Date expiryDate;
  
  public Coupon(String couponCode, double discount, Date expiryDate) {
    this.discount = discount;
    this.expiryDate = expiryDate;
  }
  
  public double useCoupon() {
    return discount;
  }
}
