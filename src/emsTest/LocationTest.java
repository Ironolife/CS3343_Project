package emsTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import ems.BackEnd;
import ems.Event;
import ems.Location;
import ems.Vendor;

public class LocationTest {

//	@Test 
//	public void testAddEventToEventList() {
//		Date testingStartTime = DateUtils.parseDate("2018-12-11 19:00");
//		Date testingEndTime = DateUtils.parseDate("2018-12-11 20:00");
//		Location testingLocation = new Location("testingLocation", 100);
//		Vendor testingVendor = new Vendor("V1", "V1", "V1");
//		Event testingEvent = new Event("Testing Event", testingStartTime, testingEndTime, testingVendor, testingLocation, true);
//		testingLocation.addEvent(testingEvent);
//		Event eventResult = testingLocation.getEvents().get(0);
//		assertEquals(testingEvent.getName(), eventResult.getName());
//	}

	String eventName1;
	String eventName2;
	Date startTime;
	Date endTime;
	String vendorLoginId;
	String vendorPassword;
	String vendorName;
	ArrayList<Event> backEndEventList;
	String locationName;
	int locationCapacity;

	@Test
	public void testGetEventsWithNoEventInLocation() {

		vendorLoginId = "vendorLoginId";
		this.vendorPassword = "vendorPassword";
		this.vendorName = "vendorName";
		BackEnd backEnd = BackEnd.getInstance();
		backEndEventList = backEnd.getEvents();
		Vendor vendor = new Vendor(this.vendorLoginId, this.vendorPassword, this.vendorName);
		this.locationName = "locationName";
		this.locationCapacity = 100;
		Location tLocation = new Location(this.locationName, this.locationCapacity);
		Event event = new Event(eventName1, startTime, endTime, vendor, tLocation, true);
		ArrayList<Event> tResult = tLocation.getEvents();
		assertEquals(0, tResult.size());
	}

	@Test
	public void testGetEventsWithOneEventInLocationButNoEventInBackEnd() {

		vendorLoginId = "vendorLoginId";
		this.vendorPassword = "vendorPassword";
		this.vendorName = "vendorName";
		BackEnd backEnd = BackEnd.getInstance();
		backEndEventList = backEnd.getEvents();
		backEndEventList.clear();
		Vendor vendor = new Vendor(this.vendorLoginId, this.vendorPassword, this.vendorName);
		this.locationName = "locationName";
		this.locationCapacity = 100;
		Location tLocation = new Location(this.locationName, this.locationCapacity);
		Event event = new Event(eventName1, startTime, endTime, vendor, tLocation, true);
		tLocation.addEvent(event);
		ArrayList<Event> tResult = tLocation.getEvents();
		assertEquals(0, tResult.size());
		backEndEventList.clear();
	}
	
	@Test
	public void testGetEventsWithOneEventInLocationAndBackEnd() {

		vendorLoginId = "vendorLoginId";
		this.vendorPassword = "vendorPassword";
		this.vendorName = "vendorName";
		BackEnd backEnd = BackEnd.getInstance();
		backEndEventList = backEnd.getEvents();
		backEndEventList.clear();
		Vendor vendor = new Vendor(this.vendorLoginId, this.vendorPassword, this.vendorName);
		this.locationName = "locationName";
		this.locationCapacity = 100;
		Location tLocation = new Location(this.locationName, this.locationCapacity);
		Event event = new Event(eventName1, startTime, endTime, vendor, tLocation, true);
		tLocation.addEvent(event);
		backEndEventList.add(event);
		ArrayList<Event> tResult = tLocation.getEvents();
		assertEquals(event, tResult.get(0));
		backEndEventList.clear();
	}
	
	@Test
	public void testGetEventsWithTwoEventsInLocationAndBackEnd() {

		vendorLoginId = "vendorLoginId";
		this.vendorPassword = "vendorPassword";
		this.vendorName = "vendorName";
		BackEnd backEnd = BackEnd.getInstance();
		backEndEventList = backEnd.getEvents();
		backEndEventList.clear();
		Vendor vendor = new Vendor(this.vendorLoginId, this.vendorPassword, this.vendorName);
		this.locationName = "locationName";
		this.locationCapacity = 100;
		Location tLocation = new Location(this.locationName, this.locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, vendor, tLocation, true);
		Event event2 = new Event(eventName2, startTime, endTime, vendor, tLocation, true);
		tLocation.addEvent(event1);
		backEndEventList.add(event1);
		tLocation.addEvent(event2);
		backEndEventList.add(event2);
		ArrayList<Event> tResult = tLocation.getEvents();
		assertEquals(event1, tResult.get(0));
		assertEquals(event2, tResult.get(1));
		backEndEventList.clear();
	}
	
	
	@Test
	public void testRemoveEventWithNoEventInLocation() {

		vendorLoginId = "vendorLoginId";
		this.vendorPassword = "vendorPassword";
		this.vendorName = "vendorName";
		BackEnd backEnd = BackEnd.getInstance();
		backEndEventList = backEnd.getEvents();
		backEndEventList.clear();
		Vendor vendor = new Vendor(this.vendorLoginId, this.vendorPassword, this.vendorName);
		this.locationName = "locationName";
		this.locationCapacity = 100;
		Location tLocation = new Location(this.locationName, this.locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, vendor, tLocation, true);
		Event tResult = tLocation.removeEvent(event1);
		assertEquals(null, tResult);
		backEndEventList.clear();
	}
	
	@Test
	public void testRemoveEventWithTheRequiredEventInTheLocation() {

		vendorLoginId = "vendorLoginId";
		this.vendorPassword = "vendorPassword";
		this.vendorName = "vendorName";
		BackEnd backEnd = BackEnd.getInstance();
		backEndEventList = backEnd.getEvents();
		backEndEventList.clear();
		Vendor vendor = new Vendor(this.vendorLoginId, this.vendorPassword, this.vendorName);
		this.locationName = "locationName";
		this.locationCapacity = 100;
		Location tLocation = new Location(this.locationName, this.locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, vendor, tLocation, true);
		tLocation.addEvent(event1);
		Event tResult = tLocation.removeEvent(event1);
		assertEquals(event1, tResult);
		backEndEventList.clear();
	}

}
