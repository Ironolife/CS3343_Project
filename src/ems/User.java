package ems;

import java.util.ArrayList;
import java.util.UUID;

public abstract class User {
	
	private UUID id;
	private String loginId;
	private String password;
	private int age;
	private String hkID;
	private ArrayList<UUID> ticketIds;
	private ArrayList<CreditCard> creditCards;
	private ArrayList<UUID> transactionIds;
	
	public User(String loginId, String password, int age, String hkID) {
		this.id = UUID.randomUUID();
		this.loginId = loginId;
		this.password = password;
		this.age = age;
		this.hkID = hkID;
		this.ticketIds = new ArrayList<UUID>();
		this.creditCards = new ArrayList<CreditCard>();
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
	
	public int getAge() {
		return this.age;
	}
	
	public ArrayList<Ticket> getTickets() {
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		for(UUID ticketId: this.ticketIds) {
			for(Ticket ticket: backEnd.getTickets()) {
				if(ticket.getId().equals(ticketId)) {
					tickets.add(ticket);
				}
			}
		}
		return tickets;
	}
	
	public void addTicket(Ticket ticket) {
		this.ticketIds.add(ticket.getId());
	}
	
	public Ticket removeTicket(Ticket ticket) {
		boolean result = this.ticketIds.remove(ticket.getId());
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
				return (11 - (sum % 11) == checkDigit);
				
			}
			return false;
			
		}
		return false;
		
	}
	
	public ArrayList<String> getCreditCard() {
		ArrayList<String> cardNumbers = new ArrayList<String>();
		for(CreditCard creditCard: this.creditCards) {
			cardNumbers.add(creditCard.getCardNumber());
		}
		return cardNumbers;
	}
	
	public void addCreditCard(CreditCard creditCard) {
		this.creditCards.add(creditCard);
	}
	
	public CreditCard removeCreditCard(String cardNumber) {
		CreditCard cardToBeRemoved = null;
		for(CreditCard creditCard: this.creditCards) {
			if(creditCard.getCardNumber() == cardNumber) {
				cardToBeRemoved = creditCard;
			}
		}
		if(cardToBeRemoved != null) {
			this.creditCards.remove(cardToBeRemoved);
		}
		return cardToBeRemoved;
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
	
	public abstract double getDiscount();

}
