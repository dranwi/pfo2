package com.andy.pfoWeb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.andy.pfoModel.Portfolio;
import com.andy.pfoModel.Stock;

import com.andy.pfoEjb.session.StockSession;

import javax.inject.Inject;

@Named("StockAddBean")
@SessionScoped
public class StockAddBean implements Serializable{
	private static final long serialVersionUID = -1926750269658077343L;
	
	@Inject
	StockSession stockSession;
	@Inject
	PortfolioManageBean portfolioManageBean;
	
	List<Stock> stockList;
	List<Stock> selectedStockList;
	Portfolio selectedPortfolio;
	String selectedStockName;
	
	public StockAddBean() {
		stockList = new ArrayList<Stock>();
	}

	public List<Stock> getStockList() {
		
		stockList = stockSession.findAll();
		selectedPortfolio = portfolioManageBean.getSelectedPortfolio();
		if (selectedPortfolio == null) {
			return new ArrayList<Stock>();
		}
		selectedStockList = selectedPortfolio.getStockList();		
		List<Stock> cleanedList = this.removeFromListByName(stockList, selectedStockList);		
		return cleanedList;
	}
	
	private List<Stock> removeFromListByName(List<Stock> list, List<Stock> removeList) {
		List<Stock> cleanedList = new ArrayList<Stock>();
		for(Stock stock : list) {
			boolean found = false;
			for(Stock s : removeList) {
				if (stock.getName().equals(s.getName())) {
					found = true;
				}
			}
			if (!found) {
				cleanedList.add(stock);
			}			
		}
		return cleanedList;
	}

	public String getSelectedStockName() {
		return selectedStockName;
	}

	public void setSelectedStockName(String selectedStockName) {
		this.selectedStockName = selectedStockName;
	}
	
	public String selectStockAction() {		
		Stock stock = stockSession.findByName(selectedStockName);
		selectedPortfolio.addStock(stock);
		stockSession.update(stock);
		selectedStockList.add(stock);
		return "OK";
	}
	
	

}
