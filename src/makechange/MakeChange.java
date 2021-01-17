// Make Change (Cash Register) by Walter S. Valdez
// Wk 1 Homework
//
// Notes:
// 1. Utilized an array to facilitate printing. 
// 2. Only accepts total amounts of $20 and under per instructions

package makechange;
import java.util.Scanner;

public class MakeChange {
	public static void main(String[] args) {

		// The user is prompted asking for the price of the item.
		int changeInts = 0;
		double total = 0.0;
		double tendered = 0.0;
		String bye = ""; // if customer does not want to keep shopping after entering too little payment
		String calChangeTrnxMsg = ""; // results from calculating exact denominations for change
		Scanner kb = new Scanner(System.in);
		System.out.println(
				"Hello!, and Welcome to our \"Er'thing $20 and Under\", and to our wonderful SuperExpress Text-Based Cashier Lane or, as we like to call it... SETBCL!!\n");
		System.out.println("We really really REALLY trust our customers here");
		// total should not exceed $20 
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
				System.out.println("\nExact Amount! That simplifies things -- Thanks");
				printMsg(total, tendered, "Exact Amount Entered -- No Change Required.");
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
					printMsg(total, tendered, "Insufficient Funds At This Time -- Please come again!");
					System.exit(0);
				} else {
					System.out.println("\nAlright, let's try this again.");
				}
			}

		} while (tendered < total);

		// modify values to get only integers
		changeInts = ((int)(tendered * 100)) - ((int)(total * 100));		
		
		// Call the Change Calculation method to determine accurate denominations
		calChangeTrnxMsg = calcChange(changeInts);

		// Print the results and
		printMsg(total, tendered, calChangeTrnxMsg);

		// Proper goodbye from
		System.out.println("\nEr'thing $20 and Under store was Lucky To Have You \n\nHave A Nice Day!!!");

		// close scanner
		kb.close();
	} // end of main

	
	// Calculate Change Method
	public static String calcChange(int changeInts) {
		int billsChange, coinsChange, tens = 0, fives = 0, ones = 0, quarters = 0, dimes = 0, nickels = 0, pennies = 0, dCounter = 0;
		boolean printTens = true, printFives = true, printOnes = true, printQuarters = true, printDimes = true, printNickels = true, printPennies = true;
		String trnxMsg = "";

		// Bills change amount
		billsChange = changeInts / 100;
		// Coins change amoount
		coinsChange = changeInts % 100;		
		
		// Bills change
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
		
		// Coins change
		
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
		
		// check how many denominations are used to create concise trnx msg
		if (tens != 0) {
			dCounter++;
		}
		if (fives != 0) {
			dCounter++;
		}
		if (ones != 0) {
			dCounter++;
		}
		if (quarters != 0) {
			dCounter++;
		}
		if (dimes != 0) {
			dCounter++;
		}
		if (nickels != 0) {
			dCounter++;
		}
		if (pennies != 0) {
			dCounter++;
		}
		// create an array for printing
		String[] denoms = new String[dCounter];		// declare and populate the array
		for(int i = 0; i < denoms.length; i++) {
			if (tens != 0 && printTens) {
				if (tens < 2) {
					denoms[i] = String.valueOf(tens) + " ten dollar bill";
					printTens = false; 
					continue;
				} else {
					denoms[i] = String.valueOf(tens) + " ten dollar bills";
					printTens = false;
					continue;
				}
			}
			if (fives != 0 && printFives) {
				if (fives < 2) {
					denoms[i] = String.valueOf(fives) + " five dollar bill";
					printFives = false;
					continue;
				} else {
					denoms[i] = String.valueOf(fives) + " ten dollar bills";
					printFives = false;
					continue;
				}
			}
			if (ones != 0 && printOnes) {
				if (ones < 2) {
					denoms[i] = String.valueOf(ones) + " one dollar bill";
					printOnes = false;
					continue;
				} else {
					denoms[i] = String.valueOf(ones) + " one dollar bills";
					printOnes = false;
					continue;
				}
			}
			if (quarters != 0 & printQuarters) {
				if (quarters < 2) {
					denoms[i] = String.valueOf(quarters) + " quarter";
					printQuarters = false;
					continue;
				} else {
					denoms[i] = String.valueOf(quarters) + " quarters";
					printQuarters = false;
					continue;
				}
			}
			if (dimes != 0 && printDimes) {
				if (dimes < 2) {
					denoms[i] = String.valueOf(dimes) + " dime";
					printDimes = false;
					continue;
				} else {
					denoms[i] = String.valueOf(dimes) + " dimes";
					printDimes = false;
					continue;
				}
			}
			if (nickels != 0 & printNickels) {
				if (nickels < 2) {
					denoms[i] = String.valueOf(nickels) + " nickel";
					printNickels = false;
					continue;
				} else {
					denoms[i] = String.valueOf(nickels) + " nickels";
					printNickels = false;
					continue;
				}
			}
			if (pennies != 0) {
				if (pennies < 2) {
					denoms[i] = String.valueOf(pennies) + " penny";
					printPennies = false;
					continue;
				} else {
					denoms[i] = String.valueOf(pennies) + " pennies";
					printPennies = false;
					continue;
				}
			}
		}
			
			// concatenate the denoms string array into a concise transaction message to return
			for (int i = 0; i < dCounter; i++ ) {
				trnxMsg += denoms[i];
				if (i == (dCounter - 1)) {
				trnxMsg += "."; 
				} else {
					 trnxMsg += ", ";
				}
			}
			return trnxMsg;
	}

	// Print Method with transaction message
	public static void printMsg(double totalAmt, double tenderedPayment, String trnxMsg) {
		// printout message
		System.out.println("Amount: " + totalAmt + ",\tTendered: " + tenderedPayment + ",\tResult: " + trnxMsg);
	}
}
