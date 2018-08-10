package com.andy.pfoEjb.service;

import java.util.List;

import javax.ejb.Local;
import com.andy.pfoModel.Portfolio;

@Local
public interface PortfolioService {
	public Portfolio findByName(String name);
	public void persist(Portfolio portfolio);
	public List<Portfolio> findAll();
	public void merge(Portfolio portfolio);
}
