package com.andy.pfoWeb;

import java.util.Comparator;

public class PortfolioItemComparator implements Comparator<PortfolioItem> {
	
	public int compare(PortfolioItem p1, PortfolioItem p2) {		
		return p1.getName().compareTo(p2.getName());
	}

}
