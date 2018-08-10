package com.andy.pfoWeb;

import java.util.Arrays;
import java.util.List;

import com.andy.pfoModel.Quote;
import com.andy.pfoWebHelper.ToStringConverter;

public class QuoteItem {
	private final static List<String> currencies = Arrays.asList("USD", "CAD", "AUD");	
	String symbol;
	String date;
	String currency;
	String value;
	ToStringConverter converter = new ToStringConverter();
	
	
	
	public QuoteItem(Quote quote) {
		this.symbol = quote.getSymbol();
		this.date = quote.getDate();
		Double dValue = quote.getValue();
		if (isCurrency(quote)) {
			this.value = converter.fromDouble4(dValue);	
		} else {
			if (dValue < 3) {
				this.value = converter.fromDouble3(dValue);	
			} else {
				this.value = converter.fromDouble(dValue);
			}
		}
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	private boolean isCurrency(Quote quote) {		
		if(currencies.contains(quote.getSymbol())) {
			return true;
		} else {
			return false;
		}
	}

}
