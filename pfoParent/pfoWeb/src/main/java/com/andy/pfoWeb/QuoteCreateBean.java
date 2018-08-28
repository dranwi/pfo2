package com.andy.pfoWeb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.pfoModel.Quote;
import com.andy.pfoModel.Stock;
import com.andy.pfoWebHelper.DateString;
import com.andy.pfoWebHelper.FromStringConverter;
import com.andy.pfoWebHelper.PfoValidator;
import com.andy.pfoEjb.session.QuoteSession;
import com.andy.pfoEjb.session.StockSession;
import com.andy.pfoEjb.vd.QuoteDetailVD;

@Named("QuoteCreateBean")
@SessionScoped
public class QuoteCreateBean implements Serializable{
	private static final long serialVersionUID = -1357449093077947576L;
	//private static Logger logger = Logger.getLogger("com.andy.portfolioWeb.QuoteCreateBean");
	
	@Inject 
	StockSession stockSession;
	
	@Inject
	QuoteSession quoteSession;
	
	@Inject
	QuoteDetailVD quoteDetailVD;
	
	FromStringConverter fromStringConverter = new FromStringConverter();
	PfoValidator validator = new PfoValidator();
	
	public QuoteCreateBean() {
	}
	
	
	public String quoteAction() throws Exception {
		String symbol = quoteDetailVD.getSymbol();
		if(symbol == null) {
			return "MISSING_STOCKSYMBOL";
		}
		String dateString = this.makeDate(quoteDetailVD);
		Quote quote = quoteSession.findBySymbolAndDate(symbol, dateString);
		Double value = fromStringConverter.toDouble(quoteDetailVD.getValue());

		if (quote != null) {
			quote.setValue(value);
			quoteSession.updateQuote(quote);
			this.setValue(null);
			return "QUOTE_UPDATED";
		} else {
			quote = new Quote(symbol, dateString, value);
			quoteSession.createQuote(quote);
			this.setValue(null);
			return "QUOTE_CREATED";
		}
	}
	
	String makeDate(QuoteDetailVD vd) throws Exception{
		String year = vd.getYear();
		String month = vd.getMonth();
		String day = vd.getDay();
		String dateString = new DateString(day,month,year).getString();
		return dateString;
	}
	
	public List<String> getSymbolList() {
		List<String> symbolList = new ArrayList<String>();
		List<Stock> stockList = stockSession.findAll();
		for (Stock stock : stockList) {
			symbolList.add(stock.getName());
		}
		return symbolList;		
	}
	
	public void validateYear(FacesContext fc, UIComponent c, Object value) {
		validator.checkYear((String)value);		
	}
	
	public void validateMonth(FacesContext fc, UIComponent c, Object value) {
		validator.checkMonth((String)value);
	}
	
	public void validateDay(FacesContext fc, UIComponent c, Object value) {
		validator.checkDay((String) value);
	}
	
	public void validateValue(FacesContext fc, UIComponent c, Object value) {
		validator.checkQuote((String)value);		
	}


	public String getSelectedSymbol() {
		return quoteDetailVD.getSymbol();
	}


	public void setSelectedSymbol(String selectedSymbol) {
		quoteDetailVD.setSymbol(selectedSymbol);
	}


	public String getYear() {
		return quoteDetailVD.getYear();
	}


	public void setYear(String year) {
		quoteDetailVD.setYear(year);
	}


	public String getMonth() {
		return quoteDetailVD.getMonth();
	}


	public void setMonth(String month) {
		quoteDetailVD.setMonth(month);
	}


	public String getDay() {
		return quoteDetailVD.getDay();
	}


	public void setDay(String day) {
		quoteDetailVD.setDay(day);
	}


	public String getValue() {
		return quoteDetailVD.getValue();
	}


	public void setValue(String value) {
		quoteDetailVD.setValue(value);
	}

}
