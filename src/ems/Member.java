package ems;

import java.util.ArrayList;
import java.util.UUID;

public class Member extends User {

	private ArrayList<Coupon> coupons;
	
	public Member(UUID id, String name, int age, String hkID) {
		
		super(id, name, age, hkID);
		this.coupons = new ArrayList<Coupon>();
		
	}

	public ArrayList<Coupon> getCoupon() {
		return this.coupons;
	}
	
	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}
	
	public Coupon removeCoupon(Coupon coupon) {
		boolean result = this.coupons.remove(coupon);
		if(result == true) {
			return coupon;
		}
		return null;
	}
	
	public double getDiscount() {
		return 0.95;
	}
}
