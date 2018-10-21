package ems;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class LogsRecorder {
	
	private static LogsRecorder instance;
	private ArrayList<Log> logs;
	private File file;
	
	private LogsRecorder() {
		this.logs = new ArrayList<Log>();
		LogsRecorder.instance = this;
		Date today = new Date();
		String dateString = today.getDate() + "-" + (today.getMonth() + 1) + "-" + (today.getYear() + 1900);
		this.file = new File(dateString + ".log");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Failed to create file.");
				e.printStackTrace();
			}
		}
		else {
			try {
				this.readFile();
			} catch (IOException e) {
				System.out.println("Failed to read from file.");
				e.printStackTrace();
			}
		}
	}
	
	public static LogsRecorder getInstance() {
		if(LogsRecorder.instance == null) {
			return new LogsRecorder();
		}
		else {
			return LogsRecorder.instance;
		}
	}
	
	private void writeFile() throws IOException {
		FileOutputStream fileOut = new FileOutputStream(file);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(this.logs);
		objectOut.close();
		fileOut.close();
	}
	
	public void readFile() throws IOException {
		FileInputStream fileIn = new FileInputStream(file);
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		try {
			this.logs = (ArrayList<Log>) objectIn.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("Log class not found.");
			e.printStackTrace();
		}
		objectIn.close();
		fileIn.close();
	}
	
	public void writeLog(Date date, String message) {
		logs.add(new Log(date, message));
		try {
			this.writeFile();
		} catch (IOException e) {
			System.out.println("Failed to write to file.");
			e.printStackTrace();
		}
	}
	
	//Read today logs
	public void readLogs() {
		System.out.println("- Start of Logs -");
		this.logs.forEach(l -> System.out.println(l.getDate() + ": " + l.getMessage()));
		System.out.println("- End of Logs -");
	}
	
	//Read specific date logs
	public void readLogs(Date date) {
		String dateString = date.getDate() + "-" + (date.getMonth() + 1) + "-" + (date.getYear() + 1900);
		File file = new File(dateString + ".log");
		if(file.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				ArrayList<Log> logs = (ArrayList<Log>) objectIn.readObject();
				objectIn.close();
				fileIn.close();
				
				System.out.println("- Start of Logs -");
				logs.forEach(l -> System.out.println(l.getDate() + ": " + l.getMessage()));
				System.out.println("- End of Logs -");
			} catch (IOException e) {
				System.out.println("Failed to read from file.");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Log class not found.");
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Logs not found on specified date.");
		}
	}
	
	public ArrayList<Log> getLogs() {
		return this.logs;
	}
	
	public void clearLogs() {
		this.logs.clear();
	}
	
	public void clearFile() {
		this.file.delete();
	}

}