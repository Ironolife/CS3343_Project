package ems;

import java.util.ArrayList;
import java.util.Date;

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
		System.out.println("8: My Total Sales");
		System.out.println("9: Logout");
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
			this.displayTotalSales();
			this.vendorOperations();
		}
		else if(operation.equals("9")) {
			
		}
		else {
			EMS.PrintHeader("Invalid Operation");
			this.vendorOperations();
		}
		
	}
	
	private void displayVendorEvents() {
		
		EMS.PrintHeader("- My Events -");
		int count = 1;
		ArrayList<Event> events = this.vendor.getEvents();
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
			System.out.println("----------");
			System.out.println("Sales: " + event.getSales());
			System.out.println();
		}
		
	}
	
	private void createLocation() {
		
		EMS.PrintHeader("- Create Location -");
		System.out.println("Name: ");
		String name = this.readInput();
		
		BackEnd backEnd = BackEnd.getInstance();
		
		while (backEnd.isDuplicateLocationName(name) == true) {
			EMS.PrintHeader("Name already exists! Please try another one.");
			System.out.println("Name: ");
			name = this.readInput();
		}
		
		int capacity = -1;
		while (capacity == -1) {
			try {
				System.out.println("Capacity: ");
				capacity = Integer.parseInt(this.readInput());
				while (capacity < 0) {
					EMS.PrintHeader("Invalid Capacity!");
					System.out.println("Capacity: ");
					capacity = Integer.parseInt(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Capacity!");
				capacity = -1;
			}
		}
		
		Location location = new Location(name, capacity);
		backEnd.createNewLocation(location);
		EMS.PrintHeader("Location Created!");
		
	}
	
	private void createEvent() {
		
		EMS.PrintHeader("- Create Event -");
		System.out.println("Name: ");
		String name = this.readInput();
		
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Location> locations = backEnd.getLocations();
		
		int locationIndex = -1;
		while (locationIndex == -1) {
			try {
				System.out.println("Please Select a Location: ");
				for(int i=0; i<locations.size(); i++) {
					System.out.println((i+1) + ": " + locations.get(i).getName());
				}
				locationIndex = Integer.parseInt(this.readInput());
				while (locationIndex < 1 || locationIndex > locations.size()) {
					EMS.PrintHeader("Invalid Location!");
					System.out.println("Please Select a Location: ");
					for(int i=0; i<locations.size(); i++) {
						System.out.println((i+1) + ": " + locations.get(i).getName());
					}
					locationIndex = Integer.parseInt(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Location!");
				locationIndex = -1;
			}
		}
		Location location = locations.get(locationIndex - 1);
		
		boolean isValidPeriod = false;
		Date startTime = null;
		Date endTime = null;
		while(isValidPeriod != true) {
			
			while(startTime == null) {
				System.out.println("Start Time (YYYY-MM-DD HH:MM): ");
				startTime = DateUtils.parseDate(this.readInput());
			}
			
			while(endTime == null) {
				System.out.println("End Time (YYYY-MM-DD HH:MM): ");
				endTime = DateUtils.parseDate(this.readInput());
			}
			
			isValidPeriod = DateUtils.validatePeriod(startTime, endTime);
			if(isValidPeriod == false) {
				EMS.PrintHeader("Invalid Time Period!");
				startTime = null;
				endTime = null;
			}
			else {
				for(Event event: location.getEvents()) {
					if(DateUtils.isOverlappedPeriod(startTime, endTime, event.getStartTime(), event.getEndTime()) == true) {
						isValidPeriod = false;
						startTime = null;
						endTime = null;
						EMS.PrintHeader("Location Time Slot Already Taken!");
						break;
					}
				}
			}
			
		}
		
		System.out.println("Is Event age-restricted? (Y/N): ");
		String isMatureString = this.readInput();
		boolean isMature;
		while(!isMatureString.equals("Y") && !isMatureString.equals("N")) {
			EMS.PrintHeader("Invalid Input");
			System.out.println("Is Event age-restricted? (Y/N): ");
			isMatureString = this.readInput();
		}
		if(isMatureString.equals("Y")) {
			isMature = true;
		}
		else {
			isMature = false;
		}
		
		System.out.println("Add Event tags (separate with ,): ");
		String tagsString = this.readInput();
		ArrayList<String> tags = new ArrayList<String>();
		if(!tagsString.equals("")) {
			for(String tag: tagsString.split(",")) {
				tags.add(tag);
			}
		}
		
		Event event = new Event(name, startTime, endTime, this.vendor, location, isMature);
		event.setTags(tags);
		this.vendor.addEvent(event);
		location.addEvent(event);
		backEnd.createNewEvent(event);

		EMS.PrintHeader("Event Created!");
		
	}
	
	private void generateTickets() {
		
		EMS.PrintHeader("- Generate Tickets -");
		
		ArrayList<Event> events = this.vendor.getEvents();
		
		int eventIndex = -1;
		while (eventIndex == -1) {
			try {
				System.out.println("Please Select an Event: ");
				for(int i=0; i<events.size(); i++) {
					System.out.println((i+1) + ": " + events.get(i).getName());
				}
				eventIndex = Integer.parseInt(this.readInput());
				while (eventIndex < 1 || eventIndex > events.size()) {
					EMS.PrintHeader("Invalid Event!");
					System.out.println("Please Select an Event: ");
					for(int i=0; i<events.size(); i++) {
						System.out.println((i+1) + ": " + events.get(i).getName());
					}
					eventIndex = Integer.parseInt(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Event!");
				eventIndex = -1;
			}
		}
		Event event = events.get(eventIndex - 1);
		
		double price = -1;
		while (price == -1) {
			try {
				System.out.println("Normal Price: ");
				price = Double.parseDouble(this.readInput());
				while (price < 0) {
					EMS.PrintHeader("Invalid Price!");
					System.out.println("Price: ");
					price = Double.parseDouble(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Price!");
				price = -1;
			}
		}
		
		double vipPriceMultiplier = -1;
		while (vipPriceMultiplier == -1) {
			try {
				System.out.println("VIP Price Multiplier: ");
				vipPriceMultiplier = Double.parseDouble(this.readInput());
				while (vipPriceMultiplier < 0) {
					EMS.PrintHeader("Invalid VIP Price Multiplier!");
					System.out.println("VIP Price Multiplier: ");
					vipPriceMultiplier = Double.parseDouble(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid VIP Price Multiplier!");
				vipPriceMultiplier = -1;
			}
		}
		
		boolean validSize = false;
		
		while(validSize != true) {
			
			int normalSize = -1;
			while (normalSize == -1) {
				try {
					System.out.println("Normal Tickets Count: ");
					normalSize = Integer.parseInt(this.readInput());
					while (normalSize < 0) {
						EMS.PrintHeader("Invalid Number!");
						System.out.println("Normal Tickets Count: ");
						normalSize = Integer.parseInt(this.readInput());
					}
				} catch (NumberFormatException e) {
					EMS.PrintHeader("Invalid Number!");
					normalSize = -1;
				}
			}
			
			int vipSize = -1;
			while (vipSize == -1) {
				try {
					System.out.println("VIP Tickets Count: ");
					vipSize = Integer.parseInt(this.readInput());
					while (vipSize < 0) {
						EMS.PrintHeader("Invalid Number!");
						System.out.println("VIP Tickets Count: ");
						vipSize = Integer.parseInt(this.readInput());
					}
				} catch (NumberFormatException e) {
					EMS.PrintHeader("Invalid Number!");
					vipSize = -1;
				}
			}
			
			ArrayList<Ticket> tickets = event.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
			if(tickets != null) {
				validSize = true;
				BackEnd backEnd = BackEnd.getInstance();
				backEnd.createNewTickets(tickets);
				EMS.PrintHeader(tickets.size() + " Tickets Generated!");
			}
			else {
				EMS.PrintHeader("Ticket count larger than location capacity!");
			}
			
		}
		
	}
	
	private void generateCoupon() {
		
		EMS.PrintHeader("- Generate Coupon -");
		System.out.println("Code: ");
		String code = this.readInput();
		
		BackEnd backEnd = BackEnd.getInstance();
		
		while (backEnd.isDuplicateCouponCode(code) == true) {
			EMS.PrintHeader("Code already exists! Please try another one.");
			System.out.println("Code: ");
			code = this.readInput();
		}
		
		ArrayList<Event> events = this.vendor.getEvents();
		
		int eventIndex = -1;
		while (eventIndex == -1) {
			try {
				System.out.println("Please Select an Event: ");
				for(int i=0; i<events.size(); i++) {
					System.out.println((i+1) + ": " + events.get(i).getName());
				}
				eventIndex = Integer.parseInt(this.readInput());
				while (eventIndex < 1 || eventIndex > events.size()) {
					EMS.PrintHeader("Invalid Event!");
					System.out.println("Please Select an Event: ");
					for(int i=0; i<events.size(); i++) {
						System.out.println((i+1) + ": " + events.get(i).getName());
					}
					eventIndex = Integer.parseInt(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Location!");
				eventIndex = -1;
			}
		}
		Event event = events.get(eventIndex - 1);
		
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
					System.out.println("Please select discount type: ");
					System.out.println("1: Flat");
					System.out.println("2: Percentage off");
					System.out.println("3: Free Purchase");
					discountType = Integer.parseInt(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid Number!");
				discountType = -1;
			}
		}
		discountType -= 1;
		
		double discount = -1;
		while (discount == -1) {
			try {
				System.out.println("Discount value: ");
				discount = Double.parseDouble(this.readInput());
				while (discount < 0) {
					EMS.PrintHeader("Invalid value!");
					System.out.println("Discount value: ");
					discount = Double.parseDouble(this.readInput());
				}
			} catch (NumberFormatException e) {
				EMS.PrintHeader("Invalid value!");
				discount = -1;
			}
		}
		
		Date expiryDate = null;
		while(expiryDate == null) {
			System.out.println("End Time (YYYY-MM-DD HH:MM): ");
			expiryDate = DateUtils.parseDate(this.readInput());
			if(expiryDate != null && expiryDate.compareTo(event.getEndTime()) < 0) {
				EMS.PrintHeader("Expiry date cannot be earlier than event!");
				expiryDate = null;
			}
		}
		
		Coupon coupon = new Coupon(code, event, discountType, discount, expiryDate);
		backEnd.createNewCoupon(coupon);
		
		EMS.PrintHeader("Coupon Generated!");
		
	}
	
	private void displayTotalSales() {
		
		System.out.println("Total Sales: " + this.vendor.getAccumulatedSales());
		System.out.println("Total Profit: " + this.vendor.getAccumulatedProfit());
		System.out.println();
		
	}
}
