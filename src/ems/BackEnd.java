package ems;

import java.util.ArrayList;

public class BackEnd {
	
	private static BackEnd instance;
	
	private ArrayList<Vendor> vendors;
	private ArrayList<User> users;
	private ArrayList<Location> locations;
	
	private BackEnd() {
		this.vendors = new ArrayList<Vendor>();
		this.users = new ArrayList<User>();
		this.locations = new ArrayList<Location>();
		BackEnd.instance = this;
	}
	
	public static BackEnd getInstance() {
		if(BackEnd.instance == null) {
			return new BackEnd();
		}
		else {
			return BackEnd.instance;
		}
	}
	
	public void createNewVendor(String loginId, String password, String name) {
		Vendor vendor = new Vendor(loginId, password, name);
		this.vendors.add(vendor);
		LogsRecorder.getInstance().writeLog("New Vendor " + vendor.getName() + " created.");
	}
	
	public ArrayList<Vendor> getVendors() {
		return this.vendors;
	}
	
	public Vendor removeVendor(Vendor vendor) {
		boolean result = this.vendors.remove(vendor);
		if(result == true) {
			LogsRecorder.getInstance().writeLog("Vendor " + vendor.getName() + " removed.");
			return vendor;
		}
		return null;
	}
	
	public void addUser(User user) {
		this.users.add(user);
		LogsRecorder.getInstance().writeLog("New User with HKID " + user.getHKID() + " registered.");
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public User removeUser(User user) {
		boolean result = this.users.remove(user);
		if(result == true) {
			LogsRecorder.getInstance().writeLog("User with HKID " + user.getHKID() + " removed.");
			return user;
		}
		return null;
	}
	
	public void createNewLocation(String name, int capacity) {
		Location location = new Location(name, capacity);
		this.locations.add(location);
		LogsRecorder.getInstance().writeLog("New Location " + location.getName() + " created.");
	}
	
	public ArrayList<Location> getLocations() {
		return this.locations;
	}
	
	public Location removeLocation(Location location) {
		boolean result = this.locations.remove(location);
		if(result == true) {
			LogsRecorder.getInstance().writeLog("Location " + location.getName() + " removed.");
			return location;
		}
		return null;
	}

}
