package util;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class DateUtils {
	private static Calendar c = Calendar.getInstance();
	
	public static Date getDateByString(String date){
		System.out.println(date);
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		
		return new Date(year, month, day);
	}
	
	public static Time getTimeByString(String time){
		int hour = Integer.parseInt(time.substring(11, 13));
		int min = Integer.parseInt(time.substring(14, 16));
		int sec = Integer.parseInt(time.substring(17, 19));
		
		return new Time(hour, min, sec);
	}
	
	public static String getDateTimeString(Date date, Time time){
		String dateTimeString = "";
		
		dateTimeString += date.getYear() + "-" + date.getMonth() + "-" + date.getDate() + " ";
		dateTimeString += time.getHours() + ":" + time.getMinutes();
		
		return dateTimeString;
	}
	
	public static long getAddMillis(Date d, Time t, Time addTime){
		c.set(d.getYear()+1900, d.getMonth(), d.getDate(), t.getHours(), t.getMinutes());
		c.add(Calendar.MINUTE, (addTime.getHours()*60) + addTime.getMinutes());
		
		return c.getTimeInMillis();
	}
}
