package emsTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ems.BackEnd;
import ems.Event;
import ems.Location;
import ems.Member;
import ems.Review;
import ems.Ticket;
import ems.User;
import ems.Vendor;

public class EventTest {
	
	String locationName1;
	int locationCapacity1;
	String locationName2;
	int locationCapacity2;
	Location location1;
	Location location2;
	ArrayList<Event> backEndEventList;
	ArrayList<Event> tempBackEndEventList;
	BackEnd backEnd = BackEnd.getInstance();
	ArrayList<Location> backEndLocationList;
	ArrayList<Location> tempBackEndLocationList;
	ArrayList<Vendor> backEndVendorList;
	ArrayList<Ticket> backEndTicketList;
	ArrayList<User> backEndUserList;
	ArrayList<Review>  backEndReviewList;
	Date startTime;
	Date endTime;
	String tEventName1;
	String eventName2;
	Event tEvent;
	String vendorLoginId1;
	String vendorName1;
	String vendorPassword1;
	Vendor vendor1;
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
	ArrayList<Review> reviewListForEvent;
	
	@Before
	public void setUp() {
		tEventName1 = "eventName1";
		eventName2 = "eventName2";
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18,10, 30);
		locationName1 = "locationName1";
		locationCapacity1 = 100;
		locationName2 = "locationName2";
		locationCapacity2 = 120;
		vendorLoginId1 = "vendorId1";
		vendorName1 = "vendorName1";
		vendorPassword1 = "vendorPassword1";
//		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		vendorLoginId2 = "vendorId2";
		vendorName2 = "vendorName2";
		vendorPassword2 = "vendorPassword2";
//		vendor2 = new Vendor(vendorLoginId2, vendorPassword2, vendorName2);
		reviewerLoginId = "reviewerLoginId";
		 reviewerName = "reviewerName" ;
		 reviewerPassword = "reviewerPassword";
		 reviewerAge = 20;
		 reviewerHKId = "A123456";
		 comment1 = "Good";
		 comment2 = "Very Good";
	}
	
	@Test
	public void getLocationWithNoLocationInBackEndLocationList() {
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		Location tResult = tEvent.getLocation();
		assertEquals(null, tResult);
	}

	
	@Test
	public void getLocationWithOneLocationInBackEndLocationListButIsNotTheOneEventRequired() {
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		location2 = new Location(locationName2, locationCapacity2);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		backEndLocationList.add(location2);
		Location tResult = tEvent.getLocation();
		assertEquals(null, tResult);
		backEndLocationList.clear();
	}
	
	@Test
	public void getLocationWithOneLocationInBackEndLocationListWhichHasTwoLocations() {
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		location2 = new Location(locationName2, locationCapacity2);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		backEndLocationList.add(location2);
		backEndLocationList.add(location1);
		Location tResult = tEvent.getLocation();
		assertEquals(location1, tResult);
		backEndLocationList.clear();
	}
	
	@Test
	public void getVendorWithNoVendorInBackEndVendorList() {
		backEndVendorList = backEnd.getVendors();
		backEndVendorList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		location2 = new Location(locationName2, locationCapacity2);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		Vendor tResult = tEvent.getVendor();
		assertEquals(null, tResult);
		
	}
	
	
	@Test
	public void getVendorWithOneVendorInBackEndVendorListButNotTheOneRequired() {
		backEndVendorList = backEnd.getVendors();
		backEndVendorList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		location2 = new Location(locationName2, locationCapacity2);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		vendor2 = new Vendor(vendorLoginId2, vendorPassword2, vendorName2);
		backEndVendorList.add(vendor2);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		Vendor tResult = tEvent.getVendor();
		assertEquals(null, tResult);
		backEndVendorList.clear();
		
	}
	
	@Test
	public void getVendorWithTwoVendorInBackEndVendorListAndTheOneRequiredIsContainedByBackEND() {
		backEndVendorList = backEnd.getVendors();
		backEndVendorList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		location2 = new Location(locationName2, locationCapacity2);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		vendor2 = new Vendor(vendorLoginId2, vendorPassword2, vendorName2);
		backEndVendorList.add(vendor2);
		backEndVendorList.add(vendor1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		Vendor tResult = tEvent.getVendor();
		assertEquals(vendor1, tResult);
		backEndVendorList.clear();
		
	}
	
	@Test
	public void generateTicketFailSinceLargerThanLocationCapacity() {
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		backEndLocationList.add(location1);
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 100;
		int vipSize = 100;
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1,  location1, true);
		ArrayList<Ticket> tResult = tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		assertEquals(null, tResult);
		backEndLocationList.clear();
	}
	
	@Test
	public void generateTicketWith0NormalSizeAnd0VipSize() {
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		backEndLocationList.add(location1);
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 0;
		int vipSize = 0;
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime,  vendor1, location1, true);
		ArrayList<Ticket> tResult = tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		assertEquals(0, tResult.size());
		backEndLocationList.clear();
	}
	
	@Test
	public void generateTicketWith1NormalSizeAnd1VipSize() {
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		backEndLocationList.add(location1);
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 1;
		int vipSize = 1;
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime,  vendor1, location1, true);
		ArrayList<Ticket> tResult = tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		assertEquals(price, tResult.get(normalSize - 1).getPrice(), 2);
		assertEquals(price*vipPriceMultiplier, tResult.get(normalSize).getPrice(), 2);
		backEndLocationList.clear();
	}
	
	@Test
	public void generateTicketWith2NormalSizeAnd2VipSize() {
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		backEndLocationList.add(location1);
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 2;
		int vipSize = 2;
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime,  vendor1, location1, true);
		ArrayList<Ticket> tResult = tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		assertEquals(price, tResult.get(normalSize - 1).getPrice(), 2);
		assertEquals(price*vipPriceMultiplier, tResult.get(normalSize).getPrice(), 2);
		backEndLocationList.clear();
	}
	
	@Test
	public void testGetTicketWithNoTicketInBackEndAndNoTicketInTheEvent() {
		backEndTicketList = backEnd.getTickets();
		backEndTicketList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime,  vendor1, location1, true);
		ArrayList<Ticket> tResult = tEvent.getTickets();
		assertEquals(0, tResult.size());

	}
	
	@Test 
	public void testGetTicketWithNoTicketInBackEnd() {
		
		backEndTicketList = backEnd.getTickets();
		backEndLocationList = backEnd.getLocations();
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 1;
		int vipSize = 0;
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		backEndLocationList.clear();
		backEndLocationList.add(location1);
		tEvent = new Event(tEventName1, startTime, endTime,  vendor1, location1, true);
		tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		backEndTicketList.clear();
		ArrayList<Ticket> tResult = tEvent.getTickets();
		assertEquals(0, tResult.size());
		backEndLocationList.clear();
	}
	
	@Test
	public void testGetTicketThatBackEndAndEventContainTheSameOne() {
	
		
		backEndTicketList = backEnd.getTickets();
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 1;
		int vipSize = 0;
		location1 = new Location(locationName1, locationCapacity1);
		backEndLocationList.add(location1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1,   location1, true);
		ArrayList<Ticket> ticketListGenerated = tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		backEndTicketList.clear();
		for(Ticket ticket: ticketListGenerated) {
			backEndTicketList.add(ticket);
		}
		ArrayList<Ticket> tResult = tEvent.getTickets();
		assertEquals(1, tResult.size());
		for(int i = 0; i < tResult.size(); i++) {
			assertEquals(ticketListGenerated.get(i), tResult.get(i));
		}
		backEndTicketList.clear();
		backEndLocationList.clear();
	}
	
	
	
	
	@Test
	public void testGetTicketToGetTwoTicketsThatBackEndAndEventBothContain() {
		
		backEndTicketList = backEnd.getTickets();
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 2;
		int vipSize = 0;
		location1 = new Location(locationName1, locationCapacity1);
		backEndLocationList.add(location1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime,  vendor1, location1, true);
		ArrayList<Ticket> ticketListGenerated = tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		backEndTicketList.clear();
		for(Ticket ticket: ticketListGenerated) {
			backEndTicketList.add(ticket);
		}
		ArrayList<Ticket> tResult = tEvent.getTickets();
		assertEquals(2, tResult.size());
		for(int i = 0; i < tResult.size(); i++) {
			assertEquals(ticketListGenerated.get(i), tResult.get(i));
		}
		backEndTicketList.clear();
		backEndLocationList.clear();
	}
	
	@Test
	public void testIsSoldOutWithNoTicketInTheEvent() {
		
		
		backEndTicketList = backEnd.getTickets();
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		backEndTicketList.clear();
		boolean tResult = tEvent.isSoldOut();
		assertEquals(true, tResult);
	}
	
	@Test
	public void testIsSoldOutWithOneTicketInTheEventButHaveNotBeenSold() {
		
		backEndTicketList = backEnd.getTickets();
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 1;
		int vipSize = 0;
		location1 = new Location(locationName1, locationCapacity1);
		backEndLocationList.add(location1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		generatedTicketListForEvent = tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		backEndTicketList.clear();
		for(Ticket ticket: generatedTicketListForEvent) {
			backEndTicketList.add(ticket);
		}
		boolean tResult = tEvent.isSoldOut();
		assertEquals(false, tResult);
		backEndTicketList.clear();
		backEndLocationList.clear();
	}
	
	
	@Test
	public void testIsSoldOutWithTwoTicketInTheEventButHaveNotBeenSold() {
		
		
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		userHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, userHKId);
		backEndTicketList = backEnd.getTickets();
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 2;
		int vipSize = 0;
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		backEndLocationList.add(location1);
		backEndTicketList.clear();
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		generatedTicketListForEvent = tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		for(Ticket ticket: generatedTicketListForEvent) {
			backEndTicketList.add(ticket);
		}
		generatedTicketListForEvent.get(0).Purchase(purchaser);
		
		boolean tResult = tEvent.isSoldOut();
		assertEquals(false, tResult);
		backEndTicketList.clear();
		backEndLocationList.clear();
	}
	
	@Test
	public void testGetSalesWithZeroTicket() {
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		backEndLocationList.add(location1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 0;
		int vipSize = 0;
		
		generatedTicketListForEvent = tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		double tResult = tEvent.getSales();
		assertEquals(0, tResult, 2);
		backEndLocationList.clear();
	}
	
	
	@Test
	public void testGetSalesWithOneTicket() {
		backEndTicketList = backEnd.getTickets();
		backEndTicketList.clear();
		backEndLocationList = backEnd.getLocations();
		backEndLocationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		backEndLocationList.add(location1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 1;
		int vipSize = 0;
		
		generatedTicketListForEvent = tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		for(Ticket ticket: generatedTicketListForEvent) {
			backEndTicketList.add(ticket);
		}
		double tResult = tEvent.getSales();
		assertEquals(0, tResult, 2);
		backEndLocationList.clear();
		backEndTicketList.clear();
	}
	
	@Test
	public void testGetSalesWithTwoTicketWhileOneTicketHasBeenPurchasedAndTheOtherNot() {
		backEndTicketList = backEnd.getTickets();
		backEndTicketList.clear();
		backEndLocationList =backEnd.getLocations();
		backEndLocationList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		backEndLocationList.add(location1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		double price = 10;
		double vipPriceMultiplier = 0.5;
		int normalSize = 2;
		int vipSize = 0;
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		userHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, userHKId);
		backEndUserList = backEnd.getUsers();
		backEndUserList.clear();
		backEndUserList.add(purchaser);
		generatedTicketListForEvent = tEvent.generateTickets(price, vipPriceMultiplier, normalSize, vipSize);
		generatedTicketListForEvent.get(0).Purchase(purchaser);
		for(Ticket ticket: generatedTicketListForEvent) {
			backEndTicketList.add(ticket);
		}
		double tResult = tEvent.getSales();
		assertEquals(10, tResult, 2);
		backEndUserList.clear();
		backEndTicketList.clear();
		backEndLocationList.clear();
	}
	
	@Test
	public void testGetReviewWithNoReviewInEvent() {
		backEndReviewList = backEnd.getReviews();
		backEndReviewList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		ArrayList<Review> tResult = tEvent.getReviews();
		assertEquals(0 ,tResult.size());
	}
	
	@Test
	public void testGetReviewWithOneReviewInEventButNoReviewInBackEnd() {
		backEndReviewList = backEnd.getReviews();
		backEndReviewList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		
		Member reviewer = new Member(reviewerLoginId, reviewerPassword,  reviewerName,  reviewerAge,  reviewerHKId);
		Review review = new Review(reviewer, 20, comment1); 
		tEvent.addReview(review);
		ArrayList<Review> tResult = tEvent.getReviews();
		assertEquals(0 ,tResult.size());
	}
	
	@Test
	public void testGetReviewWithTwoReviewInEventButNoReviewInBackEnd() {
		backEndReviewList = backEnd.getReviews();
		backEndReviewList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		
		Member reviewer = new Member(reviewerLoginId, reviewerPassword,  reviewerName,  reviewerAge,  reviewerHKId);
		Review review1 = new Review(reviewer, 20, comment1); 
		Review review2 = new Review(reviewer, 20, comment1); 
		tEvent.addReview(review1);
		tEvent.addReview(review2);
		ArrayList<Review> tResult = tEvent.getReviews();
		assertEquals(0 ,tResult.size());
		
	}
	
	@Test
	public void testGetReviewWithTwoReviewInEventButOnlyOneReviewInBackEnd() {
		backEndReviewList = backEnd.getReviews();
		backEndReviewList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		
		Member reviewer = new Member(reviewerLoginId, reviewerPassword,  reviewerName,  reviewerAge,  reviewerHKId);
		Review review1 = new Review(reviewer, 20, comment1); 
		Review review2 = new Review(reviewer, 20, comment1); 
		tEvent.addReview(review1);
		tEvent.addReview(review2);
		backEndReviewList.add(review1);
		ArrayList<Review> tResult = tEvent.getReviews();
		assertEquals(1 ,tResult.size());
		assertEquals(review1, tResult.get(0));
	}
	
	@Test
	public void testGetReviewWithTwoReviewInEventAndTwoSameReviewInBackEnd() {
		backEndReviewList = backEnd.getReviews();
		backEndReviewList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		
		Member reviewer = new Member(reviewerLoginId, reviewerPassword,  reviewerName,  reviewerAge,  reviewerHKId);
		Review review1 = new Review(reviewer, 20, comment1); 
		Review review2 = new Review(reviewer, 20, comment1); 
		tEvent.addReview(review1);
		tEvent.addReview(review2);
		backEndReviewList.add(review1);
		backEndReviewList.add(review2);
		ArrayList<Review> tResult = tEvent.getReviews();
		assertEquals(2 ,tResult.size());
		assertEquals(review1, tResult.get(0));
		assertEquals(review2, tResult.get(1));
	}
	
	@Test
	public void testGetAverageRatingWithOneReviewInEvent() {
		
		
		backEndReviewList = backEnd.getReviews();
		backEndReviewList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		Member reviewer = new Member(reviewerLoginId, reviewerPassword,  reviewerName,  reviewerAge,  reviewerHKId);
		Review review1 = new Review(reviewer, 20, comment1);
		backEndReviewList.add(review1);
		tEvent.addReview(review1);
		double tResult = tEvent.getAverageRating();
		assertEquals(20 ,tResult, 2);
	}
	
	@Test
	public void testGetAverageRatingWithTwoReviewInEvent() {
		
		
		backEndReviewList = backEnd.getReviews();
		backEndReviewList.clear();
		location1 = new Location(locationName1, locationCapacity1);
		vendor1 = new Vendor(vendorLoginId1, vendorPassword1, vendorName1);
		tEvent = new Event(tEventName1, startTime, endTime, vendor1, location1, true);
		Member reviewer = new Member(reviewerLoginId, reviewerPassword,  reviewerName,  reviewerAge,  reviewerHKId);
		Review review1 = new Review(reviewer, 20, comment1);
		Review review2 = new Review(reviewer, 20, comment2);
		backEndReviewList.add(review1);
		backEndReviewList.add(review2);
		tEvent.addReview(review1);
		tEvent.addReview(review2);
		double tResult = tEvent.getAverageRating();
		assertEquals(20 ,tResult, 2);
	}
//	@Test
//	public void testGetAverageRatingWithNoReviewInEvent() {
//		class EventStub extends Event{
//			public EventStub(String name, Date startTime, Date endTime, Location location, boolean isMature) {
//				super( name,  startTime,  endTime,  location,  isMature);
//			}
//			
//			
//			@Override
//			public ArrayList<Review> getReviews() {
//				return new ArrayList<Review>();
//				
//			}
//		}
//		
//		backEndReviewList = backEnd.getReviews();
//		backEndReviewList.clear();
//		location1 = new Location(locationName1, locationCapacity1);
//		tEvent = new Event(tEventName1, startTime, endTime, location1, true);
//	double tResult = tEvent.getAverageRating();
//		assertEquals(0 ,tResult, 2);
//	}
	
//	@Test
//	public void testGetAverageRatingWithNoReviewInEvent() {
//		backEndReviewList = backEnd.getReviews();
//		backEndReviewList.clear();
//		location1 = new Location(locationName1, locationCapacity1);
//		tEvent = new Event(tEventName1, startTime, endTime, location1, true);
//	double tResult = tEvent.getAverageRating();
//		assertEquals(0 ,tResult, 2);
//	}

}


