package emsTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.Assert.*;


import ems.DateUtils;
import ems.Event;
import ems.Location;

public class LocationTest {
	
	
	@Test 
	public void testAddEventToEventList() {
		Date testingStartTime = DateUtils.parseDate("2018-12-11 19:00");
		Date testingEndTime = DateUtils.parseDate("2018-12-11 20:00");
		Location testingLocation = new Location("testingLocation", 100);
		Event testingEvent = new Event("Testing Event", testingStartTime, testingEndTime, testingLocation, true);
		testingLocation.addEvent(testingEvent);
		Event eventResult = testingLocation.getEvents().get(0);
		assertEquals(testingEvent.getName(), eventResult.getName());
	}

}
