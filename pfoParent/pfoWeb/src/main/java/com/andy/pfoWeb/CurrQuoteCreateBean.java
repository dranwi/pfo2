package com.andy.pfoWeb;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.pfoEjb.service.CurrQuoteService;
import com.andy.pfoEjb.session.CurrQuoteSession;
import com.andy.pfoEjb.vd.CurrQuoteDetailVD;
import com.andy.pfoEjb.vd.QuoteDetailVD;
import com.andy.pfoModel.CurrQuote;
import com.andy.pfoModel.Quote;
import com.andy.pfoWebHelper.DateString;
import com.andy.pfoWebHelper.FromStringConverter;


@Named("CurrQuoteCreateBean")
@SessionScoped
public class CurrQuoteCreateBean implements Serializable {
	private static final long serialVersionUID = 6758894473588583174L;

	@Inject
	CurrQuoteSession currQuoteSession;
	
	@Inject
	CurrQuoteDetailVD currQuoteDetailVD;
	
	FromStringConverter fromStringConverter = new FromStringConverter();
	
	public CurrQuoteCreateBean() {}
		
	public String quoteAction() throws Exception {
		String symbol = currQuoteDetailVD.getSymbol();
		String dateString = this.makeDate(currQuoteDetailVD);
		CurrQuote quote = currQuoteSession.findBySymbolAndDate(symbol, dateString);
		Double value = fromStringConverter.toDouble(currQuoteDetailVD.getValue());

		if (quote != null) {
			quote.setValue(value);
			currQuoteSession.updateQuote(quote);
			currQuoteDetailVD.setValue(null);
			return "QUOTE_UPDATED";
		} else {
			quote = new CurrQuote(symbol, dateString, value);
			currQuoteSession.createCurrQuote(quote);
			currQuoteDetailVD.setValue(null);
			return "QUOTE_CREATED";
		}
	}
	
	String makeDate(CurrQuoteDetailVD vd) throws Exception{
		String year = vd.getYear();
		String month = vd.getMonth();
		String day = vd.getDay();
		String dateString = new DateString(day,month,year).getString();
		return dateString;
	}
	
	public List<String> getSymbolList() {
		List<String> symbolList = Arrays.asList("USD", "CAD", "AUD");
		return symbolList;
	}
	
	public String getSelectedSymbol() {
		return currQuoteDetailVD.getSymbol();
	}
	
	public void setSelectedSymbol(String symbol) {
		currQuoteDetailVD.setSymbol(symbol);
	}
	
	public String getYear() {
		return currQuoteDetailVD.getYear();
	}
	
	public void setYear(String year) {
		currQuoteDetailVD.setYear(year);
	}
	
	public String getMonth() {
		return currQuoteDetailVD.getMonth();
	}
	
	public void setMonth(String month) {
		currQuoteDetailVD.setMonth(month);
	}
	
	public String getDay() {
		return currQuoteDetailVD.getDay();
	}
	
	public void setDay(String day) {
		currQuoteDetailVD.setDay(day);
	}
	
	public String getValue() {
		return currQuoteDetailVD.getValue();
	}


	public void setValue(String value) {
		currQuoteDetailVD.setValue(value);
	}


}
