package com.andy.pfoModel;



import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;



@Entity
@Table(name="TRADE2")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   
@DiscriminatorColumn(name="DISC") //Default column type is CHAR
@org.hibernate.annotations.Proxy(lazy = false)
public class Trade {
	@Id
	@GeneratedValue
	@Column(name="TRADE_ID")	
	long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="STOCK_ID", nullable = false)
	Stock stock;
	
	@Column(name="AMOUNT")
	Integer amount;
	
	@Column(name="DATUM")
	String date;
	
	@Column(name="QUOTE")
	Double quote;
	
	@Column(name="FOR_QUOTE")
	Double forQuote;
	
//	@Column(name="CURR")
//	String curr;
	
	@Column(name="VALUE")
	Double value;
	
	public Trade() {
		
	}
	
//	public Trade (Stock stock, String date, Integer amount, Double quote, String curr) {
	public Trade (Stock stock, String date, Integer amount, Double quote) {
		this.stock = stock;
		this.date = date;
		this.amount = amount;
		this.quote = quote;
		this.value = amount * quote;
		//this.curr = curr;
	}
	
	

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Double getQuote() {
		return quote;
	}
	public void setQuote(Double quote) {
		this.quote = quote;
	}

	public Double getForQuote() {
		return forQuote;
	}

	public void setForQuote(Double forQuote) {
		this.forQuote = forQuote;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

/*	public String getCurr() {
		return curr;
	}

	public void setCurr(String curr) {
		this.curr = curr;
	}
*/	
}
