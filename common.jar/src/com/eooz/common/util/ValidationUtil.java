package com.eooz.common.util;

import java.util.Date;
import java.util.Calendar;

public class ValidationUtil {

	public static boolean exists(String value) {
		return true;
	}

	public static boolean validEmail(String email) {
		return true;
	}

	public static boolean length(String comparator, int num) {
		return true;
	}

	public static boolean isRole(String role) {
		return true;
	}

	public static boolean isNumeric(String basic) {
		return true;
	}

	public static boolean isDate(String startdate) {
		// TODO Auto-generated method stub
		return true;
	}
		 
	public static boolean isValidDateRange(Date date1, Date date2) {
	  Calendar cal1 = Calendar.getInstance();
	  Calendar cal2 = Calendar.getInstance();
	  cal1.setTime(date1);
	  cal2.setTime(date2);
	  if ((cal2.get(Calendar.MONTH) >= cal1.get(Calendar.MONTH)) && (cal2.get(Calendar.DATE) >= cal1.get(Calendar.DATE)) && (cal2.get(Calendar.YEAR) >= cal1.get(Calendar.YEAR)))
	  {
		  return true;
	}else{
		return false;
	}
	}

}
