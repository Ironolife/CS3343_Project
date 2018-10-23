package ems;

public class Coupon {
  public Coupon(double discount, Date expiryDate) {
    this.discount = discount;
    this.expiryDate = expiryDate;
  }
  
  public double useCoupon() {
    return discount;
  }
}
