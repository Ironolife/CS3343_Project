package emsTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.Guest;


public class GuestTest {
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void getDiscountTest() {
		Guest guest = new Guest("loginId", "password", 20, "Y957311(1)");
		double errorMargin = 0.0001;
		assertEquals(1, guest.getDiscount(), errorMargin);
	}
}
;