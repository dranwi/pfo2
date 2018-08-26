package com.andy.pfoWebHelper;

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
		if (dayString.compareTo("31") > 0) {
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
		if (monthString.compareTo("12") > 0) {
			throw new ValidatorException( new FacesMessage("Incorrect month"));
		}		
	}
	
	public void checkYear(String yearString) {
		try {
			Integer.parseInt(yearString);
		} catch (NumberFormatException ex) {
			throw new ValidatorException( new FacesMessage("Incorrect year"));
		}
		if ((yearString.compareTo("2000") < 0) || (yearString.compareTo("2030") > 0) || (yearString.length() < 4)) {
			throw new ValidatorException( new FacesMessage("Incorrect year"));
		}		
	}

}
