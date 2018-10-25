package ems;

import java.util.ArrayList;

public class Member extends User {

	private String name;
	private double balance;
	private ArrayList<Coupon> coupons;
	
	public Member(String loginId, String password, String name, int age, String hkID) {
		
		super(loginId, password, age, hkID);
		this.name = name;
		this.coupons = new ArrayList<Coupon>();
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
