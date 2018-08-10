package com.andy.pfoWebHelper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("MessageBean")
@RequestScoped
public class MessageBean {
	private String message;
	
	public MessageBean() {}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
