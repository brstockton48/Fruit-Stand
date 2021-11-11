//AUTHOR: [Billy Stockton]
//COURSE: CPT 237
//PURPOSE: [The class provides assembles array list
//for purchased items]
package edu.tridenttech.CPT237.program1;
import java.util.ArrayList;


public class SalesPurchase {
	//Purchase Item Array List
	ArrayList<PurchaseItem> itemList = new ArrayList<PurchaseItem>();

	//The SalesPurchase class constructor
	public SalesPurchase() {
	}
	//Adds item to Array List
	public void addItem(PurchaseItem item) {
		itemList.add(item);
	}//End of addItem
	//Returns Array List
	public ArrayList<PurchaseItem> getItemList() {
		return itemList;
	}//End of getItemList
	//Calculates and returns the total cost of all items in the array list
	public double getTotalCost(){
		double totalCost=0.0;
		for(PurchaseItem items : itemList) {
			totalCost += items.calculateCost();
		}
		return totalCost;
	}//End of getTotalCost
	//Returns the count of all items selected 
	public int getItemCount() {
		return itemList.size();	
	}//End of getItemCount
	//Returns the total weight of all items selected in the array list
	public double getTotalAmount() {
		double totalWeight=0.0;
		for(PurchaseItem items : itemList) {
			totalWeight += items.getAmount();
		}
		return totalWeight;
	}//End of getTotalAmount


}//End of SalesPurchase Supportive Class
