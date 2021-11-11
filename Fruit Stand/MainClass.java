//AUTHOR: [Billy Stockton]
//COURSE: CPT 237
//PURPOSE: [Users choose items from menu to purchase] 
//STARTDATE: [10/20/2020]
package edu.tridenttech.CPT237.program1;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	public static final char[] MENU_CHARS = { 'A', 'X' };
	public static final String[] MENU_OPTIONS = { "Add a new Customer Purchase", "Exit the program" };
	public static final char[] SUB_MENU_CHARS = {'A', 'B', 'C', 'P', 'S', 'Q'};	
	public static final String[] SUB_MENU_ITEMS = {"Apples", "Blueberries", "Cantaloupes", "Peaches", 
	"Strawberries"};	
	public static final double[] SUB_MENU_PRICES = {2.00, 3.00, 1.25, 2.50, 2.00};	


	public static void main(String[] args) {
		// declare and initialize Scanner
		Scanner input = new Scanner(System.in);
		//local variables
		char menuSelection=' ';
		//SalesPurchase Array List
		ArrayList<SalesPurchase> orderList = new ArrayList<SalesPurchase>();
		displayWelcomeBanner();
		menuSelection=validateMainMenu(input);
		while(menuSelection != 'X') {
			SalesPurchase salesPurchase = new SalesPurchase();
			menuSelection=validatePurchaseMenu(input);
			while(menuSelection != 'Q') {
				//Create instance of purchase item from menu and user input
				PurchaseItem purchaseItem = new PurchaseItem(SUB_MENU_ITEMS[getPurchaseSelectionIndex(menuSelection)], 
						SUB_MENU_PRICES[getPurchaseSelectionIndex(menuSelection)], validateItemWeight(input));
				salesPurchase.addItem(purchaseItem);//Adds instance of Purchase Item to array
				menuSelection=validatePurchaseMenu(input);//Purchase Menu(Inner) LCV	
			}//End of Purchase Menu Selection While (Inner Loop)
			orderList.add(salesPurchase);
			ArrayList<PurchaseItem>purchaseList=salesPurchase.getItemList();
			displayPurchaseItemList(purchaseList);
			menuSelection=validateMainMenu(input);//Main Menu (Outer) LCV
		}//End of Main Menu Selection While (Outer Loop)
		displayDailySummary(orderList);
		displayPurchaseHistory(orderList);
		displayDailyTotals(orderList);
		displayFarewellMessage();
		// Close Scanner
		input.close();
	}//End of Main Method
	// Void Methods
	//This void method displays the welcome banner
	public static void displayWelcomeBanner()
	{
		System.out.println("~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~");
		System.out.println("   Welcome to the Neighborhood Fruit Stand ");
		System.out.println(" Please Select the desired items from the menu");
		System.out.println("          Prices shown by the pound");
		System.out.println("~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~");
	}//end of Welcome Banner Display
	// This void method displays the main menu
	public static void displayMainMenu() {
		int localIndex = 0;
		System.out.printf("%n%s%n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%s%n%n","MAIN MENU");		
		while (localIndex < MENU_OPTIONS.length) {
			System.out.printf("%s%s%s%s%n", "", MENU_CHARS[localIndex],") ", MENU_OPTIONS[localIndex]);
			localIndex++;
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}// End of Main Menu Display
	// This void method displays the Purchase Menu
	public static void displayPurchaseMenu() {
		int localIndex = 0;
		System.out.printf("%n%s%n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%s%n%n","PURCHASE MENU");
		while (localIndex < SUB_MENU_ITEMS.length) {
			System.out.printf("%s%s%-19s%s%.2f%n", SUB_MENU_CHARS[localIndex],") ", SUB_MENU_ITEMS[localIndex],"$",SUB_MENU_PRICES[localIndex]);
			localIndex++;
		}
		System.out.println("Q) Complete the Transaction");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}// End of Purchase Menu Display
	//This void method Displays the Purchase Item List
	public static void displayPurchaseItemList(ArrayList<PurchaseItem> purchaseList) {
		double totalCost = 0.0;
		System.out.printf("%n%s%n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-21s%-8s%-9s%-6s%n","Item","Lbs","Price","Cost");
		for (PurchaseItem item : purchaseList) {
			System.out.printf("%n%-15s%9.1f%9.2f%9.2f", item.getName(), item.getAmount(), item.getPrice(), item.calculateCost());
			totalCost += item.calculateCost();
		}

		System.out.printf("%n%-36s%1s%5.2f%n", "Total", "$",totalCost);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of Purchase Item Display Report
	//This void method displays the Daily Summary Report
	public static void displayDailySummary(ArrayList<SalesPurchase> orderList) {
		String fruitType = "";
		double fruitCost = 0.0;
		double fruitWeight=0.0;
		double fruitSales = 0.0;
		double totalSales = 0.0;
		double totalWeight = 0.0;
		System.out.printf("%n%s%n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Purchase History");
		System.out.printf("%n%-16s%-8s%-12s%-6s%n","Fruit","Cost","Lbs Sold","Total Sales");
		for(int i=0; i<SUB_MENU_ITEMS.length; i++) {
			fruitType=SUB_MENU_ITEMS[i];
			fruitCost=SUB_MENU_PRICES[i];
			fruitWeight = getItemWeight(orderList, fruitType);//Calls getItemWeight VR Method
			fruitSales = fruitWeight*fruitCost;
			System.out.printf("%-16s%4.2f%12.1f%15.2f%n",fruitType,fruitCost,fruitWeight,fruitSales);
		}
		for (SalesPurchase sale : orderList) {
			totalSales += sale.getTotalCost();
			totalWeight += sale.getTotalAmount();
		}
		System.out.printf("%-26s%6.2f%15.2f%n","Total",totalWeight,totalSales);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of Daily Summary Display
	//This void method displays the Purchase History
	public static void displayPurchaseHistory(ArrayList<SalesPurchase> orderList) {
		System.out.printf("%n%s%n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Purchase History");
		System.out.printf("%n%-12s%-8s%n","Amt Sold","Purchase");
		for (SalesPurchase sale : orderList) {
			System.out.printf("%8.1f%12.2f%n", sale.getTotalAmount(), sale.getTotalCost());			
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of Purchase History Display
	//This void method displays the Daily Totals Report
	public static void displayDailyTotals(ArrayList<SalesPurchase> orderList) {
		int totalItems=0;
		int purchaseCount=0;
		double totalSales = 0.0;
		System.out.printf("%n%s%n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Daily Totals");
		for (SalesPurchase sale : orderList) {
			totalItems += sale.getItemCount();
			purchaseCount++;
			totalSales += sale.getTotalCost();
		}
		System.out.printf("%n%-25s%6d%n","Total items purchased:",totalItems);
		System.out.printf("%-25s%6d%n","Total Purchases:",purchaseCount);
		System.out.printf("%-25s%6.2f%n","Total sales:",totalSales);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//End of Daily Totals Report Method
	//Farewell Message
	public static void displayFarewellMessage()
	{
		System.out.printf("%n%s%n","~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~");
		System.out.println("          Have a Wonderful Day");
		System.out.println("~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~~ ~~~");
	}//End of Farewell Message	
	// VR Methods
	// This VR method validates Main Menu
	public static char validateMainMenu(Scanner menuSelection) {
		char localSelection = ' ';
		displayMainMenu();
		System.out.println("Enter your selection here ");
		localSelection = menuSelection.next().toUpperCase().charAt(0);
		while (localSelection != 'A' && localSelection != 'X') {
			System.out.println("That was an invalid selection, please try again");
			displayMainMenu();
			localSelection = menuSelection.next().toUpperCase().charAt(0);
		}
		return localSelection;
	}// end of validateMainMenu
	// This VR method validates Purchase Menu
	public static char validatePurchaseMenu(Scanner purchaseSelection) {
		char localSelection = ' ';
		displayPurchaseMenu();
		System.out.println("Please Choose a Menu Item ");
		localSelection = purchaseSelection.next().toUpperCase().charAt(0);
		while (localSelection != 'A' && localSelection != 'B' && localSelection != 'C'
				&& localSelection != 'P' && localSelection != 'S' && localSelection != 'Q') {
			System.out.println("That was an invalid selection, please try again");
			displayPurchaseMenu();
			localSelection = purchaseSelection.next().toUpperCase().charAt(0);
		}
		return localSelection;
	}// end of validatePurchaseMenu
	//VR method to return menu index
	public static int getPurchaseSelectionIndex(char purchaseSelection) {
		int localIndex = -1;
		boolean found = false;
		for(int i=0; i<SUB_MENU_CHARS.length && !found; i++) {
			if(purchaseSelection==SUB_MENU_CHARS[i]) {
				localIndex=i;
				found=true;
			}
		}
		return localIndex;
	}//End of Purchase Selection Index
	//This method prompts user for desired quantity
	public static double validateItemWeight(Scanner weightSelection) {
		double localSelection = 0.0;
		System.out.println("How many pounds would you like to purchase? ");
		localSelection = weightSelection.nextDouble();
		while(localSelection < 0.0) {
			System.out.println("That was an invalid selection, please try again");
		}
		return localSelection;
	}//End of Validate Weight Method
	//VR method to get total weight for each item receipt
	private static int getItemWeight(ArrayList<SalesPurchase> orderList, String fruitName) {
		int itemWeight = 0;
		for (SalesPurchase sale : orderList) {
			itemWeight += getTotalWeight(sale.getItemList(), fruitName);//Calls getTotalWeight VR Method
		}
		return itemWeight;
	}//End of method to get total weight per item receipt
	//VR method to get total weight per item for all combined receipts
	private static double getTotalWeight(ArrayList<PurchaseItem> purchaseList, String fruitName) {
		double totalWeight = 0.0;
		for (PurchaseItem itemName : purchaseList) {
			if (fruitName.equalsIgnoreCase(itemName.getName())) {
				totalWeight += itemName.getAmount();
			}
		}
		return totalWeight;
	}//End of method to get total weight per item for all receipts
}//End of Main Class
