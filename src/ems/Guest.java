package ems;

import java.util.UUID;

public class Guest extends User {
	
	public Guest(UUID id, String name, int age, String hkID) {
		
		super(id, name, age, hkID);
		
	}
	
	public double getDiscount() {
		return 1;
	}
}
