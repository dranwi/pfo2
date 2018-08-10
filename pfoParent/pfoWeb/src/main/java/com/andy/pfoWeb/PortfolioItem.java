package com.andy.pfoWeb;

import java.util.logging.Logger;

import com.andy.pfoWebHelper.ToStringConverter;

public class PortfolioItem {
	private static Logger logger = Logger.getLogger("com.andy.portfolioWeb.PortfolioItem"); 
	
	String name;
	String tradeValue;
	Double tradeValueD;
	String presValue;
	Double presValueD;
	Double investmentD;
	String profit;
	Double profitD;
	String margin;
	Double marginD;
	Integer color;	
	ToStringConverter converter;
	
	public PortfolioItem() {
		converter = new ToStringConverter();
		tradeValueD = 0.0;
		presValueD = 0.0;
		investmentD = 0.0;
		profitD = 0.0;
		marginD = 0.0;
	}
	
	public void convertToString() {
		tradeValue = converter.fromInteger(tradeValueD.intValue());
		presValue = converter.fromInteger(presValueD.intValue());
		profitD = presValueD - tradeValueD;
		profit = converter.fromInteger(profitD.intValue());
		marginD = profitD / investmentD;
		margin = converter.fromMargin(marginD);
	}
	
	public void addTradeValue(Double value) {
		tradeValueD = tradeValueD + value;
	}
	
	public void addPresValue(Double value) {
		presValueD = presValueD + value;
	}
	
	public void addInvestment(Double value) {
		investmentD = investmentD + value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTradeValue() {
		return tradeValue;
	}

	public void setTradeValue(String tradeValue) {
		this.tradeValue = tradeValue;
	}

	public String getPresValue() {
		return presValue;
	}

	public void setPresValue(String presValue) {
		this.presValue = presValue;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public Double getTradeValueD() {
		return tradeValueD;
	}

	public void setTradeValueD(Double tradeValueD) {
		this.tradeValueD = tradeValueD;
	}

	public Double getPresValueD() {
		return presValueD;
	}

	public void setPresValueD(Double presValueD) {
		this.presValueD = presValueD;
	}

	public Double getProfitD() {
		return profitD;
	}

	public void setProfitD(Double profitD) {
		this.profitD = profitD;
	}

	public Double getMarginD() {
		return marginD;
	}

	public void setMarginD(Double marginD) {
		this.marginD = marginD;
	}

	public Double getInvestment() {
		return investmentD;
	}

	public void setInvestment(Double investment) {
		this.investmentD = investment;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}
	
	
	
}
