package ems;

import java.util.UUID;

public class Guest extends User {
	
	public Guest(String name, int age, String hkID) {
		
		super(name, age, hkID);
		
	}
	
	public double getDiscount() {
		return 1;
	}
}
