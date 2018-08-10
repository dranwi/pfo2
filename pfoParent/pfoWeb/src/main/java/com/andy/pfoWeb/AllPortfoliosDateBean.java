
package com.andy.pfoWeb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.pfoEjb.vd.PortfolioDateDetailVD;
import com.andy.pfoWebHelper.DateString;

@SessionScoped
@Named("AllPortfoliosDateBean")
public class AllPortfoliosDateBean implements Serializable{
	private static final long serialVersionUID = 7015595080332693735L;

	@Inject
	private AllPortfoliosBean allPortfoliosBean;
	
	@Inject
	PortfolioDateDetailVD portfolioDateDetailVD;
	
	String referenceDateString;
	
	public AllPortfoliosDateBean() {
		
	}
	
	public String dateAction() throws Exception{
		referenceDateString = this.makeDate(portfolioDateDetailVD);
		allPortfoliosBean.setReferenceDate(referenceDateString);
		return "DATE_OK";
	}
	
	private String makeDate(PortfolioDateDetailVD  vd) throws Exception{
		String year = vd.getYear();
		String month = vd.getMonth();
		String day = vd.getDay();
		String dateString = new DateString(day,month,year).getString();
		return dateString;
	}
	
	public String getYear() {
		return portfolioDateDetailVD.getYear();
	}
	
	public String getMonth() {
		return portfolioDateDetailVD.getMonth();
	}
	
	public String getDay() {
		return portfolioDateDetailVD.getDay();
	}
	
	public void setYear(String year) {
		portfolioDateDetailVD.setYear(year);
	}
	
	public void setMonth(String month) {
		portfolioDateDetailVD.setMonth(month);
	}
	
	public void setDay(String day) {
		portfolioDateDetailVD.setDay(day);
	}

}
