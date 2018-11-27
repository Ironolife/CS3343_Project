package emsTest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import ems.BackEnd;
import ems.EMS;
import ems.Member;
import ems.User;
import ems.Vendor;

public class EMSTestCases {
	BackEnd backEnd = BackEnd.getInstance();
	ArrayList<Vendor> backEndVendorList;
	ArrayList<User> backEndUserList;

//	@Test
//	public void UITest() {
//		String[] inputs = {"2", "1"};
//		class EMSStub extends EMS {
//			private int inputCount = 0;
//			@Override
//			public String readInput() {
//				String result = inputs[inputCount];
//				System.out.println(result);
//				inputCount++;
//				return result;
//			}
//		}
//		EMSStub emsStub = new EMSStub();
//	}
//	@Test
//	public void testReadInput()
//	{
//		EMS testingEMS = new EMS();
//		String input = "This is an input.";
//		System.setIn(new ByteArrayInputStream(input.getBytes()));
//		String result = testingEMS.readInput();
//		assertEquals(input, result);
//	}

	Vendor vendor1;
	Vendor vendor2;
	Vendor vendorWeWillUse;
	User user1;
	User user2;
	User userWeWillUse;

	@Test
	public void testAccountManagementLogin() {

		class EMSStub extends EMS {
			int readInputCount = 0;

			public EMSStub() {

			}

			@Override
			public String readInput() {
				if (this.readInputCount == 0) {
					this.readInputCount++;
					return "1";
				} else if (this.readInputCount == 1) {
					this.readInputCount++;
					return "V3";
				} else if (this.readInputCount == 2) {
					this.readInputCount++;
					return "V4";
				} else if (this.readInputCount == 3) {
					this.readInputCount++;
					return "1";
				} else if (this.readInputCount == 4) {
					this.readInputCount++;
					return "V3";
				} else if (this.readInputCount == 5) {
					this.readInputCount++;
					return "V3";
				}
				return "11";

			}
		}
		vendor1 = new Vendor("V1", "V1", "V1");
		vendor2 = new Vendor("V2", "V2", "V2");
		vendorWeWillUse = new Vendor("V3", "V3", "V3");
		backEndVendorList = backEnd.getVendors();
		backEndVendorList.clear();
		this.backEndUserList = backEnd.getUsers();
		this.backEndUserList.clear();
		backEndVendorList.add(vendor1);
		backEndVendorList.add(vendor2);
		backEndVendorList.add(vendorWeWillUse);
		String input = "11";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		EMS.PrintHeader("- Event Management System -");
		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		System.out.println();
		System.out.println("- Login -");
		System.out.println();
		System.out.println("LoginId: ");
		System.out.println("Password: ");
		System.out.println();
		System.out.println("Invalid Password!");
		System.out.println();
		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		System.out.println();
		System.out.println("- Login -");
		System.out.println();
		System.out.println("LoginId: ");
		System.out.println("Password: ");
		System.out.println();
		System.out.println("- Welcome " + vendorWeWillUse.getName() + " -");
		System.out.println();
		System.out.println("Choose operations: ");
		System.out.println("1: Display Events");
		System.out.println("2: Search Events");
		System.out.println("3: My Events");
		System.out.println("4: Create Location");
		System.out.println("5: Create Event");
		System.out.println("6: Generate Tickets");
		System.out.println("7: Generate Coupon");
		System.out.println("8: Check-In");
		System.out.println("9: Check-Out");
		System.out.println("10: My Total Sales");
		System.out.println("11: Logout");
		System.out.println();
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));
		EMSStub tEMSStub = new EMSStub();
		assertEquals(outContent1.toString(), outContent2.toString());
	}

	@Test
	public void testAccountManagementUserLogin() {
		class BackEndStub extends BackEnd {
			public ArrayList<Vendor> getVendors() {
				ArrayList<Vendor> vendorList = new ArrayList<>();
				vendorList.add(vendor1);
				vendorList.add(vendor2);
				vendorList.add(vendorWeWillUse);
				return vendorList;
			}
		}

		class EMSStub extends EMS {
			int readInputCount = 0;

			public EMSStub() {

			}

			@Override
			public String readInput() {
				if (this.readInputCount == 0) {
					this.readInputCount++;
					return "1";
				} else if (this.readInputCount == 1) {
					this.readInputCount++;
					return "U3";
				} else if (this.readInputCount == 2) {
					this.readInputCount++;
					return "V4";
				} else if (this.readInputCount == 3) {
					this.readInputCount++;
					return "1";
				} else if (this.readInputCount == 4) {
					this.readInputCount++;
					return "U3";
				} else if (this.readInputCount == 5) {
					this.readInputCount++;
					return "U3";
				}
				return "8";

			}
		}
		user1 = new Member("U1", "U1", "U1", 20, "U1234567");
		user2 = new Member("U2", "U2", "U2", 20, "U1234567");
		userWeWillUse = new Member("U3", "U3", "U3", 20, "U1234567");
		backEndVendorList = backEnd.getVendors();
		backEndVendorList.clear();
		this.backEndUserList = backEnd.getUsers();
		this.backEndUserList.clear();
		backEndUserList.add(user1);
		backEndUserList.add(user2);
		backEndUserList.add(userWeWillUse);
		String input = "8";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		EMS.PrintHeader("- Event Management System -");
		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		System.out.println();
		System.out.println("- Login -");
		System.out.println();
		System.out.println("LoginId: ");
		System.out.println("Password: ");
		System.out.println();
		System.out.println("Invalid Password!");
		System.out.println();
		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		System.out.println();
		System.out.println("- Login -");
		System.out.println();
		System.out.println("LoginId: ");
		System.out.println("Password: ");
		EMS.PrintHeader("- Welcome " + ((Member)this.userWeWillUse).getName() + " -");
		System.out.println("Choose operations: ");
		System.out.println("1: Display Events");
		System.out.println("2: Search Events");
		System.out.println("3: My Upcoming Events");
		System.out.println("4: Purchase Ticket");
		System.out.println("5: Review Event");
		System.out.println("6: My Account");
		System.out.println("7: Purchase History");
		System.out.println("8: Logout");
		System.out.println();
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));
		EMSStub tEMSStub = new EMSStub();
		assertEquals(outContent1.toString(), outContent2.toString());
	}

	@Test
	public void testAccountManagementInvalidLogin() {

		class EMSStub extends EMS {
			int readInputCount = 0;

			public EMSStub() {

			}

			@Override
			public String readInput() {
				if (this.readInputCount == 0) {
					this.readInputCount++;
					return "1";
				} else if (this.readInputCount == 1) {
					this.readInputCount++;
					return "ABC";
				} else if (this.readInputCount == 2) {
					this.readInputCount++;
					return "ABC";
				} else if (this.readInputCount == 3) {
					this.readInputCount++;
					return "1";
				} else if (this.readInputCount == 4) {
					this.readInputCount++;
					return "U1";
				} 
					return "U1";
				
			

			}
		}
		user1 = new Member("U1", "U1", "U1", 20, "U1234567");
		backEndVendorList = backEnd.getVendors();
		backEndVendorList.clear();
		this.backEndUserList = backEnd.getUsers();
		this.backEndUserList.clear();
		backEndUserList.add(user1);

		String input = "8";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		EMS.PrintHeader("- Event Management System -");
		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		System.out.println();
		System.out.println("- Login -");
		System.out.println();
		System.out.println("LoginId: ");
		System.out.println("Password: ");
		EMS.PrintHeader("Invalid Login!");
		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		System.out.println();
		System.out.println("- Login -");
		System.out.println();
		System.out.println("LoginId: ");
		System.out.println("Password: ");
		EMS.PrintHeader("- Welcome " + ((Member)user1).getName() + " -");
		System.out.println("Choose operations: ");
		System.out.println("1: Display Events");
		System.out.println("2: Search Events");
		System.out.println("3: My Upcoming Events");
		System.out.println("4: Purchase Ticket");
		System.out.println("5: Review Event");
		System.out.println("6: My Account");
		System.out.println("7: Purchase History");
		System.out.println("8: Logout");
		System.out.println();
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));
		EMSStub tEMSStub = new EMSStub();
		assertEquals(outContent1.toString(), outContent2.toString());
	}

	@Test
	public void testUserRegister() {

		class EMSStub extends EMS {
			int readInputCount = 0;

			public EMSStub() {
				super();
			}

			@Override
			public String readInput() {
				if (this.readInputCount == 0) {
					this.readInputCount++;
					return "2";
				} else if (this.readInputCount == 1) {
					this.readInputCount++;
					return "3";
				} else if (this.readInputCount == 2) {
					this.readInputCount++;
					return "4";
				} else if (this.readInputCount == 3) {
					this.readInputCount++;
					return "1";
				} else if (this.readInputCount == 4) {
					this.readInputCount++;
					return "U1";
				} else if (this.readInputCount == 5) {
					this.readInputCount++;
					return "U2";
				} else if (this.readInputCount == 6) {
					this.readInputCount++;
					return "U4";
				} else if (this.readInputCount == 7) {
					this.readInputCount++;
					return "U4";
				} else if (this.readInputCount == 8) {
					this.readInputCount++;
					return "A1234567";
				} else if (this.readInputCount == 9) {
					this.readInputCount++;
					return "A1234567";
				} else if (this.readInputCount == 10) {
					this.readInputCount++;
					return "B1234567";
				} else if (this.readInputCount == 11) {
					this.readInputCount++;
					return "-50";
				} else if (this.readInputCount == 12) {
					this.readInputCount++;
					return "188";
				} else if (this.readInputCount == 13) {
					this.readInputCount++;
					return "ABCDE";
				} else if (this.readInputCount == 14) {
					this.readInputCount++;
					return "20";
				} else if(this.readInputCount == 15) {
					this.readInputCount++;
					return "3";
				} else if(this.readInputCount == 16) {
					this.readInputCount++;
					return "2";
				}  else if(this.readInputCount == 17) {
					this.readInputCount++;
					return "U4";
				} else if (this.readInputCount == 18) {
					this.readInputCount++;
					return "1";
				} else if (this.readInputCount == 19) {
					this.readInputCount++;
					return "U1";
				} else if (this.readInputCount == 20) {
					this.readInputCount++;
					return "U1";
				}
				return "8";
				

			}
		}
		user1 = new Member("U1", "U1", "U1", 20, "A1234567");
		user2 = new Member("U2", "U2", "U2", 20, "A1234567");
		userWeWillUse = new Member("U3", "U3", "U3", 20, "A1234567");
		backEndVendorList = backEnd.getVendors();
		backEndVendorList.clear();
		this.backEndUserList = backEnd.getUsers();
		this.backEndUserList.clear();
		backEndUserList.add(user1);
		backEndUserList.add(user2);
		backEndUserList.add(userWeWillUse);
		String input = "8";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		EMS.PrintHeader("- Event Management System -");
		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		EMS.PrintHeader("- Register -");
		System.out.println("Account Type: ");
		System.out.println("1: User");
		System.out.println("2: Vendor");
		EMS.PrintHeader("Invalid Account Type!");
		System.out.println("Account Type: ");
		System.out.println("1: User");
		System.out.println("2: Vendor");
		EMS.PrintHeader("Invalid Account Type!");
		System.out.println("Account Type: ");
		System.out.println("1: User");
		System.out.println("2: Vendor");
		System.out.println("LoginId: ");
		EMS.PrintHeader("Duplicate LoginId! Please try another one.");
		System.out.println("LoginId: ");
		EMS.PrintHeader("Duplicate LoginId! Please try another one.");
		System.out.println("LoginId: ");
		System.out.println("Password: ");
		System.out.println("HKID: ");
		EMS.PrintHeader("HKID already registered!");
		System.out.println("HKID: ");
		EMS.PrintHeader("HKID already registered!");
		System.out.println("HKID: ");
		System.out.println("Age: ");
		EMS.PrintHeader("Invalid Input!");
		System.out.println("Age: ");
		EMS.PrintHeader("Invalid Input!");
		System.out.println("Age: ");
		EMS.PrintHeader("Invalid Input!");
		System.out.println("Age: ");
		System.out.println("Please select an account type: ");
		System.out.println("1: Guest");
		System.out.println("2: Member");
		EMS.PrintHeader("Invalid Input");
		System.out.println("Please select an account type: ");
		System.out.println("1: Guest");
		System.out.println("2: Member");
		System.out.println("Name: ");
		EMS.PrintHeader("Member Created!");
		System.out.println("Choose operations: ");
		System.out.println("1: Login");
		System.out.println("2: Register");
		EMS.PrintHeader("- Login -");
		System.out.println("LoginId: ");
		System.out.println("Password: ");
		EMS.PrintHeader("- Welcome " + ((Member)user1).getName() + " -");
		System.out.println("Choose operations: ");
		System.out.println("1: Display Events");
		System.out.println("2: Search Events");
		System.out.println("3: My Upcoming Events");
		System.out.println("4: Purchase Ticket");
		System.out.println("5: Review Event");
		System.out.println("6: My Account");
		System.out.println("7: Purchase History");
		System.out.println("8: Logout");
		System.out.println();
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));
		EMSStub tEMSStub = new EMSStub();
		assertEquals(outContent1.toString(), outContent2.toString());
	}
}
