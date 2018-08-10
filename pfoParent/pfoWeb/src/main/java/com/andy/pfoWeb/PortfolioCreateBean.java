package com.andy.pfoWeb;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.pfoWebHelper.MessageBean;
import com.andy.pfoEjb.session.PortfolioSession;
import com.andy.pfoEjb.vd.PortfolioDetailVD;


@SessionScoped
@Named("PortfolioCreateBean")
public class PortfolioCreateBean implements Serializable{
	private static final long serialVersionUID = -4836768038297913312L;
	
		private static Logger logger = Logger.getLogger("com.andy.bankWeb.PortfolioCreateBean");
		@Inject 
		private PortfolioSession portfolioSession;
		@Inject
		private PortfolioDetailVD portfolioDetailVD;
		@Inject
		private MessageBean messageBean;
		
		
		public PortfolioCreateBean() {}
		
		public String portfolioCreateAction() {	
			logger.info("Starting sendPortfolioCreateAction");
			String name = portfolioDetailVD.getName();
			if(name == null || name.length() == 0) {
				messageBean.setMessage("No portfolio created" );
				return "ERROR";
			}
			
			boolean success = portfolioSession.createPortfolio(name);		
			if (success) {
				logger.info("Success sendPOrtfolioCreateAction");
				return "PORTFOLIO_CREATED";
			} else {
				logger.info("Error sendPortfolioCreateAction");
				messageBean.setMessage("Customer already exists" );
				return "ERROR";
			}
		}
		
		public void setName(String name) {
			portfolioDetailVD.setName(name);
		}
		
		
		public String getName() {
			return portfolioDetailVD.getName();
		}

	}

