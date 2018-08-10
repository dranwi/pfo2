package com.andy.pfoWeb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.pfoModel.*;
import com.andy.pfoWebHelper.FromStringConverter;
import com.andy.pfoWebHelper.ToStringConverter;
import com.andy.pfoEjb.session.CurrQuoteSession;
import com.andy.pfoEjb.session.QuoteSession;
import com.andy.pfoEjb.vd.PortfolioDateDetailVD;

@SessionScoped
@Named("PortfolioViewBean")
public class PortfolioViewBean implements Serializable {
	private static final long serialVersionUID = 4746686264842756850L;
	private static Logger logger = Logger.getLogger("com.andy.pfoWeb.PortfolioViewBean");
	
	List<TradeItem> tradeItemList;
	Portfolio portfolio;
	List<Stock> stockList;
	String rowClasses;
	ToStringConverter toStringConverter;
	FromStringConverter fromStringConverter;
	String pfoTotalTradeSumString;
	String pfoTotalPresSumString;
	String pfoTotalProfitString;
	String pfoTotalMarginString;
	
	Double pfoTotalProfitD;
	Double pfoTotalMarginD;
	String referenceDate;
	
	@Inject
	QuoteSession quoteSession;
	
	@Inject
	CurrQuoteSession currQuoteSession;
	
	@Inject
	PortfolioDateDetailVD portfolioDateDetailVD;

	public PortfolioViewBean() {
		toStringConverter = new ToStringConverter();
		fromStringConverter = new FromStringConverter();
	}
	
	public String getTableTitel() {		
		String tableTitel = "Portfolio " + portfolio.getName() + " on " + referenceDate;
		return tableTitel;
	}
	
	public List<TradeItem> getTradeItemList() {
		stockList = portfolio.getStockList();
		stockList.sort(new StockComparator());
		tradeItemList = new ArrayList<TradeItem>();
		Double pfoTotalTradeSum = 0.0;
		Double pfoTotalBuySum = 0.0;
		Double pfoTotalPresSum = 0.0;
		Integer color = 1;

		for (Stock s : stockList) {
			Double presQuote = 0.0;
			Double presForeignQuote = 0.0;
			Double presCurrencyQuote = 0.0;
			
			boolean presQuoteFound = false;
						
			Integer totalAmount = 0;
			Double totalTradeSum = 0.0;
			Double totalPresSum = 0.0;
			Double totalProfit = 0.0;
			Double totalMargin = 0.0;
			Double totalBuySum = 0.0;

			List<TradeItem> subTradeItemList = new ArrayList<TradeItem>();
			
			List<Trade> tradeList = s.getTradeList();
			List<Trade> timedTradeList = tradeList
										.stream()
										.filter(t -> t.getDate().compareTo(referenceDate) <= 0)
										.collect(Collectors.toList()); 

			logger.info("PROCESSING: " + s.getName());
			for (Trade t : timedTradeList) {
				TradeItem item = new TradeItem();

				// Date
				item.setDate(t.getDate());

				// Trade
				String tType = (t instanceof Purchase) ? "BUY" : "SELL";
				item.setTradeType(tType);

				// Symbol
				item.setSymbol(s.getName());

				// Currency
				item.setCurrency(s.getCurrency());

				// Amount
				Integer amount = t.getAmount();
				if (t instanceof Sale) {
					amount = amount * (-1);
				}
				String amountString = toStringConverter.fromInteger(amount);
				item.setAmount(amountString);

				totalAmount = totalAmount + amount;
				
				if (!presQuoteFound) {
					presQuote = this.findQuote(s.getName(), referenceDate);
					
					if(s.isForeign()) {
						presCurrencyQuote = this.findCurrQuote(s.getCurrency(), referenceDate);
						presForeignQuote = presQuote;
						presQuote = presQuote / presCurrencyQuote;
					} else {
						presForeignQuote = null;
					}
					
					presQuoteFound = true;
				}
								
				if (s.isForeign()) {
					
					//Foreign Trade Quote
					String foreignTradeQuoteString = toStringConverter.fromDouble(t.getForQuote());
					item.setForTradeQuote(foreignTradeQuoteString);
					
					//Exch.Rate for foreign trade
					Double currencyQuote = t.getForQuote() /  t.getQuote() ;
					String currencyQuoteString = toStringConverter.fromDouble4(currencyQuote);
					item.setCurrQuote(currencyQuoteString);
					
					//Present Foreign Quote
					String presForeignQuoteString = toStringConverter.fromDouble(presForeignQuote);
					item.setPresForQuote(presForeignQuoteString);						
				}

				// TradeQuote
				String tradeQuote = toStringConverter.fromDouble(t.getQuote());
				item.setTradeQuote(tradeQuote);
			
				// Pres EUR Quote
				String presQuoteString = toStringConverter.fromDouble(presQuote);
				item.setPresQuote(presQuoteString);			
				

				// TradeSum
				Double amountD = fromStringConverter.toDouble(item.getAmount());
				logger.info("AMOUNT: " + amountD);
				Double tradeSum = amountD * t.getQuote();
				logger.info("TRADESUM: " + tradeSum);
				String tradeSumString = toStringConverter.fromInteger(tradeSum.intValue());
				logger.info("TRADESUM_STRING: " + tradeSumString);
				item.setTradeSum(tradeSumString);

				totalTradeSum = totalTradeSum + tradeSum;
				pfoTotalTradeSum = pfoTotalTradeSum + tradeSum;
				
				if(t instanceof Purchase) {
					totalBuySum = totalBuySum + tradeSum;
					pfoTotalBuySum = pfoTotalBuySum + tradeSum;
				}

				// PresSum
				Double presSum = amountD * presQuote;
				String presSumString = toStringConverter.fromInteger(presSum.intValue());
				item.setPresSum(presSumString);

				totalPresSum = totalPresSum + presSum;
				pfoTotalPresSum = pfoTotalPresSum + presSum;

				// Profit
				Double profit = presSum - tradeSum;
				String profitString = toStringConverter.fromInteger(profit.intValue());
				item.setProfit(profitString);
				item.setProfitInt(profit.intValue());

				// Margin
				Double margin = profit / tradeSum;
				if(t instanceof Sale) {
					margin = margin * (-1.0);
				}
				String stringMargin = toStringConverter.fromMargin(margin);
				item.setMargin(stringMargin);
				item.setMarginD(margin);

				subTradeItemList.add(item);
			}	//end Trade loop
			
			if (subTradeItemList.size() == 0) {
				continue;
			}
			
			color = color * (-1);
			this.setColor(subTradeItemList,color);

			subTradeItemList.sort(new TradeItemComparator());
			TradeItem firstItem = subTradeItemList.get(0);

			//Total Aount 
			String totalAmountString = toStringConverter.fromInteger(totalAmount);
			firstItem.setTotalAmount(totalAmountString);

			String totalTradeSumString = toStringConverter.fromInteger(totalTradeSum.intValue());
			firstItem.setTotalTradeSum(totalTradeSumString);

			//Total Pres Sum
			String totalPresSumString = toStringConverter.fromInteger(totalPresSum.intValue());
			firstItem.setTotalPresSum(totalPresSumString);

			//Total Profit
			totalProfit = totalPresSum - totalTradeSum;
			String totalProfitString = toStringConverter.fromInteger(totalProfit.intValue());
			firstItem.setTotalProfit(totalProfitString);
			firstItem.setTotalProfitInt(totalProfit.intValue());

			//totalMargin = totalProfit / totalTradeSum;
			totalMargin = totalProfit / totalBuySum;
			String totalMarginString = toStringConverter.fromMargin(totalMargin);
			firstItem.setTotalMargin(totalMarginString);
			firstItem.setTotalMarginD(totalMargin);

			tradeItemList.addAll(subTradeItemList);
		}	// end Stock loop
		
		this.createRowClasses();
		pfoTotalTradeSumString = toStringConverter.fromInteger(pfoTotalTradeSum.intValue());
		pfoTotalPresSumString = toStringConverter.fromInteger(pfoTotalPresSum.intValue());
		pfoTotalProfitD = new Double(pfoTotalPresSum - pfoTotalTradeSum);
		pfoTotalMarginD =  new Double((pfoTotalPresSum - pfoTotalTradeSum) / pfoTotalBuySum);
		pfoTotalProfitString = toStringConverter.fromInteger(pfoTotalProfitD.intValue());
		pfoTotalMarginString = toStringConverter.fromMargin(pfoTotalMarginD);
		return tradeItemList;	
	}
	
	public String getPfoTotalTradeSum() {
		return pfoTotalTradeSumString;
	}
	
	public String getPfoTotalPresSum() {
		return pfoTotalPresSumString;
	}
	
	public String getPfoTotalProfit() {
		return pfoTotalProfitString;
	}
	
	public String getPfoTotalMargin() {
		return pfoTotalMarginString;
	}
	
	void createRowClasses() {
		int length = tradeItemList.size();
		int counter = 0;
		StringBuilder sb = new StringBuilder("");
		String rowClass = null;
		for (TradeItem item : tradeItemList) {
			counter = counter + 1;
			if (item.getTradeType().equals("SELL")) {
				rowClass = " p";
			} else {
				rowClass = (item.getColor() == 1) ? " g" : " y";				
			}
			sb.append(rowClass);
			if (counter < length) {
				sb.append(",");
			}
		}
		rowClasses = sb.toString();	
		logger.info("ROW_CLASSES: " + rowClasses);
	}

	public String getRowClasses() {
		logger.info("GET_ROW_CLASSES CALLED");
		return rowClasses;
	}
	
	
	void setColor(List<TradeItem> tradeItemList, Integer color) {
		for(TradeItem item : tradeItemList) {
			item.setColor(color);
		}
	}
	
	Double findQuote(String symbol, String date) {
		Quote quote = quoteSession.findBySymbolAndDate(symbol, date);
		return quote.getValue();
	}
	
	Double findCurrQuote(String symbol, String date) {
		CurrQuote currQuote = currQuoteSession.findBySymbolAndDate(symbol, date);
		return currQuote.getValue();
	}
	
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	
	
	
	public void setReferenceDate(String referenceDate) {
		this.referenceDate = referenceDate;
	}

	public Double getPfoTotalProfitD() {
		return pfoTotalProfitD;
	}

	public void setPfoTotalProfitD(Double pfoTotalProfitD) {
		this.pfoTotalProfitD = pfoTotalProfitD;
	}

	public Double getPfoTotalMarginD() {
		return pfoTotalMarginD;
	}

	public void setPfoTotalMarginD(Double pfoTotalMarginD) {
		this.pfoTotalMarginD = pfoTotalMarginD;
	}
	
	
}
