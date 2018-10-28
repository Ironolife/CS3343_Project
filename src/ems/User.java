package ems;

import java.util.ArrayList;
import java.util.UUID;

public abstract class User {
	
	private UUID id;
	private String loginId;
	private String password;
	private int age;
	private String hkID;
	private ArrayList<Ticket> tickets;
	
	public User(String loginId, String password, int age, String hkID) {
		this.id = UUID.randomUUID();
		this.loginId = loginId;
		this.password = password;
		this.age = age;
		this.tickets = new ArrayList<Ticket>();
		this.hkID = hkID;
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getLoginId() {
		return this.loginId;
	}
	
	public boolean validatePassword(String inputPassword) {
		return this.password.equals(inputPassword);
	}
	
	public void changePassword(String newPassword) {
		this.password = newPassword;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public ArrayList<Ticket> getTickets() {
		return this.tickets;
	}
	
	public void addTicket(Ticket ticket) {
		this.tickets.add(ticket);
	}
	
	public Ticket removeTicket(Ticket ticket) {
		boolean result = this.tickets.remove(ticket);
		if(result == true) {
			return ticket;
		}
		return null;
	}
	
	public String getHKID() {
		return this.hkID;
	}
	
	public static boolean validateHKID(String hkID) {
		
		if(hkID.length() == 10) {
			
			if(Character.isLetter(hkID.charAt(0))) {
				
				int sum = 0;
				int checkDigit = (int)hkID.charAt(hkID.length()-2);
				
				if(Character.isLetter(hkID.charAt(hkID.length()-2))) {
					if(hkID.charAt(hkID.length()-2) == 'A') {
						checkDigit = 0;
					}
					else {
						return false;
					}
				}
				else {
					if(Character.isDigit(hkID.charAt(hkID.length()-2))) {
						checkDigit = (int)hkID.charAt(hkID.length()-2) - 48;
					}
				}
				
				for(int i = 0; i <= 6; i++) {
					if(i > 0 && !Character.isDigit(hkID.charAt(i))) {
						return false;
					}
					if(i == 0) {
						sum += ((int)hkID.charAt(i) - 64) * (8 - i);
					}
					else {
						sum += ((int)hkID.charAt(i) - 48) * (8 - i);
					}
				}
				
				return (11 - (sum % 11) == checkDigit);
				
			}
			return false;
			
		}
		else if(hkID.length() == 11) {
			
			if(Character.isLetter(hkID.charAt(0)) && Character.isLetter(hkID.charAt(1))) {
				
				int sum = 0;
				int checkDigit = (int)hkID.charAt(hkID.length()-2);
				
				if(Character.isLetter(hkID.charAt(hkID.length()-2))) {
					if(hkID.charAt(hkID.length()-2) == 'A') {
						checkDigit = 0;
					}
					else {
						return false;
					}
				}
				else {
					if(Character.isDigit(hkID.charAt(hkID.length()-2))) {
						checkDigit = (int)hkID.charAt(hkID.length()-2) - 48;
					}
				}
				
				for(int i = 0; i <= 7; i++) {
					if(i > 1 && !Character.isDigit(hkID.charAt(i))) {
						return false;
					}
					if(i < 2) {
						sum += ((int)hkID.charAt(i) - 64) * (9 - i);
					}
					else {
						sum += ((int)hkID.charAt(i) - 48) * (9 - i);
					}
				}
				System.out.println(sum);
				return (11 - (sum % 11) == checkDigit);
				
			}
			return false;
			
		}
		return false;
		
	}
	
	public abstract double getDiscount();

}
