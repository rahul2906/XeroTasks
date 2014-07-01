package com.Xero.DataObjects;

public class Inventory {

	String itemCode;
	String description;
	String salesPrice;
	String salesAccount;
	
	public String getDescription() {
		return description;
	}public String getItemCode() {
		return itemCode;
	}public String getSalesAccount() {
		return salesAccount;
	}public String getSalesPrice() {
		return salesPrice;
	}public void setDescription(String description) {
		this.description = description;
	}public void setItemCode(String string) {
		this.itemCode = string;
	}public void setSalesAccount(String salesAccount) {
		this.salesAccount = salesAccount;
	}public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}
}
