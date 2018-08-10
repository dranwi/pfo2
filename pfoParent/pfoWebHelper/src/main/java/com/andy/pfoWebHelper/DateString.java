package com.andy.pfoWebHelper;

public class DateString {
	String year;
	String month;
	String day;
	
	public DateString(String day, String month, String year) {
		if(day.length() == 1) {
			this.day = "0" + day;
		} else {
			this.day = day;
		}
		if (month.length() == 1) {
			this.month = "0" + month;
		} else {
			this.month = month;
		}
		this.year = year;		
	}
	
	public String getString() {
		return year + "." + month + "." + day;
	}

}
