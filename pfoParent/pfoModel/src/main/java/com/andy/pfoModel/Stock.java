package com.andy.pfoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Table(name="STOCK2")
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
public class Stock {
	@Id
	@GeneratedValue
	@Column(name="STOCK_ID")
	private long id;
	
	@Column(name="NAME")
	String name;
	
	@Column(name="CURRENCY")
	String currency;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PORTFOLIO_ID") 
	Portfolio portfolio;
	
	@OneToMany(mappedBy="stock", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
	List<Trade>  tradeList;
	
	@Transient
	Map<String,Double> timelineQuote;
	
	public Stock() {
		tradeList = new ArrayList<Trade>();
	}

	public List<Trade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}
	
	public void addTrade(Trade t) {
		tradeList.add(t);
		t.setStock(this);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Map<String, Double> getTimelineQuote() {
		return timelineQuote;
	}

	public void setTimelineQuote(Map<String, Double> timelineQuote) {
		this.timelineQuote = timelineQuote;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public boolean isForeign() {
		if ("EUR".equals(currency)) {
			return false;
		} else {
			return true;
		}
	}

}
