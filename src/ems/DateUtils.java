package ems;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static Date parseDate(String dateString) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(dateString);
			return date;
		} catch (ParseException e) {
			System.out.println("Invalid Date Format!");
			return null;
		}
		
	}

	public static boolean validatePeriod(Date startTime, Date endTime) {
		
		return startTime.compareTo(endTime) < 0;

	}

	public static boolean isOverlappedPeriod(Date startTimeA, Date endTimeA, Date startTimeB, Date endTimeB) {
		
		return (startTimeA.compareTo(endTimeB) < 0) && (endTimeA.compareTo(startTimeB) > 0);
		
	}

}
