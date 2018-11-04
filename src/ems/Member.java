package ems;

import java.util.ArrayList;
import java.util.UUID;

public class Member extends User {

	private String name;
	private double balance;
	private ArrayList<UUID> couponIds;
	
	public Member(String loginId, String password, String name, int age, String hkID) {
		
		super(loginId, password, age, hkID);
		this.name = name;
		this.couponIds = new ArrayList<UUID>();
		this.balance = 0;
		
	}

	public String getName() {
		return this.name;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void addBalance(double amount) {
		this.balance += amount;
	}
	
	public boolean substractBalance(double amount) {
		double resultBalance = this.balance - amount;
		if(resultBalance >= 0) {
			this.balance = resultBalance;
			return true;
		}
		else {
			return false;
		}
	}

	public ArrayList<Coupon> getCoupons() {
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		for(UUID couponId: this.couponIds) {
			for(Coupon coupon: backEnd.getCoupons()) {
				if(coupon.getId().equals(couponId)) {
					coupons.add(coupon);
				}
			}
		}
		return coupons;
	}
	
	public void addCoupon(Coupon coupon) {
		this.couponIds.add(coupon.getId());
	}
	
	public Coupon removeCoupon(Coupon coupon) {
		boolean result = this.couponIds.remove(coupon.getId());
		if(result == true) {
			return coupon;
		}
		return null;
	}
	
	public double getDiscount() {
		return 0.95;
	}
}
