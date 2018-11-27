package emsTest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import ems.BackEnd;
import ems.Coupon;
import ems.CreditCard;
import ems.DateUtils;
import ems.EMS;
import ems.Event;
import ems.Guest;
import ems.Location;
import ems.Member;
import ems.Review;
import ems.Ticket;
import ems.Transaction;
import ems.User;
import ems.UserFrontEnd;
import ems.VIPTicket;
import ems.Vendor;

public class UserFrontEndTest {
	private User user;
	private User userUnderage;
	Vendor vendor;
	UserFrontEnd userFrontEnd;
	StubUserFrontEnd stubUserFrontEnd;
	Event event;
	Event eventOverdue;
	Event eventForUnderage;
	Location location;
	Transaction transaction;
	Transaction transactionVIP;
	Transaction transactionGuest;
	Ticket ticket;
	Ticket ticketVIP;
	Ticket ticketOverdue;
	Ticket ticketForUnderage;
	StubUserFrontEndOverride stubUserFrontEndOverride;
	ArrayList<Ticket> tickets;
	
	private class StubUserFrontEnd extends UserFrontEnd{
		
		public StubUserFrontEnd(User user) {
			super(user);
		}

		@Override
		public String readInput() {
			return "8";
		}
		
		@Override
		public int listSelection(int size, String text) {
			return 0;
		}
	}
	
	private class StubUserFrontEndOverride extends UserFrontEnd{
		
		public StubUserFrontEndOverride(User user) {
			super(user);
		}

		
		@Override
		public void displayEvents() {
		}
		
		@Override
		public void searchEvents() {
		}
		
		@Override
		public void displayUpcomingEvents() {
		}
		
		@Override
		public void purchaseTicket() {
		}
		
		@Override
		public void reviewEvent() {
		}
		
		@Override
		public void displayAccountOperations() {
		}
		
		@Override
		public void displayPurchaseHistory() {
		}
	}
	
	@Before
	public void setUp() {
		user = new Member("U2", "U2", "U2", 20, "Y666283(6)");
		userUnderage = new Member("U2", "U2", "U2", 15, "Y666283(6)");
		vendor = new Vendor("vendorId1", "vendorPassword1", "vendorName1");
		stubUserFrontEnd = new StubUserFrontEnd(user);
		userFrontEnd = new UserFrontEnd(user);
		
		location = new Location("locationName1", 100);
		event = new Event("eventName1", new Date(2018, 12, 18, 9, 30), new Date(2018, 12, 18, 10, 30), vendor, location, true);
		eventOverdue = new Event("overdue", DateUtils.parseDate("2012-11-10 09:18"), DateUtils.parseDate("2012-11-11 09:18"), vendor, location, true);
		eventForUnderage = new Event("overdue", DateUtils.parseDate("2012-12-10 09:18"), DateUtils.parseDate("2012-12-11 09:18"), vendor, location, false);
		ticket = new Ticket(event, 100 ,20);
		ticketVIP = new VIPTicket(event, 200, 20, 1);
		ticketOverdue = new Ticket(eventOverdue, 100 ,20);
		ticketForUnderage = new Ticket(eventForUnderage, 100 ,20);
		tickets = new ArrayList<Ticket>();
		tickets.add(ticket);
		tickets.add(ticketVIP);
		tickets.add(ticketOverdue);
		tickets.add(ticketForUnderage);
		BackEnd.getInstance().createNewTickets(tickets);
		BackEnd.getInstance().addUser(user);
		BackEnd.getInstance().addUser(userUnderage);;
		BackEnd.getInstance().createNewVendor(vendor);
		

		
		transaction = new Transaction(ticket, user, vendor);
		transactionGuest = new Transaction(ticket, new Guest("", "", 0, ""), vendor);
		transactionVIP = new Transaction(ticketVIP, user, vendor);
		
		BackEnd.getInstance().createNewLocation(location);
		BackEnd.getInstance().createNewEvent(event);
		BackEnd.getInstance().createNewEvent(eventOverdue);
		BackEnd.getInstance().createNewEvent(eventForUnderage);
		BackEnd.getInstance().createNewTransaction(transaction);
		BackEnd.getInstance().createNewTransaction(transactionGuest);
		BackEnd.getInstance().createNewTransaction(transactionVIP);
		stubUserFrontEndOverride = new StubUserFrontEndOverride(user);
		
		eventForUnderage.generateTickets(100, 1, 10, 10);
	}
	
//	@After
//	public void tearDown() {
//		
//	}
	
	@Test
	public void UserFrontEndTest() {
		stubUserFrontEnd = new StubUserFrontEnd(new Guest("", "", 0, ""));
	}
	
	
	
	@Test
	public void userOperationsTest() {
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Choose operations: ");
		System.out.println("1: Display Events");
		System.out.println("2: Search Events");
		System.out.println("3: My Upcoming Events");
		System.out.println("4: Purchase Ticket");
		System.out.println("5: Review Event");
		System.out.println("6: My Account");
		System.out.println("7: Purchase History");
		System.out.println("8: Logout");

		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.userOperations();
		assertEquals(testContent.toString(), systemContent.toString());
		
	}
	
	@Test
	public void validateUserInputTest() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("Invalid Operation");

		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.validateUserInput("9");
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	
	@Test
	public void validateUserInputTest_01() {
		assertEquals(true, stubUserFrontEndOverride.validateUserInput("1"));
	}
	
	@Test
	public void validateUserInputTest_02() {
		assertEquals(true, stubUserFrontEndOverride.validateUserInput("2"));
	}
	
	@Test
	public void validateUserInputTest_03() {
		assertEquals(true, stubUserFrontEndOverride.validateUserInput("3"));
	}
	
	@Test
	public void validateUserInputTest_04() {
		assertEquals(true, stubUserFrontEndOverride.validateUserInput("4"));
	}
	
	@Test
	public void validateUserInputTest_05() {
		assertEquals(true, stubUserFrontEndOverride.validateUserInput("5"));
	}
	
	@Test
	public void validateUserInputTest_06() {
		assertEquals(true, stubUserFrontEndOverride.validateUserInput("6"));
	}
	
	@Test
	public void validateUserInputTest_07() {
		assertEquals(true, stubUserFrontEndOverride.validateUserInput("7"));
	}
	
	@Test
	public void validateUserInputTest_08() {
		stubUserFrontEndOverride = new StubUserFrontEndOverride(new Guest("", "", 0, ""));
		assertEquals(true, stubUserFrontEndOverride.validateUserInput("5"));
	}
	
	@Test
	public void displayUpcomingEventsTest_01() {
		class StubUserFrontEnd extends UserFrontEnd{
			
			public StubUserFrontEnd(User user) {
				super(user);
			}

			@Override
			public String readInput() {
				return "8";
			}
			
			@Override
			public int listSelection(int size, String text) {
				return 0;
			}
			
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("- Upcoming Events -");
		System.out.println();
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		
		stubUserFrontEnd.displayUpcomingEvents();
		assertEquals(testContent.toString(), systemContent.toString());
		
	}
	
//	@Test
//	public void displayUpcomingEventsTest_02() {
//		class StubUserFrontEnd extends UserFrontEnd{
//			
//			public StubUserFrontEnd(User user) {
//				super(user);
//			}
//
//			@Override
//			public String readInput() {
//				return "8";
//			}
//			
//			@Override
//			public int listSelection(int size, String text) {
//				return 1;
//			}
//			
//			
//		}
//		user.addTicket(new Ticket(event, 100, 20));
//		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
//		
//		
//		
//		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(testContent));
//		EMS.PrintHeader("- Upcoming Events -");
//		System.out.println();
//		
//		
//		
//		System.out.println("Event Name: " + event.getName());
//		System.out.println("Start Time: " + event.getStartTime().toLocaleString());
//		System.out.println("End Time: " + event.getEndTime().toLocaleString());
//		System.out.println("Location: locationName1");
//		if(event.getIsMature() == true) {
//			System.out.println("* Event is only available for age over 18.");
//		}
//		System.out.println("----------");
//
//		System.out.println("Total Tickets: 20");
//		System.out.println("VIP Tickets: 20 Available, 0 Purchased, " + "Price: 100.0");
//		System.out.println("Normal Tickets: 0 Available, 0 Purchased, " + "Price: 0");
//		System.out.println("----------");		
//		//Reviews 
//		if(event.getReviews().size() > 0) {
//			System.out.println("Average Rating: " + event.getAverageRating());
//			System.out.println();
//		}
//		System.out.println("Reviews: ");
//		if(event.getReviews().size() == 0) {
//			System.out.println("No Reviews.");
//			System.out.println();
//		}
//		for(Review r: event.getReviews()) {
//			System.out.println(r.getMember().getName() + " - " + r.getRating());
//			System.out.println(r.getComment() + " - " + r.getDate());
//			System.out.println();
//		}
//		
//		
//		assertEquals(0, stubUserFrontEnd.getUpcomingEventList().size());
//		
//		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(systemContent));
//		stubUserFrontEnd.displayUpcomingEvents();
//		
//		assertEquals(testContent.toString(), systemContent.toString());
//		
//	}
	
	
	
	
	
	
	
	
	@Test
	public void getUpcomingEventListTest_01() {
		assertEquals(new ArrayList<Event>(), stubUserFrontEnd.getUpcomingEventList());
	}
	
	@Test
	public void getUpcomingEventListTest_02() {
		user.addTicket(ticket);
		assertEquals(1, stubUserFrontEnd.getUpcomingEventList().size());
		assertEquals(event, stubUserFrontEnd.getUpcomingEventList().get(0));
	}
	
	
	@Test
	public void getUpcomingEventListTest_03() {
		user.addTicket(ticket);
		user.addTicket(ticketOverdue);
		assertEquals(1, stubUserFrontEnd.getUpcomingEventList().size());
		assertEquals(event, stubUserFrontEnd.getUpcomingEventList().get(0));
	}
	//Need one more test case to show 
	
	
	@Test
	public void getAvailableEventListTest_01() {
		assertEquals(new ArrayList<Event>(), stubUserFrontEnd.getAvailableEventList());
	}

	@Test
	public void getAvailableEventListTest_02() {
		userUnderage.addTicket(ticket);
		userUnderage.addTicket(ticketOverdue);
		stubUserFrontEnd = new StubUserFrontEnd(userUnderage);
		assertEquals(0, stubUserFrontEnd.getAvailableEventList().size());
	}

	@Test
	public void getAvailableEventListTest_03() {
		userUnderage.addTicket(ticket);
		userUnderage.addTicket(ticketOverdue);
		userUnderage.addTicket(ticketForUnderage);
		stubUserFrontEnd = new StubUserFrontEnd(userUnderage);
		//assertEquals(1, stubUserFrontEnd.getAvailableEventList().size());
		//assertEquals(eventForUnderage, stubUserFrontEnd.getAvailableEventList().get(0));
	}
	
	@Test
	public void printTicketDetailsTest() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Choose ticket type: ");
		System.out.print("1: Normal Ticket, 10 left");
		System.out.print(", " + Transaction.round((100 * user.getDiscount()), 1) + " HKD");
		System.out.println();
		System.out.print("2: VIP Ticket, 10 left");
		System.out.print(", " + Transaction.round((150 * this.user.getDiscount()), 1) + " HKD");
		System.out.println();
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.printTicketDetails(10,10,100,150);
		assertEquals(testContent.toString(), systemContent.toString());
		
	}
	
	@Test
	public void validateTicketPurchaseTest_01() {
		Ticket ticket = userFrontEnd.validateTicketPurchase("0", new Event("eventName1", new Date(2018, 9, 18, 9, 30), new Date(2018, 9, 18,10, 30), new Vendor("vendorId1", "vendorPassword1", "vendorName1"), new Location("locationName1", 100), true),10,10);
		assertEquals(ticket, null);
	}
	
	@Test
	public void validateTicketPurchaseTest_02() {
		Ticket ticket = userFrontEnd.validateTicketPurchase("1", new Event("eventName1", new Date(2018, 9, 18, 9, 30), new Date(2018, 9, 18,10, 30), new Vendor("vendorId1", "vendorPassword1", "vendorName1"), new Location("locationName1", 100), true),10,10);
		assertEquals(ticket, null);
	}
	
	@Test
	public void validateTicketPurchaseTest_03() {
		Ticket ticket = userFrontEnd.validateTicketPurchase("2", new Event("eventName1", new Date(2018, 9, 18, 9, 30), new Date(2018, 9, 18,10, 30), new Vendor("vendorId1", "vendorPassword1", "vendorName1"), new Location("locationName1", 100), true),10,10);
		assertEquals(ticket, null);
	}
	
	@Test
	public void validateTicketPurchaseTest_04() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("Normal Ticket sold out!");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		Ticket ticket = userFrontEnd.validateTicketPurchase("1", new Event("eventName1", new Date(2018, 9, 18, 9, 30), new Date(2018, 9, 18,10, 30), new Vendor("vendorId1", "vendorPassword1", "vendorName1"), new Location("locationName1", 100), true),0,10);
		assertEquals(ticket, null);
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	@Test
	public void validateTicketPurchaseTest_05() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("VIP Ticket sold out!");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		Ticket ticket = userFrontEnd.validateTicketPurchase("2", new Event("eventName1", new Date(2018, 9, 18, 9, 30), new Date(2018, 9, 18,10, 30), new Vendor("vendorId1", "vendorPassword1", "vendorName1"), new Location("locationName1", 100), true),10,0);
		assertEquals(ticket, null);
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	@Test
	public void validateTicketPurchaseTest_06() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("VIP Ticket sold out!");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		Ticket ticket = userFrontEnd.validateTicketPurchase("2", new Event("eventName1", new Date(2018, 9, 18, 9, 30), new Date(2018, 9, 18,10, 30), new Vendor("vendorId1", "vendorPassword1", "vendorName1"), new Location("locationName1", 100), true),10,0);
		assertEquals(ticket, null);
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	@Test
	public void validateCouponOptionTest_01() {
		assertEquals(null, userFrontEnd.validateCouponOption("A"));
	}
	
	@Test
	public void validateCouponOptionTest_02() {
		assertEquals("Y", userFrontEnd.validateCouponOption("Y"));
	}
	
	@Test
	public void validateCouponOptionTest_03() {
		assertEquals("N", userFrontEnd.validateCouponOption("N"));
	}
	
	@Test
	public void consumeCouponTest_01() {
		class StubUserFrontEnd extends UserFrontEnd{
			
			public StubUserFrontEnd(User user) {
				super(user);
			}

			@Override
			public String readInput() {
				return "0";
			}
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Coupon Code (0 to cancel): ");
		
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.consumeCoupon(event);
		assertEquals(testContent.toString(), systemContent.toString());
		assertEquals(null, stubUserFrontEnd.consumeCoupon(event));
	}
	
	@Test
	public void consumeCouponTest_02() {
		class StubUserFrontEnd extends UserFrontEnd{
			int count = 1;
			public StubUserFrontEnd(User user) {
				super(user);
			}

			@Override
			public String readInput() {
				if(count == 1) {
					count--;
					return "1";
				}
				return "0";
			}
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Coupon Code (0 to cancel): ");
		EMS.PrintHeader("Invalid Coupon Code");
		System.out.println("Coupon Code (0 to cancel): ");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.consumeCoupon(event);
		assertEquals(testContent.toString(), systemContent.toString());
		assertEquals(null, stubUserFrontEnd.consumeCoupon(event));
	}
	
	@Test
	public void printTransactionDetailTest_01() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("Coupon Applied!");
		System.out.println("Discounted Price: 84.4");
		//System.out.println("Price: 100");
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.printTransactionDetail(new Transaction(new Ticket(event, 100 ,20 ), user, new Vendor("V1", "V1", "V1")), new Coupon("code", event, 0, 10.6, DateUtils.parseDate("2012-12-10 09:18")));
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	@Test
	public void printTransactionDetailTest_02() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Price: 100.0");
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		userFrontEnd.printTransactionDetail(transactionGuest, null);
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	@Test
	public void handlePaymentTest_01() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Please insert cash . . .");
		System.out.println("95.0 received.");
		EMS.PrintHeader("Ticket Purchased!");
		
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.handlePayment("1", transaction); // This line causes nullpointerException
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	@Test
	public void handlePaymentTest_02() {
		class StubUserFrontEnd extends UserFrontEnd{
			public StubUserFrontEnd(User user) {
				super(user);
			}
			
			@Override
			public String readInput() {
				return "1";
			}
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("Insufficient balance! Please add 95.0 to balance: ");
		System.out.println("Choose payment method: ");
		System.out.println("1: Cash");
		System.out.println("2: Credit Card");	
		System.out.println("Please insert cash . . .");
		System.out.println("95.0 received.");
		EMS.PrintHeader("Balance addded!");
		System.out.println("Payment in progress . . .");
		System.out.println("95.0 deducted from balance. 0.0 left in balance.");
		EMS.PrintHeader("Ticket Purchased!");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.handlePayment("3", transaction);
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	@Test
	public void handlePaymentTest_03() {
		class StubUserFrontEnd extends UserFrontEnd{
			public StubUserFrontEnd(User user) {
				super(user);
			}
			
			@Override
			public void addBalance(double amount) {}
		}
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("Invalid input!");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.handlePayment("4", transaction);
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	
	
	
//	@Test
//	public void handlePaymentTest_04() {
//		class StubUserFrontEnd extends UserFrontEnd{
//			int count = 0;
//			public StubUserFrontEnd(User user) {
//				super(user);
//			}
//
//
//			@Override
//			public int listSelection(int size, String text) {
//				return 0;
//			}
//			
//			@Override
//			public String readInput() {
//				if(count == 0) {
//					count++;
//					return "4123567812345678";
//				}
//				if(count == 1) {
//					count++;
//					return "11/25";
//				}
//				return "11/25";
//			}
//			
//			@Override
//			public int readIntInput(String string) {
//				return 999;
//				//security code
//			}
//			
//			
//		}
//		
//		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(testContent));
//		System.out.println("Payment in progress . . .");
//		System.out.println("Card Number: ");
//		System.out.println("Expiry Date (MM/YY): ");
//		EMS.PrintHeader("Validation Success!");
//		System.out.println("Payment in progress . . .");
//		System.out.println(transaction.getDiscountedAmount() + " deducted from credit card 4123567812345678.");
//		EMS.PrintHeader("Ticket Purchased!");
//		
//		
//		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(systemContent));
//		stubUserFrontEnd.handlePayment("2", transaction);
//		assertEquals(testContent.toString(), systemContent.toString());
//	}
	
	
	
	
	
	
	
	@Test
	public void getAttenededEventTest() {
		assertEquals(new ArrayList<Event>(), stubUserFrontEnd.getAttenededEvent());
	}
	
	@Test
	public void validateRatingTest_01() {
		class StubUserFrontEnd extends UserFrontEnd{
			public StubUserFrontEnd(User user) {
				super(user);
			}
			
			@Override
			public double readDoubleInput(String string) {
				return 1;
			}
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
		double errorMargin = 0.0005;
		assertEquals(1, stubUserFrontEnd.validateRating(), errorMargin);
	}
	
	@Test
	public void validateRatingTest_02() {
		class StubUserFrontEnd extends UserFrontEnd{
			int count = 0;
			public StubUserFrontEnd(User user) {
				super(user);
			}
			
			@Override
			public double readDoubleInput(String string) {
				return 5.5 + count--;
			}
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("Invalid Input");

		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.validateRating();
		assertEquals(testContent.toString(), systemContent.toString());
		double errorMargin = 0.0005;
		assertEquals(3.5, stubUserFrontEnd.validateRating(), errorMargin);
	}
	
	@Test
	public void createReviewTest() {
		class StubUserFrontEnd extends UserFrontEnd{
			int count = 0;
			public StubUserFrontEnd(User user) {
				super(user);
			}
			
			@Override
			public double readDoubleInput(String string) {
				return 4.5;
			}
			
			@Override
			public String readInput() {
				return "very good!";
			}
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Comment: ");
		EMS.PrintHeader("Review Submitted!");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		
		Review review = stubUserFrontEnd.createReview(event);
		double marginalError = 0.0001;
		assertEquals(4.5, review.getRating(), marginalError);
		assertEquals("very good!", review.getComment());
		assertEquals(this.user, review.getMember());
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	
	
	
	
	
	
	
	@Test
	public void purchaseTicketTest_01() {
		class StubUserFrontEnd extends UserFrontEnd{
			public StubUserFrontEnd(User user) {
				super(user);
			}
			
			@Override
			public int listSelection(int size, String text) {
				return 0;
			}
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("- Purchase Ticket -");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.purchaseTicket();
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
//	@Test
//	public void purchaseTicketTest_02() {
//		class StubUserFrontEnd extends UserFrontEnd{
//			public StubUserFrontEnd(User user) {
//				super(user);
//			}
//			
//			@Override
//			public ArrayList<Event> getAvailableEventList(){
//				ArrayList<Event> availableEvents = new ArrayList<Event>();
//				availableEvents.add(event);
//				return availableEvents;
//			}
//			
//			@Override
//			public int listSelection(int size, String text) {
//				return 1;
//			}
//			
//			@Override
//			public String readInput() {
//				return "1";
//			}
//		}
//		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
//		
//		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(testContent));
//		EMS.PrintHeader("- Purchase Ticket -");
//		System.out.println("1: " + event.getName());
//		System.out.println("Choose ticket type: ");
//		System.out.print("1: Normal Ticket, 0 left");
//		System.out.println();
//		System.out.print("2: VIP Ticket, 0 left");
//		System.out.println();
//		EMS.PrintHeader("Normal Ticket sold out!");
//		
//		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(systemContent));
//		stubUserFrontEnd.purchaseTicket();
//		assertEquals(testContent.toString(), systemContent.toString());
//	}
	
	
	
	
	
	
	@Test
	public void reviewEventTest() {
		class StubUserFrontEnd extends UserFrontEnd{
			public StubUserFrontEnd(User user) {
				super(user);
			}
			
			@Override
			public int listSelection(int size, String text) {
				return 0;
			}
			
			@Override 
			public double validateRating() {
				return 2.5;
			}
			
			@Override
			public String readInput() {
				return "this is so bad.";
			}
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("- Review Event -");
		/*
		System.out.println("Select an Event to review (0 to exit): ");
		System.out.println("Rating (0 to 5): ");
		System.out.println("Comment: ");
		*/
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.reviewEvent();
		assertEquals(testContent.toString(), systemContent.toString());
		/*
		assertEquals(review.getComment());
		assertEquals(review.getRating());
		assertEquals(review.getMember());
		*/
	}
	
	@Test
	public void displayAccountOperationsAsGuestTest() {
		class StubUserFrontEnd extends UserFrontEnd{
			public StubUserFrontEnd(User user) {
				super(user);
			}
			
			@Override
			public String readInput() {
				return "1";
			}
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(new Guest("", "", 0, ""));
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Choose operations: ");
		System.out.println("1: View Account Details");
		System.out.println("2: Upgrade to Member");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.displayAccountOperationsAsGuest();
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	@Test
	public void displayAccountOperationsTest() {
		
	}
	
	
	
	@Test
	public void validateAccountOperationAsGuestTest_01() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("- Account Details -");
		
		System.out.println("HKID: " + this.user.getHKID());
		System.out.println("Age: " + this.user.getAge());
		System.out.println("Tickets Count: " + this.user.getTickets().size());
		System.out.println();
		
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.validateAccountOperationAsGuest("1");
		assertEquals(testContent.toString(), systemContent.toString());
		assertEquals("1", stubUserFrontEnd.validateAccountOperationAsGuest("1"));
	}
	
	@Test
	public void validateAccountOperationAsGuestTest_02() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("Invalid Operation");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.validateAccountOperationAsGuest("3");
		assertEquals(testContent.toString(), systemContent.toString());
		assertEquals("-1", stubUserFrontEnd.validateAccountOperationAsGuest("3"));
	}
	
	@Test
	public void validateAccountOperationAsGuestTest_03() {
		class StubUserFrontEnd extends UserFrontEnd{
			public StubUserFrontEnd(User user) {
				super(user);
			}
			
			@Override
			public String readInput() {
				return "1";
			}
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(new Guest("", "", 0, ""));
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("- Upgrade Account -");
		System.out.println("Name: ");
		EMS.PrintHeader("Account Upgraded!");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		String returnValue = stubUserFrontEnd.validateAccountOperationAsGuest("2");
		assertEquals(testContent.toString(), systemContent.toString());
		assertEquals("2", returnValue);
	}
	
	
	
	@Test
	public void displayAccountOperationsAsUserTest() {
		class StubUserFrontEnd extends UserFrontEnd{
			public StubUserFrontEnd(User user) {
				super(user);
			}
			
			@Override
			public String readInput() {
				return "1";
			}
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
		Member member = (Member)user;
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Choose operations: ");
		System.out.println("1: View Account Details");
		System.out.println("2: Add Balance");
		EMS.PrintHeader("- Account Details -");
		System.out.println("Name: " + member.getName());
		System.out.println("HKID: " + member.getHKID());
		System.out.println("Age: " + member.getAge());
		System.out.println("Tickets Count: " + member.getTickets().size());
		System.out.println("Balance: " + member.getBalance());
		System.out.println();
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.displayAccountOperationsAsUser();
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	
	@Test
	public void displayAccountOperationsTest_01() {
		
	}
	
	@Test
	public void displayAccountOperationsTest_02() {
		
	}
	
	@Test
	public void validateAccountOperationAsUserTest_01() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("- Account Details -");
		Member member = (Member)this.user;
		System.out.println("Name: " + member.getName());
		System.out.println("HKID: " + member.getHKID());
		System.out.println("Age: " + member.getAge());
		System.out.println("Tickets Count: " + member.getTickets().size());
		System.out.println("Balance: " + member.getBalance());
		System.out.println();
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.validateAccountOperationAsUser("1");
		assertEquals(testContent.toString(), systemContent.toString());
		assertEquals("1", stubUserFrontEnd.validateAccountOperationAsUser("1"));
	}
	
	@Test
	public void validateAccountOperationAsUserTest_02() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("Invalid Operation");
		
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.validateAccountOperationAsUser("3");
		assertEquals(testContent.toString(), systemContent.toString());
		assertEquals("-1", stubUserFrontEnd.validateAccountOperationAsUser("3"));
	}
	
	@Test
	public void validateAccountOperationAsUserTest_03() {
		class StubUserFrontEnd extends UserFrontEnd{
			public StubUserFrontEnd(User user) {
				super(user);
			}
			
			@Override
			public double readDoubleInput(String string) {
				return 5;
			}
			
			@Override
			public String readInput() {
				return "1";
			}
			
		}
		StubUserFrontEnd stubUserFrontEnd = new StubUserFrontEnd(user);
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("- Add Balance -");
		System.out.println("Choose payment method: ");
		System.out.println("1: Cash");
		System.out.println("2: Credit Card");	
		System.out.println("Please insert cash . . .");
		System.out.println("5.0 received.");
		EMS.PrintHeader("Balance addded!");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		String returnValue = stubUserFrontEnd.validateAccountOperationAsUser("2");
		assertEquals(testContent.toString(), systemContent.toString());
		assertEquals("2", returnValue);
	}
	
	
	
	
	@Test
	public void displayPurchaseHistoryTest_01() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("- Purchase History -");
		System.out.println("----------");
		System.out.println("0 transactions, 0.0 spent.");
		System.out.println();
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.displayPurchaseHistory();
		assertEquals(testContent.toString(), systemContent.toString());

	}
	
	@Test
	public void displayPurchaseHistoryTest_02() {
		user.addTransaction(transaction);
		user.addTransaction(transactionVIP);
		stubUserFrontEnd = new StubUserFrontEnd(user);
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("- Purchase History -");
		System.out.print("1: ");
		System.out.print("Purchased a Normal Ticket");
		
		System.out.print(" for eventName1");
		System.out.print(" at 95.0");
		System.out.print(" from vendorName1");
		System.out.println(" on " + transaction.getDate() + ".");
		System.out.print("2: ");
		System.out.print("Purchased a VIP Ticket");
		System.out.print(" for eventName1");
		System.out.print(" at 190.0");
		System.out.print(" from vendorName1");
		System.out.println(" on " + transaction.getDate() + ".");
		System.out.println("----------");
		System.out.println("2 transactions, 285.0 spent.");
		System.out.println();
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.displayPurchaseHistory();
		assertEquals(testContent.toString(), systemContent.toString());

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void printCreditCardListTest_01() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.printCreditCardList();
		assertEquals(testContent.toString(), systemContent.toString());
	}

	@Test
	public void printCreditCardListTest_02() {
		user.addCreditCard(new CreditCard("4123456712345678", 995, DateUtils.parseDate("2018-12-18 10:30")));
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("1: " + "4123456712345678");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.printCreditCardList();
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	@Test
	public void printCreditCardListTest_03() {
		user.addCreditCard(new CreditCard("3123456712345678", 9953, DateUtils.parseDate("2018-12-18 10:30")));
		user.addCreditCard(new CreditCard("4123456712345678", 995, DateUtils.parseDate("2018-12-26 10:30")));
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("1: " + "3123456712345678");
		System.out.println("2: " + "4123456712345678");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.printCreditCardList();
		assertEquals(testContent.toString(), systemContent.toString());
	}
	
	
	
	
	@Test
	public void inputCreditCardInfoTest() {
		
	}
	
	@Test
	public void creditCardPaymentTest() {
		
	}
	
	
	
	
	
	@Test
	public void validateAddBalanceMethodTest_01() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Please insert cash . . .");
		System.out.println("500.0 received.");
		EMS.PrintHeader("Balance addded!");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.validateAddBalanceMethod("1", 500);
		assertEquals(testContent.toString(), systemContent.toString());
		assertEquals("1",stubUserFrontEnd.validateAddBalanceMethod("1", 500));
	}
	
	@Test
	public void validateAddBalanceMethodTest_02() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Payment in progress . . .");
		//System.out.println("500.0 deducted from credit card 0.");
		EMS.PrintHeader("Balance addded!");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		//stubUserFrontEnd.validateAddBalanceMethod("2", 500);
		//assertEquals(testContent.toString(), systemContent.toString());
	}
	
	@Test
	public void validateAddBalanceMethodTest_03() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		EMS.PrintHeader("Invalid input!");
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.validateAddBalanceMethod("3", 500);
		assertEquals(testContent.toString(), systemContent.toString());
		assertEquals("-1",stubUserFrontEnd.validateAddBalanceMethod("3", 500));
	}
	
	
	
	
	@Test
	public void displayPaymentMethodTest() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Choose payment method: ");
		System.out.println("1: Cash");
		System.out.println("2: Credit Card");	
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.displayPaymentMethod();
		assertEquals(testContent.toString(), systemContent.toString());

	}
	
	
	@Test
	public void addBalanceTest() {
		
	}
}
;