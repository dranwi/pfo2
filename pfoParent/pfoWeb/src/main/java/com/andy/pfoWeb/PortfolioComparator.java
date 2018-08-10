package com.andy.pfoWeb;

import java.util.Comparator;

import com.andy.pfoModel.Portfolio;

public class PortfolioComparator implements Comparator<Portfolio>{
	
	public int compare(Portfolio p1, Portfolio p2) {		
		return p1.getName().compareTo(p2.getName());
	}
}
