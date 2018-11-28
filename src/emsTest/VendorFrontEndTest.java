package emsTest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import org.junit.Test;

import ems.BackEnd;
import ems.Coupon;
import ems.DateUtils;
import ems.EMS;
import ems.Event;
import ems.Location;
import ems.Member;
import ems.Ticket;
import ems.User;
import ems.Vendor;
import ems.VendorFrontEnd;

public class VendorFrontEndTest {
	
	Member purchaser1;
	String locationNameMaterial;
	int locationCapacityMaterial;
	Location locationMaterial;
	Date startTimeMaterial;
	Date endTimeMaterial;
	String eventNameMaterial;
	Event eventMaterial;
	Event eventWeWantToUseForCheckIn ;
	Event eventStartTimeLaterThanCurrentTimeMaterial;
	Event eventStartTimeLaterThanCurrentTimeMaterial2;
	Event eventEndTimeEarlierThanCurrentTimeMaterial;
	

	

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

	@Test
	public void testDisplayVendorEvents() {
		class VendorStub extends Vendor {

			public VendorStub(String loginId, String password, String name) {
				super(loginId, password, name);
				// TODO Auto-generated constructor stub
			}

			@Override
			public ArrayList<Event> getEvents() {
				ArrayList<Event> eventList = new ArrayList<Event>();
				eventList.add(eventMaterial);
				return eventList;
			}

		}

		class VendorFrontEndStub extends VendorFrontEnd {

			public VendorFrontEndStub(Vendor vendor) {
				super(vendor);
			}

			@Override
			public void vendorOperations() {

			}

			

			protected void printEventList(ArrayList<Event> events) {

				System.out.println(1 + ": " + events.get(0).getName());

			}

			protected int listSelection(int size, String text) {

				return 1;

			}

			public void displayEventInfo(Event event) {

			}

			protected void displayVendorEvents() {

				super.displayVendorEvents();

			}

		}

		VendorStub vendorStub = new VendorStub("loginId", "password", "name");

		locationNameMaterial = "locationName";
		locationCapacityMaterial = 100;
		locationMaterial = new Location(locationNameMaterial, locationCapacityMaterial);
		startTimeMaterial = new Date(2018, 9, 18, 9, 30);
		endTimeMaterial = new Date(2018, 9, 18, 10, 30);
		eventNameMaterial = "eventName";
		eventMaterial = new Event(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
				true);
		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		System.out.println();
		System.out.println("- Welcome " + vendorStub.getName() + " -");
		System.out.println();
		System.out.println();
		System.out.println("- My Events -");
		System.out.println();
		System.out.println(1 + ": " + eventMaterial.getName());
		System.out.println();
		System.out.println("----------");
		System.out.println("Sales: " + this.eventMaterial.getSales());
		System.out.println();
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));
		VendorFrontEndStub vendorFrontEndStub = new VendorFrontEndStub(vendorStub);
		vendorFrontEndStub.displayVendorEvents();
		assertEquals(outContent1.toString(), outContent2.toString());

	}

	@Test
	public void testCreateLocation() {
		
		class VendorStub extends Vendor {

			public VendorStub(String loginId, String password, String name) {
				super(loginId, password, name);
				// TODO Auto-generated constructor stub
			}

			

		}

		class BackEndStub extends BackEnd {

			int backEndDuplicatioLocationNameLoopCount = 0;

			

			@Override
			public boolean isDuplicateLocationName(String name) {

				if (this.backEndDuplicatioLocationNameLoopCount == 0) {
					this.backEndDuplicatioLocationNameLoopCount++;
					return true;
				} else {
					return false;
				}
			}

			@Override
			public void createNewLocation(Location location) {

			}
		}

		class VendorFrontEndStub extends VendorFrontEnd {
			int readInputCount = 0;

			public VendorFrontEndStub(Vendor vendor) {
				super(vendor);
			}

			@Override
			public void vendorOperations() {

			}

			
			public void setBackEnd(BackEnd backEnd) {
				this.backEnd = backEnd;
			}

			@Override
			public String readInput() {
				return "locationName";

			}

			@Override
			protected int readIntInput(String inputField) {

				return 30;

			}

			@Override
			protected void createLocation() {

				super.createLocation();

			}

		}

		BackEndStub backEndStub = new BackEndStub();
		VendorStub vendorStub = new VendorStub("loginId", "password", "name");
		VendorFrontEndStub tVendorFrontEndtub = new VendorFrontEndStub(vendorStub);
		tVendorFrontEndtub.setBackEnd(backEndStub);
		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		System.out.println();
		System.out.println("- Create Location -");
		System.out.println();
		System.out.println("Name: ");
		System.out.println();
		System.out.println("Name already exists! Please try another one.");
		System.out.println();
		System.out.println("Name: ");
		System.out.println();
		System.out.println("Location Created!");
		System.out.println();
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));
		tVendorFrontEndtub.createLocation();
		assertEquals(outContent1.toString(), outContent2.toString());
	}

	@Test
	public void testCreateEvent() {
		eventNameMaterial = "eventName";
		startTimeMaterial = DateUtils.parseDate("2018-10-18 09:30");
		endTimeMaterial = DateUtils.parseDate("2018-10-18 10:30");
		
		class VendorStub extends Vendor {

			public VendorStub(String loginId, String password, String name) {
				super(loginId, password, name);
				// TODO Auto-generated constructor stub
			}

			

		}

		class LocationStub extends Location {

			public LocationStub(String name, int capacity) {
				super(name, capacity);
				// TODO Auto-generated constructor stub
			}

			@Override
			public ArrayList<Event> getEvents() {
				ArrayList<Event> eventList = new ArrayList<>();
				eventList.add(eventMaterial);
				return eventList;
			}

		}

		locationNameMaterial = "locationName";
		locationCapacityMaterial = 100;
		LocationStub locationStub = new LocationStub(locationNameMaterial, locationCapacityMaterial);
		class BackEndStub extends BackEnd {

			@Override
			public ArrayList<Location> getLocations() {
				ArrayList<Location> locationList = new ArrayList<>();
				locationList.add(locationStub);
				return locationList;
			}

		}

		class VendorFrontEndStub extends VendorFrontEnd {
			int readInputCount = 0;

			public VendorFrontEndStub(Vendor vendor) {
				super(vendor);
			}

			@Override
			public void vendorOperations() {

			}

			public void setBackEnd(BackEnd backEnd) {
				this.backEnd = backEnd;
			}

			@Override
			public String readInput() {
				if (this.readInputCount == 0) {
					this.readInputCount++;
					return eventNameMaterial;
				} else if (this.readInputCount == 1) {
					this.readInputCount++;
					return "2018-10-18 09:30"; // wrong time pair start
				} else if (this.readInputCount == 2) {
					this.readInputCount++;
					return "2018-10-18 08:30"; // wrong time pair end
				} else if (this.readInputCount == 3) { // time overlap
					this.readInputCount++;
					return "2018-10-18 09:30";
				} else if (this.readInputCount == 4) { // time overlap
					this.readInputCount++;
					return "2018-10-18 10:30";
				} else if (this.readInputCount == 5) {
					this.readInputCount++;
					return "2018-10-18 11:30";
				} else if (this.readInputCount == 6) {
					this.readInputCount++;
					return "2018-10-18 13:30";
				} else if (this.readInputCount == 7) { // wrong mature input
					this.readInputCount++;
					return "A";
				} else if (this.readInputCount == 8) { // correct mature input
					this.readInputCount++;
					return "N";
				}
				this.readInputCount++;
				return "A,B,C"; // tag

			}

			

			@Override
			protected void printLocationList(ArrayList<Location> locations) {

			}

			@Override
			protected int listSelection(int size, String text) {

				return 1;

			}

			protected void createEvent() {

				super.createEvent();

			}

		}

		BackEndStub backEndStub = new BackEndStub();
		VendorStub vendorStub = new VendorStub("loginId", "password", "name");
		VendorFrontEndStub tVendorFrontEndtub = new VendorFrontEndStub(vendorStub);
		tVendorFrontEndtub.setBackEnd(backEndStub);

		eventMaterial = new Event(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationStub,
				true);

		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		//expected result

		System.out.println();
		System.out.println("- Create Event -");
		System.out.println();
		System.out.println("Name: ");
		System.out.println("Start Time (YYYY-MM-DD HH:MM): "); // wrong time pair
		System.out.println("End Time (YYYY-MM-DD HH:MM): ");
		System.out.println();
		System.out.println("Invalid Time Period!");
		System.out.println();
		System.out.println("Start Time (YYYY-MM-DD HH:MM): "); // overlapped time slot
		System.out.println("End Time (YYYY-MM-DD HH:MM): ");
		System.out.println();
		System.out.println("Location Time Slot Already Taken!");
		System.out.println();
		System.out.println("Start Time (YYYY-MM-DD HH:MM): "); // correct time
		System.out.println("End Time (YYYY-MM-DD HH:MM): ");
		System.out.println("Is Event age-restricted? (Y/N): "); // wrong words
		System.out.println();
		System.out.println("Invalid Input");
		System.out.println();
		System.out.println("Is Event age-restricted? (Y/N): ");
		System.out.println("Add Event tags (separate with ,): ");
		System.out.println();
		System.out.println("Event Created!");
		System.out.println();
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));
		tVendorFrontEndtub.createEvent();
		assertEquals(outContent1.toString(), outContent2.toString());
	}
	
	
	@Test 
	public void testGenerateCoupon4() {
		class VendorStub extends Vendor {

			public VendorStub(String loginId, String password, String name) {
				super(loginId, password, name);
				// TODO Auto-generated constructor stub
			}

			@Override
			public ArrayList<Event> getEvents() {
				ArrayList<Event> eventList = new ArrayList<Event>();
				eventList.add(eventMaterial);
				return eventList;
			}

		}
		
		class BackEndStub extends BackEnd {
			int duplicateCouponCodeCount = 0;
			
			
			@Override
			public boolean isDuplicateCouponCode(String code) {
				if(this.duplicateCouponCodeCount == 0) {
					this.duplicateCouponCodeCount++;
					return true;
				}
				return false;
			}
			
			
		}
		
		class VendorFrontEndStub extends VendorFrontEnd {
			int readIntInputCount = 0;
			int readInputCount = 0;

			public VendorFrontEndStub(Vendor vendor) {
				super(vendor);
			}

			@Override
			public void vendorOperations() {

			}

			public void setBackEnd(BackEnd backEnd) {
				this.backEnd = backEnd;
			}
			
			@Override 
			public String readInput() {
				if(this.readInputCount == 0) {
					this.readInputCount++;
					return "0";
				} else if(this.readInputCount == 1) {
					this.readInputCount++;
					return "0";
				}
				
					this.readInputCount++;
					return "123456";
				
			}
			
			
			
			@Override
			protected void printEventList(ArrayList<Event> events) {
				
				
			}
			
			@Override
			protected int listSelection(int size, String text) {
				return 0;
				
			}
			
			@Override
			protected void generateCoupon() {super.generateCoupon();}
		}
			
			VendorStub vendorStub = new VendorStub("loginId", "password", "name");
			locationNameMaterial = "locationName";
			locationCapacityMaterial = 100;
			locationMaterial = new Location(locationNameMaterial, locationCapacityMaterial);
			eventNameMaterial = "eventName";
			startTimeMaterial = DateUtils.parseDate("2018-10-18 09:30");
			endTimeMaterial = DateUtils.parseDate("2018-10-18 10:30");
			eventMaterial = new Event(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
					true);
			BackEndStub backEndStub = new BackEndStub();
			VendorFrontEndStub tVendorFrontEndStub = new VendorFrontEndStub(vendorStub);
			tVendorFrontEndStub.setBackEnd(backEndStub);
			ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent1));
			System.out.println();
			System.out.println("- Generate Coupon -");
			System.out.println();
			System.out.println("Code: ");
			System.out.println();
			System.out.println("Code already exists! Please try another one.");
			System.out.println();
			System.out.println("Code: ");
			System.out.println();
			System.out.println("Code 0 is reserved! Please try another one.");
			System.out.println();
			System.out.println("Code: ");
			
			ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent2));
			tVendorFrontEndStub.generateCoupon();
			assertEquals(outContent1.toString(), outContent2.toString());

		}

	@Test
	public void testGenertaeTicket() {
		
		class VendorStub extends Vendor {

			public VendorStub(String loginId, String password, String name) {
				super(loginId, password, name);
				// TODO Auto-generated constructor stub
			}

			@Override
			public ArrayList<Event> getEvents() {
				ArrayList<Event> eventList = new ArrayList<Event>();
				eventList.add(eventMaterial);
				return eventList;
			}

		}
		
		class BackEndStub extends BackEnd {
			@Override
			public void createNewTickets(ArrayList<Ticket> tickets) {
				
			}
		}
		
		class EventStub extends Event {
			int generateTicketCount = 0;

			public EventStub(String name, Date startTime, Date endTime, Vendor vendor, Location location,
					boolean isMature) {
				super(name, startTime, endTime, vendor, location, isMature);
				// TODO Auto-generated constructor stub
			}
			
			@Override
			public ArrayList<Ticket> generateTickets(double price, double vipPriceMultiplier, int normalSize, int vipSize) {
				if(this.generateTicketCount == 0) {
					this.generateTicketCount++;
					return null;
				}
				ArrayList<Ticket> tickets = new ArrayList<>();
				return tickets;
			}
			
		}
		
		
		class VendorFrontEndStub extends VendorFrontEnd {
			int readIntInputCount = 0;

			public VendorFrontEndStub(Vendor vendor) {
				super(vendor);
			}

			@Override
			public void vendorOperations() {

			}

			public void setBackEnd(BackEnd backEnd) {
				this.backEnd = backEnd;
			}

			

			@Override
			protected int readIntInput(String inputField) {

				if(this.readIntInputCount == 0) {
					this.readIntInputCount++;
					return 100;
				} else if(this.readIntInputCount == 1) {
					this.readIntInputCount++;
					return 100;
				} else if (this.readIntInputCount == 2) {
					this.readIntInputCount++;
					return 80;
				}
				return 20;

			}
			
			@Override
			protected void printEventList(ArrayList<Event> events) {
				
				
			}

			@Override
			protected int listSelection(int size, String text) {

				return 1;

			}
			
			
			
			@Override
			protected double readDoubleInput(String inputField) {
				
				if(inputField.equals("Price")) {
					return 100;
				} 
				return 12;
				
			}
			
			@Override
			protected void generateTickets() {super.generateTickets();}
			
		}
		VendorStub vendorStub = new VendorStub("loginId", "password", "name");
		locationNameMaterial = "locationName";
		locationCapacityMaterial = 100;
		locationMaterial = new Location(locationNameMaterial, locationCapacityMaterial);
		eventNameMaterial = "eventName";
		startTimeMaterial = DateUtils.parseDate("2018-10-18 09:30");
		endTimeMaterial = DateUtils.parseDate("2018-10-18 10:30");
		eventMaterial = new EventStub(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
				true);
		VendorFrontEndStub tVendorFrontStub = new VendorFrontEndStub(vendorStub);
		BackEndStub backEndStub = new BackEndStub();
		tVendorFrontStub.setBackEnd(backEndStub);
		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		//expected result 
		System.out.println();
		System.out.println("- Generate Tickets -");
		System.out.println();
		System.out.println();
		System.out.println("Ticket count larger than location capacity!");
		System.out.println();
		System.out.println();
		System.out.println(0 + " Tickets Generated!");
		System.out.println();
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));
		tVendorFrontStub.generateTickets();
		assertEquals(outContent1.toString(), outContent2.toString());
	}
	
	
	@Test
	public void testGenertaeTicket2() {
		
		class VendorStub extends Vendor {

			public VendorStub(String loginId, String password, String name) {
				super(loginId, password, name);
				// TODO Auto-generated constructor stub
			}

			@Override
			public ArrayList<Event> getEvents() {
				ArrayList<Event> eventList = new ArrayList<Event>();
				eventList.add(eventMaterial);
				return eventList;
			}

		}
		
		class BackEndStub extends BackEnd {
			
		}
		
		class EventStub extends Event {
			int generateTicketCount = 0;

			public EventStub(String name, Date startTime, Date endTime, Vendor vendor, Location location,
					boolean isMature) {
				super(name, startTime, endTime, vendor, location, isMature);
				// TODO Auto-generated constructor stub
			}
			
			
		}
		
		
		class VendorFrontEndStub extends VendorFrontEnd {
			int readIntInputCount = 0;

			public VendorFrontEndStub(Vendor vendor) {
				super(vendor);
			}

			@Override
			public void vendorOperations() {

			}

			public void setBackEnd(BackEnd backEnd) {
				this.backEnd = backEnd;
			}

			

			
			
			@Override
			protected void printEventList(ArrayList<Event> events) {
				
				
			}

			@Override
			protected int listSelection(int size, String text) {

				return 0;

			}
			
			
			
			
			
			@Override
			protected void generateTickets() {super.generateTickets();}
			
		}
		VendorStub vendorStub = new VendorStub("loginId", "password", "name");
		locationNameMaterial = "locationName";
		locationCapacityMaterial = 100;
		locationMaterial = new Location(locationNameMaterial, locationCapacityMaterial);
		eventNameMaterial = "eventName";
		startTimeMaterial = DateUtils.parseDate("2018-10-18 09:30");
		endTimeMaterial = DateUtils.parseDate("2018-10-18 10:30");
		eventMaterial = new EventStub(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
				true);
		VendorFrontEndStub tVendorFrontStub = new VendorFrontEndStub(vendorStub);
		BackEndStub backEndStub = new BackEndStub();
		tVendorFrontStub.setBackEnd(backEndStub);
		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		//expected result 
		System.out.println();
		System.out.println("- Generate Tickets -");
		System.out.println();
		
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));
		tVendorFrontStub.generateTickets();
		assertEquals(outContent1.toString(), outContent2.toString());
	}
	
	@Test 
	public void testGenerateCoupon() {
		class VendorStub extends Vendor {

			public VendorStub(String loginId, String password, String name) {
				super(loginId, password, name);
				// TODO Auto-generated constructor stub
			}

			@Override
			public ArrayList<Event> getEvents() {
				ArrayList<Event> eventList = new ArrayList<Event>();
				eventList.add(eventMaterial);
				return eventList;
			}

		}
		
		class BackEndStub extends BackEnd {
			int duplicateCouponCodeCount = 0;
			
			
			@Override
			public boolean isDuplicateCouponCode(String code) {
				if(this.duplicateCouponCodeCount == 0) {
					this.duplicateCouponCodeCount++;
					return true;
				}
				return false;
			}
			
			@Override
			public void createNewCoupon(Coupon coupon) {
				
			}
		}
		
		class VendorFrontEndStub extends VendorFrontEnd {
			int readIntInputCount = 0;
			int readInputCount = 0;

			public VendorFrontEndStub(Vendor vendor) {
				super(vendor);
			}

			@Override
			public void vendorOperations() {

			}

			public void setBackEnd(BackEnd backEnd) {
				this.backEnd = backEnd;
			}
			
			@Override 
			public String readInput() {
				if(this.readInputCount == 0) {
					this.readInputCount++;
					return "0";
				} else if(this.readInputCount == 1) {
					this.readInputCount++;
					return "0";
				}
				else if(this.readInputCount == 2) {
					this.readInputCount++;
					return "123456";
				} else if(this.readInputCount == 3) {
					this.readInputCount++;
					return "ABCD";
				} else if(this.readInputCount == 4) {
					this.readInputCount++;
					return "-10";
				} else if (this.readInputCount == 5) {
					this.readInputCount++;
					return "3";  // will be changed for branch testing
				} else if(this.readInputCount == 6) {
					this.readInputCount++;
					return "2018-10-18 09:00";
					
				} 

				return "2018-10-20 09:00";
			}
			
			
			
			@Override
			protected void printEventList(ArrayList<Event> events) {
				
				
			}
			
			@Override
			protected int listSelection(int size, String text) {
				return 1;
				
			}
			
			@Override
			protected void generateCoupon() {super.generateCoupon();}
		}
			
			VendorStub vendorStub = new VendorStub("loginId", "password", "name");
			locationNameMaterial = "locationName";
			locationCapacityMaterial = 100;
			locationMaterial = new Location(locationNameMaterial, locationCapacityMaterial);
			eventNameMaterial = "eventName";
			startTimeMaterial = DateUtils.parseDate("2018-10-18 09:30");
			endTimeMaterial = DateUtils.parseDate("2018-10-18 10:30");
			eventMaterial = new Event(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
					true);
			BackEndStub backEndStub = new BackEndStub();
			VendorFrontEndStub tVendorFrontEndStub = new VendorFrontEndStub(vendorStub);
			tVendorFrontEndStub.setBackEnd(backEndStub);
			ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent1));
			System.out.println();
			System.out.println("- Generate Coupon -");
			System.out.println();
			System.out.println("Code: ");
			System.out.println();
			System.out.println("Code already exists! Please try another one.");
			System.out.println();
			System.out.println("Code: ");
			System.out.println();
			System.out.println("Code 0 is reserved! Please try another one.");
			System.out.println();
			System.out.println("Code: ");
			System.out.println("Please select discount type: ");
			System.out.println("1: Flat");
			System.out.println("2: Percentage off");
			System.out.println("3: Free Purchase");
			System.out.println();
			System.out.println("Invalid Number!");//"ABCD"
			System.out.println();
			System.out.println("Please select discount type: ");
			System.out.println("1: Flat");
			System.out.println("2: Percentage off");
			System.out.println("3: Free Purchase");
			System.out.println();
			System.out.println("Invalid Number!");//-10
			System.out.println();
			System.out.println("Please select discount type: ");
			System.out.println("1: Flat");
			System.out.println("2: Percentage off");
			System.out.println("3: Free Purchase"); //correct
			System.out.println("End Time (YYYY-MM-DD HH:MM): ");
			System.out.println();
			System.out.println("Expiry date cannot be earlier than event!");
			System.out.println();
			System.out.println("End Time (YYYY-MM-DD HH:MM): ");
			System.out.println();
			System.out.println("Coupon Generated!");
			System.out.println();
			ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent2));
			tVendorFrontEndStub.generateCoupon();
			assertEquals(outContent1.toString(), outContent2.toString());

		}
		// another test case for generate coupon will be written
	
	@Test 
	public void testGenerateCoupon2() {
		class VendorStub extends Vendor {

			public VendorStub(String loginId, String password, String name) {
				super(loginId, password, name);
				// TODO Auto-generated constructor stub
			}

			@Override
			public ArrayList<Event> getEvents() {
				ArrayList<Event> eventList = new ArrayList<Event>();
				eventList.add(eventMaterial);
				return eventList;
			}

		}
		
		class BackEndStub extends BackEnd {
			int duplicateCouponCodeCount = 0;
			
			
			@Override
			public boolean isDuplicateCouponCode(String code) {
				if(this.duplicateCouponCodeCount == 0) {
					this.duplicateCouponCodeCount++;
					return true;
				}
				return false;
			}
			
			@Override
			public void createNewCoupon(Coupon coupon) {
				
			}
		}
		
		class VendorFrontEndStub extends VendorFrontEnd {
			int readIntInputCount = 0;
			int readInputCount = 0;

			public VendorFrontEndStub(Vendor vendor) {
				super(vendor);
			}

			@Override
			public void vendorOperations() {

			}

			public void setBackEnd(BackEnd backEnd) {
				this.backEnd = backEnd;
			}
			
			@Override 
			public String readInput() {
				if(this.readInputCount == 0) {
					this.readInputCount++;
					return "0";
				} else if(this.readInputCount == 1) {
					this.readInputCount++;
					return "0";
				}
				else if(this.readInputCount == 2) {
					this.readInputCount++;
					return "123456";
				} else if(this.readInputCount == 3) {
					this.readInputCount++;
					return "ABCD";
				} else if(this.readInputCount == 4) {
					this.readInputCount++;
					return "-10";
				} else if (this.readInputCount == 5) {
					this.readInputCount++;
					return "2";  // will be changed for branch testing
				} 

				return "2018-10-20 09:00";
			}
			
			@Override
			protected double readDoubleInput(String inputField) {
				
				return 12;
				
			}
			
			@Override
			protected void printEventList(ArrayList<Event> events) {
				
				
			}
			
			@Override
			protected int listSelection(int size, String text) {
				return 1;
				
			}
			
			@Override
			protected void generateCoupon() {super.generateCoupon();}
		}
			
			VendorStub vendorStub = new VendorStub("loginId", "password", "name");
			locationNameMaterial = "locationName";
			locationCapacityMaterial = 100;
			locationMaterial = new Location(locationNameMaterial, locationCapacityMaterial);
			eventNameMaterial = "eventName";
			startTimeMaterial = DateUtils.parseDate("2018-10-18 09:30");
			endTimeMaterial = DateUtils.parseDate("2018-10-18 10:30");
			eventMaterial = new Event(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
					true);
			BackEndStub backEndStub = new BackEndStub();
			VendorFrontEndStub tVendorFrontEndStub = new VendorFrontEndStub(vendorStub);
			tVendorFrontEndStub.setBackEnd(backEndStub);
			ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent1));
			System.out.println();
			System.out.println("- Generate Coupon -");
			System.out.println();
			System.out.println("Code: ");
			System.out.println();
			System.out.println("Code already exists! Please try another one.");
			System.out.println();
			System.out.println("Code: ");
			System.out.println();
			System.out.println("Code 0 is reserved! Please try another one.");
			System.out.println();
			System.out.println("Code: ");
			System.out.println("Please select discount type: ");
			System.out.println("1: Flat");
			System.out.println("2: Percentage off");
			System.out.println("3: Free Purchase");
			System.out.println();
			System.out.println("Invalid Number!");//"ABCD"
			System.out.println();
			System.out.println("Please select discount type: ");
			System.out.println("1: Flat");
			System.out.println("2: Percentage off");
			System.out.println("3: Free Purchase");
			System.out.println();
			System.out.println("Invalid Number!");//-10
			System.out.println();
			System.out.println("Please select discount type: ");
			System.out.println("1: Flat");
			System.out.println("2: Percentage off");
			System.out.println("3: Free Purchase"); //correct
			System.out.println("End Time (YYYY-MM-DD HH:MM): ");
			
			
			System.out.println();
			System.out.println("Coupon Generated!");
			System.out.println();
			ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent2));
			tVendorFrontEndStub.generateCoupon();
			assertEquals(outContent1.toString(), outContent2.toString());

		}
	
	@Test 
	public void testGenerateCoupon3() {
		class VendorStub extends Vendor {

			public VendorStub(String loginId, String password, String name) {
				super(loginId, password, name);
				// TODO Auto-generated constructor stub
			}

			@Override
			public ArrayList<Event> getEvents() {
				ArrayList<Event> eventList = new ArrayList<Event>();
				eventList.add(eventMaterial);
				return eventList;
			}

		}
		
		class BackEndStub extends BackEnd {
			int duplicateCouponCodeCount = 0;
			
			
			@Override
			public boolean isDuplicateCouponCode(String code) {
				if(this.duplicateCouponCodeCount == 0) {
					this.duplicateCouponCodeCount++;
					return true;
				}
				return false;
			}
			
			@Override
			public void createNewCoupon(Coupon coupon) {
				
			}
		}
		
		class VendorFrontEndStub extends VendorFrontEnd {
			int readIntInputCount = 0;
			int readInputCount = 0;

			public VendorFrontEndStub(Vendor vendor) {
				super(vendor);
			}

			@Override
			public void vendorOperations() {

			}

			public void setBackEnd(BackEnd backEnd) {
				this.backEnd = backEnd;
			}
			
			@Override 
			public String readInput() {
				if(this.readInputCount == 0) {
					this.readInputCount++;
					return "0";
				} else if(this.readInputCount == 1) {
					this.readInputCount++;
					return "0";
				}
				else if(this.readInputCount == 2) {
					this.readInputCount++;
					return "123456";
				} else if(this.readInputCount == 3) {
					this.readInputCount++;
					return "ABCD";
				} else if(this.readInputCount == 4) {
					this.readInputCount++;
					return "-10";
				} else if (this.readInputCount == 5) {
					this.readInputCount++;
					return "3";  // will be changed for branch testing
				} else if(this.readInputCount == 6) {
					this.readInputCount++;
					return "ABAB";
					
				} 

				return "2018-10-20 09:00";
			}
			
			
			
			@Override
			protected void printEventList(ArrayList<Event> events) {
				
				
			}
			
			@Override
			protected int listSelection(int size, String text) {
				return 1;
				
			}
			
			@Override
			protected void generateCoupon() {super.generateCoupon();}
		}
			
			VendorStub vendorStub = new VendorStub("loginId", "password", "name");
			locationNameMaterial = "locationName";
			locationCapacityMaterial = 100;
			locationMaterial = new Location(locationNameMaterial, locationCapacityMaterial);
			eventNameMaterial = "eventName";
			startTimeMaterial = DateUtils.parseDate("2018-10-18 09:30");
			endTimeMaterial = DateUtils.parseDate("2018-10-18 10:30");
			eventMaterial = new Event(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
					true);
			BackEndStub backEndStub = new BackEndStub();
			VendorFrontEndStub tVendorFrontEndStub = new VendorFrontEndStub(vendorStub);
			tVendorFrontEndStub.setBackEnd(backEndStub);
			ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent1));
			System.out.println();
			System.out.println("- Generate Coupon -");
			System.out.println();
			System.out.println("Code: ");
			System.out.println();
			System.out.println("Code already exists! Please try another one.");
			System.out.println();
			System.out.println("Code: ");
			System.out.println();
			System.out.println("Code 0 is reserved! Please try another one.");
			System.out.println();
			System.out.println("Code: ");
			System.out.println("Please select discount type: ");
			System.out.println("1: Flat");
			System.out.println("2: Percentage off");
			System.out.println("3: Free Purchase");
			System.out.println();
			System.out.println("Invalid Number!");//"ABCD"
			System.out.println();
			System.out.println("Please select discount type: ");
			System.out.println("1: Flat");
			System.out.println("2: Percentage off");
			System.out.println("3: Free Purchase");
			System.out.println();
			System.out.println("Invalid Number!");//-10
			System.out.println();
			System.out.println("Please select discount type: ");
			System.out.println("1: Flat");
			System.out.println("2: Percentage off");
			System.out.println("3: Free Purchase"); //correct
			System.out.println("End Time (YYYY-MM-DD HH:MM): ");
			System.out.println("Invalid Date Format!");
			System.out.println("End Time (YYYY-MM-DD HH:MM): ");
			System.out.println();
			System.out.println("Coupon Generated!");
			System.out.println();
			ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent2));
			tVendorFrontEndStub.generateCoupon();
			assertEquals(outContent1.toString(), outContent2.toString());

		}
	
		@Test
		public void testCheckIn() {
			class TicketStub extends Ticket{
				
				
				public TicketStub(Event event, double price, int seat) {
					super(event, price, seat);
				}
				
				
				
				@Override
				public User getPurchaser() {
					return purchaser1;
				}
				
				@Override
				public boolean setEntryTime() {
					
					return true;
				}
			}
			
			
			
			class EventStub extends Event {

				public EventStub(String name, Date startTime, Date endTime, Vendor vendor, Location location,
						boolean isMature) {
					super(name, startTime, endTime, vendor, location, isMature);
					// TODO Auto-generated constructor stub
				}
				
				public ArrayList<Ticket> getTickets() {
					ArrayList ticketList = new ArrayList<>();
					TicketStub ticketNotPurchased1 = new TicketStub(eventWeWantToUseForCheckIn, 100, 201);
					ticketList.add(ticketNotPurchased1);
					TicketStub ticketNotPurchased2 = new TicketStub(eventWeWantToUseForCheckIn, 100, 202);
					ticketList.add(ticketNotPurchased2);
					TicketStub ticketPurchased = new TicketStub(eventWeWantToUseForCheckIn, 100, 203);
					ticketPurchased.Purchase(purchaser1);
					ticketList.add(ticketPurchased);
					
					return ticketList;
				}
				
			}
			
			
			
			
			
			class VendorStub extends Vendor {

				public VendorStub(String loginId, String password, String name) {
					super(loginId, password, name);
					// TODO Auto-generated constructor stub
				}

				@Override
				public ArrayList<Event> getEvents() {
					ArrayList<Event> eventList = new ArrayList<Event>();
					eventList.add(eventStartTimeLaterThanCurrentTimeMaterial);
					eventList.add(eventEndTimeEarlierThanCurrentTimeMaterial);
					eventList.add(eventWeWantToUseForCheckIn);
					return eventList;
				}

			}
			
			
			
			class VendorFrontEndStub extends VendorFrontEnd {
				int readIntInputCount = 0;
				int readInputCount = 0;

				public VendorFrontEndStub(Vendor vendor) {
					super(vendor);
				}

				@Override
				public void vendorOperations() {

				}

				
				
				protected void printUserList(ArrayList<User> users) {
					
					
					
				}
				
				@Override
				protected void printEventList(ArrayList<Event> events) {
					
					
					
				}
				
				@Override
				protected int listSelection(int size, String text) {
					
					return 1;
					
				}
				
				protected void checkIn() {
					
					super.checkIn();
					
				}
				
				
		
			}
			VendorStub vendorStub = new VendorStub("loginId", "password", "name");
			locationNameMaterial = "locationName";
			locationCapacityMaterial = 100;
			locationMaterial = new Location(locationNameMaterial, locationCapacityMaterial);
			eventNameMaterial = "eventName";
			Date today = new Date();
			Date yesterday = new Date();
			yesterday.setDate(today.getDate() - 1);
			Date tmrDate = new Date();
			tmrDate.setDate(today.getDate() + 1);
			///////
			this.startTimeMaterial = tmrDate;
			Date tmrDatePlusOneDay = new Date();
			tmrDatePlusOneDay.setDate(tmrDate.getDate() + 1);
			this.endTimeMaterial = tmrDatePlusOneDay;
			eventStartTimeLaterThanCurrentTimeMaterial = new EventStub(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
					true);
			///////
			this.endTimeMaterial = yesterday;
			Date aDayBeforeYesterday = new Date();
			aDayBeforeYesterday.setDate(yesterday.getDate() - 1);
			this.startTimeMaterial = aDayBeforeYesterday;
			eventEndTimeEarlierThanCurrentTimeMaterial = new EventStub(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
					true);
			/////////
			startTimeMaterial = yesterday;
			endTimeMaterial = tmrDate;
			eventWeWantToUseForCheckIn = new EventStub(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
					true);
			purchaser1 = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
			VendorFrontEndStub tVendorFrontEndStub = new VendorFrontEndStub(vendorStub);
			ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent1));
			System.out.println();
			System.out.println("- Check-In -");
			System.out.println();
			System.out.println();
			System.out.println("Checked-In participant!");
			System.out.println();
			ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent2));
			tVendorFrontEndStub.checkIn();
			assertEquals(outContent1.toString(), outContent2.toString());

			
		}
		
		
		@Test
		public void testCheckOut() {
			class TicketStub extends Ticket{
				
				
				public TicketStub(Event event, double price, int seat) {
					super(event, price, seat);
				}
				
				
				
				@Override
				public User getPurchaser() {
					return purchaser1;
				}
				
				
			}
			
			
			
			class EventStub extends Event {

				public EventStub(String name, Date startTime, Date endTime, Vendor vendor, Location location,
						boolean isMature) {
					super(name, startTime, endTime, vendor, location, isMature);
					// TODO Auto-generated constructor stub
				}
				
				public ArrayList<Ticket> getTickets() {
					ArrayList ticketList = new ArrayList<>();
					TicketStub ticketNotPurchased1 = new TicketStub(eventWeWantToUseForCheckIn, 100, 201);
					ticketList.add(ticketNotPurchased1);
					TicketStub ticketNotPurchased2 = new TicketStub(eventWeWantToUseForCheckIn, 100, 202);
					ticketList.add(ticketNotPurchased2);
					TicketStub ticketThatPurchaserHasEntryed = new TicketStub(eventWeWantToUseForCheckIn, 100, 203);
					ticketThatPurchaserHasEntryed.Purchase(purchaser1);
					ticketThatPurchaserHasEntryed.setEntryTime();
					ticketList.add(ticketThatPurchaserHasEntryed);
					
					return ticketList;
				}
				
			}
			
			
			
			
			
			class VendorStub extends Vendor {

				public VendorStub(String loginId, String password, String name) {
					super(loginId, password, name);
					// TODO Auto-generated constructor stub
				}

				@Override
				public ArrayList<Event> getEvents() {
					ArrayList<Event> eventList = new ArrayList<Event>();
					eventList.add(eventStartTimeLaterThanCurrentTimeMaterial);
					eventList.add(eventStartTimeLaterThanCurrentTimeMaterial2);
					eventList.add(eventWeWantToUseForCheckIn);
					return eventList;
				}

			}
			
			
			
			class VendorFrontEndStub extends VendorFrontEnd {
				int readIntInputCount = 0;
				int readInputCount = 0;

				public VendorFrontEndStub(Vendor vendor) {
					super(vendor);
				}

				@Override
				public void vendorOperations() {

				}

				
				
				protected void printUserList(ArrayList<User> users) {
					
					
					
				}
				
				@Override
				protected void printEventList(ArrayList<Event> events) {
					
					
					
				}
				
				@Override
				protected int listSelection(int size, String text) {
					
					return 1;
					
				}
				
				protected void checkOut() {super.checkOut();}
				
				
		
			}
			VendorStub vendorStub = new VendorStub("loginId", "password", "name");
			locationNameMaterial = "locationName";
			locationCapacityMaterial = 100;
			locationMaterial = new Location(locationNameMaterial, locationCapacityMaterial);
			eventNameMaterial = "eventName";
			Date today = new Date();
			Date yesterday = new Date();
			yesterday.setDate(today.getDate() - 1);
			Date tmrDate = new Date();
			tmrDate.setDate(today.getDate() + 1);
			///////
			this.startTimeMaterial = tmrDate;
			Date tmrDatePlusOneDay = new Date();
			tmrDatePlusOneDay.setDate(tmrDate.getDate() + 1);
			this.endTimeMaterial = tmrDatePlusOneDay;
			eventStartTimeLaterThanCurrentTimeMaterial = new EventStub(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
					true);
			///////
			this.startTimeMaterial = tmrDate;
			this.endTimeMaterial = tmrDatePlusOneDay;
			eventStartTimeLaterThanCurrentTimeMaterial2 = new EventStub(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
					true);
			/////////
			startTimeMaterial = yesterday;
			endTimeMaterial = tmrDate;
			eventWeWantToUseForCheckIn = new EventStub(eventNameMaterial, startTimeMaterial, endTimeMaterial, vendorStub, locationMaterial,
					true);
			purchaser1 = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
			VendorFrontEndStub tVendorFrontEndStub = new VendorFrontEndStub(vendorStub);
			ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent1));
			System.out.println();
			System.out.println("- Check-Out -");
			System.out.println();
			System.out.println();
			System.out.println("Checked-Out participant!");
			System.out.println();
			ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent2));
			tVendorFrontEndStub.checkOut();
			assertEquals(outContent1.toString(), outContent2.toString());

			
		}
		
	}


