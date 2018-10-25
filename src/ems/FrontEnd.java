package ems;

import java.util.ArrayList;

public abstract class FrontEnd {
	
	public void displayEvents() {
		
		BackEnd backEnd = BackEnd.getInstance();
		
		int count = 0;
		for(Vendor vendor: backEnd.getVendors()) {
			for(Event event: vendor.getEvents()) {
				System.out.println(count + ": " + event.getName());
				count ++;
			}
		}
		
	}
	
	public void displayEvents(String input) {
		
		//TODO search events with user input
		
	}
	
	public void displayEventInfo(Event event) {
		
		System.out.println("Event Name: " + event.getName());
		System.out.println("Start Time: " + event.getStartTime().toLocaleString());
		System.out.println("End Time: " + event.getEndTime().toLocaleString());
		System.out.println("Location: " + event.getLocation());
		if(event.getIsMature() == true) {
			System.out.println("* Event is only available for age over 18.");
		}
		System.out.println();
		System.out.println("----------");
		
		System.out.println("Average Rating: " + event.getAverageRating());
		System.out.println();
		System.out.println("Reviews: ");
		for(Review r: event.getReviews()) {
			System.out.println(r.getMember().getName() + " - " + r.getRating());
			System.out.println(r.getComment() + " - " + r.getDate());
			System.out.println();
		}
		
	}
	
	public void displayEventTickets(Event event) {
		
		ArrayList<Ticket> tickets = event.getTickets();
		
		System.out.println("Total Tickets: " + tickets.size());
		
		int availableNormalTicket = 0;
		int purchasedNormalTicket = 0;
		int availableVIPTicket = 0;
		int purchasedVIPTicket = 0;
		
		double price = -1;
		double vipPrice = -1;
		
		for(Ticket ticket: tickets) {
			if(ticket instanceof VIPTicket) {
				if(ticket.getStatus() == 0) {
					availableVIPTicket++;
				}
				else {
					purchasedVIPTicket++;
				}
				vipPrice = ticket.getPrice();
			}
			else {
				if(ticket.getStatus() == 0) {
					availableNormalTicket++;
				}
				else {
					purchasedNormalTicket++;
				}
				price = ticket.getPrice();
			}
		}
		
		if(vipPrice != -1) {
			System.out.println("VIP Tickets: " + availableVIPTicket + " Available " + purchasedVIPTicket + " Purchased, " + "Price: " + vipPrice);
		}
		if(price != -1) {
			System.out.println("Normal Tickets: " + availableNormalTicket + " Available " + purchasedNormalTicket + " Purchased, " + "Price: " + price);
		}
		
	}

}
