package com.andy.pfoWeb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.pfoModel.Quote;
import com.andy.pfoModel.Stock;
import com.andy.pfoWebHelper.ToStringConverter;
import com.andy.pfoEjb.session.CurrQuoteSession;
import com.andy.pfoEjb.session.QuoteSession;
import com.andy.pfoEjb.session.StockSession;
import com.andy.pfoEjb.vd.QuoteSelectDetailVD;

@Named("QuoteListBean")
@SessionScoped
public class QuoteListBean implements Serializable {
	private static final long serialVersionUID = -3523180957944435853L;
	private static Logger logger = Logger.getLogger("com.andy.pfoWeb.QuoteListBean");
	
	@Inject
	QuoteSession quoteSession;
	
	@Inject 
	CurrQuoteSession currQuoteSession;

	@Inject
	StockSession stockSession;
	
	QuoteSelectDetailVD vd;
	

	
	public QuoteListBean() {}
	
	public void setVd(QuoteSelectDetailVD vd) {
		this.vd = vd;
	}

	public List<QuoteItem> getQuoteItemList() {
		String selectedSymbol = vd.getSelectedSymbol();
		String selectedCurrency = vd.getSelectedCurrency();
		if ((selectedSymbol == null) && (selectedCurrency == null)) {
			return new ArrayList<QuoteItem>();
		}
		if (selectedSymbol != null) {
			List<QuoteItem> quoteItemList = new ArrayList<QuoteItem>();
			Stock stock = stockSession.findByNameWithTimeline(selectedSymbol);
			Map<String, Double> timeline = stock.getTimelineQuote();
			List<String> keyList = new ArrayList<String>(timeline.keySet());
			keyList.sort(Comparator.naturalOrder());
			for (String date : keyList) {
				Quote q = new Quote(selectedSymbol, date, timeline.get(date));
				QuoteItem item = new QuoteItem(q);
				quoteItemList.add(item);
			}
			return quoteItemList;
		} else {
			List<QuoteItem> quoteItemList = new ArrayList<QuoteItem>();
			Map<String, Double> timeline = currQuoteSession.findCurrTimeline(selectedCurrency);
			List<String> keyList = new ArrayList<String>(timeline.keySet());
			keyList.sort(Comparator.naturalOrder());
			for (String date : keyList) {
				Quote q = new Quote(selectedCurrency, date, timeline.get(date));
				QuoteItem item = new QuoteItem(q);
				quoteItemList.add(item);
			}
			return quoteItemList;
		}
	}
	

	public String getValueHeader() {
		String selectedSymbol = vd.getSelectedSymbol();
		String selectedCurrency = vd.getSelectedCurrency();
		if ((selectedSymbol == null) && (selectedCurrency == null)) {
			return null;
		}
				
		if (selectedSymbol != null) {
			Stock stock = stockSession.findByName(selectedSymbol);
			return stock.getCurrency();
		} else {
			String exch = "EUR" + selectedCurrency;	
			return exch;
		}
		
		
	}
}
