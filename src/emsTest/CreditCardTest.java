package emsTest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.CreditCard;
import ems.DateUtils;

public class CreditCardTest {
	
	@Before
	public void setUp() {
	}
	
	
	@Test
	public void validateSecurityCodeTest_01() {
		CreditCard card = new CreditCard("3234567890123456",9999,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(true,card.validateSecurityCode());
	}
	
	@Test
	public void validateSecurityCodeTest_02() {
		CreditCard card = new CreditCard("3234567890123456",999,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(false,card.validateSecurityCode());
	}
	
	@Test
	public void validateSecurityCodeTest_03() {
		CreditCard card = new CreditCard("3234567890123456",10001,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(false,card.validateSecurityCode());
	}
	
	
	@Test
	public void validateSecurityCodeTest_04() {
		CreditCard card = new CreditCard("4234567890123456",999,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(true,card.validateSecurityCode());
	}
	
	@Test
	public void validateSecurityCodeTest_05() {
		CreditCard card = new CreditCard("4234567890123456",1000,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(false,card.validateSecurityCode());
	}
	
	@Test
	public void validateSecurityCodeTest_06() {
		CreditCard card = new CreditCard("5234567890123456",999,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(true,card.validateSecurityCode());
	}
	
	@Test
	public void validateSecurityCodeTest_07() {
		CreditCard card = new CreditCard("5234567890123456",1000,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(false,card.validateSecurityCode());
	}

	@Test
	public void validateSecurityCodeTest_08() {
		CreditCard card = new CreditCard("5234567890123456",10,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(false,card.validateSecurityCode());
	}
	
	@Test
	public void validateSecurityCodeTest_09() {
		CreditCard card = new CreditCard("",10,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(false,card.validateSecurityCode());
	}
	
	@Test
	public void validateTest_01() {
		CreditCard card = new CreditCard("5234567890123456",996,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(true,card.validate());
	}
	
	@Test
	public void validateTest_02() {
		CreditCard card = new CreditCard("",996,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(false,card.validate());
	}
	
	@Test
	public void validateTest_03() {
		CreditCard card = new CreditCard("abc",996,DateUtils.parseDate("2012-12-30 09:18"));
		assertEquals(false,card.validate());
	}
	
	@Test
	public void validateTest_04() {
		CreditCard card = new CreditCard("523456789012345",996,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(false,card.validate());
	}
	
	@Test
	public void validateTest_05() {
		CreditCard card = new CreditCard("5234567890123456",100000,DateUtils.parseDate("2021-12-30 09:18"));
		assertEquals(false,card.validate());
	}
	
	@Test
	public void validateTest_06() {
		CreditCard card = new CreditCard("5234567890123456",996,DateUtils.parseDate("2012-12-30 09:18"));
		assertEquals(false,card.validate());
	}
	
	@Test
	public void getCardNumberTest() {
		CreditCard card = new CreditCard("5234567890123456",996,DateUtils.parseDate("2012-12-30 09:18"));
		assertEquals("5234567890123456",card.getCardNumber());
	}
	
	@Test
	public void getSecurityCodeTest() {
		CreditCard card = new CreditCard("5234567890123456",996,DateUtils.parseDate("2012-12-30 09:18"));
		assertEquals(996,card.getSecurityCode());
	}
	
	@After
	public void tearDown() {
		
	}
}
