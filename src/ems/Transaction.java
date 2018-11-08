package ems;

import java.util.Date;
import java.util.UUID;

public class Transaction {
	
	private UUID id;
	private Date date;
	private UUID ticketId;
	private UUID purchaserId;
	private UUID vendorId;
	private double initialAmount;
	private double discountedAmount;
	
	public Transaction(Ticket ticket, User purchaser, Vendor vendor) {
		
		this.id = UUID.randomUUID();
		this.date = new Date();
		
		this.ticketId = ticket.getId();
		this.purchaserId = purchaser.getId();
		this.vendorId = vendor.getId();
		
		this.initialAmount = ticket.getPrice();
		this.discountedAmount = this.initialAmount * purchaser.getDiscount();
		
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getDate() {
		return this.date.toLocaleString();
	}
	
	public Ticket getTicket() {
		BackEnd backEnd = BackEnd.getInstance();
		for(Ticket ticket: backEnd.getTickets()) {
			if(ticket.getId().equals(this.ticketId)) {
				return ticket;
			}
		}
		return null;
	}
	
	public User getPurchaser() {
		BackEnd backEnd = BackEnd.getInstance();
		for(User user: backEnd.getUsers()) {
			if(user.getId().equals(this.purchaserId)) {
				return user;
			}
		}
		return null;
	}
	
	public Vendor getVendor() {
		BackEnd backEnd = BackEnd.getInstance();
		for(Vendor vendor: backEnd.getVendors()) {
			if(vendor.getId().equals(this.vendorId)) {
				return vendor;
			}
		}
		return null;
	}
	
	public double getInitialAmount() {
		return this.initialAmount;
	}
	
	public double getDiscountedAmount() {
		return this.discountedAmount;
	}
	
	public void useCoupon(Coupon coupon) {
		this.discountedAmount = coupon.getDiscountedAmount(discountedAmount);
	}
	
	public double completeTransaction() {
		
		this.getTicket().Purchase(this.getPurchaser());
		this.getPurchaser().addTransaction(this);
		this.getPurchaser().addTicket(this.getTicket());
		this.getVendor().addTransaction(this);
		
		return this.discountedAmount;
		
	}
	
}
