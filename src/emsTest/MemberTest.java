package emsTest;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;
import ems.Coupon;
import ems.Member;

public class MemberTest {
	@Test
	public void testAddBalance() {
		Member testMember = new Member("testId", "password", "name", 20, "hkid");
		testMember.addBalance(500);
		assertEquals(testMember.getBalance(), 500, 0.01);
	
	}
	
	//Stub required
	@Test
	public void testSubtractBalanceSuccess() {
		Member testMember = new Member("testId", "password", "name", 20, "hkid");
		testMember.addBalance(500);
		boolean testSuccess = testMember.substractBalance(300);
		assertTrue(testSuccess);
	}
	
	@Test
	public void testSubtractBalanceFail() {
		Member testMember = new Member("testId", "password", "name", 20, "hkid");
		boolean testFail = testMember.substractBalance(300);
		assertFalse(testFail);
	}
	
}