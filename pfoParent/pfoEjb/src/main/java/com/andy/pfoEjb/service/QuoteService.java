package com.andy.pfoEjb.service;

import java.util.List;

import javax.ejb.Local;

import com.andy.pfoModel.Quote;

@Local
public interface QuoteService {
	public void persist(Quote quote);
	public void update(Quote quote);
	public List<Quote> findBySymbol(String symbol);
	public Quote findBySymbolAndDate(String symbol, String date);
}
