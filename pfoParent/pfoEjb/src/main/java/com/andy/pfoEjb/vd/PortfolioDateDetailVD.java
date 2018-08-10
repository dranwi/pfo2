package com.andy.pfoEjb.vd;

import java.io.Serializable;
import java.time.LocalDate;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PortfolioDateDetailVD implements Serializable{
	private static final long serialVersionUID = 6215440144637077322L;
	
	String year;
	String month;
	String day;
	
	
	public PortfolioDateDetailVD() {
		LocalDate date = LocalDate.now();
		Integer y = date.getYear();
		Integer m = date.getMonthValue();
		Integer d = date.getDayOfMonth();
		year = String.valueOf(y);
		month = String.valueOf(m);
		day = String.valueOf(d);
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
	
	

}
