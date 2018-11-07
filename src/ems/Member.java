package ems;

import java.util.UUID;

public class Member extends User {

	private String name;
	private double balance;
	
	public Member(String loginId, String password, String name, int age, String hkID) {
		
		super(loginId, password, age, hkID);
		this.name = name;
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
		BackEnd.getInstance().serialize();
	}
	
	public boolean substractBalance(double amount) {
		double resultBalance = this.balance - amount;
		if(resultBalance >= 0) {
			this.balance = resultBalance;
			BackEnd.getInstance().serialize();
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getDiscount() {
		return 0.95;
	}
}
