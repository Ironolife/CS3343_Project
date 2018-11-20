package emsTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ems.BackEnd;
import ems.Coupon;
import ems.Event;
import ems.Location;
import ems.LogsRecorder;
import ems.Member;
import ems.Review;
import ems.Ticket;
import ems.Transaction;
import ems.User;
import ems.Vendor;

public class BackEndTest {
	
	BackEnd backEnd = BackEnd.getInstance();
	
	ArrayList<User> userList;
	User user1;
	String userLoginId1;
	String userPassword1;
	String userName1;
	int userAge1;
	String userHkid1;
	
	ArrayList<Vendor> vendorList;
	Vendor vendor1;
	String vendorLoginId1;
	String vendorName1;
	String vendorPassword1;
	
	ArrayList<Location> locationList;
	Location location1;
	String locationName1;
	int locationCapacity1;
	
	ArrayList<Event> eventList;
	Event event1;
	String eventName1;
	Date eventStartTime1;
	Date eventEndTime1;
	Vendor eventVendor1;
	Location eventLocation1;
	boolean eventIsMature1;
	
	ArrayList<Ticket> ticketList;
	Ticket ticket1;
	Event ticketEvent1; 
	double ticketPrice1;
	int ticketSeat1;
	
	ArrayList<Coupon> couponList;
	Coupon coupon1;
	String couponCode1;
	Event couponEvent1; 
	int couponDiscountType1;
	double couponDiscount1;
	Date couponExpiryDate1;
	
	ArrayList<Review> reviewList;
	Review review1;
	Member reviewMember1;
	double reviewRating1;
	String reviewComment1;
	
	ArrayList<Transaction> transactionList;
	Transaction transaction1;
	Ticket transactionTicket1;
	User transactionPurchaser1;
	Vendor transactionVendor1;
	
	

	@Before
	public void setUp() throws Exception {
		
		userLoginId1 = "member1";
		userPassword1 = "123456";
		userName1 = "Member 1";
		userAge1 = 20;
		userHkid1 = "V102142(7)";
		
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
		
		ticketEvent1 = new Event(eventName1, eventStartTime1, eventEndTime1, eventVendor1, eventLocation1, eventIsMature1); 
		ticketPrice1 = 400;
		ticketSeat1 = 2;
		
		couponCode1 = "C001";
		couponEvent1 = new Event(eventName1, eventStartTime1, eventEndTime1, eventVendor1, eventLocation1, eventIsMature1); 
		couponDiscountType1 = 0;
		couponDiscount1 = 20;
		couponExpiryDate1 = new Date();
		
		reviewMember1 = new Member(userLoginId1, userPassword1, userName1, userAge1, userHkid1);
		reviewRating1 = 2 ;
		reviewComment1 = "Good";
		
		transactionTicket1 = new Ticket(ticketEvent1, ticketPrice1, ticketSeat1);
		transactionPurchaser1 = new Member(userLoginId1, userPassword1, userName1, userAge1, userHkid1);
		transactionVendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);	
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
		userList = backEnd.getUsers();
		user1 = new Member(userLoginId1, userPassword1, userName1, userAge1, userHkid1);
		boolean result = vendorList.add(vendor1);
		assertEquals(true, result);
		vendorList.clear();
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
		ticketList = backEnd.getTickets();
		ticket1 = new Ticket(ticketEvent1, ticketPrice1, ticketSeat1);
		boolean result = ticketList.add(ticket1);
		assertEquals(true, result);
		ticketList.clear();
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
		couponList = backEnd.getCoupons();
		coupon1 = new Coupon(couponCode1, couponEvent1, couponDiscountType1, couponDiscount1, couponExpiryDate1);
		boolean result = couponList.add(coupon1);
		assertEquals(true, result);
		couponList.clear();
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
		reviewList = backEnd.getReviews();
		review1 = new Review(reviewMember1, reviewRating1, reviewComment1);
		boolean result = reviewList.add(review1);
		assertEquals(true, result);
		reviewList.clear();
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
		transactionList = backEnd.getTransactions();
		transaction1 = new Transaction(transactionTicket1, transactionPurchaser1, transactionVendor1);
		boolean result = transactionList.add(transaction1);
		assertEquals(true, result);
		transactionList.clear();
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
