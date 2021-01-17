//Hint: Mod operator
//
//User Story #1
//The user is prompted asking for the price of the item.
//
//User Story #2
//The user is then prompted asking how much money was tendered by the customer.
//
//User Story #3
//Display an appropriate message if the customer provided too little money or the exact amount.
//
//User Story #4
//If the amount tendered is more than the cost of the item, display the number of bills and coins that should be given to the customer.
//Grading
//This is a graded project. You are expected to have your project completed by the start of class on Monday morning.
//
//You will be given either a pass or fail based on whether your code works given all of the following test conditions:
//
//Amount: .67, Tendered: .50, Result: Error message
//Amount: .67, Tendered: 1.00, Result: 1 quarter, 1 nickel, 3 pennies.
//Amount: .59, Tendered: 1.00, Result: 1 quarter, 1 dime, 1 nickel, 1 penny.
//Amount: 3.96, Tendered: 20.00, Result: 1 ten dollar bill, 1 five dollar bill, 1 one dollar bill, 4 pennies.
//Amount: any amount less than 20.00, Tendered: anything greater than amount: correct denominations for correct change.
//If the project does work with all of the above test conditions, you will be given a 1 for this week's project.
//
//If the project does not work with the above test conditions, you will be given a 0 for this week's project.
//
//If you get a zero on the project, you can upgrade to a score of .5 if you turn in a working project by the start of 
//class on the following Monday morning AND notify an instructor that you wish to get partial credit.
//
//To turn in a project, you must push it to GitHub. You must include a README.md that describes how to run your program.

package makechange;

import java.util.Scanner;

public class MakeChange {
	public static void main(String[] args) {
		// calculate amount of change to be returned to a customer
		// notify attendant how many of each piece of currency ($20 ,$10 ,$5 ,$1, .25c,
		// .10c, .05c, .01c)
		// is needed to make the change for customer, and denominations NOT used are NOT
		// displayed
		// Use largest bill/coin denomination
		// Hint: Mod Operator

		// The user is prompted asking for the price of the item.
		double total = 0.0;
		double tendered = 0.0;
		String bye = ""; // if customer does not want to keep shopping after entering too little payment
		String calChangeTrnxMsg = ""; // results from calculating exact denominations for change
		Scanner kb = new Scanner(System.in);
		System.out.println(
				"Hello!, and Welcome to our \"Er'thing $20 and Under\", and to our wonderful SuperExpress Text-Based Cashier Lane or, as we like to call it... SETBCL!!\n");
		System.out.println("We really really REALLY trust our customers here");
		// total should not exceed $20 ////////////////UNTESTED
		do {
			System.out.println("Please type in the total amount ($20.00 or less) of your shopping today");
			System.out.print("(Don't forget to add the cents!): ");
			total = kb.nextDouble();
		} while (total > 20.00);

		do {
			// The user is then prompted asking how much money was tendered by the customer.
			System.out.print("\nHow would you like to pay for that? \n(Please enter full amount): ");
			tendered = kb.nextDouble();
			// Tendered is more than the total
			if (tendered > total) {
				System.out.println("That definitely enough! Thank You");
				break;
				// Exact amount, exits after printing
			} else if (tendered == total) {
				System.out.println("Exact Amount! Well, that simplifies things. Thanks.");
				printMsg(total, tendered, "Exact Amount Entered. No Change Required.");
				System.exit(0);
				// Tendered is less than the total, asks again or if user opts for "bye", exits
				// after printing
			} else if (tendered < total) {
				// Display an appropriate message if the customer provided too little money or
				// the exact amount.
				System.out.println(
						"\nWell, this is a bit awkward, but you seems that you have NOT provided enough payment for your purchase.");
				System.out.print("If you would like to come again another time, type \"Bye\", otherwise hit Enter");
				kb.nextLine(); // to stop for user input
				bye = kb.nextLine().toLowerCase();
				if (bye.equals("bye")) {
					printMsg(total, tendered, "Insufficient Funds At This Time. Please come again!");
					System.exit(0);
				} else {
					System.out.println("\nAlright, let's try this again.");
				}
			}

		} while (tendered < total);

		// Call the Change Calculation method to determine accurate denominations
		calChangeTrnxMsg = calcChange(total, tendered);

		// Print the results and
		printMsg(total, tendered, calcChangeTrnxMsg);

		// Proper goodbye from
		System.out.println("\nSETBCL Was Lucky To Have You. Have A Nice Day!!!");

		// close scanner
		kb.close();
	} // end of main

	// Change Calculation Method

	calcChange(double totalAmt, double tenderedPayment) {
				int changeInts = 0, tens = 0, fives = 0, ones = 0, quarters = 0, dimes = 0, nickels = 0, pennies = 0, billsChange = 0, coinsChange = 0;
				double changeOwed = 0.0;
				String str1 = ""; 
				// examples
				//Amount: .67, Tendered: .50, Result: Error message
				//Amount: .67, Tendered: 1.00, Result: 1 quarter, 1 nickel, 3 pennies.
				//Amount: .59, Tendered: 1.00, Result: 1 quarter, 1 dime, 1 nickel, 1 penny.
				//Amount: 3.96, Tendered: 20.00, Result: 1 ten dollar bill, 1 five dollar bill, 1 one dollar bill, 4 pennies.
				//Amount: any amount less than 20.00, Tendered: anything greater than amount: correct denominations for correct change.
				// calculate how much is owed
				changeOwed = totalAmt - tenderedPayment;
				// converstion to string, getting rid of decimal point, and then converted into an integer
				str1 = String.valueOf(changeOwed).replace(".", "");
				changeInts = Integer.parseInt(str1);
				// Bills change amount
				billsChange = changeInts / 100;
				// Coins change amoount
				coinsChange = changeInts % 100;
				// check if $10 bills are needed for change
				
				// Calculate Bills change
				while (billsChange > 9) {
					tens++;
					billsChange -= 10;
				}
				// $5 bills
				while (billsChange > 4) {
					fives++;
					billsChange -= 5;
				}
				
				while (billsChange > 0) {
					ones++;
					billsChange--;
				}
				
				// Calculate Coins change
				
				while (coinsChange > 24) {
					quarters++;
					coinsChange -= 25;
				}
				
				while (coinsChange > 9) {
					dimes++;
					coinsChange -= 10;
				}
				
				while (coinsChange > 4) {
					nickels++;
					coinsChange -= 5;
				}
				
				while (coinsChange > 0) {
					pennies++;
					coinsChange--;
				}
			}

	// Print Method with transaction message
	public static void printMsg(double totalAmt, double tenderedPayment, String trnxMsg) {
		// e.g. output Amount: .67, Tendered: .50, Result: Error message
		System.out.println("Amount: " + totalAmt + ",\tTendered: " + tenderedPayment + ",\tResult: " + trnxMsg);
	}
}
