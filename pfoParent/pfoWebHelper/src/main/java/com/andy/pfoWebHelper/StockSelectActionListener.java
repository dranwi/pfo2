package com.andy.pfoWebHelper;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Named;

@Named("StockSelectActionListener")
@RequestScoped
public class StockSelectActionListener implements ActionListener{
private String name;
	
	public void processAction(ActionEvent event) {
		UIComponent component = event.getComponent();
		name = getStockName(component);
		
	}
	
	private String getStockName (UIComponent component) {
		Map<String, Object> attributeMap = component.getAttributes();
		return (String) attributeMap.get("name");
	}
	
	public String getName() {
		return name;
	}

}