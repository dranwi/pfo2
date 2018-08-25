package com.andy.pfoEjb.service;

import com.andy.pfoModel.Portfolio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
//import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.andy.pfoWebHelper.MessageBean;
import com.andy.pfoEjb.service.PortfolioService;


@Stateless
public class PortfolioServiceImpl implements PortfolioService, Serializable {
	private static final long serialVersionUID = -2601170258063153166L;

	@PersistenceContext(unitName="portfolioUnit2")
	private EntityManager em;
		
	@Inject
	private MessageBean messageBean;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Portfolio findByName(String name) {
		
			Portfolio portfolio = null;
			Query q = em.createQuery("select p from Portfolio p where p.name = :name");
			q.setParameter("name", name);
			try {
				portfolio = (Portfolio)q.getSingleResult();
			} catch (NoResultException nex) {
				messageBean.setMessage("No customer found");
				return null;
			} catch (Exception ex) {
				messageBean.setMessage("DB access problem");
				return null;
			}
			return portfolio;
		
		}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Portfolio portfolio) {
		em.persist(portfolio);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Portfolio> findAll() {
		Query q = em.createQuery("select p from Portfolio p");
		List<Portfolio> portfolioList = (List<Portfolio>)q.getResultList();	
		return portfolioList;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void merge(Portfolio portfolio) {
		em.merge(portfolio);
	}
		
	}
