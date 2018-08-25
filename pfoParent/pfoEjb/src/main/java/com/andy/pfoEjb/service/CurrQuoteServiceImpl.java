package com.andy.pfoEjb.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.andy.pfoModel.CurrQuote;
//import com.andy.pfoModel.Quote;

@Stateless
public class CurrQuoteServiceImpl implements Serializable, CurrQuoteService {
	private static final long serialVersionUID = -7672765729653289108L;
	Logger logger = Logger.getLogger("com.andy.pfoEjb.service.CurrQuoteServiceImpl");
	
	@PersistenceContext(unitName="portfolioUnit2")
	EntityManager em;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)	
	public void persist(CurrQuote quote) {
		em.persist(quote);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(CurrQuote quote) {
		String symbol = quote.getSymbol();
		String date = quote.getDate();
		Double value = quote.getValue();
		
		Query query = em.createQuery("UPDATE CurrQuote q SET q.value = :value WHERE q.symbol = :symbol AND q.date = :date");
		query.setParameter("symbol", symbol);
		query.setParameter("date", date);
		query.setParameter("value", value);
		query.executeUpdate();
		
		
		
		em.merge(quote);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<CurrQuote> findBySymbol(String symbol) {
		List<CurrQuote> quoteList = new ArrayList<CurrQuote>();
		Query q = em.createQuery("select c from CurrQuote c where c.symbol = :symbol");
		q.setParameter("symbol", symbol);
		try {
			quoteList = (List<CurrQuote>) q.getResultList();
		} catch(Exception ex) {
			return null;
		}
		return quoteList;
	}
	
	@Override
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public CurrQuote findBySymbolAndDate(String symbol, String date) {
			//logger.info("QUOTESERVICE FINDBYSYMBOL CALLED WITH: " + symbol + " AND " + date);
			CurrQuote quote = null;
			Query q = em.createQuery("select c from CurrQuote c where c.symbol = :symbol AND c.date = :date");
		    q.setParameter("symbol", symbol);
		    q.setParameter("date", date);
		    try {
		    	//logger.info("QUOTE QUERY BEING CALLED");
		    	quote = (CurrQuote)q.getSingleResult();
		    } catch (NoResultException ex) {
		    	logger.info("QUOTE QUERY RESULTED IN EXCEPTION");
		    	return null;
		    } catch (Exception ex) {
		    	logger.info("DB ACCESS PROBLEM");
		    	return null;
		    }
		   return quote;
		}

}
