package com.andy.pfoEjb.session;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.andy.pfoModel.CurrQuote;
import com.andy.pfoModel.Quote;

@Local
public interface CurrQuoteSession {
	public void createCurrQuote(CurrQuote quote);
	public List<CurrQuote> findBySymbol(String symbol);
	public CurrQuote findBySymbolAndDate(String symbol, String date);
	public void updateQuote(CurrQuote quote);
	public Map<String,Double> findCurrTimeline(String symbol);
}
