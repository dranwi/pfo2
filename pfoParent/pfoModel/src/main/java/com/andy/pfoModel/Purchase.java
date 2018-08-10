package com.andy.pfoModel;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="P")
public class Purchase extends Trade{
	
	public Purchase() {
		super();
	}
	
/*	public Purchase(Stock stock, String date,Integer amount, Double quote, String curr) {		
		super(stock, date, amount, quote, curr);
	}
*/	
	public Purchase(Stock stock, String date,Integer amount, Double quote) {		
		super(stock, date, amount, quote);
	}
}
