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
	User user2;
	String userLoginId2;
	String userPassword2;
	String userName2;
	int userAge2;
	String userHkid2;
	
	ArrayList<Vendor> vendorList;
	Vendor vendor1;
	String vendorLoginId1;
	String vendorName1;
	String vendorPassword1;
	Vendor vendor2;
	String vendorLoginId2;
	String vendorName2;
	String vendorPassword2;
	
	ArrayList<Location> locationList;
	Location location1;
	String locationName1;
	int locationCapacity1;
	Location location2;
	String locationName2;
	int locationCapacity2;
	
	ArrayList<Event> eventList;
	Event event1;
	String eventName1;
	Date eventStartTime1;
	Date eventEndTime1;
	Vendor eventVendor1;
	Location eventLocation1;
	boolean eventIsMature1;
	Event event2;
	String eventName2;
	Date eventStartTime2;
	Date eventEndTime2;
	Vendor eventVendor2;
	Location eventLocation2;
	boolean eventIsMature2;
	
	ArrayList<Ticket> ticketList;
	Ticket ticket1;
	Event ticketEvent1; 
	double ticketPrice1;
	int ticketSeat1;
	Ticket ticket2;
	Event ticketEvent2; 
	double ticketPrice2;
	int ticketSeat2;
	
	ArrayList<Coupon> couponList;
	Coupon coupon1;
	String couponCode1;
	Event couponEvent1; 
	int couponDiscountType1;
	double couponDiscount1;
	Date couponExpiryDate1;
	Coupon coupon2;
	String couponCode2;
	Event couponEvent2; 
	int couponDiscountType2;
	double couponDiscount2;
	Date couponExpiryDate2;
	
	ArrayList<Review> reviewList;
	Review review1;
	Member reviewMember1;
	double reviewRating1;
	String reviewComment1;
	Review review2;
	Member reviewMember2;
	double reviewRating2;
	String reviewComment2;
	
	ArrayList<Transaction> transactionList;
	Transaction transaction1;
	Ticket transactionTicket1;
	User transactionPurchaser1;
	Vendor transactionVendor1;
	Transaction transaction2;
	Ticket transactionTicket2;
	User transactionPurchaser2;
	Vendor transactionVendor2;
	
	@Before
	public void setUp() throws Exception {
		
		userLoginId1 = "member1";
		userPassword1 = "123456";
		userName1 = "Member 1";
		userAge1 = 20;
		userHkid1 = "V102142(7)";
		userLoginId2 = "member2";
		userPassword2 = "123456";
		userName2 = "Member 2";
		userAge2 = 40;
		userHkid2 = "E165151(3)";
		
		vendorLoginId1 = "vendor1";
		vendorName1 = "Vendor 1";
		vendorPassword1 = "123456";		
		vendorLoginId2 = "vendor2";
		vendorName2 = "Vendor 2";
		vendorPassword2 = "123456";
		
		locationName1 = "Location 1";
		locationCapacity1 = 200;
		locationName2 = "Location 2";
		locationCapacity2 = 300;
		
		eventName1 = "Event 1";
		eventStartTime1 = new Date();
		eventEndTime1 =  new Date();
		eventVendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		eventLocation1 = new Location(locationName1, locationCapacity1);
		eventIsMature1 = true;
		eventName2 = "Event 2";
		eventStartTime2 = new Date();
		eventEndTime2 =  new Date();
		eventVendor2 = new Vendor(vendorLoginId2, vendorPassword2, vendorName2);
		eventLocation2 = new Location(locationName2, locationCapacity2);
		eventIsMature2 = true;
		
		ticketEvent1 = new Event(eventName1, eventStartTime1, eventEndTime1, eventVendor1, eventLocation1, eventIsMature1); 
		ticketPrice1 = 400;
		ticketSeat1 = 2;
		ticketEvent2 = new Event(eventName2, eventStartTime2, eventEndTime2, eventVendor2, eventLocation2, eventIsMature2); 
		ticketPrice2 = 600;
		ticketSeat2 = 3;
		
		couponCode1 = "C001";
		couponEvent1 = new Event(eventName1, eventStartTime1, eventEndTime1, eventVendor1, eventLocation1, eventIsMature1); 
		couponDiscountType1 = 0;
		couponDiscount1 = 20;
		couponExpiryDate1 = new Date();
		couponCode2 = "C002";
		couponEvent2 = new Event(eventName2, eventStartTime2, eventEndTime2, eventVendor2, eventLocation2, eventIsMature2); 
		couponDiscountType2 = 1;
		couponDiscount2 = 40;
		couponExpiryDate2 = new Date();
		
		reviewMember1 = new Member(userLoginId1, userPassword1, userName1, userAge1, userHkid1);
		reviewRating1 = 5 ;
		reviewComment1 = "Good";
		reviewMember2 = new Member(userLoginId2, userPassword2, userName2, userAge2, userHkid2);
		reviewRating2 = 1 ;
		reviewComment2 = "Bad";
		
		transactionTicket1 = new Ticket(ticketEvent1, ticketPrice1, ticketSeat1);
		transactionPurchaser1 = new Member(userLoginId1, userPassword1, userName1, userAge1, userHkid1);
		transactionVendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);	
		transactionTicket2 = new Ticket(ticketEvent2, ticketPrice2, ticketSeat2);
		transactionPurchaser2 = new Member(userLoginId2, userPassword2, userName2, userAge2, userHkid2);
		transactionVendor2 = new Vendor(vendorLoginId2, vendorPassword2, vendorName2);	
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
		vendorList = backEnd.getVendors();
		vendorList.clear();
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		vendor2 = new Vendor(vendorLoginId2, vendorPassword2, vendorName2);
		vendorList.add(vendor1);
		vendorList.add(vendor2);
		ArrayList<Vendor> result = vendorList;
		assertEquals(2, result.size());
	}

	@Test
	public void testRemoveVendor() {
		vendorList = backEnd.getVendors();
		vendorList.clear();
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		vendorList.add(vendor1);
		Vendor result = backEnd.removeVendor(vendor1);
		assertEquals(vendor1, result);
	}

	@Test
	public void testAddUser() {
		userList = backEnd.getUsers();
		user1 = new Member(userLoginId1, userPassword1, userName1, userAge1, userHkid1);
		boolean result = userList.add(user1);
		assertEquals(true, result);
		userList.clear();
	}

	@Test
	public void testGetUsers() {
		userList = backEnd.getUsers();
		userList.clear();
		user1 = new Member(userLoginId1, userPassword1, userName1, userAge1, userHkid1);
		user2 = new Member(userLoginId2, userPassword2, userName2, userAge2, userHkid2);
		userList.add(user1);
		userList.add(user2);
		ArrayList<User> result = userList;
		assertEquals(2, result.size());
	}

	@Test
	public void testRemoveUser() {
		userList = backEnd.getUsers();
		userList.clear();
		user1 = new Member(userLoginId1, userPassword1, userName1, userAge1, userHkid1);
		userList.add(user1);
		User result = backEnd.removeUser(user1);
		assertEquals(user1, result);
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
		locationList = backEnd.getLocations();
		locationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		location2 = new Location(locationName2, locationCapacity2);
		locationList.add(location1);
		locationList.add(location2);
		ArrayList<Location> result = locationList;
		assertEquals(2, result.size());
	}

	@Test
	public void testRemoveLocation() {
		locationList = backEnd.getLocations();
		locationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		locationList.add(location1);
		Location result = backEnd.removeLocation(location1);
		assertEquals(location1, result);
	}

	@Test
	public void testIsDuplicateLocationName() {
		locationList = backEnd.getLocations();
		location1 = new Location(locationName1, locationCapacity1);
		locationList.add(location1);
		boolean result = backEnd.isDuplicateLocationName(locationName1);
		assertEquals(true, result);
		locationList.clear();	}

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
		eventList = backEnd.getEvents();
		eventList.clear();
		event1 = new Event(eventName1, eventStartTime1, eventEndTime1, eventVendor1, eventLocation1, eventIsMature1);
		event2 = new Event(eventName2, eventStartTime2, eventEndTime2, eventVendor2, eventLocation2, eventIsMature2);
		eventList.add(event1);
		eventList.add(event2);
		ArrayList<Event> result = eventList;
		assertEquals(2, result.size());
	}

	@Test
	public void testRemoveEvent() {
		eventList = backEnd.getEvents();
		eventList.clear();
		event1 = new Event(eventName1, eventStartTime1, eventEndTime1, eventVendor1, eventLocation1, eventIsMature1);
		eventList.add(event1);
		Event result = backEnd.removeEvent(event1);
		assertEquals(event1, result);
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
		ticketList = backEnd.getTickets();
		ticketList.clear();
		ticket1 = new Ticket(ticketEvent1, ticketPrice1, ticketSeat1);
		ticket2 = new Ticket(ticketEvent2, ticketPrice2, ticketSeat2);
		ticketList.add(ticket1);
		ticketList.add(ticket2);
		ArrayList<Ticket> result = ticketList;
		assertEquals(2, result.size());
	}

	@Test
	public void testRemoveTicket() {
		ticketList = backEnd.getTickets();
		ticketList.clear();
		ticket1 = new Ticket(ticketEvent1, ticketPrice1, ticketSeat1);
		ticketList.add(ticket1);
		Ticket result = backEnd.removeTicket(ticket1);
		assertEquals(ticket1, result);
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
		couponList = backEnd.getCoupons();
		couponList.clear();
		coupon1 = new Coupon(couponCode1, couponEvent1, couponDiscountType1, couponDiscount1, couponExpiryDate1);
		coupon2 = new Coupon(couponCode2, couponEvent2, couponDiscountType2, couponDiscount2, couponExpiryDate2);
		couponList.add(coupon1);
		couponList.add(coupon2);
		ArrayList<Coupon> result = couponList;
		assertEquals(2, result.size());
	}

	@Test
	public void testRemoveCoupon() {
		couponList = backEnd.getCoupons();
		couponList.clear();
		coupon1 = new Coupon(couponCode1, couponEvent1, couponDiscountType1, couponDiscount1, couponExpiryDate1);
		couponList.add(coupon1);
		Coupon result = backEnd.removeCoupon(coupon1);
		assertEquals(coupon1, result);
	}

//	@Test
//	public void testIsDuplicateCouponCode() {
//		couponList = backEnd.getCoupons();
//		coupon1 = new Coupon(couponCode1, couponEvent1, couponDiscountType1, couponDiscount1, couponExpiryDate1);
//		backEnd.createNewCoupon(coupon1);
//		boolean result = backEnd.isDuplicateCouponCode(couponCode1);
//		assertEquals(true, result);
//		couponList.clear();
//	}

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
		reviewList = backEnd.getReviews();
		reviewList.clear();
		review1 = new Review(reviewMember1, reviewRating1, reviewComment1);
		review2 = new Review(reviewMember2, reviewRating2, reviewComment2);
		reviewList.add(review1);
		reviewList.add(review2);
		ArrayList<Review> result = reviewList;
		assertEquals(2, result.size());
	}

	@Test
	public void testRemoveReview() {
		reviewList = backEnd.getReviews();
		reviewList.clear();
		review1 = new Review(reviewMember1, reviewRating1, reviewComment1);
		reviewList.add(review1);
		Review result = backEnd.removeReview(review1);
		assertEquals(review1, result);
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
		transactionList = backEnd.getTransactions();
		transactionList.clear();
		transaction1 = new Transaction(transactionTicket1, transactionPurchaser1, transactionVendor1);
		transaction2 = new Transaction(transactionTicket2, transactionPurchaser2, transactionVendor2);
		transactionList.add(transaction1);
		transactionList.add(transaction2);
		ArrayList<Transaction> result = transactionList;
		assertEquals(2, result.size());
	}

	@Test
	public void testRemoveTransaction() {
		transactionList = backEnd.getTransactions();
		transactionList.clear();
		transaction1 = new Transaction(transactionTicket1, transactionPurchaser1, transactionVendor1);
		transactionList.add(transaction1);
		Transaction result = backEnd.removeTransaction(transaction1);
		assertEquals(transaction1, result);
	}

}
