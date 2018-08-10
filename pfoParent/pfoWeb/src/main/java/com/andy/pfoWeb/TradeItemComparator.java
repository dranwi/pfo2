package com.andy.pfoWeb;

import java.util.Comparator;

public class TradeItemComparator implements Comparator<TradeItem> {
	
	public int compare(TradeItem t1, TradeItem t2) {		
		return t1.getDate().compareTo(t2.getDate());
	}

}
