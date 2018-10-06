package ems;

import java.util.UUID;

public abstract class User {
	
	private UUID id;
	private String name;
	private int age;
	private Ticket[] tickets;
	
	public User(UUID id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.tickets = new Ticket[] {};
	}

}
