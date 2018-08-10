package com.andy.pfoEjb.session;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.andy.pfoEjb.service.PortfolioService;
import com.andy.pfoModel.Portfolio;



@Stateless
public class PortfolioSessionImpl implements PortfolioSession, Serializable{
	private static final long serialVersionUID = -8185183677126192701L;
	
	@Inject
	private PortfolioService dao; 
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean createPortfolio(String name) {

		Portfolio p = dao.findByName(name);
		if (p != null) {
			// name is not unique
			return false;
		}
		
		Portfolio portfolio = new Portfolio();
		portfolio.setName(name);
		
		dao.persist(portfolio);
		return true;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Portfolio> findAll() {
		return dao.findAll();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Portfolio findByName(String name) {
		try {
			return dao.findByName(name);
		} catch (Exception ex) {
			return null;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Portfolio portfolio) {
		dao.merge(portfolio);
	}

}
