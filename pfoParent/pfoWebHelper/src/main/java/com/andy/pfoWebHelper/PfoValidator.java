package com.andy.pfoWebHelper;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

public class PfoValidator {
	
	public PfoValidator() {
		
	}
	
	public void checkDay(String dayString) {
		try {
			Integer.parseInt(dayString);
		} catch (NumberFormatException ex) {
			throw new ValidatorException( new FacesMessage("Incorrect day"));
		}
		if(dayString.length() < 2) {
			dayString = "0" + dayString;
		}
		if ((dayString.length() > 2) || (dayString.compareTo("31") > 0)) {
			throw new ValidatorException( new FacesMessage("Incorrect day"));
		}	
	}
	
	public void checkMonth(String monthString) {
		try {
			Integer.parseInt(monthString);
		} catch (NumberFormatException ex) {
			throw new ValidatorException( new FacesMessage("Incorrect month"));
		}
		if(monthString.length() < 2) {
			monthString = "0" + monthString;
		}
		if ((monthString.length() > 2) || (monthString.compareTo("12") > 0)) {
			throw new ValidatorException( new FacesMessage("Incorrect month"));
		}		
	}
	
	public void checkYear(String yearString) {
		try {
			Integer.parseInt(yearString);
		} catch (NumberFormatException ex) {
			throw new ValidatorException( new FacesMessage("Incorrect year"));
		}
		if ((yearString.compareTo("2000") < 0) || (yearString.compareTo("2030") > 0) || (yearString.length() < 4) || (yearString.length() > 4)) {
			throw new ValidatorException( new FacesMessage("Incorrect year"));
		}		
	}
	
	public void checkQuote(String quoteString) {
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		try {
			nf.parse(quoteString).doubleValue();
		} catch (ParseException ex) {
			throw new ValidatorException(new FacesMessage("Incorrect value"));
		}
	}
	
	public void checkAmount(String amountString) {
		try {
			Integer amount = Integer.parseInt(amountString);
			if (amount < 1) throw new NumberFormatException();
		} catch (NumberFormatException ex) {
			throw new ValidatorException( new FacesMessage("Incorrect amount"));
		}
	}

}
