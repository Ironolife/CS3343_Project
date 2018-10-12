package ems;

import java.util.Date;

public class Log {
	
	private Date date;
	private String message;
	
	public Log(Date date, String message) {
		this.date = date;
		this.message = message;
	}
	
	public String getDate() {
		return this.date.toString();
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
