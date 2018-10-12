package ems;

import java.util.ArrayList;
import java.util.Date;

public class LogsRecorder {
	
	private static LogsRecorder instance;
	private ArrayList<Log> logs;
	
	private LogsRecorder() {
		this.logs = new ArrayList<Log>();
		LogsRecorder.instance = this;
	}
	
	public LogsRecorder getInstance() {
		if(LogsRecorder.instance == null) {
			return new LogsRecorder();
		}
		else {
			return LogsRecorder.instance;
		}
	}
	
	public void writeLog(Date date, String message) {
		logs.add(new Log(date, message));
	}
	
	public void readLogs() {
		this.logs.forEach(l -> System.out.println(l.getDate() + ": " + l.getMessage()));
	}

}