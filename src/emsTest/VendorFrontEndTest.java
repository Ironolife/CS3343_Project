package emsTest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

import ems.BackEnd;
import ems.Coupon;
import ems.DateUtils;
import ems.EMS;
import ems.Event;
import ems.Location;
import ems.Review;
import ems.Ticket;
import ems.User;
import ems.Vendor;
import ems.VendorFrontEnd;

public class VendorFrontEndTest {

	class VendorFrontEndStub extends VendorFrontEnd {
		VendorFrontEndStub(Vendor vendor) {
			super(vendor);
		}

		
	}

	@Test
	public void testConstructor() {
		class VendorFrontEndStub extends VendorFrontEnd {

			public VendorFrontEndStub(Vendor vendor) {
				super(vendor);
			}

			@Override
			public void vendorOperations() {
				System.out.println("This is an Operation");
			}

		}

		Vendor vendor = new Vendor("loginId", "password", "name");
		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		System.out.println();
		System.out.println("- Welcome " + vendor.getName() + " -");
		System.out.println();
		System.out.println("This is an Operation");
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));
		VendorFrontEndStub vendorFrontEndStub = new VendorFrontEndStub(vendor);
		assertEquals(outContent1.toString(), outContent2.toString());

	}

	@Test
	public void testVendorOperation() {

		class VendorFrontEndStub extends VendorFrontEnd {
			
			int readCount = 0;

			protected VendorFrontEndStub(Vendor vendor) {
				super(vendor);
			}

			@Override
			public String readInput() {
				if (this.readCount == 10) {
					this.readCount = this.readCount + 1;
					return String.valueOf(this.readCount + 1);

				} else if (this.readCount == 11) {
					
					return String.valueOf(this.readCount);
				}
				this.readCount = this.readCount + 1;
				return String.valueOf(this.readCount);

			}
			
			public void displayEvents() {
				
				
				
			}
			
			public void searchEvents() {
				
				
			}

			
			protected void displayVendorEvents() {

			}

			protected void createLocation() {

			}

			protected void createEvent() {

			}

			protected void generateTickets() {

			}

			protected void generateCoupon() {

			}

			protected void displayTotalSales() {

			}

			protected void checkIn() {

			}

			protected void checkOut() {

			}

			public void displayEventInfo(Event event) {

			}
		}

		Vendor vendor = new Vendor("loginId", "password", "name");
		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		System.out.println();
		System.out.println("- Welcome " + vendor.getName() + " -");
		System.out.println();
		for (int j = 0; j < 11; j++) {
			
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
			
			if (j == 10) {
				System.out.println();
				System.out.println("Invalid Operation");
				System.out.println();
			}
		}
		
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

		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));

		VendorFrontEndStub vendorFrontEndStub = new VendorFrontEndStub(vendor);
		
		assertEquals(outContent1.toString(), outContent2.toString());

	}

}
