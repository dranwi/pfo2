package com.andy.pfoEjb.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.andy.pfoWebHelper.MessageBean;
import com.andy.pfoModel.Stock;


@Stateless
public class StockServiceImpl implements StockService, Serializable {
	private static final long serialVersionUID = 4321182743197996303L;

	@PersistenceContext(unitName="portfolioUnit2")
	private EntityManager em;
		
	@Inject
	private MessageBean messageBean;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Stock findByName(String name) {
		
			Stock stock = null;
			Query q = em.createQuery("select s from Stock s where s.name = :name");
			q.setParameter("name", name);
			try {
				stock = (Stock)q.getSingleResult();
			} catch (NoResultException nex) {
				messageBean.setMessage("No stock found");
				return null;
			} catch (Exception ex) {
				messageBean.setMessage("DB access problem");
				return null;
			}
			return stock;
		
		}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Stock stock) {
		em.persist(stock);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Stock> findAll() {
		Query q = em.createQuery("select s from Stock s");
		List<Stock> stockList = (List<Stock>)q.getResultList();	
		return stockList;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void merge(Stock stock) {
		System.out.println("MERGING STOCK " + stock.getName());
		System.out.println("STOCK LINKED TO PORTFOLIO " + stock.getPortfolio().getName());
		em.merge(stock);
	}
		
	}
