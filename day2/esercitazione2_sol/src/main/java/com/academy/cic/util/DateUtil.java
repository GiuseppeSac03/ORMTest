package com.academy.cic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	
	public static Date stringToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		Date date = null;
		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return formatter.format(date);  
	}

}
