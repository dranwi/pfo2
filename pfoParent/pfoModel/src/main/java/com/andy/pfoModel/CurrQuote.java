package com.andy.pfoModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CURR_QUOTE2")
public class CurrQuote {
	@Id
	@GeneratedValue
	@Column(name="QUOTE_ID")
	long id;
	
	@Column(name="SYMBOL")
	String symbol;
	
	@Column(name="DATUM")
	String date;
	
	@Column(name="VALUE")
	Double value;
	
	public CurrQuote() {
	}

	public CurrQuote(String symbol, String date, Double value) {
		this.symbol = symbol;
		this.date = date;
		this.value = value;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	

}
