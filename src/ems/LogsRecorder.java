package ems;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class LogsRecorder {
	
	private static LogsRecorder instance;
	private ArrayList<Log> logs;
	private File file;
	
	private LogsRecorder() throws IOException {
		this.logs = new ArrayList<Log>();
		LogsRecorder.instance = this;
		this.file = new File("logs.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		else {
			this.readFile();
		}
	}
	
	public LogsRecorder getInstance() throws IOException {
		if(LogsRecorder.instance == null) {
			return new LogsRecorder();
		}
		else {
			return LogsRecorder.instance;
		}
	}
	
	private void readFile() throws IOException {
		FileReader fileReader = new FileReader(this.file);
		String fileString = new String();
		int fileChar;
		while((fileChar = fileReader.read()) != -1) {
			fileString += fileChar;
		}
		//TO DO: Input fileString into ArrayList<Log> logs
	}
	
	private void writeFile() throws IOException {
		FileWriter fileWriter = new FileWriter(this.file);
		String fileString = new String();
		//TO DO: Output ArrayList<Log> logs into fileString
		fileWriter.write(fileString);
	}
	
	public void writeLog(Date date, String message) throws IOException {
		logs.add(new Log(date, message));
		this.writeFile();
	}
	
	public void readLogs() {
		System.out.println("- Start of Logs -");
		this.logs.forEach(l -> System.out.println(l.getDate() + ": " + l.getMessage()));
		System.out.println("- End of Logs -");
	}
	
	public void clearLogs() {
		this.logs.clear();
	}

}