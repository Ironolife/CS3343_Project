package emsTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import ems.Log;

public class LogTest {
	String message;
	
	@Test
	public void testGetDate() {
		this.message = "message";
		Date expectedDate =  new Date();
		String expectedDateString = expectedDate.toLocaleString().substring(0, 11);
		Log tLog = new Log(this.message);
		String tResult = tLog.getDate().substring(0, 11);
		assertEquals(expectedDateString, tResult);
	}
	
	
	@Test
	public void testGetMessage() {
		this.message = "message";
		Log tLog = new Log(this.message);
		String tResult = tLog.getMessage();
		assertEquals(this.message, tResult);
	}

}
