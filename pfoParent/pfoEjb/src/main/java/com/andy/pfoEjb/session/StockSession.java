package com.andy.pfoEjb.session;

import java.util.List;

import javax.ejb.Local;

import com.andy.pfoModel.Stock;


@Local
public interface StockSession {
	public boolean createStock(String name, String currency);
	public List<Stock> findAll();
	public Stock findByName(String name);
	public Stock findByNameWithTimeline(String name);
	public void update(Stock stock);
}
