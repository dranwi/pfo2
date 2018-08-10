package com.andy.pfoEjb.session;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.andy.pfoEjb.service.QuoteService;
import com.andy.pfoModel.Quote;

@Stateless
public class QuoteSessionImpl implements QuoteSession, Serializable {
	private static final long serialVersionUID = -3167219767779500476L;
	
	@Inject
	QuoteService dao;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void createQuote(Quote quote) {
		dao.persist(quote);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateQuote(Quote quote) {
		dao.update(quote);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Quote> findBySymbol(String symbol) {
		List<Quote> quoteList = dao.findBySymbol(symbol);
		return quoteList;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Quote findBySymbolAndDate(String symbol, String date) {
		Quote quote = dao.findBySymbolAndDate(symbol, date);
		return quote;
	}
}
