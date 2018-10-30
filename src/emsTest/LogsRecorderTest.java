package emsTest;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import ems.LogsRecorder;
import ems.Log;

public class LogsRecorderTest {
	
	@Test
	public void writeLogTest_0() {
		LogsRecorder logsRecorder = LogsRecorder.getInstance();
		
		String message = "Test Message 0";
		
		//Clear old logs for serialization testing
		logsRecorder.clearFile();
		logsRecorder.clearLogs();
		
		//Create a new log and serialize it to file.
		logsRecorder.writeLog(message);
		
		//Clear program logs variable.
		logsRecorder.clearLogs();
		
		//Deserialize the written log from file.
		logsRecorder.deserialize();
		
		//Compare the written log object with the deserialized object
		Log log = new Log(message);
		assertEquals(log.getMessage(), logsRecorder.getLogs().get(0).getMessage());
	}

}
