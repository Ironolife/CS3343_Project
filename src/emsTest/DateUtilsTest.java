package emsTest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.Test;

import ems.DateUtils;

public class DateUtilsTest {
	@Test
	public void parseDateWithValidDate() {
		String validDateString = "2012-12-12 09:18";
		Date tResult = DateUtils.parseDate(validDateString);
		Date expectedDateObject = new Date(111, 12, 12, 9, 18); // 1355303880 = he milliseconds since January 1, 1970,
																// 00:00:00
		// GMT. to 2012-12-12 09:18:00
		assertEquals("Wed Dec 12 09:18:00 CST 2012", tResult.toString());
	}

	@Test
	public void parseDateWithInvalidDate() {
		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		System.out.println("Invalid Date Format!");
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));
		String invalidDateString = "2012-12-38 09:18";
		Date tResult = DateUtils.parseDate(invalidDateString);
		assertEquals(outContent1.toString(), outContent2.toString());

	}
	@Test
	public void testValidPeriod() {
		String validStartTimeString = "2012-12-12 09:18";
		String validEndTimeString = "2012-12-12 10:18";
		Date startTime =  DateUtils.parseDate(validStartTimeString);
		Date endTime =  DateUtils.parseDate(validEndTimeString);
		boolean tResult = DateUtils.validatePeriod(startTime, endTime);
		assertEquals(true, tResult);
		
	}
	
	@Test 
	public void testInvalidPeriod() {
		String invalidStartTimeString = "2012-12-12 09:18";
		String invalidEndTimeString = "2012-12-12 08:18";
		Date startTime =  DateUtils.parseDate(invalidStartTimeString);
		Date endTime =  DateUtils.parseDate(invalidEndTimeString);
		boolean tResult = DateUtils.validatePeriod(startTime, endTime);
		assertEquals(false, tResult);
	}
	
	@Test
	public void testIsOverlappedPeriodTT() {
		String startTimeStringA = "2012-12-12 09:18";
		String endTimeStringA = "2012-12-12 20:20";
		String startTimeStringB = "2012-12-12 18:30";
		String endTimeStringB =  "2012-12-13 20:20";
		Date startTimeA = DateUtils.parseDate(startTimeStringA);
		Date endTimeA = DateUtils.parseDate(endTimeStringA);
		Date startTimeB = DateUtils.parseDate(startTimeStringB);
		Date endTimeB = DateUtils.parseDate(endTimeStringB);
		boolean tResult  = DateUtils.isOverlappedPeriod(startTimeA, endTimeA, startTimeB, endTimeB);
		assertEquals(true, tResult);
		
		
				
	}
	
	@Test
	public void testIsOverlappedPeriodTF() {
		String startTimeStringA = "2012-12-12 09:18";
		String endTimeStringA = "2012-12-12 20:20";
		String startTimeStringB = "2012-12-12 23:30";
		String endTimeStringB =  "2012-12-13 20:20";
		Date startTimeA = DateUtils.parseDate(startTimeStringA);
		Date endTimeA = DateUtils.parseDate(endTimeStringA);
		Date startTimeB = DateUtils.parseDate(startTimeStringB);
		Date endTimeB = DateUtils.parseDate(endTimeStringB);
		boolean tResult  = DateUtils.isOverlappedPeriod(startTimeA, endTimeA, startTimeB, endTimeB);
		assertEquals(false, tResult);
		
		
				
	}
	
	@Test
	public void testIsOverlappedPeriodFT() {
		String startTimeStringA = "2012-12-12 09:18";
		String endTimeStringA = "2012-12-12 20:20";
		String startTimeStringB = "2012-12-12 18:30";
		String endTimeStringB =  "2012-12-12 08:18";
		Date startTimeA = DateUtils.parseDate(startTimeStringA);
		Date endTimeA = DateUtils.parseDate(endTimeStringA);
		Date startTimeB = DateUtils.parseDate(startTimeStringB);
		Date endTimeB = DateUtils.parseDate(endTimeStringB);
		boolean tResult  = DateUtils.isOverlappedPeriod(startTimeA, endTimeA, startTimeB, endTimeB);
		assertEquals(false, tResult);
		
		
				
	}

}
