package com.andy.pfoWeb;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.pfoModel.*;
import com.andy.pfoWebHelper.DateString;
import com.andy.pfoWebHelper.FromStringConverter;
import com.andy.pfoWebHelper.PfoValidator;
import com.andy.pfoEjb.session.StockSession;
import com.andy.pfoEjb.vd.StockSellDetailVD;


@Named("StockSellBean")
@SessionScoped
public class StockSellBean implements Serializable {
	private static final long serialVersionUID = -5836528907193714299L;
	private static Logger logger = Logger.getLogger("com.andy.portfolioWeb.StockSellBean");
	
	@Inject
	PortfolioManageBean portfolioManageBean;
	
	@Inject
	StockSellDetailVD stockSellDetailVD;
	
	@Inject
	StockSession stockSession;
	
	Portfolio portfolio;
	List<Stock> stockList;
	Stock stock;
	String buyDate;
	PfoValidator validator = new PfoValidator();
	
	
	
	public StockSellBean() {
	}
	
	public String stockSellAction() throws Exception{
			
		String stockName = stockSellDetailVD.getSelectedStockName();
		stock = stockSession.findByName(stockName);
		Sale sale = new Sale();
		FromStringConverter fromStringConverter = new FromStringConverter();
		sale.setDate(makeDate(stockSellDetailVD));
//		sale.setCurr(stockSellDetailVD.getCurrency());
		Double quote = fromStringConverter.toDouble(stockSellDetailVD.getQuote());
		sale.setQuote(quote);
		Double forQuote = fromStringConverter.toDouble(stockSellDetailVD.getForQuote());
		sale.setForQuote(forQuote);
		
/*		if (foreign(stockSellDetailVD)) {
			//Double forQuote = fromStringConverter.toDouble(stockSellDetailVD.getForQuote());
		}
*/		
		Integer amount = fromStringConverter.toInteger(stockSellDetailVD.getAmount());
		sale.setAmount(amount);
		
		Double value = amount * quote;
		sale.setValue(value);
		
		stock.addTrade(sale);
		stockSession.update(stock);		
		return "STOCK_SOLD";
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
	
	public void validateQuote(FacesContext fc, UIComponent c, Object value) {
		validator.checkQuote((String) value);
	}
	
	public void validateForQuote(FacesContext fc, UIComponent c, Object value) {
		if (value != null) {		
			validator.checkQuote((String) value);
		}
	}
	
	public void validateAmount(FacesContext fc, UIComponent c, Object value) {
		validator.checkAmount((String) value);
	}
	
	String makeDate(StockSellDetailVD vd) throws Exception{
		String date;
		String year = vd.getYear();
		String month = vd.getMonth();
		String day = vd.getDay();
		date = new DateString(day,month,year).getString();
		return date;
	}
	
	public List<Stock> getStockList() {
		portfolio = portfolioManageBean.getSelectedPortfolio();
		stockList = portfolio.getStockList();	
		return stockList;
	}


	public String getSelectedStockName() {
		String name = stockSellDetailVD.getSelectedStockName();
		logger.info("SELECTED STOCK NAME: " + name);
		return name;
	}

	public void setSelectedStockName(String selectedStockName) {
		stockSellDetailVD.setSelectedStockName(selectedStockName);
	}
	
	public String getYear() {
		return stockSellDetailVD.getYear();
	}

	public void setYear(String year) {
		stockSellDetailVD.setYear(year);
	}

	public String getMonth() {
		return stockSellDetailVD.getMonth();
	}

	public void setMonth(String month) {
		stockSellDetailVD.setMonth(month);
	}

	public String getDay() {
		return stockSellDetailVD.getDay();
	}

	public void setDay(String day) {
		stockSellDetailVD.setDay(day);
	}

/*	public String getCurrency() {
		return stockSellDetailVD.getCurrency();
	}

	public void setCurrency(String currency) {
		stockSellDetailVD.setCurrency(currency);
	}
*/
	public String getQuote() {
		return stockSellDetailVD.getQuote();
	}

	public void setQuote(String quote) {
		stockSellDetailVD.setQuote(quote);
	}

	public String getAmount() {
		return stockSellDetailVD.getAmount();
	}

	public void setAmount(String amount) {
		stockSellDetailVD.setAmount(amount);
	}
	
	public String getForQuote() {
		return stockSellDetailVD.getForQuote();
	}

	public void setForQuote(String quote) {
		stockSellDetailVD.setForQuote(quote);
	}
	
}

	


