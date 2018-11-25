package ems;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class FrontEnd {
	
	protected BackEnd backEnd = BackEnd.getInstance();
	
	public FrontEnd() {
		
	}
	
	protected void baseOperations() {

		System.out.println("Choose operations: ");
		System.out.println("1: Display Events");
		System.out.println("2: Search Events");
		
	}
	
	public void displayEvents() {
		
		EMS.PrintHeader("- All Events -");
		
		//Get event List
		ArrayList<Event> events = backEnd.getEvents();
		
		//Print event list
		this.printEventList(events);
		
		//Event selection
		int eventIndex = this.listSelection(events.size(), "Select an Event to view details (0 to exit)");
		System.out.println();
		
		//Display details for selected event
		if(eventIndex > 0) {
			Event event = events.get(eventIndex - 1);
			this.displayEventInfo(event);
		}
		
	}
	
	public void searchEvents() {
		
		EMS.PrintHeader("- Search Events -");
		
		//Read search input
		System.out.println("Search: ");
		String searchPhrase = this.readInput();
		
		//Filter list by input (name, location, vendor or tag)
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
		
		//Print list
		this.printEventList(searchResult);
		
		//Event selection
		int eventIndex = this.listSelection(searchResult.size(), "Select an Event to view details (0 to exit)");
		System.out.println();
		
		//Display details for selected event
		if(eventIndex > 0) {
			Event event = searchResult.get(eventIndex - 1);
			this.displayEventInfo(event);
		}
		
	}
	
	public void displayEventInfo(Event event) {
		
		//Basic event information
		System.out.println("Event Name: " + event.getName());
		System.out.println("Start Time: " + event.getStartTime().toLocaleString());
		System.out.println("End Time: " + event.getEndTime().toLocaleString());
		System.out.println("Location: " + event.getLocation().getName());
		if(event.getIsMature() == true) {
			System.out.println("* Event is only available for age over 18.");
		}
		System.out.println("----------");
		
		//Ticket information
		this.displayEventTickets(event);
		
		System.out.println("----------");
		
		//Reviews 
		if(event.getReviews().size() > 0) {
			System.out.println("Average Rating: " + event.getAverageRating());
			System.out.println();
		}
		System.out.println("Reviews: ");
		if(event.getReviews().size() == 0) {
			System.out.println("No Reviews.");
			System.out.println();
		}
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
	
	protected int listSelection(int size, String text) {
		
		int index = -1;
		while(index == -1) {
			try {
				System.out.println(text + ": ");
				index = Integer.parseInt(this.readInput());
				if(index < 0 || index > size) {
					EMS.PrintHeader("Invalid Input!");
					index = -1;
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Input!");
				index = -1;
			}
		}
		return index;
		
	}
	
	protected void printEventList(ArrayList<Event> events) {
		
		int count = 1;
		for(Event event: events) {
			System.out.println(count + ": " + event.getName());
			count++;
		}
		
	}
	
	protected void printLocationList(ArrayList<Location> locations) {
		
		int count = 1;
		for(Location location: locations) {
			System.out.println(count + ": " + location.getName());
			count++;
		}
		
	}
	
	protected void printUserList(ArrayList<User> users) {
		
		int count = 1;
		for(User user: users) {
			System.out.println(count + ": " + user.getHKID());
			count++;
		}
		
	}
	
	protected int readIntInput(String inputField) {
		
		int input = -1;
		while (input == -1) {
			try {
				System.out.println(inputField + ": ");
				input = Integer.parseInt(this.readInput());
				if (input < 0) {
					EMS.PrintHeader("Invalid Input!");
					input = -1;
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Input!");
				input = -1;
			}
		}
		return input;
		
	}
	
	protected double readDoubleInput(String inputField) {
		
		double input = -1;
		while (input == -1) {
			try {
				System.out.println(inputField + ": ");
				input = Double.parseDouble(this.readInput());
				if (input < 0) {
					EMS.PrintHeader("Invalid Input!");
					input = -1;
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Input!");
				input = -1;
			}
		}
		return input;
		
	}

}
