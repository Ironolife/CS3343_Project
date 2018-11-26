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
import ems.Vendor;

public class UserFrontEndTest {
	private User user;
	Vendor vendor;
	UserFrontEnd userFrontEnd;
	StubUserFrontEnd stubUserFrontEnd;
	Event event;
	Transaction transaction;
	Transaction transactionGuest;
	StubUserFrontEndOverride stubUserFrontEndOverride;
	
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
		vendor = new Vendor("vendorId1", "vendorPassword1", "vendorName1");
		stubUserFrontEnd = new StubUserFrontEnd(user);
		userFrontEnd = new UserFrontEnd(user);
		
		event = new Event("eventName1", new Date(2018, 9, 18, 9, 30), new Date(2018, 9, 18,10, 30), vendor, new Location("locationName1", 100), true);
		Ticket ticket = new Ticket(event, 100 ,20);
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(ticket);
		BackEnd.getInstance().createNewTickets(tickets);
		BackEnd.getInstance().addUser(user);
		BackEnd.getInstance().createNewVendor(vendor);
		transaction = new Transaction(ticket, user, vendor);
		transactionGuest = new Transaction(ticket, new Guest("", "", 0, ""), vendor);
		
		stubUserFrontEndOverride = new StubUserFrontEndOverride(user);
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
	public void displayUpcomingEventsTest() {
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
	
	
	
	
	
	
	
	
	
	@Test
	public void getUpcomingEventListTest() {
		assertEquals(new ArrayList<Event>(), stubUserFrontEnd.getUpcomingEventList());
	}
	//Need one more test case to show 
	
	
	@Test
	public void getAvailableEventListTest() {
		assertEquals(new ArrayList<Event>(), stubUserFrontEnd.getAvailableEventList());
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
	
	
	
	
	@Test
	public void getAttenededEventTest() {
		assertEquals(new ArrayList<Event>(), stubUserFrontEnd.getAttenededEvent());
	}
	
	@Test
	public void validateRatingTest() {
	}
	
	@Test
	public void createReviewTest() {
		//assertEquals(new Review((Member)this.user, 0, ""), stubUserFrontEnd.createReview(event));
	}
	
	@Test
	public void purchaseTicketTest() {
		
	}
	
	@Test
	public void reviewEventTest() {
		
		
		
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
	public void displayPurchaseHistoryTest() {
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
	public void printCreditCardListTest() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.printCreditCardList();
		assertEquals(testContent.toString(), systemContent.toString());
	}
	//This one need more
	
	
	
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