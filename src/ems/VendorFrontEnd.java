package ems;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class VendorFrontEnd extends FrontEnd{
	
	private Vendor vendor;
	
	public VendorFrontEnd(Vendor vendor) {
		
		this.vendor = vendor;
		
		EMS.PrintHeader("- Welcome " + vendor.getName() + " -");
		
		this.vendorOperations();
		
	}
	
	protected void vendorOperations() {
		
		this.baseOperations();
		System.out.println("3: My Events");
		System.out.println("4: Create Location");
		System.out.println("5: Create Event");
		System.out.println("6: Generate Tickets");
		System.out.println("7: Generate Coupon");
		System.out.println("8: Check-In");
		System.out.println("9: Check-Out");
		System.out.println("10: My Total Sales");
		System.out.println("11: Logout");
		String operation = this.readInput();
		
		if(operation.equals("1")) {
			this.displayEvents();
			this.vendorOperations();
		}
		else if(operation.equals("2")) {
			this.searchEvents();
			this.vendorOperations();
		}
		else if(operation.equals("3")) {
			this.displayVendorEvents();
			this.vendorOperations();
		}
		else if(operation.equals("4")) {
			this.createLocation();
			this.vendorOperations();
		}
		else if(operation.equals("5")) {
			this.createEvent();
			this.vendorOperations();
		}
		else if(operation.equals("6")) {
			this.generateTickets();
			this.vendorOperations();
		}
		else if(operation.equals("7")) {
			this.generateCoupon();;
			this.vendorOperations();
		}
		else if(operation.equals("8")) {
			this.checkIn();
			this.vendorOperations();
		}
		else if(operation.equals("9")) {
			this.checkOut();
			this.vendorOperations();
		}
		else if(operation.equals("10")) {
			this.displayTotalSales();
			this.vendorOperations();
		}
		else if(operation.equals("11")) {
			//return to accountManagement() in EMS
		}
		else {
			EMS.PrintHeader("Invalid Operation");
			this.vendorOperations();
		}
		
	}
	
	private void displayVendorEvents() {
		
		EMS.PrintHeader("- My Events -");
		
		//Get vendor event list
		ArrayList<Event> events = this.vendor.getEvents();
		
		//Print event list
		this.printEventList(events);
		
		//Event selection
		int eventIndex = this.listSelection(events.size(), "Select an Event to view details (0 to exit)");
		System.out.println();
		
		//Display details for selected event
		if(eventIndex > 0) {
			Event event = events.get(eventIndex - 1);
			this.displayEventInfo(event);
			System.out.println("----------");
			System.out.println("Sales: " + event.getSales());
			System.out.println();
		}
		
	}
	
	private void createLocation() {
		
		EMS.PrintHeader("- Create Location -");
		
		//Read name and check for duplication
		String name = null;
		while (name == null) {
			System.out.println("Name: ");
			name = this.readInput();
			if(backEnd.isDuplicateLocationName(name) == true) {
				EMS.PrintHeader("Name already exists! Please try another one.");
				name = null;
			}
		}
		
		//Read capacity
		int capacity = this.readIntInput("Capacity");
		
		//Create location
		Location location = new Location(name, capacity);
		backEnd.createNewLocation(location);
		
		EMS.PrintHeader("Location Created!");
		
	}
	
	private void createEvent() {
		
		EMS.PrintHeader("- Create Event -");
		
		//Read name
		System.out.println("Name: ");
		String name = this.readInput();
		
		//Get location list
		ArrayList<Location> locations = backEnd.getLocations();
		
		//Print location list
		this.printLocationList(locations);
		
		//Location selection
		int locationIndex = this.listSelection(locations.size(), "Select a Location (0 to exit)");
		
		if(locationIndex > 0) {
			
			Location location = locations.get(locationIndex - 1);
			
			//Get event period and validation
			Date startTime = null;
			Date endTime = null;
			while(startTime == null || endTime == null) {
				
				while(startTime == null) {
					System.out.println("Start Time (YYYY-MM-DD HH:MM): ");
					startTime = DateUtils.parseDate(this.readInput());
				}
				
				while(endTime == null) {
					System.out.println("End Time (YYYY-MM-DD HH:MM): ");
					endTime = DateUtils.parseDate(this.readInput());
				}
				
				//Validate startTime earlier than endTime
				if(DateUtils.validatePeriod(startTime, endTime) == false) {
					EMS.PrintHeader("Invalid Time Period!");
					startTime = null;
					endTime = null;
					continue;
				}
				
				//Validate event period overlapped with other events
				for(Event event: location.getEvents()) {
					if(DateUtils.isOverlappedPeriod(startTime, endTime, event.getStartTime(), event.getEndTime()) == true) {
						EMS.PrintHeader("Location Time Slot Already Taken!");
						startTime = null;
						endTime = null;
						break;
					}
				}
				
			}
			
			//Get event age restriction
			System.out.println("Is Event age-restricted? (Y/N): ");
			String isMatureString = null;
			while(isMatureString == null) {
				System.out.println("Is Event age-restricted? (Y/N): ");
				isMatureString = this.readInput();
				if(!isMatureString.equals("Y") && !isMatureString.equals("N")) {
					EMS.PrintHeader("Invalid Input");
					isMatureString = null;
				}
			}
			boolean isMature = isMatureString.equals("Y");
			
			//Get event tags
			System.out.println("Add Event tags (separate with ,): ");
			String tagsString = this.readInput();
			ArrayList<String> tags = new ArrayList<String>();
			if(!tagsString.equals("")) {
				for(String tag: tagsString.split(",")) {
					tags.add(tag);
				}
			}
			
			//Create event
			Event event = new Event(name, startTime, endTime, this.vendor, location, isMature);
			event.setTags(tags);
			this.vendor.addEvent(event);
			location.addEvent(event);
			backEnd.createNewEvent(event);

			EMS.PrintHeader("Event Created!");
			
		}
		
	}
	
	private void generateTickets() {
		
		EMS.PrintHeader("- Generate Tickets -");
		
		//Get vendor event list
		ArrayList<Event> events = this.vendor.getEvents();
		
		//Print event list
		this.printEventList(events);
		
		//Event selection
		int eventIndex = this.listSelection(events.size(), "Select an Event (0 to exit)");
		
		if(eventIndex > 0) {
			
			Event event = events.get(eventIndex - 1);
			
			//Read price
			double price = this.readDoubleInput("Price");
			
			//Read VIP price multiplier
			double vipPriceMultiplier = this.readDoubleInput("VIP Price Multiplier");
			
			//Ticket Generation and validation
			ArrayList<Ticket> tickets = null;
			while(tickets == null) {
				
				//Read normal ticket size
				int normalSize = this.readIntInput("Normal Tickets Count");
				
				//Get VIP ticket size
				int vipSize = this.readIntInput("VIP Tickets Count");
				
				//generate tickets and validate size
				tickets = event.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
				if(tickets == null) {
					EMS.PrintHeader("Ticket count larger than location capacity!");
				}
				
			}
			backEnd.createNewTickets(tickets);
			
			EMS.PrintHeader(tickets.size() + " Tickets Generated!");
			
		}
		
	}
	
	private void generateCoupon() {
		
		EMS.PrintHeader("- Generate Coupon -");
		
		//Read code and validate
		String code = null;
		while (code == null) {
			System.out.println("Code: ");
			code = this.readInput();
			if(backEnd.isDuplicateCouponCode(code)) {
				EMS.PrintHeader("Code already exists! Please try another one.");
				code = null;
			}
			else if(code.equals("0")) {
				EMS.PrintHeader("Code 0 is reserved! Please try another one.");
				code = null;
			}
		}
		
		//Get vendor event list
		ArrayList<Event> events = this.vendor.getEvents();
		
		//Event selection
		int eventIndex = this.listSelection(events.size(), "Select an Event (0 to exit)");
		
		if(eventIndex > 0) {
			
			Event event = events.get(eventIndex - 1);
			
			//Read discount type
			int discountType = -1;
			while (discountType == -1) {
				try {
					System.out.println("Please select discount type: ");
					System.out.println("1: Flat");
					System.out.println("2: Percentage off");
					System.out.println("3: Free Purchase");
					discountType = Integer.parseInt(this.readInput());
					while (discountType < 1 || discountType > 3) {
						EMS.PrintHeader("Invalid Number!");
						discountType = -1;
					}
				} catch (NumberFormatException e) {
					EMS.PrintHeader("Invalid Number!");
					discountType = -1;
				}
			}
			discountType -= 1;
			
			//Read discount value
			double discount = -1;
			if(discountType != 2) {
				discount = this.readDoubleInput("Discount value");
			}
			else {
				discount = 0;
			}
			
			//Read expiryDate and validate
			Date expiryDate = null;
			while(expiryDate == null) {
				System.out.println("End Time (YYYY-MM-DD HH:MM): ");
				expiryDate = DateUtils.parseDate(this.readInput());
				if(expiryDate != null && expiryDate.compareTo(event.getEndTime()) < 0) {
					EMS.PrintHeader("Expiry date cannot be earlier than event!");
					expiryDate = null;
				}
			}
			
			//Create Coupon
			Coupon coupon = new Coupon(code, event, discountType, discount, expiryDate);
			backEnd.createNewCoupon(coupon);
			
			EMS.PrintHeader("Coupon Generated!");
			
		}
		
	}
	
	private void displayTotalSales() {
		
		System.out.println("Total Sales: " + this.vendor.getAccumulatedSales());
		System.out.println("Total Profit: " + this.vendor.getAccumulatedProfit());
		System.out.println();
		
	}
	
	private void checkIn() {
		
		EMS.PrintHeader("- Check-In -");
		
		//Get vendor event list
		ArrayList<Event> ongoingEvents = this.vendor.getEvents();
		
		//Filter for ongoing events
		Iterator<Event> eventIterator = ongoingEvents.iterator();
		while(eventIterator.hasNext()) {
			Event event = eventIterator.next();
			if(event.getStartTime().compareTo(new Date()) > 0) {
				eventIterator.remove();
			}
			else if(event.getEndTime().compareTo(new Date()) < 0) {
				eventIterator.remove();
			}
		}
		
		//Print event list
		this.printEventList(ongoingEvents);
		
		//Event selection
		int eventIndex = this.listSelection(ongoingEvents.size(), "Select an Event (0 to exit)");
		
		if(eventIndex > 0) {
			
			Event event = ongoingEvents.get(eventIndex - 1);
			
			//Get purchased ticket list
			ArrayList<Ticket> tickets = new ArrayList<Ticket>();
			for(Ticket ticket: event.getTickets()) {
				if(ticket.getStatus() == 1) {
					tickets.add(ticket);
				}
			}
			
			//Print user list
			ArrayList<User> users = new ArrayList<User>();
			for(Ticket ticket: tickets) {
				users.add(ticket.getPurchaser());
			}
			this.printUserList(users);
			
			//User selection
			int ticketIndex = this.listSelection(users.size(), "Select a User (0 to exit)");
			
			if(ticketIndex > 0) {
				
				//Set ticket entry time
				tickets.get(ticketIndex - 1).setEntryTime();
				BackEnd.getInstance().serialize();
				
				EMS.PrintHeader("Checked-In participant!");
			}
			
		}
		
	}
	
	private void checkOut() {
		
		EMS.PrintHeader("- Check-Out -");
		
		//Get vendor event list
		ArrayList<Event> startedEvents = this.vendor.getEvents();
		
		//Filter for started events
		Iterator<Event> eventIterator = startedEvents.iterator();
		while(eventIterator.hasNext()) {
			Event event = eventIterator.next();
			if(event.getStartTime().compareTo(new Date()) > 0) {
				eventIterator.remove();
			}
		}
		
		//Print event list
		this.printEventList(startedEvents);
		
		//Event selection
		int eventIndex = this.listSelection(startedEvents.size(), "Select an Event (0 to exit)");
		
		if(eventIndex > 0) {
			
			Event event = startedEvents.get(eventIndex - 1);
			
			//Get entered ticket list
			ArrayList<Ticket> tickets = new ArrayList<Ticket>();
			for(Ticket ticket: event.getTickets()) {
				if(ticket.getStatus() == 2) {
					tickets.add(ticket);
				}
			}
			
			//Print user list
			ArrayList<User> users = new ArrayList<User>();
			for(Ticket ticket: tickets) {
				users.add(ticket.getPurchaser());
			}
			this.printUserList(users);
			
			//User selection
			int ticketIndex = this.listSelection(users.size(), "Select a User (0 to exit)");
			
			if(ticketIndex > 0) {
				
				//Set ticket exit time
				tickets.get(ticketIndex - 1).setExitTime();
				BackEnd.getInstance().serialize();
				
				EMS.PrintHeader("Checked-Out participant!");
			}
			
		}
		
	}
}
