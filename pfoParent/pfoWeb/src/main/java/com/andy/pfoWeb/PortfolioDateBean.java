package com.andy.pfoWeb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;


import com.andy.pfoEjb.vd.PortfolioDateDetailVD;
import com.andy.pfoWebHelper.DateString;
import com.andy.pfoWebHelper.PfoValidator;

@SessionScoped
@Named("PortfolioDateBean")
public class PortfolioDateBean implements Serializable{
	private static final long serialVersionUID = 4817174866508938205L;
	
	@Inject
	private PortfolioViewBean portfolioViewBean;
	
	@Inject
	PortfolioDateDetailVD portfolioDateDetailVD;
	
	String referenceDateString;
	PfoValidator validator = new PfoValidator();
	
	public PortfolioDateBean() {
	}
	
	public String dateAction() throws Exception{
		referenceDateString = this.makeDate(portfolioDateDetailVD);
		portfolioViewBean.setReferenceDate(referenceDateString);
		return "DATE_OK";
	}
	
	private String makeDate(PortfolioDateDetailVD  vd) throws Exception{
		String year = vd.getYear();
		String month = vd.getMonth();
		String day = vd.getDay();
		String dateString = new DateString(day,month,year).getString();
		return dateString;
	}
	
	public void validateYear(FacesContext fc, UIComponent c, Object value) {
		validator.checkYear((String)value);		
	}
	
	public void validateMonth(FacesContext fc, UIComponent c, Object value) {
		validator.checkMonth((String)value);
	}
	
	public void validateDay(FacesContext fc, UIComponent c, Object value) {
		validator.checkDay((String) value);
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
