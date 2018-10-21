package ems;

import java.util.Date;

public class Log implements java.io.Serializable {
	
	private Date date;
	private String message;
	
	public Log(Date date, String message) {
		this.date = date;
		this.message = message;
	}
	
	public String getDate() {
		return this.date.toLocaleString();
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
