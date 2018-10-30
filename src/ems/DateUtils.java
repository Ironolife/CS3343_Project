package ems;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static String dateFormat = "yyyyMMdd HH:mm";
	private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

	public static boolean isDateStringValid(String dateString) {

		sdf.setLenient(false);
		try {
			Date date = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

	public static Date convertDateStringToDateObject(String dateString) {
		/**
		 * for correctness of the program
		 */
		sdf.setLenient(false);
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static boolean isStartTimeAndEndTimePairValid(Date startTime, Date endTime) {
		sdf.setLenient(false);
		int validationResult = startTime.compareTo(endTime);
		if (validationResult == 0) {
			System.out.println("Start time and end time should not be the same.");
			return false;
		} else if (validationResult > 0) {
			System.out.println("Start time should not be later than end time.");
			return false;
		}
		return true;

	}

	public static boolean isStartTimeEndTimePairOverlappedWithOtherEventDatePair(Date startTime,
			Date endTime, Event eventToBeChecked) {
		Date createdEventStartTime = eventToBeChecked.getStartTime();
		Date createdEventEndTime = eventToBeChecked.getEndTime();
		if(startTime.compareTo(createdEventStartTime) == 0 && endTime.compareTo(createdEventEndTime) == 0) {
			System.out.println("The time overlapped with other event.");
			return true;
		} else if(startTime.compareTo(createdEventStartTime) < 0 && endTime.compareTo(createdEventStartTime) > 0 && endTime.compareTo(createdEventEndTime) < 0) {
			System.out.println("The time overlapped with other event.");
			return true;
		} else if(startTime.compareTo(createdEventStartTime) > 0 && startTime.compareTo(createdEventEndTime) < 0 && endTime.compareTo(createdEventEndTime) > 0) {
			System.out.println("The time overlapped with other event.");
			return true;
		}
		return false;
	}

	public static String convertDateObjectToDateString(Date date) {
		return sdf.format(date);
	}

}
