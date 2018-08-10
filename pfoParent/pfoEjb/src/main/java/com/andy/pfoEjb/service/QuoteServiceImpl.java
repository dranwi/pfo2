package com.andy.pfoEjb.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ejb.TransactionAttributeType;

import com.andy.pfoWebHelper.MessageBean;
import com.andy.pfoWebHelper.ToStringConverter;
import com.andy.pfoModel.Quote;

@Stateless
public class QuoteServiceImpl implements Serializable, QuoteService {
	private static final long serialVersionUID = 8319421301106539628L;
	private static Logger logger = Logger.getLogger("com.andy.service.QuoteServiceImpl");
	
	@PersistenceContext(unitName="portfolioUnit2")
	EntityManager em;
	
	@Inject
	MessageBean messageBean;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Quote quote) {
		em.persist(quote);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Quote quote) {
		String symbol = quote.getSymbol();
		String date = quote.getDate();
		Double value = quote.getValue();
		
		Query query = em.createQuery("UPDATE Quote q SET q.value = :value WHERE q.symbol = :symbol AND q.date = :date");
		query.setParameter("symbol", symbol);
		query.setParameter("date", date);
		query.setParameter("value", value);
		query.executeUpdate();
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Quote> findBySymbol(String symbol) {
		//logger.info("QUOTESERVICE FINDBYSYMBOL CALLED WITH: " + symbol);
		List<Quote> quoteList = new ArrayList<Quote>();
		Query q = em.createQuery("select q from Quote q where q.symbol = :symbol");
		q.setParameter("symbol", symbol);
		try {
			//logger.info("QUERY BEING CALLED");
			quoteList = (List<Quote>) q.getResultList();
			//logger.info("QUERY EXECUTED");
		} catch (Exception ex) {
			messageBean.setMessage("DB access problem");
			logger.info("QUERY EXCEPTION FOR: " + symbol);
			return null;
		}
		return quoteList;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Quote findBySymbolAndDate(String symbol, String date) {
		//logger.info("QUOTESERVICE FINDBYSYMBOL CALLED WITH: " + symbol + " AND " + date);
		Quote quote = null;
		Query q = em.createQuery("select q from Quote q where q.symbol = :symbol AND q.date = :date");
	    q.setParameter("symbol", symbol);
	    q.setParameter("date", date);
	    try {
	    	//logger.info("QUOTE QUERY BEING CALLED");
	    	quote = (Quote)q.getSingleResult();
	    } catch (NoResultException ex) {
	    	logger.info("QUERY NORESULT EXCEPTION FOR: " + symbol + " AND " + date);
	    	return null;
	    } catch (Exception ex) {
		    logger.info("DB ACCESS PROBLEM");
		    return null;
		    }
	   return quote;
	}
	

}
