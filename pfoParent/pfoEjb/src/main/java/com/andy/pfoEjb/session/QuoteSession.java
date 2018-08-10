package com.andy.pfoEjb.session;

import java.util.List;

import javax.ejb.Local;

import com.andy.pfoModel.Quote;

@Local
public interface QuoteSession {
	public void createQuote(Quote quote);
	public List<Quote> findBySymbol(String symbol);
	public Quote findBySymbolAndDate(String symbol, String date);
	public void updateQuote(Quote quote);
}
