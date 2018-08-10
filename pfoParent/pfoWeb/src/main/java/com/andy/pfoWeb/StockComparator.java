package com.andy.pfoWeb;

import java.util.Comparator;

import com.andy.pfoModel.Stock;

public class StockComparator implements Comparator<Stock> {
	
	public int compare(Stock stock1, Stock stock2) {
		return stock1.getName().compareTo( stock2.getName());
	}
	
}
