package com.andy.pfoWebHelper;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Named;

@Named("PortfolioSelectActionListener")
@RequestScoped
public class PortfolioSelectActionListener implements ActionListener{
private String name;
	
	public void processAction(ActionEvent event) {
		UIComponent component = event.getComponent();
		name = getPortfolioName(component);
		System.out.println("PortfolioSelectActionListener.processAction: name= " + name);
	}
	
	private String getPortfolioName (UIComponent component) {
		Map<String, Object> attributeMap = component.getAttributes();
		return (String) attributeMap.get("name");
	}
	
	public String getName() {
		return name;
	}

}
