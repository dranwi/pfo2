package com.andy.pfoEjb.session;

import java.util.List;

import javax.ejb.Local;

import com.andy.pfoModel.Portfolio;


@Local
public interface PortfolioSession {
	public boolean createPortfolio(String name);
	public List<Portfolio> findAll();
	public Portfolio findByName(String name);
	public void update(Portfolio portfolio);
}
