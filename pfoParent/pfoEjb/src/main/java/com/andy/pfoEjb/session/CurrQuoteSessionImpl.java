package com.andy.pfoEjb.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.andy.pfoEjb.service.CurrQuoteService;
import com.andy.pfoModel.CurrQuote;
import com.andy.pfoModel.Quote;

@Stateless
public class CurrQuoteSessionImpl implements CurrQuoteSession, Serializable {
	private static final long serialVersionUID = -5063522941951927249L;
	
	@Inject
	CurrQuoteService dao;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void createCurrQuote(CurrQuote quote) {
		dao.persist(quote);
	}
	

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateQuote(CurrQuote quote) {
		dao.update(quote);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<CurrQuote> findBySymbol(String symbol) {
		List<CurrQuote> currQuoteList = dao.findBySymbol(symbol);
		return currQuoteList;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CurrQuote findBySymbolAndDate(String symbol, String date) {
		CurrQuote quote = dao.findBySymbolAndDate(symbol, date);
		return quote;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Map<String,Double> findCurrTimeline(String symbol) {
		List<CurrQuote> currQuoteList = dao.findBySymbol(symbol);
		Map<String,Double> timeline = new HashMap<String,Double>();
		for (CurrQuote q : currQuoteList) {
			timeline.put(q.getDate(), q.getValue());
		}
		return timeline;
	}

}
