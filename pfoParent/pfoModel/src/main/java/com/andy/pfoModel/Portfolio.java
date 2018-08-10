package com.andy.pfoModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PORTFOLIO2")
@org.hibernate.annotations.Proxy(lazy = false)
public class Portfolio {
	@Id
	@GeneratedValue
	@Column(name="PORTFOLIO_ID")	
	private long id;
	
	@Column(name="NAME")
	String name;
	
	@OneToMany(mappedBy="portfolio", fetch = FetchType.EAGER)
	List<Stock> stockList;
	
	public Portfolio() {
		stockList = new ArrayList<Stock>();
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
	
	public void addStock(Stock s) {
		stockList.add(s);
		s.setPortfolio(this);
	}

}
