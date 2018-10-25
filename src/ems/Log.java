package ems;

import java.util.Date;

public class Log implements java.io.Serializable {
	
	private Date date;
	private String message;
	
	public Log(String message) {
		this.date = new Date();
		this.message = message;
	}
	
	public String getDate() {
		return this.date.toLocaleString();
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
