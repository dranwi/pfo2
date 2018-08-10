package com.andy.pfoWeb;


public class TradeItem {
	
	String date;
	String tradeType;
	String symbol;
	String currency;
	String amount;
	String forTradeQuote;
	String currQuote;
	String tradeQuote;
	String presForQuote;
	String presQuote;
	String tradeSum;
	String totalAmount;
	String totalTradeSum;
	String totalPresSum;
	String totalProfit;
	String totalMargin;
	String presSum;
	String profit;
	
	String margin;
	Integer color;
	
	Integer profitInt;
	Integer totalProfitInt; 
	Double marginD;
	Double totalMarginD;
	
	public TradeItem() {
		totalAmount = null;
		totalTradeSum = null;
		totalPresSum = null;
		totalProfit = null;
		totalMargin = null;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTradeQuote() {
		return tradeQuote;
	}

	public void setTradeQuote(String tradeQuote) {
		this.tradeQuote = tradeQuote;
	}

	public String getPresQuote() {
		return presQuote;
	}

	public void setPresQuote(String presQuote) {
		this.presQuote = presQuote;
	}

	public String getTradeSum() {
		return tradeSum;
	}

	public void setTradeSum(String tradeSum) {
		this.tradeSum = tradeSum;
	}

	public String getPresSum() {
		return presSum;
	}

	public void setPresSum(String presSum) {
		this.presSum = presSum;
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

	public String getTotalTradeSum() {
		return totalTradeSum;
	}

	public void setTotalTradeSum(String totalTradeSum) {
		this.totalTradeSum = totalTradeSum;
	}

	public String getTotalPresSum() {
		return totalPresSum;
	}

	public void setTotalPresSum(String totalPresSum) {
		this.totalPresSum = totalPresSum;
	}

	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getTotalMargin() {
		return totalMargin;
	}

	public void setTotalMargin(String totalMargin) {
		this.totalMargin = totalMargin;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public String getForTradeQuote() {
		return forTradeQuote;
	}

	public void setForTradeQuote(String forTradeQuote) {
		this.forTradeQuote = forTradeQuote;
	}

	public String getCurrQuote() {
		return currQuote;
	}

	public void setCurrQuote(String currQuote) {
		this.currQuote = currQuote;
	}

	public String getpresForQuote() {
		return presForQuote;
	}

	public void setPresForQuote(String presForQuote) {
		this.presForQuote = presForQuote;
	}

	public Integer getProfitInt() {
		return profitInt;
	}

	public void setProfitInt(Integer profitInt) {
		this.profitInt = profitInt;
	}

	public Integer getTotalProfitInt() {
		return totalProfitInt;
	}

	public void setTotalProfitInt(Integer totalProfitInt) {
		this.totalProfitInt = totalProfitInt;
	}

	public Double getMarginD() {
		return marginD;
	}

	public void setMarginD(Double marginD) {
		this.marginD = marginD;
	}

	public Double getTotalMarginD() {
		return totalMarginD;
	}

	public void setTotalMarginD(Double totalMarginD) {
		this.totalMarginD = totalMarginD;
	}
	
	
}
