package com.andy.pfoWeb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.pfoModel.Stock;
import com.andy.pfoEjb.session.StockSession;
import com.andy.pfoEjb.vd.QuoteSelectDetailVD;

@Named("QuoteListSelectBean")
@SessionScoped
public class QuoteListSelectBean implements Serializable {
	private static final long serialVersionUID = -2660015622477305570L;
	
	@Inject
	QuoteSelectDetailVD quoteSelectDetailVD;

	@Inject
	StockSession stockSession;
	
	@Inject
	QuoteListBean quoteListBean;
	
	public QuoteListSelectBean() {}
	
	public List<String> getSymbolList() {
		List<Stock> stockList = stockSession.findAll();
		List<String> symbolList = new ArrayList<String>();
		for (Stock stock : stockList) {
			symbolList.add(stock.getName());
		}
		return symbolList;
	}
	
	public List<String> getCurrencyList() {
		List<String> list = new ArrayList<String>();
		list.add("USD");
		list.add("CAD");
		list.add("AUD");
		return list;
	}

	public String getSelectedSymbol() {
		return quoteSelectDetailVD.getSelectedSymbol();
	}

	public void setSelectedSymbol(String selectedSymbol) {
		quoteSelectDetailVD.setSelectedSymbol(selectedSymbol);
	}
	

	public String getSelectedCurrency() {
		return quoteSelectDetailVD.getSelectedCurrency();
	}

	public void setSelectedCurrency(String selectedCurrency) {
		quoteSelectDetailVD.setSelectedCurrency(selectedCurrency);
	}
	
	public String selectAction() {
		quoteListBean.setVd(quoteSelectDetailVD);
		return "SELECTION_OK";
	}
}

