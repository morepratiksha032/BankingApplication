package com.Application;

import java.util.Scanner;

import com.Entity.Bank;

public class App {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Bank b = new Bank();
		
		boolean ch = true;
		
		while(ch) {
			System.out.println("1. Adding New Customer");
			System.out.println("2. Updating Customer's Details");
			System.out.println("3. Delete Customer");
			System.out.println("4. Data of All Customer");
			System.out.println("5. Getting Single Customer's Details");
			System.out.println("6. Withdraw Amount");
			System.out.println("7. Deposite Amount");
			System.out.println("8. Exit");
		
			
			int choice = sc.nextInt();
			
			switch(choice) {
			
			case 1 : b.insert();
			break;
			
			case 2 : b.update();
			break;
			
			case 3 : b.delete();
			break;
			
			case 4 : b.getAllData();
			break;
			
			case 5 : b.getSingleData();
			break;
			
			case 6 : b.withdraw();
			break;
			
			case 7 : b.deposite();
			break;
			
			case 8 : ch = false;
			System.out.println("Thank-U for visiting");
			break;
			
			default :
				System.out.println("Invalid");
				break;
			}
		}
	}
}
