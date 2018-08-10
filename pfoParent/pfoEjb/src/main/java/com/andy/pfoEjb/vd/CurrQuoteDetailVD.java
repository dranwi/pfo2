package com.andy.pfoEjb.vd;

import java.io.Serializable;
import java.time.LocalDate;

import javax.enterprise.context.RequestScoped;


@RequestScoped
public class CurrQuoteDetailVD implements Serializable {
	private static final long serialVersionUID = -6972712396984160623L;
	
	String symbol;
	String year;
	String month;
	String day;
	String value;
	
	public CurrQuoteDetailVD() {
		LocalDate date = LocalDate.now();
		Integer y = date.getYear();
		Integer m = date.getMonthValue();
		Integer d = date.getDayOfMonth();
		year = String.valueOf(y);
		month = String.valueOf(m);
		day = String.valueOf(d);
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
