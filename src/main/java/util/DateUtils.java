package util;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class DateUtils {
	private static Calendar c = Calendar.getInstance();
	
	public static Date getDateByString(String date){
		String dateString = date.split(" ")[0];
		String d[] = dateString.split("-");
		
		int year = Integer.parseInt(d[0]);
		int month = Integer.parseInt(d[1]);
		int day = Integer.parseInt(d[2]);
		
		return new Date(year, month, day);
	}
	
	public static Time getTimeByString(String time){
		String dateString = time.split(" ")[1];
		String t[] = dateString.split(":");
		
		int hour = Integer.parseInt(t[0]);
		int min = Integer.parseInt(t[1]);
		
		return new Time(hour, min, 0);
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
	
	public static Time getTimeByHoursInt(int hourInt){
		Time time = new Time(hourInt, 0, 0);
		return time;
	}
}
