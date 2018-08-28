package com.andy.pfoWeb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.pfoModel.Portfolio;
import com.andy.pfoModel.Stock;
import com.andy.pfoEjb.session.PortfolioSession;


@SessionScoped
@Named("PortfolioManageBean")
public class PortfolioManageBean implements Serializable {
	private static final long serialVersionUID = 4929013357748365496L;
	
	@Inject
	private PortfolioSession portfolioSession;
	
	@Inject
	private PortfolioViewBean portfolioViewBean;
	
	
	Portfolio selectedPortfolio;
	List<Stock> stockList;
	String selectedPortfolioName;
	List<Portfolio> portfolioList;

	public PortfolioManageBean() {}
	
	public List<Portfolio> getPortfolioList() {
		portfolioList = portfolioSession.findAll();
		return portfolioList;
	}

	public void setPortfolioList(List<Portfolio> portfolioList) {
		this.portfolioList = portfolioList;
	}
	
	public String getSelectedPortfolioName() {
		return selectedPortfolioName;
	}
		
	public void setSelectedPortfolioName(String selectedPortfolioName) {
		this.selectedPortfolioName = selectedPortfolioName;
	}
	
	public String dateAction() {
		this.findPortfolio();
		portfolioViewBean.setPortfolio(selectedPortfolio);
		if (selectedPortfolio == null) {			
			return "ERROR";
		}
		return "GET_DATE";
	}
	
	public String includeStockAction() {
		this.findPortfolio();
		if (selectedPortfolio == null) {			
			return "ERROR";
		}
		System.out.println("FOUND SELECTED PORTFOLIO WITH NAME:" + selectedPortfolio.getName());
		return "INCLUDE";
	}
	
	public String buyStockAction() {
		this.findPortfolio();
		if (selectedPortfolio == null) {
			return "ERROR";
		}
		System.out.println("FOUND SELECTED PORTFOLIO WITH NAME:" + selectedPortfolio.getName());
		return "BUY";
	}
	
	public String sellStockAction() {
		this.findPortfolio();
		if (selectedPortfolio == null) {
			return "ERROR";
		}
		System.out.println("FOUND SELECTED PORTFOLIO WITH NAME:" + selectedPortfolio.getName());
		return "SELL";
	}
	
	public String viewPortfolioAction() {
		this.findPortfolio();
		if (selectedPortfolio == null) {
			return "ERROR";
		}		
		System.out.println("FOUND SELECTED PORTFOLIO WITH NAME:" + selectedPortfolio.getName());
		return "VIEW";
	}
	
	private void findPortfolio() {
		try {
			selectedPortfolio = portfolioSession.findByName(selectedPortfolioName);
			selectedPortfolioName = null;
		} catch (Exception ex) {
			System.out.println("NO SELECTED PORTFOLIO FOUND");
		}
	}

	public Portfolio getSelectedPortfolio() {
		return selectedPortfolio;
	}

	public void setSelectedPortfolio(Portfolio selectedPortfolio) {
		this.selectedPortfolio = selectedPortfolio;
	}
	
	
/*	
	public void portfolioSelectManageAction() {
		String name = portfolioSelectActionListener.getName();
		selectedPortfolio = portfolioSession.findByName(name);
		portfolioName = selectedPortfolio.getName();
		System.out.println("completed PortfolioManageBean.portfolioSelectManageAction()");
	}
	
	public void portfolioSelectViewAction() {
		String name = portfolioSelectActionListener.getName();
		selectedPortfolio = portfolioSession.findByName(name);
		portfolioName = selectedPortfolio.getName();
		System.out.println("completed PortfolioManageBean.portfolioSelectViewction()");
	}




	public List<Stock> getStockList() {
		if (stockList == null) {
			stockList = stockSession.findAll();
		}
		return stockList;
	}
	

	public Portfolio getSelectedPortfolio() {
		return selectedPortfolio;
	}

	public void sendStockSelectAction() {
		
		String name = stockSelectActionListener.getName();
		Stock selectedStock = stockSession.findByName(name);
		selectedPortfolio.addStock(selectedStock);
		//portfolioSession.update(selectedPortfolio);
		stockSession.update(selectedStock);
	}
		
*/	
}
