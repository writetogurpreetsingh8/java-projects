package com.trade.fare.bean;

public class TradeBean {
	
	//Matching weight for different fields are - 
	//first_party = 10 %, second_party = 10 %, product = 20 %, amount = 25 %, trade_ref = 35 %
	private String firstParty;
	private String secondParty;
	private int amount;
	private String tradeRef;
	private String location;
	private String product;
	
	// just to keep trace that how many trades has been generated so far!!
	private int tradeNumber;
	private static int tradeNumberCounter;
	
	public TradeBean() {
		TradeBean.tradeNumberCounter++;
		this.tradeNumber = TradeBean.tradeNumberCounter;
	}
	public String getFirstParty() {
		return firstParty;
	}
	public void setFirstParty(String firstParty) {
		this.firstParty = firstParty;
	}
	public String getSecondParty() {
		return secondParty;
	}
	public void setSecondParty(String secondParty) {
		this.secondParty = secondParty;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTradeRef() {
		return tradeRef;
	}
	public void setTradeRef(String tradeRef) {
		this.tradeRef = tradeRef;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	
	public int getTradeNumber() {
		return tradeNumber;
	}
	
	public static int getTradeNumberCounter() {
		return tradeNumberCounter;
	}
	@Override
	public String toString() {
		return "Trade_"+tradeNumber+" [amount=" + amount + ", tradeRef=" + tradeRef + ", location=" + location+ ", firstParty=" + firstParty
				+", secondParty=" + secondParty + ", product=" + product + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		int abc1 = (firstParty == null) ? 0 : firstParty.hashCode();
		int abc2 = (secondParty == null) ? abc1 : secondParty.hashCode();
		result = (prime * 1 + abc1+abc2);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean found = false;
		if (this == obj)
			return true;
		if (obj == null)
			found = false;
		if (getClass() != obj.getClass())
			found = false;
		TradeBean other = (TradeBean) obj;
		if( (firstParty == null || secondParty == null) || (other.firstParty == null || other.secondParty == null)){
			found = false;
		}
		/*
		 * else if(firstParty.equals(other.firstParty) &&
		 * secondParty.equals(other.secondParty)) { return false; }
		 */else if(!firstParty.equals(other.secondParty) && !secondParty.equals(other.firstParty)) {
			 found = false;
		}else {
			found = true;
		}
		if(found) {
			if((location == null || other.location == null) || (product == null || other.product == null) 
					|| (tradeRef == null || other.tradeRef == null)) {
				found = false;
			}else {
				found = (other.location.equals(location) && other.product.equals(product) && 
						other.tradeRef.equals(tradeRef) && other.tradeNumber == tradeNumber);
			}
		}
		return found;
	}
	

}
