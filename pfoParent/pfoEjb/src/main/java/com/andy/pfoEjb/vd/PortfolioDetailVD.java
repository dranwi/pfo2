package com.andy.pfoEjb.vd;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PortfolioDetailVD  implements Serializable{
	private static final long serialVersionUID = -1247512283246249759L;
	
	private String name;
	
	public PortfolioDetailVD() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
