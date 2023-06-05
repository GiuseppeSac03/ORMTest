package com.academy.cic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.bytebuddy.asm.Advice.Exit;

public class DateUtil {
    
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String DATE_FORMAT_DB = "yyyy-MM-dd";
    
    public static Date stringToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date date = null;
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return date;
    }
    
    public static Date stringToDateDB(String strDate) {
    	
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_DB);
        Date date = null;
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return date;
    }
    
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(date);  
    }

}
