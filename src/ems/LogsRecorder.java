package ems;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class LogsRecorder {
	
	private static LogsRecorder instance;
	private ArrayList<Log> logs;
	private File file;
	
	private LogsRecorder() {
		this.logs = new ArrayList<Log>();
		LogsRecorder.instance = this;
		
		Date today = new Date();
		String dateString = today.getDate() + "-" + (today.getMonth() + 1) + "-" + (today.getYear() + 1900);
		File folder = new File("logs");
		if(!folder.exists()) {
			folder.mkdir();
		}
		this.file = new File("logs/" + dateString + ".log");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Failed to create file.");
				e.printStackTrace();
			}
		}

		this.deserialize();
	}
	
	public static LogsRecorder getInstance() {
		if(LogsRecorder.instance == null) {
			return new LogsRecorder();
		}
		else {
			return LogsRecorder.instance;
		}
	}
	
	private void serialize() {
		try {
			FileWriter fileWriter = new FileWriter(this.file);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(this.logs);
			fileWriter.write(json);
			fileWriter.close();
		}
		catch (IOException e) {
			System.out.println("Failed to write to file.");
			e.printStackTrace();
		}
	}
	
	public void deserialize() {
		try {
			FileReader fileReader = new FileReader(this.file);
			Gson gson = new Gson();
			JsonReader jsonReader = new JsonReader(fileReader);
			this.logs = gson.fromJson(jsonReader, new TypeToken<ArrayList<Log>>(){}.getType()) ;
			if(this.logs == null) {
				this.logs = new ArrayList<Log>();
			}
			jsonReader.close();
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println("Failed to read from file.");
			e.printStackTrace();
		}
	}
	
	public void writeLog(String message) {
		logs.add(new Log(message));
		this.serialize();
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
		File file = new File("logs/" + dateString + ".log");
		if(file.exists()) {
			try {
				FileReader fileReader = new FileReader(file);
				Gson gson = new Gson();
				JsonReader jsonReader = new JsonReader(fileReader);
				ArrayList<Log> logs = gson.fromJson(jsonReader, new TypeToken<ArrayList<Log>>(){}.getType()) ;
				if(logs == null) {
					logs = new ArrayList<Log>();
				}
				jsonReader.close();
				fileReader.close();
				
				System.out.println("- Start of Logs -");
				logs.forEach(l -> System.out.println(l.getDate() + ": " + l.getMessage()));
				System.out.println("- End of Logs -");
			} catch (IOException e) {
				System.out.println("Failed to read from file.");
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