package ems;

import java.util.ArrayList;
import java.util.UUID;

public class Vendor {
	
	private UUID id;
	private String loginId;
	private String password;
	private String name;
	private ArrayList<UUID> eventIds;
	private ArrayList<UUID> transactionIds;
	
	public Vendor(String loginId, String password, String name){
		this.id = UUID.randomUUID();
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.eventIds = new ArrayList<UUID>();
		this.transactionIds = new ArrayList<UUID>();
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
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Event> getEvents() {
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> events = new ArrayList<Event>();
		for(UUID eventId: this.eventIds) {
			for(Event event: backEnd.getEvents()) {
				if(event.getId().equals(eventId)) {
					events.add(event);
				}
			}
		}
		return events;
	}
	
	public void addEvent(Event event) {
		this.eventIds.add(event.getId());
	}
	
	public Event removeEvent(Event event) {
		boolean result = this.eventIds.remove(event.getId());
		if(result == true) {
			return event;
		}
		return null;
	}
	
	public ArrayList<Transaction> getTransactions() {
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		for(UUID transactionId: this.transactionIds) {
			for(Transaction transaction: backEnd.getTransactions()) {
				if(transaction.getId().equals(transactionId)) {
					transactions.add(transaction);
				}
			}
		}
		return transactions;
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactionIds.add(transaction.getId());
	}
	
	public Transaction removeTransaction(Transaction transaction) {
		boolean result = this.transactionIds.remove(transaction.getId());
		if(result == true) {
			return transaction;
		}
		return null;
	}
	
	public double getAccumulatedSales()  {
		double total = 0;
		
		for(Transaction transaction: this.getTransactions()) {
			total += transaction.getInitialAmount();
		}
		return total;
	}
	
	public double getAccumulatedProfit()  {
		double total = 0;
		
		for(Transaction transaction: this.getTransactions()) {
			total += transaction.getDiscountedAmount();
		}
		return total;
	}
}
