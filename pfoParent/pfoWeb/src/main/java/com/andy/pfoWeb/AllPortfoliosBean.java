package com.andy.pfoWeb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.pfoEjb.session.CurrQuoteSession;
import com.andy.pfoEjb.session.PortfolioSession;
import com.andy.pfoEjb.session.QuoteSession;
import com.andy.pfoModel.CurrQuote;
import com.andy.pfoModel.Portfolio;
import com.andy.pfoModel.Purchase;
import com.andy.pfoModel.Quote;
import com.andy.pfoModel.Sale;
import com.andy.pfoModel.Stock;
import com.andy.pfoModel.Trade;
import com.andy.pfoWebHelper.ToStringConverter;

@SessionScoped
@Named("AllPortfoliosBean")
public class AllPortfoliosBean implements Serializable{
	private static final long serialVersionUID = -5233445467221358790L;
	private static Logger logger = Logger.getLogger("com.andy.portfolioWeb.AllPortfoliosBean"); 
	
	List<PortfolioItem> portfolioItemList;
	String rowClasses;
	String referenceDate;
	String totalTradeSum;
	String totalPresSum;
	String totalProfit;
	String totalMargin;
	
	@Inject
	PortfolioSession portfolioSession;
	
	@Inject
	QuoteSession quoteSession;
	
	@Inject
	CurrQuoteSession currQuoteSession;
	
	ToStringConverter converter;
	
	
	public AllPortfoliosBean() {
		converter = new ToStringConverter();
	}
	
	public List<PortfolioItem> getPortfolioItemList() {
		Double totalTradeSumD = 0.0;
		Double totalPresSumD = 0.0;
		Double totalProfitD = 0.0;		
		Double totalInvestSumD = 0.0;
		portfolioItemList = new ArrayList<PortfolioItem>();
		
		List<Portfolio> portfolioList = portfolioSession.findAll();
		portfolioList.sort(new PortfolioComparator());
		Integer color = 1;
		for(Portfolio p : portfolioList) {			
			PortfolioItem portfolioItem = new PortfolioItem();
			color = color * (-1);
			portfolioItem.setName(p.getName());
			List<Stock> stockList = p.getStockList();
			for (Stock s : stockList) {
				String curr = s.getCurrency();
				CurrQuote currQuote = null;
				Double exchR = 1.0;
				if (isForeign(curr)) {
					currQuote = currQuoteSession.findBySymbolAndDate(curr, referenceDate);
					exchR = currQuote.getValue();
				}
								
				List<Trade> tradeList = s.getTradeList();
				Double tradeSum = 0.0;
				Double investSum = 0.0;
				Integer tradeAmountSum = 0;

				for (Trade t : tradeList) {
					Double tradeValue = t.getValue();
					Integer tradeAmount = t.getAmount();
					if (t instanceof Sale) {
						tradeSum = tradeSum - tradeValue;
						tradeAmountSum = tradeAmountSum - tradeAmount;
					} else {
						tradeSum = tradeSum + tradeValue;
						tradeAmountSum = tradeAmountSum + tradeAmount;
						investSum = investSum + tradeValue;
					}
				}
				Quote quote = quoteSession.findBySymbolAndDate(s.getName(), referenceDate);
				Double quoteValue = quote.getValue();
				if (isForeign(curr)) {
					quoteValue = quoteValue / exchR;
				}								
				Double presValue = tradeAmountSum * quoteValue;
				portfolioItem.addTradeValue(tradeSum);
				portfolioItem.addPresValue(presValue);
				portfolioItem.addInvestment(investSum);
				
/*				logger.info(s.getName() + " TRADESUM=" + tradeSum);
				logger.info(s.getName()	+ " PRESVALUE=" + presValue);
				logger.info(s.getName()	+ " INVESTSUM=" + investSum);
				logger.info(s.getName()	+ " PROFIT=" + (presValue - tradeSum));
*/				
				totalTradeSumD = totalTradeSumD + tradeSum;
				totalPresSumD = totalPresSumD + presValue;
				totalInvestSumD = totalInvestSumD + investSum;
				totalProfitD = totalProfitD + (presValue - tradeSum);
				
			}
			portfolioItem.setColor(color);
			logger.info("PORTFOLIO: " + portfolioItem.getName() + " COLOUR: " + color);
			portfolioItem.convertToString();
			portfolioItemList.add(portfolioItem);
		}
		
		totalTradeSum = converter.fromInteger(totalTradeSumD.intValue());
		totalPresSum = converter.fromInteger(totalPresSumD.intValue());
		totalProfit = converter.fromInteger(totalProfitD.intValue());
		Double totalMarginD = totalProfitD / totalInvestSumD;
		totalMargin = converter.fromMargin(totalMarginD);
		portfolioItemList.sort(new PortfolioItemComparator());
		this.createRowClasses();
		return portfolioItemList;
	}
	
	void createRowClasses() {
		int length = portfolioItemList.size();
		int counter = 0;
		StringBuilder sb = new StringBuilder("");
		String rowClass = null;
		for (PortfolioItem item : portfolioItemList) {
			logger.info("PFO: " + item.getName() + " COLOUR: " + item.getColor());
			counter = counter + 1;
			rowClass = (item.getColor() == 1) ? " g" : " y";				
			sb.append(rowClass);
			if (counter < length) {
				sb.append(",");
			}
		}
		rowClasses = sb.toString();	
		logger.info("ALL PFO ROW_CLASSES: " + rowClasses);
	}
	
	public String getTableTitel() {
		return "All Portfolios on " + referenceDate;
	}
	
	public String getTotalTradeSum() {
		return totalTradeSum;
	}

	public String getTotalPresSum() {
		return totalPresSum;
	}

	public String getTotalProfit() {
		return totalProfit;
	}

	public String getTotalMargin() {
		return totalMargin;
	}

	public String getRowClasses() {
		return rowClasses;
	}

	public String getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(String referenceDate) {
		this.referenceDate = referenceDate;
	}

	private boolean isForeign(String symbol) {
		if ("EUR".equals(symbol)) {
			return false;
		} else {
			return true;
		}
	}
}
