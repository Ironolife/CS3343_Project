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
		System.out.println("95.0 received.");
		EMS.PrintHeader("Ticket Purchased!");
		
		
		ByteArrayOutputStream systemContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemContent));
		stubUserFrontEnd.handlePayment("1", transaction); // This line causes nullpointerException
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