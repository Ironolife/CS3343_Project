package emsTest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import ems.EMS;
import ems.Member;
import ems.User;
import ems.UserFrontEnd;

public class UserFrontEndTest {
	private User user;
	UserFrontEnd userFrontEnd;
	StubUserFrontEnd stubUserFrontEnd;
	
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