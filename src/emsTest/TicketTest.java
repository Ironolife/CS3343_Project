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
import ems.User;
import ems.Vendor;

public class TicketTest {
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
	String userLoginId2;
	String userName2;
	String userPassword2;
	int userAge2;
	String useHKId2;
	Vendor testingVendor;
	
	@Before
	public void setUp() {
		eventName1 = "eventName1";
		eventName2 = "eventName2";
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18,10, 30);
		locationName = "locationName";
		locationCapacity = 100;
		testingVendor = new Vendor("V1", "V1", "V1");
	}
	
//	@Test
//	public void getIdTest() {
//		Ticket tTicket = new Ticket(event, 12, 10);
//		assertEquals(tTicket.getId(), event.getId());
//	}
	
	@Test
	public void testGetEvent1() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 100, 20);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> eventList = backEnd.getEvents();
		eventList.clear();
		Event tResult =  tTicket.getEvent();
		assertEquals(null, tResult);
		
	}
	
	@Test
	public void testGetEvent2() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 100, 20);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> eventList = backEnd.getEvents();
		eventList.clear();
		eventList.add(event1);
		Event tResult =  tTicket.getEvent();
		assertEquals(event1, tResult);
		eventList.clear();
		
	}
	
	@Test
	public void testGetEvent3() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Event event2 = new Event(eventName2, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event2, 100, 20);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<Event> eventList = backEnd.getEvents();
		eventList.clear();
		eventList.add(event1);
		eventList.add(event2);
		Event tResult =  tTicket.getEvent();
		assertEquals(event2, tResult);
		eventList.clear();
		
	}
	
	@Test
	public void testGetSeat() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 100, 20);
		assertEquals(20, tTicket.getSeat());
	}
	
	@Test
	public void testGetPrice() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		assertEquals(12.5, tTicket.getPrice(), 1);
	}
	
	@Test
	public void testSetEntryTime1() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		boolean tResult = tTicket.setEntryTime();
		assertEquals(true, tResult);
		
	}
	
	@Test
	public void testSetEntryTime2() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		tTicket.setEntryTime();
		boolean tResult = tTicket.setEntryTime();
		assertEquals(false, tResult);
		
	}
	
	@Test
	public void testSetExitTime1() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		boolean tResult = tTicket.setExitTime();
		assertEquals(true, tResult);
		
	}
	
	@Test
	public void testSetExitTime2() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		tTicket.setExitTime();
		boolean tResult = tTicket.setExitTime();
		assertEquals(false, tResult);
		
	}
	
	@Test
	public void testPurchase1() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		boolean tResult = tTicket.Purchase(purchaser);
		assertEquals(true, tResult);

	}
	
	@Test
	public void testPurchase2() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		tTicket.Purchase(purchaser);
		boolean tResult = tTicket.Purchase(purchaser);
		assertEquals(false, tResult);

	}
	
	@Test
	public void testGetPurchaser1() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<User> userList = backEnd.getUsers();
		userList.clear();
		tTicket.Purchase(purchaser);
		User tResult = tTicket.getPurchaser();
		assertEquals(null, tResult);
		userList.clear();
		
	}
	
	@Test
	public void testGetPurchaser2() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<User> userList = backEnd.getUsers();
		userList.clear();
		userList.add(purchaser);
		tTicket.Purchase(purchaser);
		User tResult = tTicket.getPurchaser();
		assertEquals(purchaser, tResult);
		userList.clear();
		
	}
	
	@Test
	public void testGetPurchaser3() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		userLoginId2 = "human2";
		userName2 = "human2";
		userPassword2 = "human123123";
		userAge2 = 10;
		useHKId2 = "A234567";
		Member aMember = new Member(userLoginId2, userPassword2, userName2, userAge2, useHKId2);
		BackEnd backEnd = BackEnd.getInstance();
		ArrayList<User> userList = backEnd.getUsers();
		userList.clear();
		userList.add(aMember);
		userList.add(purchaser);
		tTicket.Purchase(purchaser);
		User tResult = tTicket.getPurchaser();
		assertEquals(purchaser, tResult);
		userList.clear();
		
	}
	
	
	@Test
	public void testGetPurchaseTime() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
	
		tTicket.Purchase(purchaser);
		Date aDate = new Date();
		Date tResult = tTicket.getPurchaseTime();
		assertEquals(aDate.getYear(), tResult.getYear());
		assertEquals(aDate.getDay(), tResult.getDay());
		assertEquals(aDate.getDay(), tResult.getDay());
		
		
	}
	
	@Test
	public void testGetStatus1() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		int tResult = tTicket.getStatus();
		assertEquals(0,tResult);
		
		
	}
	
	@Test
	public void testGetStatus2() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		tTicket.Purchase(purchaser);
		int tResult = tTicket.getStatus();
		assertEquals(1,tResult);

	}
	
	@Test
	public void testGetStatus3() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		tTicket.Purchase(purchaser);
		tTicket.setEntryTime();
		int tResult = tTicket.getStatus();
		assertEquals(2, tResult);

	}
	
	@Test
	public void testGetStatus4() {
		Location location = new Location(locationName, locationCapacity);
		Event event1 = new Event(eventName1, startTime, endTime, testingVendor, location, true);
		Ticket tTicket = new Ticket(event1, 12.5, 20);
		userLoginId = "human";
		userName = "human";
		userPassword = "human123";
		userAge = 10;
		useHKId = "A123456";
		Member purchaser = new Member(userLoginId, userPassword, userName, userAge, useHKId);
		tTicket.Purchase(purchaser);
		tTicket.setEntryTime();
		tTicket.setExitTime();
		int tResult = tTicket.getStatus();
		assertEquals(3, tResult);

	}
	
	

}
;