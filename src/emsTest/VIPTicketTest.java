package emsTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ems.DateUtils;
import ems.Event;
import ems.Location;
import ems.VIPTicket;
import ems.Vendor;

public class VIPTicketTest {
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void getPriceTest_01() {
		Vendor vendor = new Vendor("loginId", "password", "name");
		Location location = new Location("HK", 20);
		Event event = new Event("name", DateUtils.parseDate("2012-12-10 09:18"), DateUtils.parseDate("2012-12-20 09:18"),
				vendor, location, true
				);
		VIPTicket ticket = new VIPTicket(event, 20, 50, 1.5);
		double errorMargin = 0.0001;
		assertEquals(30, ticket.getPrice(), errorMargin);
	}
	
	
	

}
;