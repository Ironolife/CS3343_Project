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
	
	private File vendorFile;
	private File userFile;
	private File locationFile;
	
	private BackEnd() {
		this.vendors = new ArrayList<Vendor>();
		this.users = new ArrayList<User>();
		this.locations = new ArrayList<Location>();
		BackEnd.instance = this;
		
		File folder = new File("data");
		if(!folder.exists()) {
			folder.mkdir();
		}
		this.vendorFile = new File("data/" + "vendor.txt");
		this.userFile = new File("data/" + "user.txt");
		this.locationFile = new File("data/" + "location.txt");
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
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Gson userGson = new GsonBuilder()
					.registerTypeAdapter(this.users.getClass(), new UserListAdapter())
					.setPrettyPrinting().create();
			
			String vendorJson = gson.toJson(this.vendors);
			String userJson = userGson.toJson(this.users);
			String locationJson = gson.toJson(this.locations);
			
			vendorFileWriter.write(vendorJson);
			userFileWriter.write(userJson);
			locationFileWriter.write(locationJson);
			
			vendorFileWriter.close();
			userFileWriter.close();
			locationFileWriter.close();
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
			
			Gson gson = new Gson();
			Gson userGson = new GsonBuilder().registerTypeAdapter(this.users.getClass(), new UserListAdapter()).create();
			
			JsonReader vendorJsonReader = new JsonReader(vendorFileReader);
			JsonReader userJsonReader = new JsonReader(userFileReader);
			JsonReader locationJsonReader = new JsonReader(locationFileReader);
			
			this.vendors = gson.fromJson(vendorJsonReader, new TypeToken<ArrayList<Vendor>>(){}.getType());
			this.users = userGson.fromJson(userJsonReader, new TypeToken<ArrayList<User>>(){}.getType());
			this.locations = gson.fromJson(locationJsonReader, new TypeToken<ArrayList<Location>>(){}.getType());
			
			if(this.vendors == null) {
				this.vendors = new ArrayList<Vendor>();
			}
			if(this.users == null) {
				this.users = new ArrayList<User>();
			}
			if(this.locations == null) {
				this.locations = new ArrayList<Location>();
			}
			
			vendorJsonReader.close();
			userJsonReader.close();
			locationJsonReader.close();
			
			vendorFileReader.close();
			userFileReader.close();
			locationFileReader.close();
		}
		catch (IOException e) {
			System.out.println("Failed to read from file.");
			e.printStackTrace();
		}
	}
	
	public void createNewVendor(String loginId, String password, String name) {
		Vendor vendor = new Vendor(loginId, password, name);
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
	
	public void createNewLocation(String name, int capacity) {
		Location location = new Location(name, capacity);
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
	
	public Location getRequiredLocation(String locationName) {
		for(Location location: this.getLocations()) {
			if(location.getName().equals(locationName)) {
				return location;
			}
		}
		return new Location();
	}
	
	public boolean isEventBeingCreatedHasConflictWithOtherEvents(Location eventGoingToBeCreatedLocation, Date startTime, Date endTime) {
		Location targetLocation = new Location();
		boolean foundTheTargetedLocation = false;
		for(int i = 0; i < this.locations.size() && !foundTheTargetedLocation; i++) {
			if(locations.get(i).getName().equals(eventGoingToBeCreatedLocation.getName())) {
				targetLocation = locations.get(i);
				foundTheTargetedLocation = true;		
			}
		}
		
		
		List<Event> eventListOfTargetLocation = targetLocation.getEventList();
		for(Event aEvent: eventListOfTargetLocation) {
			if(DateUtils.isStartTimeEndTimePairOverlappedWithOtherEventDatePair(startTime, endTime, aEvent)) {
				return true;
			}
		}
		return false;
		
		
	}
}
