package emsTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.Coupon;
import ems.DateUtils;
import ems.Event;
import ems.Location;
import ems.Vendor;


public class CouponTest {
	Vendor vendor;
	Location location;
	Event event;
	
	@Before
	public void setUp() {
		vendor = new Vendor("loginId", "password", "name");
		location = new Location("HK", 20);
		event = new Event("name", DateUtils.parseDate("2012-12-10 09:18"), DateUtils.parseDate("2012-12-20 09:18"),
				vendor, location, true
				);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void getDiscountedAmountTest_01() {
		Coupon coupon = new Coupon("code", event, 0, 10.6, DateUtils.parseDate("2012-12-10 09:18"));
		double errorMargin = 0.0001;
		assertEquals(89.9, coupon.getDiscountedAmount(100.5), errorMargin);
	}
	
	@Test
	public void getDiscountedAmountTest_02() {
		Coupon coupon = new Coupon("code", event, 0, 45.6, DateUtils.parseDate("2012-12-10 09:18"));
		double errorMargin = 0.0001;
		assertEquals(954.4, coupon.getDiscountedAmount(1000), errorMargin);
	}

	@Test
	public void getDiscountedAmountTest_03() {
		Coupon coupon = new Coupon("code", event, 1, 0.34, DateUtils.parseDate("2012-12-10 09:18"));
		double errorMargin = 0.0001;
		assertEquals(514.8, coupon.getDiscountedAmount(780), errorMargin);
	}
	
	@Test
	public void getDiscountedAmountTest_04() {
		Coupon coupon = new Coupon("code", event, 2, 30, DateUtils.parseDate("2012-12-10 09:18"));
		double errorMargin = 0.0001;
		assertEquals(0, coupon.getDiscountedAmount(100), errorMargin);
	}
	
	@Test
	public void getDiscountedAmountTest_05() {
		Coupon coupon = new Coupon("code", event, 3, 30, DateUtils.parseDate("2012-12-10 09:18"));
		double errorMargin = 0.0001;
		assertEquals(-1, coupon.getDiscountedAmount(100), errorMargin);
	}
	
	@Test
	public void getIdTest() {
		Coupon coupon = new Coupon("code", event, 3, 30, DateUtils.parseDate("2012-12-10 09:18"));
		assertEquals(coupon.getId(), coupon.getId());
	}
	 
	@Test
	public void getCodeTest() {
		Coupon coupon = new Coupon("code", event, 3, 30, DateUtils.parseDate("2012-12-10 09:18"));
		assertEquals("code", coupon.getCode());
	}
	
	@Test
	public void getEventTest() {
		Coupon coupon = new Coupon("code", event, 3, 30, DateUtils.parseDate("2012-12-10 09:18"));
		assertEquals(null, coupon.getEvent());
	}
	
	@Test
	public void getEventTest_01() {
		//Not null result
		Coupon coupon = new Coupon("code", event, 3, 30, DateUtils.parseDate("2012-12-10 09:18"));
		assertEquals(null, coupon.getEvent());
	}
	 
	@Test
	public void getDiscountTypeTest() {
		Coupon coupon = new Coupon("code", event, 2, 30, DateUtils.parseDate("2012-12-10 09:18"));
		assertEquals(2, coupon.getDiscountType());
	}
	 
	@Test
	public void getDiscountTest() {
		Coupon coupon = new Coupon("code", event, 2, 30, DateUtils.parseDate("2012-12-10 09:18"));
		double errorMargin = 0.00001;
		assertEquals(30, coupon.getDiscount(), errorMargin);
	}
    
	@Test
	public void getExpiryDateTest() {
		Coupon coupon = new Coupon("code", event, 2, 30, DateUtils.parseDate("2012-12-10 09:18"));
		assertEquals(DateUtils.parseDate("2012-12-10 09:18"), coupon.getExpiryDate());
	}
}
;