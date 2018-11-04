package ems;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class VendorFrontEnd extends FrontEnd{
	
	private Vendor vendor;
	
	public VendorFrontEnd(Vendor vendor) {
		
		this.vendor = vendor;
		
		EMS.PrintHeader("- Welcome " + vendor.getName() + " -");
		
		this.vendorOperations();
		
	}
	
	private void vendorOperations() {
		
		super.baseOperations();
		System.out.println("3: Create Location");
		System.out.println("4: Create Event");
		String operation = this.readInput();
		
		if(operation.equals("1")) {
			super.displayEvents();
		}
		else if(operation.equals("2")) {
			super.searchEvents();
		}
		else if(operation.equals("3")) {
			this.createLocation();
		}
		else if(operation.equals("4")) {
			this.createEvent();
		}
		
	}
	
	private void createLocation() {
		
		EMS.PrintHeader("- Create Location -");
		System.out.println("Name: ");
		String name = this.readInput();
		
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<String> names = new ArrayList<String>();
		for(Location location: backEnd.getLocations()) {
			names.add(location.getName());
		}
		
		boolean isDuplicateName = names.contains(name);
		while (isDuplicateName == true) {
			EMS.PrintHeader("Location already exists! Please try another one.");
			System.out.println("Name: ");
			name = this.readInput();
			isDuplicateName = names.contains(name);
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
		this.vendorOperations();
		
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
			
			System.out.println("Start Time (YYYY-MM-DD HH:MM): ");
			startTime = DateUtils.parseDate(this.readInput());
			while(startTime == null) {
				System.out.println("Start Time (YYYY-MM-DD HH:MM): ");
				startTime = DateUtils.parseDate(this.readInput());
			}
			
			System.out.println("End Time (YYYY-MM-DD HH:MM): ");
			endTime = DateUtils.parseDate(this.readInput());
			while(endTime == null) {
				System.out.println("End Time (YYYY-MM-DD HH:MM): ");
				endTime = DateUtils.parseDate(this.readInput());
			}
			
			isValidPeriod = DateUtils.validatePeriod(startTime, endTime);
			if(isValidPeriod == false) {
				EMS.PrintHeader("Invalid Time Period!");
			}
			else {
				for(Event event: location.getEvents()) {
					if(DateUtils.isOverlappedPeriod(startTime, endTime, event.getStartTime(), event.getEndTime()) == true) {
						isValidPeriod = false;
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
		
		Event event = new Event(name, startTime, endTime, location, isMature);
		this.vendor.addEvent(event);
		location.addEvent(event);
		backEnd.createNewEvent(event);
		
		this.vendorOperations();
		
	}
	
}
