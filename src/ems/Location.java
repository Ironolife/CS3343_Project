package ems;

import java.util.UUID;

public class Location {
	
	private UUID id;
	private String name;
	private int capacity;
	
	public Location(String name, int capacity) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.capacity = capacity;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCapacity() {
		return this.capacity;
	}

}
