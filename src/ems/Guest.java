package ems;

public class Guest extends User {
	
	public Guest(String loginId, String password, int age, String hkID) {
		
		super(loginId, password, age, hkID);
		
	}
	
	public double getDiscount() {
		return 1;
	}
}
