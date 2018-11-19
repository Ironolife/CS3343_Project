package emsTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import ems.BackEnd;
import ems.Event;
import ems.Location;
import ems.Member;
import ems.Ticket;
import ems.Transaction;
import ems.User;
import ems.Vendor;

public class TransactionTest {
	User purchaser1;
	User purchaser2;
	Vendor vendor1;
	Vendor vendor2;
	Ticket ticket;
	BackEnd backEnd = BackEnd.getInstance();
	ArrayList<Ticket> backEndTicketList;
	ArrayList<User> backEndUserList;
	ArrayList<Vendor> backEndVendorList;
	Date startTime;
	Date endTime;
	String eventName;
	String locationName;
	int locationCapacity;
	Location location;
	Event event;
	Ticket ticket1;
	Ticket ticket2;
	
	
	@Test
	public void testGetTicketWithNoTicketInBackEnd() {
		this.backEndTicketList = backEnd.getTickets();
		this.backEndTicketList.clear();
		vendor1 = new Vendor("V1", "V1", "V1");
		purchaser1 = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		locationName = "locationName";
		locationCapacity = 100;
		location = new Location(locationName, locationCapacity);
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18,10, 30);
		eventName = "eventName";
		event = new Event(eventName, startTime, endTime, vendor1, location, true);
		ticket1 = new Ticket(event, 100, 20);
		Transaction tTransaction = new Transaction(ticket1, purchaser1, vendor1);
		Ticket tResult = tTransaction.getTicket();
		assertEquals(null, tResult);
	}
	
	@Test
	public void testGetTicketWithOneSameTicketInBackEnd() {
		this.backEndTicketList = backEnd.getTickets();
		this.backEndTicketList.clear();
		vendor1 = new Vendor("V1", "V1", "V1");
		purchaser1 = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		locationName = "locationName";
		locationCapacity = 100;
		location = new Location(locationName, locationCapacity);
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18,10, 30);
		eventName = "eventName";
		event = new Event(eventName, startTime, endTime, vendor1, location, true);
		ticket1 = new Ticket(event, 100, 20);
		backEndTicketList.add(ticket1);
		Transaction tTransaction = new Transaction(ticket1, purchaser1, vendor1);
		Ticket tResult = tTransaction.getTicket();
		assertEquals(ticket1, tResult);
		this.backEndTicketList.clear();
	}
	
	@Test
	public void testGetTicketWithTwoTicketInBackEndAndOnlyOneTicketIsTheRequiredOne() {
		this.backEndTicketList = backEnd.getTickets();
		this.backEndTicketList.clear();
		vendor1 = new Vendor("V1", "V1", "V1");
		purchaser1 = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		locationName = "locationName";
		locationCapacity = 100;
		location = new Location(locationName, locationCapacity);
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18,10, 30);
		eventName = "eventName";
		event = new Event(eventName, startTime, endTime, vendor1, location, true);
		ticket1 = new Ticket(event, 100, 20);
		ticket2 = new Ticket(event, 100, 28);
		backEndTicketList.add(ticket1);
		backEndTicketList.add(ticket2);
		Transaction tTransaction = new Transaction(ticket2, purchaser1, vendor1);
		Ticket tResult = tTransaction.getTicket();
		assertEquals(ticket2, tResult);
		this.backEndTicketList.clear();
	}
	
	
	@Test
	public void testGetPurchaserWithNoUserInBackEnd() {
		this.backEndUserList = backEnd.getUsers();
		this.backEndUserList.clear();
		vendor1 = new Vendor("V1", "V1", "V1");
		purchaser1 = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		locationName = "locationName";
		locationCapacity = 100;
		location = new Location(locationName, locationCapacity);
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18,10, 30);
		eventName = "eventName";
		event = new Event(eventName, startTime, endTime, vendor1, location, true);
		ticket1 = new Ticket(event, 100, 20);
		Transaction tTransaction = new Transaction(ticket1, purchaser1, vendor1);
		User tResult = tTransaction.getPurchaser();
		assertEquals(null, tResult);
	}
	
	@Test
	public void testGetPurchaserWithTheSameUserInBackEnd() {
		this.backEndUserList = backEnd.getUsers();
		this.backEndUserList.clear();
		vendor1 = new Vendor("V1", "V1", "V1");
		purchaser1 = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		this.backEndUserList.add(purchaser1);
		locationName = "locationName";
		locationCapacity = 100;
		location = new Location(locationName, locationCapacity);
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18,10, 30);
		eventName = "eventName";
		event = new Event(eventName, startTime, endTime, vendor1, location, true);
		ticket1 = new Ticket(event, 100, 20);
		Transaction tTransaction = new Transaction(ticket1, purchaser1, vendor1);
		User tResult = tTransaction.getPurchaser();
		assertEquals(purchaser1, tResult);
		this.backEndUserList.clear();
	}
	
	@Test
	public void testGetPurchaserWithTwoUserInBackEndAndOnlyOneUserIsRequired() {
		this.backEndUserList = backEnd.getUsers();
		this.backEndUserList.clear();
		vendor1 = new Vendor("V1", "V1", "V1");
		purchaser1 = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		purchaser2 = new Member("userLoginId2", "userPassword2", "userName2", 22, "A1357900");
		this.backEndUserList.add(purchaser1);
		this.backEndUserList.add(purchaser2);
		locationName = "locationName";
		locationCapacity = 100;
		location = new Location(locationName, locationCapacity);
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18,10, 30);
		eventName = "eventName";
		event = new Event(eventName, startTime, endTime, vendor1, location, true);
		ticket1 = new Ticket(event, 100, 20);
		Transaction tTransaction = new Transaction(ticket1, purchaser2, vendor1);
		User tResult = tTransaction.getPurchaser();
		assertEquals(purchaser2, tResult);
		this.backEndUserList.clear();
	}
	
	
	@Test
	public void testGetVendorWithNoUserInBackEnd() {
		this.backEndVendorList = backEnd.getVendors();
		this.backEndVendorList.clear();
		vendor1 = new Vendor("V1", "V1", "V1");
		purchaser1 = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		locationName = "locationName";
		locationCapacity = 100;
		location = new Location(locationName, locationCapacity);
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18,10, 30);
		eventName = "eventName";
		event = new Event(eventName, startTime, endTime, vendor1, location, true);
		ticket1 = new Ticket(event, 100, 20);
		Transaction tTransaction = new Transaction(ticket1, purchaser1, vendor1);
		Vendor tResult = tTransaction.getVendor();
		assertEquals(null, tResult);
	}
	
	@Test
	public void testGetVendorWithTheSameVendorInBackEnd() {
		this.backEndVendorList = backEnd.getVendors();
		this.backEndVendorList.clear();
		vendor1 = new Vendor("V1", "V1", "V1");
		purchaser1 = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		backEndVendorList.add(vendor1);
		locationName = "locationName";
		locationCapacity = 100;
		location = new Location(locationName, locationCapacity);
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18,10, 30);
		eventName = "eventName";
		event = new Event(eventName, startTime, endTime, vendor1, location, true);
		ticket1 = new Ticket(event, 100, 20);
		Transaction tTransaction = new Transaction(ticket1, purchaser1, vendor1);
		Vendor tResult = tTransaction.getVendor();
		assertEquals(vendor1, tResult);
		this.backEndVendorList.clear();
	}
	
	@Test
	public void testGetVendorWithTwoVendorsInBackEndAndOnlyOneVendorIsTheRequiredOne() {
		this.backEndVendorList = backEnd.getVendors();
		this.backEndVendorList.clear();
		vendor1 = new Vendor("V1", "V1", "V1");
		vendor2 = new Vendor("V2", "V2", "V2");
		this.backEndVendorList.add(vendor1);
		this.backEndVendorList.add(vendor2);
		purchaser1 = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		backEndVendorList.add(vendor1);
		locationName = "locationName";
		locationCapacity = 100;
		location = new Location(locationName, locationCapacity);
		startTime = new Date(2018, 9, 18, 9, 30);
		endTime = new Date(2018, 9, 18,10, 30);
		eventName = "eventName";
		event = new Event(eventName, startTime, endTime, vendor1, location, true);
		ticket1 = new Ticket(event, 100, 20);
		Transaction tTransaction = new Transaction(ticket1, purchaser1, vendor2);
		Vendor tResult = tTransaction.getVendor();
		assertEquals(vendor2, tResult);
		this.backEndVendorList.clear();
	}
	
	

}
