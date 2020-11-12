package com.application;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.application.menus.CustomerMenu;
import com.application.menus.EmployeeMenu;
import com.application.menus.SystemMenu;

// Log4j2 to be implemented after user stories are met.
public class Application {
	private static final Logger logger = LogManager.getLogger(Application.class.getName());
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean run = true;
		do {
			
			String choice = SystemMenu.showMenu();
			switch(choice) {
				case "1":
					CustomerMenu.mainCustomerMenu();
					break;
				case "2":
					EmployeeMenu.mainEmployeeMenu();
					break;
				case "0":
					System.out.println("Bye Bye! Come back soon!");
					run = false;
					break;
				default:
					System.out.println("Please try again");
					SystemMenu.showMenu();
					choice = input.nextLine();
					break;
			}
		} while(run);
		System.out.println();
		logger.info("Program End");
	}
}