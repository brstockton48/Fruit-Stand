//AUTHOR: [Billy Stockton]
//COURSE: CPT 237
//PURPOSE: [The class provides details for each item purchased]
//STARTDATE: [10/20/2020]
package edu.tridenttech.CPT237.program1;

public class PurchaseItem {
	//Non-Constant Class Attributes
	private String name="";
	private double price=0.0;
	private double amount=0.0;

	//The PurchaseItem class constructor
	public PurchaseItem(String name, double price, double amount){
		this.name=name;
		this.price=price;
		this.amount=amount;
	}//End of PurchaseItem Class Constructor
	//Getters
	//Returns item name
	public String getName() {
		return name;
	}//End of getName
	//Returns item price
	public double getPrice() {
		return price;
	}//End of getPrice
	//Returns item weight
	public double getAmount() {
		return amount;
	}//End of getAmount
	//Returns calculated cost
	public double calculateCost() {
		return price*amount;
	}//End of calculateCost
}//End of PurchaseItem Supportive Class
