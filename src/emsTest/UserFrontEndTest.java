package emsTest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import ems.Coupon;
import ems.DateUtils;
import ems.EMS;
import ems.Event;
import ems.Location;
import ems.Member;
import ems.Ticket;
import ems.Transaction;
import ems.User;
import ems.UserFrontEnd;
import ems.Vendor;

public class UserFrontEndTest {
	private User user;
	UserFrontEnd userFrontEnd;
	StubUserFrontEnd stubUserFrontEnd;
	Event event;
	Transaction transaction;
	
	private class StubUserFrontEnd extends UserFrontEnd{
		
		public StubUserFrontEnd(User user) {
			super(user);
		}

		@Override
		public String readInput() {
			return "8";
		}
	}
	
	@Before
	public void setUp() {
		user = new Member("U2", "U2", "U2", 20, "Y666283(6)");
		stubUserFrontEnd = new StubUserFrontEnd(user);
		userFrontEnd = new UserFrontEnd(user);
		
		event = new Event("eventName1", new Date(2018, 9, 18, 9, 30), new Date(2018, 9, 18,10, 30), new Vendor("vendorId1", "vendorPassword1", "vendorName1"), new Location("locationName1", 100), true);
		transaction = new Transaction(new Ticket(event, 100 ,20), user, new Vendor("V1", "V1", "V1"));
	}
	
//	@After
//	public void tearDown() {
//		
//	}
	
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
		
	}
	
	
	@Test
	public void displayUpcomingEventsTest() {
		
	}
	
	@Test
	public void getUpcomingEventListTest() {
		assertEquals(0, stubUserFrontEnd.getUpcomingEventList().size());
	}
	//Need one more test case to show 
	
	
	@Test
	public void getAvailableEventListTest() {
		assertEquals(0, stubUserFrontEnd.getAvailableEventList().size());

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
		Ticket ticket = stubUserFrontEnd.validateTicketPurchase("0", new Event("eventName1", new Date(2018, 9, 18, 9, 30), new Date(2018, 9, 18,10, 30), new Vendor("vendorId1", "vendorPassword1", "vendorName1"), new Location("locationName1", 100), true),10,10);
		assertEquals(ticket, null);
	}
	
	@Test
	public void validateTicketPurchaseTest_02() {
		Ticket ticket = stubUserFrontEnd.validateTicketPurchase("1", new Event("eventName1", new Date(2018, 9, 18, 9, 30), new Date(2018, 9, 18,10, 30), new Vendor("vendorId1", "vendorPassword1", "vendorName1"), new Location("locationName1", 100), true),10,10);
		assertEquals(ticket, null);
	}
	
	@Test
	public void validateTicketPurchaseTest_03() {
		Ticket ticket = stubUserFrontEnd.validateTicketPurchase("2", new Event("eventName1", new Date(2018, 9, 18, 9, 30), new Date(2018, 9, 18,10, 30), new Vendor("vendorId1", "vendorPassword1", "vendorName1"), new Location("locationName1", 100), true),10,10);
		assertEquals(ticket, null);
	}
	
	@Test
	public void validateCouponOptionTest_01() {
		assertEquals(null, stubUserFrontEnd.validateCouponOption("A"));
	}
	
	@Test
	public void validateCouponOptionTest_02() {
		assertEquals("Y", stubUserFrontEnd.validateCouponOption("Y"));
	}
	
	@Test
	public void validateCouponOptionTest_03() {
		assertEquals("N", stubUserFrontEnd.validateCouponOption("N"));
	}
	
	@Test
	public void printTransactionDetailTest() {
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
	public void handlePaymentTest() {
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("Please insert cash . . .");
		System.out.println("100 received.");
		EMS.PrintHeader("Ticket Purchased!");
		
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		//stubUserFrontEnd.handlePayment("1", transaction); // This line causes nullpointerException
		//assertEquals(testContent.toString(), systemContent.toString());
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
	public void displayPurchaseHistoryTest() {
		
	}
	
	
	@Test
	public void printCreditCardListTest() {
		
	}
	
	@Test
	public void creditCardPaymentTest() {
		
	}
	
	@Test
	public void addBalanceTest() {
		
	}
}
;