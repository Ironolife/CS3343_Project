package emsTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ems.BackEnd;
import ems.Event;
import ems.Location;
import ems.LogsRecorder;
import ems.Member;
import ems.Review;
import ems.Ticket;
import ems.User;
import ems.Vendor;

public class BackEndTest {
	
	BackEnd backEnd = BackEnd.getInstance();
	
	User user1;
	String userLoginId1;
	String userPassword1;
	int userAge1;
	String userHkid1;
	ArrayList<User> userList;

	String vendorLoginId1;
	String vendorName1;
	String vendorPassword1;
	Vendor vendor1;
	ArrayList<Vendor> vendorList;
	
	String locationName1;
	int locationCapacity1;
	Location location1;
	ArrayList<Location> locationList;
	
	String eventName1;
	Date eventStartTime1;
	Date eventEndTime1;
	Vendor eventVendor1;
	Location eventLocation1;
	boolean eventIsMature1;
	Event event1;
	ArrayList<Event> eventList;

	
	/*
		ArrayList<Event> backEndEventList;
	ArrayList<Event> tempBackEndEventList;
	
	
	ArrayList<Location> tempBackEndLocationList;
	
	ArrayList<Ticket> backEndTicketList;
	
	ArrayList<Review>  backEndReviewList;
	Date startTime;
	Date endTime;
	String tEventName1;
	String eventName2;
	Event tEvent;

	String vendorLoginId2;
	String vendorName2;
	String vendorPassword2;
	Vendor vendor2;
	ArrayList<Ticket> generatedTicketListForEvent;
	String userLoginId;
	String userName;
	String userPassword;
	int userAge;
	String userHKId;
	String reviewerLoginId;
	String reviewerName;
	String reviewerPassword;
	int reviewerAge;
	String reviewerHKId;
	String comment1;
	String comment2;
	ArrayList<Review> reviewListForEvent;(*/
	
	

	@Before
	public void setUp() throws Exception {
		vendorLoginId1 = "vendor1";
		vendorName1 = "Vendor 1";
		vendorPassword1 = "123456";
		
		locationName1 = "Location 1";
		locationCapacity1 = 200;
		
		eventName1 = "Event 1";
		eventStartTime1 = new Date();
		eventEndTime1 =  new Date();
		eventVendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		eventLocation1 = new Location(locationName1, locationCapacity1);
		eventIsMature1 = true;
		
		
	}

	@Test
	public void testCreateNewVendor() {
		vendorList = backEnd.getVendors();
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		boolean result = vendorList.add(vendor1);
		assertEquals(true, result);
		vendorList.clear();
	}

	@Test
	public void testGetVendors() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveVendor() {
		vendorList = backEnd.getVendors();
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		boolean result = vendorList.remove(vendor1);
		assertEquals(vendor1, result);
		vendorList.clear();
	}

	@Test
	public void testAddUser() {

	}

	@Test
	public void testGetUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNewLocation() {
		locationList = backEnd.getLocations();
		location1 = new Location(locationName1, locationCapacity1);
		boolean result = locationList.add(location1);
		assertEquals(true, result);
		locationList.clear();
	}

	@Test
	public void testGetLocations() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDuplicateLocationName() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNewEvent() {
		eventList = backEnd.getEvents();
		event1 = new Event(eventName1, eventStartTime1, eventEndTime1, eventVendor1, eventLocation1, eventIsMature1);
		boolean result = eventList.add(event1);
		assertEquals(true, result);
		eventList.clear();
	}

	@Test
	public void testGetEvents() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNewTickets() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTickets() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveTicket() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNewCoupon() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCoupons() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCoupon() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDuplicateCouponCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNewReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReviews() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNewTransaction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTransactions() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveTransaction() {
		fail("Not yet implemented");
	}

}
