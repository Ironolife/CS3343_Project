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
import ems.Ticket;
import ems.Transaction;
import ems.Vendor;

public class VendorTest {
	String vendorLoginId;
	String vendorName;
	String vendorPassword;
	String newPassword;
	Date startTime;
	Date endTime;
	String locationName;
	int locationCapacity;
	String eventName1;
	String eventName2;
	Event event1;
	Event event2;
	String userLoginId;
	String userName;
	String userPassword;
	int userAge;
	String useHKId;

	@Before
	public void setUp() {
		eventName1 = "eventName1";
		eventName2 = "eventName2";
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18, 10, 30);
		locationName = "locationName";
		locationCapacity = 100;

	}

	@Test
	public void TestGetLoginId() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		String tResult = tVendor.getLoginId();
		assertEquals(vendorLoginId, tResult);

	}

	@Test
	public void TestValidatePassword1() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		boolean tResult = tVendor.validatePassword(vendorPassword);
		assertEquals(true, tResult);

	}

	@Test
	public void TestValidatePassword2() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		boolean tResult = tVendor.validatePassword("abc");
		assertEquals(false, tResult);

	}

	@Test
	public void TestChangePassword() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		tVendor.changePassword(newPassword);
		boolean tResult = tVendor.validatePassword(newPassword);
		assertEquals(true, tResult);

	}

	@Test
	public void testGetName() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		String tResult = tVendor.getName();
		assertEquals(vendorName, tResult);
	}

	@Test
	public void testGetEvents1() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		ArrayList<Event> tResult = tVendor.getEvents();
		assertEquals(0, tResult.size());

	}

	@Test
	public void testGetEvents2() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> eventList = backEnd.getEvents();
		eventList.clear();
		tVendor.addEvent(event1);
		ArrayList<Event> tResult = tVendor.getEvents();
		assertEquals(0, tResult.size());
	}

	@Test
	public void testGetEvents3() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> eventList = backEnd.getEvents();
		eventList.clear();
		eventList.add(event1);
		tVendor.addEvent(event1);
		ArrayList<Event> tResult = tVendor.getEvents();
		assertEquals(event1, tResult.get(0));
		eventList.clear();
	}

	@Test
	public void testGetEvents4() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Event event2 = new Event(eventName2, startTime, endTime, location, true);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> eventList = backEnd.getEvents();
		eventList.clear();
		eventList.add(event2);
		tVendor.addEvent(event1);
		ArrayList<Event> tResult = tVendor.getEvents();
		assertEquals(0, tResult.size());
		eventList.clear();
	}

	@Test
	public void testGetEvents5() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Event event2 = new Event(eventName2, startTime, endTime, location, true);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> eventList = backEnd.getEvents();
		eventList.clear();
		eventList.add(event1);
		eventList.add(event2);
		tVendor.addEvent(event1);
		tVendor.addEvent(event2);
		ArrayList<Event> tResult = tVendor.getEvents();
		assertEquals(event1, tResult.get(0));
		assertEquals(event2, tResult.get(1));
		eventList.clear();
	}

	@Test
	public void testRemoveEvent1() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);

		tVendor.addEvent(event1);

		Event tResult = tVendor.removeEvent(event1);
		assertEquals(event1, tResult);

	}

	@Test
	public void testRemoveEvent2() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Event tResult = tVendor.removeEvent(event1);
		assertEquals(null, tResult);

	}

	@Test
	public void testGetAccumlateSales1() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		double tResult = tVendor.getAccumulatedSales();
		assertEquals(0, tResult, 1);

	}
	
	@Test
	public void testGetAccumlateSales2() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> eventList = backEnd.getEvents();
		eventList.clear();
		eventList.add(event1);
		tVendor.addEvent(event1);
		double tResult = tVendor.getAccumulatedSales();
		assertEquals(0, tResult, 1);
		eventList.clear();

	}
	
	@Test
	public void testGetAccumlateSales3() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> eventList = backEnd.getEvents();
		eventList.clear();
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Event event2 = new Event(eventName2, startTime, endTime, location, true);
		tVendor.addEvent(event1);
		tVendor.addEvent(event2);
		eventList.add(event1);
		eventList.add(event2);
		double tResult = tVendor.getAccumulatedSales();
		assertEquals(0, tResult, 1);
		eventList.clear();

	}
	
	@Test
	public void testGetTransactions1() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		ArrayList<Transaction> transactionList = tVendor.getTransactions();
		assertEquals(0, transactionList.size());
	}
	
	
	@Test
	public void testGetTransactions2() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Ticket aTicket = new Ticket(event1, 12, 100);
		Transaction aTransaction = new Transaction(aTicket, purchaser, tVendor);
		tVendor.addTransaction(aTransaction);
		ArrayList<Transaction> transactionList = tVendor.getTransactions();
		assertEquals(0, transactionList.size());
	}
	
	@Test
	public void testGetTransactionsWithVendorHaveTwoTransactionId() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Event event2 = new Event(eventName2, startTime, endTime, location, true);
		Ticket ticketForEvent1 = new Ticket(event1, 12, 100);
		Ticket ticketForEvent2 = new Ticket(event2, 20, 18);
		Transaction transactionForTicket1 = new Transaction(ticketForEvent1, purchaser, tVendor);
		Transaction transactionForTicket2 = new Transaction(ticketForEvent2, purchaser, tVendor);
		tVendor.addTransaction(transactionForTicket1);
		tVendor.addTransaction(transactionForTicket2);
		ArrayList<Transaction> transactionList = tVendor.getTransactions();
		assertEquals(0, transactionList.size());
	}
	
	@Test
	public void testGetTransactionsWithVendorHaveTwoTransactionIdAndBackEndContainsDifferent() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Event event2 = new Event(eventName2, startTime, endTime, location, true);
		Ticket ticketForEvent1 = new Ticket(event1, 12, 100);
		Ticket ticketForEvent2 = new Ticket(event2, 20, 18);
		Transaction transactionForTicket1 = new Transaction(ticketForEvent1, purchaser, tVendor);
		Transaction transactionForTicket2 = new Transaction(ticketForEvent2, purchaser, tVendor);
		tVendor.addTransaction(transactionForTicket1);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Transaction> backEndTransactionList = backEnd.getTransactions();
		backEndTransactionList.clear();
		backEndTransactionList.add(transactionForTicket2);
		ArrayList<Transaction> transactionList = tVendor.getTransactions();
		assertEquals(0, transactionList.size());
		backEndTransactionList.clear();
	}
	
	
	@Test
	public void testGetTransactionsWithVendorHaveTwoTransactionIdAndBackEndContainsOneSameTransactionWithAnotherDifferentTransaction() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Event event2 = new Event(eventName2, startTime, endTime, location, true);
		Ticket ticketForEvent1 = new Ticket(event1, 12, 100);
		Ticket ticketForEvent2 = new Ticket(event2, 20, 18);
		Transaction transactionForTicket1 = new Transaction(ticketForEvent1, purchaser, tVendor);
		Transaction transactionForTicket2 = new Transaction(ticketForEvent2, purchaser, tVendor);
		tVendor.addTransaction(transactionForTicket2);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Transaction> backEndTransactionList = backEnd.getTransactions();
		backEndTransactionList.clear();
		backEndTransactionList.add(transactionForTicket1);
		backEndTransactionList.add(transactionForTicket2);
		ArrayList<Transaction> transactionList = tVendor.getTransactions();
		assertEquals(1, transactionList.size());
		backEndTransactionList.clear();
	}
	
	@Test
	public void testRemoveTransactionWithNoTransanctionInVendor() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Ticket ticketForEvent1 = new Ticket(event1, 12, 100);
		Transaction transactionForTicket1 = new Transaction(ticketForEvent1, purchaser, tVendor);
		Transaction tResult = tVendor.removeTransaction(transactionForTicket1);
		assertEquals(null, tResult);
	}
	
	@Test
	public void testRemoveTransactionWithOneTransanctionInVendor() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Ticket ticketForEvent1 = new Ticket(event1, 12, 100);
		Transaction transactionForTicket1 = new Transaction(ticketForEvent1, purchaser, tVendor);
		tVendor.addTransaction(transactionForTicket1);
		Transaction tResult = tVendor.removeTransaction(transactionForTicket1);
		assertEquals(transactionForTicket1, tResult);
	}
	
	@Test
	public void testgetAccumulatedSales1() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Ticket ticketForEvent1 = new Ticket(event1, 12, 100);
		Transaction transactionForTicket1 = new Transaction(ticketForEvent1, purchaser, tVendor);
		double tResult = tVendor.getAccumulatedSales();
		assertEquals(0, tResult, 1);
	}
	
	@Test
	public void testgetAccumulatedSales2() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Ticket ticketForEvent1 = new Ticket(event1, 12, 100);
		Transaction transactionForTicket1 = new Transaction(ticketForEvent1, purchaser, tVendor);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Transaction> backEndTransactionList = backEnd.getTransactions();
		backEndTransactionList.clear();
		backEndTransactionList.add(transactionForTicket1);
		tVendor.addTransaction(transactionForTicket1);
		double tResult = tVendor.getAccumulatedSales();
		assertEquals(12, tResult, 1);
		backEndTransactionList.clear();
	}
	
	@Test
	public void testgetAccumulatedSales3() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Event event2 = new Event(eventName2, startTime, endTime, location, true);
		Ticket ticketForEvent1 = new Ticket(event1, 12, 100);
		Ticket ticketForEvent2 = new Ticket(event2, 20, 18);
		Transaction transactionForTicket1 = new Transaction(ticketForEvent1, purchaser, tVendor);
		Transaction transactionForTicket2 = new Transaction(ticketForEvent2, purchaser, tVendor);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Transaction> backEndTransactionList = backEnd.getTransactions();
		backEndTransactionList.clear();
		backEndTransactionList.add(transactionForTicket1);
		backEndTransactionList.add(transactionForTicket2);
		tVendor.addTransaction(transactionForTicket1);
		tVendor.addTransaction(transactionForTicket2);
		double tResult = tVendor.getAccumulatedSales();
		assertEquals(32, tResult, 1);
		backEndTransactionList.clear();
	}
	
	@Test
	public void testgetAccumulatedProfit1() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Ticket ticketForEvent1 = new Ticket(event1, 12, 100);
		double tResult = tVendor.getAccumulatedProfit();
		assertEquals(0, tResult, 1);
	}
	
	@Test
	public void testgetAccumulatedProfit2() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Ticket ticketForEvent1 = new Ticket(event1, 12, 100);
		Transaction transactionForTicket1 = new Transaction(ticketForEvent1, purchaser, tVendor);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Transaction> backEndTransactionList = backEnd.getTransactions();
		backEndTransactionList.clear();
		backEndTransactionList.add(transactionForTicket1);
		tVendor.addTransaction(transactionForTicket1);
		double tResult = tVendor.getAccumulatedProfit();
		double expectedResult = ticketForEvent1.getPrice()*purchaser.getDiscount();
		assertEquals(expectedResult, tResult, 2);
		backEndTransactionList.clear();
	}
	
	@Test
	public void testgetAccumulatedProfit3() {
		vendorLoginId = "vendorId";
		vendorName = "vendorName";
		vendorPassword = "vendorPassword";
		newPassword = "newPassword";
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		Vendor tVendor = new Vendor(vendorLoginId, vendorPassword, vendorName);
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, location, true);
		Event event2 = new Event(eventName2, startTime, endTime, location, true);
		Ticket ticketForEvent1 = new Ticket(event1, 12, 100);
		Ticket ticketForEvent2 = new Ticket(event2, 20, 18);
		Transaction transactionForTicket1 = new Transaction(ticketForEvent1, purchaser, tVendor);
		Transaction transactionForTicket2 = new Transaction(ticketForEvent2, purchaser, tVendor);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Transaction> backEndTransactionList = backEnd.getTransactions();
		backEndTransactionList.clear();
		backEndTransactionList.add(transactionForTicket1);
		backEndTransactionList.add(transactionForTicket2);
		tVendor.addTransaction(transactionForTicket1);
		tVendor.addTransaction(transactionForTicket2);
		double tResult = tVendor.getAccumulatedProfit();
		double expectResult = ticketForEvent1.getPrice()*purchaser.getDiscount() + ticketForEvent2.getPrice()*purchaser.getDiscount();
		assertEquals(expectResult, tResult, 1);
		backEndTransactionList.clear();
	}
}
