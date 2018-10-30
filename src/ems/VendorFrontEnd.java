package ems;

import java.util.Date;
import java.util.Scanner;

public class VendorFrontEnd extends FrontEnd{
	
	private Vendor vendor;
	
	
	public VendorFrontEnd(Vendor vendor) {
		
		this.vendor = vendor;
		
	}
	
	
	// considering whether we shall use EMS.readInput() by putting EMS as parameter
	public void createEvent() {
		System.out.println("Please input the name of the event:");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		name = name.trim();
		boolean isStartTimeAndEndTimePairValid = false;
		Date startTime = new Date();
		Date endTime = new Date();
		Location eventLocation = new Location();
		
		while (!isStartTimeAndEndTimePairValid) {
			boolean startDateTimeAndEndDateTimePairCorrect = false;
			while (!startDateTimeAndEndDateTimePairCorrect) {
				/*
				 * Start Time
				 */
				System.out.println("Please input the start time of the event (in yyyyMMdd HH:mm format):");
				String startTimeDateString = input.nextLine();
				startTimeDateString = startTimeDateString.trim();
				while (!DateUtils.isDateStringValid(startTimeDateString)) {
					System.out.println("Invalid date!!!");
					System.out.println("Please input the start time of the event (in yyyyMMdd HH:mm format):");
					startTimeDateString = input.nextLine();
					startTimeDateString = startTimeDateString.trim();
				}
				startTime = DateUtils.convertDateStringToDateObject(startTimeDateString);
				/*
				 * End Time
				 */
				System.out.println("Please input the end time of the event (in yyyyMMdd HH:mm format):");
				String endTimeDateString = input.nextLine();
				endTimeDateString = endTimeDateString.trim();
				while (!DateUtils.isDateStringValid(endTimeDateString)) {
					System.out.println("Invalid date!!!");
					System.out.println("Please input the start time of the event (in yyyyMMdd HH:mm format):");
					endTimeDateString = input.nextLine();
					endTimeDateString = endTimeDateString.trim();
				}
				endTime = DateUtils.convertDateStringToDateObject(endTimeDateString);

				if (DateUtils.isStartTimeAndEndTimePairValid(startTime, endTime)) {
					startDateTimeAndEndDateTimePairCorrect = true;
				}

			}
			BackEnd backEndInstance = BackEnd.getInstance();
			System.out.println("Location:");
			int indexOfLocationList = 1;
			for (Location location : backEndInstance.getLocations()) {
				System.out.println(indexOfLocationList + ". " + location.getName());
			}
			System.out.println("Please choose a location:");
			String eventLocationName = input.nextLine();
			eventLocationName = eventLocationName.trim();
			eventLocation = backEndInstance.getRequiredLocation(eventLocationName);
			while (eventLocation.getName() == null) {
				System.out.println("Invalid location name");
				indexOfLocationList = 1;
				for (Location location : backEndInstance.getLocations()) {
					System.out.println(indexOfLocationList + ". " + location.getName());
				}
				eventLocationName = input.nextLine();
				eventLocationName = eventLocationName.trim();
				eventLocation = backEndInstance.getRequiredLocation(eventLocationName);
			}
			if(!backEndInstance.isEventBeingCreatedHasConflictWithOtherEvents(eventLocation, startTime, endTime)) {
				isStartTimeAndEndTimePairValid = true;
			}
		}
		
		Event newEvent = new Event(name,startTime, endTime, eventLocation, true);
		eventLocation.addEventToTheEventList(newEvent);
		vendor.addEvent(newEvent);
		
		input.close();
		
		
	}
	
	public void vendorFrontEndInitialization() {
		System.out.printf("Welcome! %s", vendor.getName());
	}
	
	public void vendorFrontEndOperation() {
		System.out.println("Choose Operation:");
		//TODO : Display Vendor allowed operation and the handling of operation
	}

}
