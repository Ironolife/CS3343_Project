package ems;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class BackEnd {
	
	private static BackEnd instance;
	
	private ArrayList<Vendor> vendors;
	private ArrayList<User> users;
	private ArrayList<Location> locations;
	private ArrayList<Event> events;
	private ArrayList<Ticket> tickets;
	private ArrayList<Coupon> counpons;
	private ArrayList<Review> reviews;
	private ArrayList<Transaction> transactions;
	
	private File vendorFile;
	private File userFile;
	private File locationFile;
	private File eventFile;
	private File ticketFile;
	private File couponFile;
	private File reviewFile;
	private File transactionFile;
	
	private BackEnd() {
		this.vendors = new ArrayList<Vendor>();
		this.users = new ArrayList<User>();
		this.locations = new ArrayList<Location>();
		this.events = new ArrayList<Event>();
		this.tickets = new ArrayList<Ticket>();
		this.counpons = new ArrayList<Coupon>();
		this.reviews = new ArrayList<Review>();
		this.transactions = new ArrayList<Transaction>();
		
		BackEnd.instance = this;
		
		File folder = new File("data");
		if(!folder.exists()) {
			folder.mkdir();
		}
		this.vendorFile = new File("data/" + "vendor.txt");
		this.userFile = new File("data/" + "user.txt");
		this.locationFile = new File("data/" + "location.txt");
		this.eventFile = new File("data/" + "event.txt");
		this.ticketFile = new File("data/" + "ticket.txt");
		this.couponFile = new File("data/" + "coupon.txt");
		this.reviewFile = new File("data/" + "review.txt");
		this.transactionFile = new File("data/" + "transaction.txt");
		try {
			if(!vendorFile.exists()) {
				vendorFile.createNewFile();
			}
			if(!userFile.exists()) {
				userFile.createNewFile();
			}
			if(!locationFile.exists()) {
				locationFile.createNewFile();
			}
			if(!eventFile.exists()) {
				eventFile.createNewFile();
			}
			if(!ticketFile.exists()) {
				ticketFile.createNewFile();
			}
			if(!couponFile.exists()) {
				couponFile.createNewFile();
			}
			if(!reviewFile.exists()) {
				reviewFile.createNewFile();
			}
			if(!transactionFile.exists()) {
				transactionFile.createNewFile();
			}
		} catch (IOException e) {
			System.out.println("Failed to create file.");
			e.printStackTrace();
		}
		
		this.deserialize();
	}
	
	public static BackEnd getInstance() {
		if(BackEnd.instance == null) {
			return new BackEnd();
		}
		else {
			return BackEnd.instance;
		}
	}
	
	public void serialize() {
		try {
			FileWriter vendorFileWriter = new FileWriter(this.vendorFile);
			FileWriter userFileWriter = new FileWriter(this.userFile);
			FileWriter locationFileWriter = new FileWriter(this.locationFile);
			FileWriter eventFileWriter = new FileWriter(this.eventFile);
			FileWriter ticketFileWriter = new FileWriter(this.ticketFile);
			FileWriter couponFileWriter = new FileWriter(this.couponFile);
			FileWriter reviewFileWriter = new FileWriter(this.reviewFile);
			FileWriter transactionFileWriter = new FileWriter(this.transactionFile);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Gson userGson = new GsonBuilder()
					.registerTypeAdapter(User.class, new UserAdapter())
					.setPrettyPrinting().create();
			Gson ticketGson = new GsonBuilder()
					.registerTypeAdapter(this.tickets.getClass(), new TicketListAdapter())
					.setPrettyPrinting().create();
			
			String vendorJson = gson.toJson(this.vendors, new TypeToken<ArrayList<Vendor>>(){}.getType());
			String userJson = userGson.toJson(this.users, new TypeToken<ArrayList<User>>(){}.getType());
			String locationJson = gson.toJson(this.locations, new TypeToken<ArrayList<Location>>(){}.getType());
			String eventJson = gson.toJson(this.events, new TypeToken<ArrayList<Event>>(){}.getType());
			String ticketJson = ticketGson.toJson(this.tickets, new TypeToken<ArrayList<Ticket>>(){}.getType());
			String couponJson = gson.toJson(this.counpons, new TypeToken<ArrayList<Coupon>>(){}.getType());
			String reviewJson = gson.toJson(this.reviews, new TypeToken<ArrayList<Review>>(){}.getType());
			String transactionJson = gson.toJson(this.transactions, new TypeToken<ArrayList<Transaction>>(){}.getType());
			
			vendorFileWriter.write(vendorJson);
			userFileWriter.write(userJson);
			locationFileWriter.write(locationJson);
			eventFileWriter.write(eventJson);
			ticketFileWriter.write(ticketJson);
			couponFileWriter.write(couponJson);
			reviewFileWriter.write(reviewJson);
			transactionFileWriter.write(transactionJson);
			
			vendorFileWriter.close();
			userFileWriter.close();
			locationFileWriter.close();
			eventFileWriter.close();
			ticketFileWriter.close();
			couponFileWriter.close();
			reviewFileWriter.close();
			transactionFileWriter.close();
		}
		catch (IOException e) {
			System.out.println("Failed to write to file.");
			e.printStackTrace();
		}
	}
	
	private void deserialize() {
		try {
			FileReader vendorFileReader = new FileReader(this.vendorFile);
			FileReader userFileReader = new FileReader(this.userFile);
			FileReader locationFileReader = new FileReader(this.locationFile);
			FileReader eventFileReader = new FileReader(this.eventFile);
			FileReader ticketFileReader = new FileReader(this.ticketFile);
			FileReader couponFileReader = new FileReader(this.couponFile);
			FileReader reviewFileReader = new FileReader(this.reviewFile);
			FileReader transactionFileReader = new FileReader(this.transactionFile);
			
			Gson gson = new Gson();
			Gson userGson = new GsonBuilder().registerTypeAdapter(User.class, new UserAdapter()).create();
			Gson ticketGson = new GsonBuilder().registerTypeAdapter(this.tickets.getClass(), new TicketListAdapter()).create();
			
			JsonReader vendorJsonReader = new JsonReader(vendorFileReader);
			JsonReader userJsonReader = new JsonReader(userFileReader);
			JsonReader locationJsonReader = new JsonReader(locationFileReader);
			JsonReader eventJsonReader = new JsonReader(eventFileReader);
			JsonReader ticketJsonReader = new JsonReader(ticketFileReader);
			JsonReader couponJsonReader = new JsonReader(couponFileReader);
			JsonReader reviewJsonReader = new JsonReader(reviewFileReader);
			JsonReader transactionJsonReader = new JsonReader(transactionFileReader);
			
			this.vendors = gson.fromJson(vendorJsonReader, new TypeToken<ArrayList<Vendor>>(){}.getType());
			this.users = userGson.fromJson(userJsonReader, new TypeToken<ArrayList<User>>(){}.getType());
			this.locations = gson.fromJson(locationJsonReader, new TypeToken<ArrayList<Location>>(){}.getType());
			this.events = gson.fromJson(eventJsonReader, new TypeToken<ArrayList<Event>>(){}.getType());
			this.tickets = ticketGson.fromJson(ticketJsonReader, new TypeToken<ArrayList<Ticket>>(){}.getType());
			this.counpons = gson.fromJson(couponJsonReader, new TypeToken<ArrayList<Coupon>>(){}.getType());
			this.reviews = gson.fromJson(reviewJsonReader, new TypeToken<ArrayList<Review>>(){}.getType());
			this.transactions = gson.fromJson(transactionJsonReader, new TypeToken<ArrayList<Transaction>>(){}.getType());
			
			if(this.vendors == null) {
				this.vendors = new ArrayList<Vendor>();
			}
			if(this.users == null) {
				this.users = new ArrayList<User>();
			}
			if(this.locations == null) {
				this.locations = new ArrayList<Location>();
			}
			if(this.events == null) {
				this.events = new ArrayList<Event>();
			}
			if(this.tickets == null) {
				this.tickets = new ArrayList<Ticket>();
			}
			if(this.counpons == null) {
				this.counpons = new ArrayList<Coupon>();
			}
			if(this.reviews == null) {
				this.reviews = new ArrayList<Review>();
			}
			if(this.transactions == null) {
				this.transactions = new ArrayList<Transaction>();
			}
			
			vendorJsonReader.close();
			userJsonReader.close();
			locationJsonReader.close();
			eventJsonReader.close();
			ticketJsonReader.close();
			couponJsonReader.close();
			reviewJsonReader.close();
			transactionJsonReader.close();
			
			vendorFileReader.close();
			userFileReader.close();
			locationFileReader.close();
			eventFileReader.close();
			ticketFileReader.close();
			couponFileReader.close();
			reviewFileReader.close();
			transactionFileReader.close();
		}
		catch (IOException e) {
			System.out.println("Failed to read from file.");
			e.printStackTrace();
		}
	}
	
	public void createNewVendor(Vendor vendor) {
		this.vendors.add(vendor);
		this.serialize();
		LogsRecorder.getInstance().writeLog("New Vendor " + vendor.getName() + " created.");
	}
	
	public ArrayList<Vendor> getVendors() {
		return this.vendors;
	}
	
	public Vendor removeVendor(Vendor vendor) {
		boolean result = this.vendors.remove(vendor);
		if(result == true) {
			this.serialize();
			LogsRecorder.getInstance().writeLog("Vendor " + vendor.getName() + " removed.");
			return vendor;
		}
		return null;
	}
	
	public void addUser(User user) {
		this.users.add(user);
		this.serialize();
		LogsRecorder.getInstance().writeLog("New User with HKID " + user.getHKID() + " registered.");
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public User removeUser(User user) {
		boolean result = this.users.remove(user);
		if(result == true) {
			this.serialize();
			LogsRecorder.getInstance().writeLog("User with HKID " + user.getHKID() + " removed.");
			return user;
		}
		return null;
	}
	
	public boolean isDuplicateLoginId(String loginId) {
		
		ArrayList<String> loginIds = new ArrayList<String>();
		
		for (Vendor vendor : this.vendors) {
			loginIds.add(vendor.getLoginId());
		}
		for (User user : this.users) {
			loginIds.add(user.getLoginId());
		}
		return loginIds.contains(loginId);
		
	}
	
	public boolean isDuplicateHKID(String hkID) {
		
		ArrayList<String> hkIDs = new ArrayList<String>();
		for (User user : this.users) {
			hkIDs.add(user.getHKID());
		}
		return hkIDs.contains(hkID);
		
	}
	
	public void createNewLocation(Location location) {
		this.locations.add(location);
		this.serialize();
		LogsRecorder.getInstance().writeLog("New Location " + location.getName() + " created.");
	}
	
	public ArrayList<Location> getLocations() {
		return this.locations;
	}
	
	public Location removeLocation(Location location) {
		boolean result = this.locations.remove(location);
		if(result == true) {
			this.serialize();
			LogsRecorder.getInstance().writeLog("Location " + location.getName() + " removed.");
			return location;
		}
		return null;
	}
	
	public boolean isDuplicateLocationName(String name) {
		ArrayList<String> names = new ArrayList<String>();
		for(Location location: this.locations) {
			names.add(location.getName());
		}
		return names.contains(name);
	}
	
	public void createNewEvent(Event event) {
		this.events.add(event);
		this.serialize();
		LogsRecorder.getInstance().writeLog("New Event " + event.getName() + " created.");
	}
	
	public ArrayList<Event> getEvents() {
		return this.events;
	}
	
	public Event removeEvent(Event event) {
		boolean result = this.events.remove(event);
		if(result == true) {
			this.serialize();
			LogsRecorder.getInstance().writeLog("Event " + event.getName() + " removed.");
			return event;
		}
		return null;
	}
	
	public void createNewTickets(ArrayList<Ticket> tickets) {
		this.tickets.addAll(tickets);
		this.serialize();
		LogsRecorder.getInstance().writeLog(tickets.size() + " Tickets added.");
	}
	
	public ArrayList<Ticket> getTickets() {
		return this.tickets;
	}
	
	public Ticket removeTicket(Ticket ticket) {
		boolean result = this.tickets.remove(ticket);
		if(result == true) {
			this.serialize();
			LogsRecorder.getInstance().writeLog("Ticket " + ticket.getId() + " removed.");
			return ticket;
		}
		return null;
	}
	
	public void createNewCoupon(Coupon coupon) {
		this.counpons.add(coupon);
		this.serialize();
		LogsRecorder.getInstance().writeLog("New Coupon " + coupon.getCode() + " created.");
	}
	
	public ArrayList<Coupon> getCoupons() {
		return this.counpons;
	}
	
	public Coupon removeCoupon(Coupon coupon) {
		boolean result = this.counpons.remove(coupon);
		if(result == true) {
			this.serialize();
			LogsRecorder.getInstance().writeLog("Coupon " + coupon.getCode() + " removed.");
			return coupon;
		}
		return null;
	}
	
	public boolean isDuplicateCouponCode(String code) {
		ArrayList<String> codes = new ArrayList<String>();
		for(Coupon coupon: this.counpons) {
			codes.add(coupon.getCode());
		}
		return codes.contains(code);
	}
	
	public void createNewReview(Review review) {
		this.reviews.add(review);
		this.serialize();
		LogsRecorder.getInstance().writeLog("New Review " + review.getId() + " created.");
	}
	
	public ArrayList<Review> getReviews() {
		return this.reviews;
	}
	
	public Review removeReview(Review review) {
		boolean result = this.reviews.remove(review);
		if(result == true) {
			this.serialize();
			LogsRecorder.getInstance().writeLog("Review " + review.getId() + " removed.");
			return review;
		}
		return null;
	}
	
	public void createNewTransaction(Transaction transaction) {
		this.transactions.add(transaction);
		this.serialize();
		LogsRecorder.getInstance().writeLog("New Transaction " + transaction.getId() + " created.");
	}
	
	public ArrayList<Transaction> getTransactions() {
		return this.transactions;
	}
	
	public Transaction removeTransaction(Transaction transaction) {
		boolean result = this.transactions.remove(transaction);
		if(result == true) {
			this.serialize();
			LogsRecorder.getInstance().writeLog("Transaction " + transaction.getId() + " removed.");
			return transaction;
		}
		return null;
	}
	
}
