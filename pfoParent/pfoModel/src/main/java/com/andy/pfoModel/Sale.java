package com.andy.pfoModel;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="S")
public class Sale extends Trade {
	
	public Sale () {
		super();
	}
	
/*	public Sale (Stock stock, String date, Integer amount, Double quote, String curr) {		
		super(stock, date, amount, quote, curr);
	}
*/
	public Sale (Stock stock, String date, Integer amount, Double quote) {		
		super(stock, date, amount, quote);
	}
	
}
