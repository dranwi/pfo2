package com.andy.pfoEjb.service;

import java.util.List;

import javax.ejb.Local;

import com.andy.pfoModel.CurrQuote;
//import com.andy.pfoModel.Quote;

@Local
public interface CurrQuoteService {
	public void persist(CurrQuote quote);
	public void update(CurrQuote quote);
	public List<CurrQuote> findBySymbol(String symbol);
	public CurrQuote findBySymbolAndDate(String symbol, String date);
}
