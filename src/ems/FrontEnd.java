package ems;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class FrontEnd {
	
	public FrontEnd() {
		
	}
	
	protected void baseOperations() {

		System.out.println("Choose operations: ");
		System.out.println("1: Display Events");
		System.out.println("2: Search Events");
		
	}
	
	public void displayEvents() {
		
		EMS.PrintHeader("- All Events -");
		
		BackEnd backEnd = BackEnd.getInstance();
		
		int count = 1;
		ArrayList<Event> events = backEnd.getEvents();
		for(Event event: events) {
			System.out.println(count + ": " + event.getName());
			count++;
		}
		
		int eventIndex = -1;
		while(eventIndex == -1) {
			try {
				System.out.println("Select an event to view details (0 to exit): ");
				eventIndex = Integer.parseInt(this.readInput());
				while(eventIndex < 0 || eventIndex > events.size()) {
					EMS.PrintHeader("Invalid Input");
					System.out.println("Select an event to view details (0 to exit): ");
					eventIndex = Integer.parseInt(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Input!");
				eventIndex = -1;
			}
		}
		System.out.println();
		if(eventIndex > 0) {
			Event event = events.get(eventIndex - 1);
			this.displayEventInfo(event);
		}
		
	}
	
	public void searchEvents() {
		
		EMS.PrintHeader("- Search Events -");
		System.out.println("Search: ");
		String searchPhrase = this.readInput();
		
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> searchResult = new ArrayList<Event>();
		for(Event event: backEnd.getEvents()) {
			if(event.getName().equals(searchPhrase)) {
				searchResult.add(event);
			}
			else if(event.getLocation().getName().equals(searchPhrase)) {
				searchResult.add(event);
			}
			else if(event.getVendor().getName().equals(searchPhrase)) {
				searchResult.add(event);
			}
			else if(event.getTags().contains(searchPhrase)) {
				searchResult.add(event);
			}
		}
		
		int count = 1;
		for(Event event: searchResult) {
			System.out.println(count + ": " + event.getName());
			count++;
		}
		
		int eventIndex = -1;
		while(eventIndex == -1) {
			try {
				System.out.println("Select an event to view details (0 to exit): ");
				eventIndex = Integer.parseInt(this.readInput());
				while(eventIndex < 0 || eventIndex > searchResult.size()) {
					EMS.PrintHeader("Invalid Input");
					System.out.println("Select an event to view details (0 to exit): ");
					eventIndex = Integer.parseInt(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Input!");
				eventIndex = -1;
			}
		}
		System.out.println();
		if(eventIndex > 0) {
			Event event = searchResult.get(eventIndex - 1);
			this.displayEventInfo(event);
		}
		
	}
	
	public void displayEventInfo(Event event) {
		
		System.out.println("Event Name: " + event.getName());
		System.out.println("Start Time: " + event.getStartTime().toLocaleString());
		System.out.println("End Time: " + event.getEndTime().toLocaleString());
		System.out.println("Location: " + event.getLocation().getName());
		if(event.getIsMature() == true) {
			System.out.println("* Event is only available for age over 18.");
		}
		System.out.println();
		System.out.println("----------");
		
		this.displayEventTickets(event);
		
		System.out.println();
		System.out.println("----------");
		
		if(event.getReviews().size() > 0) {
			System.out.println("Average Rating: " + event.getAverageRating());
			System.out.println();
		}
		System.out.println("Reviews: ");
		if(event.getReviews().size() == 0) {
			System.out.println("No Reviews.");
		}
		for(Review r: event.getReviews()) {
			System.out.println(r.getMember().getName() + " - " + r.getRating());
			System.out.println(r.getComment() + " - " + r.getDate());
			System.out.println();
		}
		System.out.println();
		
	}
	
	public void displayEventTickets(Event event) {
		
		ArrayList<Ticket> tickets = event.getTickets();
		
		System.out.println("Total Tickets: " + tickets.size());
		System.out.println();
		
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
			System.out.println("VIP Tickets: " + availableVIPTicket + " Available, " + purchasedVIPTicket + " Purchased, " + "Price: " + vipPrice);
		}
		if(price != -1) {
			System.out.println("Normal Tickets: " + availableNormalTicket + " Available, " + purchasedNormalTicket + " Purchased, " + "Price: " + price);
		}
		
	}
	
	public String readInput() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}

}
