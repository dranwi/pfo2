package com.andy.pfoEjb.service;

import java.util.List;

import javax.ejb.Local;

import com.andy.pfoModel.Stock;

@Local
public interface StockService {
	public Stock findByName(String name);
	public void persist(Stock stock);
	public List<Stock> findAll();
	public void merge(Stock stock);
}
