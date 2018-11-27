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
	
	protected void setId(UUID id) {
		this.id = id;
	}
	
	public String getLoginId() {
		return this.loginId;
	}
	
	protected String getPassword() {
		return this.password;
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
	
	
	
	public ArrayList<CreditCard> getCreditCard() {
		return this.creditCards;
	}
	
	public void addCreditCard(CreditCard creditCard) {
		this.creditCards.add(creditCard);
		BackEnd.getInstance().serialize();
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
		BackEnd.getInstance().serialize();
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
	
	public static Member upgradeAccount(Guest guest, String name) {
		Member member = new Member(guest.getLoginId(), guest.getPassword(), name, guest.getAge(), guest.getHKID());
		member.setId(guest.getId());
		for(Ticket ticket: guest.getTickets()) {
			member.addTicket(ticket);
		}
		for(CreditCard creditCard: guest.getCreditCard()) {
			member.addCreditCard(creditCard);
		}
		for(Transaction transaction: guest.getTransactions()) {
			member.addTransaction(transaction);
		}
		
		BackEnd backEnd = BackEnd.getInstance();
		backEnd.removeUser(guest);
		backEnd.addUser(member);
		EMS.PrintHeader("Account Upgraded!");
		
		return member;
	}
	
	public abstract double getDiscount();

}
