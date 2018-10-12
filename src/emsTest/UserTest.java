package emsTest;

import static org.junit.Assert.*;
import org.junit.Test;

import ems.User;

public class UserTest {
	
	@Test
	public void validateHKIDTest_0() {
		String hkID = "A123456(3)";
		assertEquals(true, User.validateHKID(hkID));
	}
	
	@Test
	public void validateHKIDTest_1() {
		String hkID = "A123456(6)";
		assertEquals(false, User.validateHKID(hkID));
	}
	
	@Test
	public void validateHKIDTest_2() {
		String hkID = "AB987654(2)";
		assertEquals(true, User.validateHKID(hkID));
	}
	
	@Test
	public void validateHKIDTest_3() {
		String hkID = "AB987654(5)";
		assertEquals(false, User.validateHKID(hkID));
	}
	
	@Test
	public void validateHKIDTest_4() {
		String hkID = "ABCDEFG(A)";
		assertEquals(false, User.validateHKID(hkID));
	}
	
}
