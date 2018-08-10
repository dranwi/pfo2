package com.andy.pfoWebHelper;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;


public class ToStringConverter {
	private static Logger logger = Logger.getLogger("com.andy.pfoWeb.StringConverter");
	
	public ToStringConverter() {
		
	}
	
	public String fromInteger(Integer intValue) {
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		String stringInteger = nf.format(intValue);
		return stringInteger;		
	}
	
	public String fromDouble(Double dValue) {
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		String quote = nf.format(dValue);
		return quote;
	}
	
	public String fromDouble3(Double dValue) {
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		nf.setMinimumFractionDigits(3);
		nf.setMaximumFractionDigits(3);
		String quote = nf.format(dValue);
		return quote;
	}
	
	public String fromDouble4(Double dValue) {
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		nf.setMinimumFractionDigits(4);
		nf.setMaximumFractionDigits(4);
		String quote = nf.format(dValue);
		return quote;
	}
	
	public String fromMargin(Double margin) {
		margin = margin * 100;
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		String stringMargin = nf.format(margin);
		if(Objects.isNull(stringMargin) ||  stringMargin.length() <= 2) {
			stringMargin = "0,00";
		}
		return stringMargin + "%";
	}
	
	public Double toDouble(String value) {
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		Number number;
		try {
			number = nf.parse(value);			
		} catch (Exception ex) {
			logger.info("FORMAT EXEPTION FOR: " + value);
			return new Double(0.0);
		}
		Double d = (Double)number;
		return d;
		
	}
	
	public String todayString() {
		LocalDate date = LocalDate.now();

		String year = String.valueOf(date.getYear());
		String month = String.valueOf(date.getMonthValue());
		String day = String.valueOf(date.getDayOfMonth());
		
		DateString dateString =  new DateString(day, month, year);
		logger.info("TODAY STRING: " + dateString.getString());
		return dateString.getString();
	}
	
	

}
