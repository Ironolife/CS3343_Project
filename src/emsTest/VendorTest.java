package emsTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ems.BackEnd;
import ems.Event;
import ems.Location;
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
}
