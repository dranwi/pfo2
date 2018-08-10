package com.andy.pfoEjb.session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.andy.pfoEjb.service.QuoteService;
import com.andy.pfoEjb.service.StockService;
import com.andy.pfoModel.Quote;
import com.andy.pfoModel.Stock;

import java.util.HashMap;


@Stateless
public class StockSessionImpl implements StockSession, Serializable {
	private static final long serialVersionUID = 1260563591991348148L;

	@Inject
	private StockService dao; 
	
	@Inject
	private QuoteService quoteDao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean createStock(String name, String currency) {

		Stock p = dao.findByName(name);
		if (p != null) {
			// name is not unique
			return false;
		}
		
		Stock stock = new Stock();
		stock.setName(name);
		stock.setCurrency(currency);
		
		dao.persist(stock);
		return true;
	}
	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Stock> findAll() {
		return dao.findAll();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Stock findByName(String name) {
		return dao.findByName(name);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Stock findByNameWithTimeline(String name) {
		Stock stock =  dao.findByName(name);
		List<Quote> quoteList = quoteDao.findBySymbol(name);
		Map<String,Double> timeline = new HashMap<String,Double>();
		for (Quote q : quoteList) {
			timeline.put(q.getDate(), q.getValue());
		}
		stock.setTimelineQuote(timeline);
		return stock;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Stock stock) {
		dao.merge(stock);
	}

}
