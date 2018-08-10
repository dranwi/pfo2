package com.andy.pfoEjb.vd;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class QuoteSelectDetailVD implements Serializable {
	private static final long serialVersionUID = 4014999571606435035L;
	
	String selectedSymbol;
	String selectedCurrency;
	
	public QuoteSelectDetailVD() {
		
	}

	public String getSelectedSymbol() {
		return selectedSymbol;
	}

	public void setSelectedSymbol(String selectedSymbol) {
		this.selectedSymbol = selectedSymbol;
	}

	public String getSelectedCurrency() {
		return selectedCurrency;
	}

	public void setSelectedCurrency(String selectedCurrency) {
		this.selectedCurrency = selectedCurrency;
	}
	
	

}
