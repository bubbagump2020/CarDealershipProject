package com.application.menus;

import java.util.Scanner;

import com.application.controllers.EmployeeController;
import com.application.controllers.OfferController;
import com.application.controllers.VehicleController;
import com.application.models.Employee;
import com.application.models.Offer;
import com.application.models.Vehicle;

public class EmployeeMenu {
	public static EmployeeController ec = new EmployeeController();
	public static OfferController oc = new OfferController();
	public static VehicleController vc = new VehicleController();
	
	private static final int PIN = 1234;
	private static final int MANAGER_PIN = 5678;
	protected static int tries = 3;
	protected static int managerTries = 3;
	private static Employee user = new Employee();
	
	public static void mainEmployeeMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("==========================");
		System.out.println();
		System.out.println("1. Create account");
		System.out.println("2. Login");
		System.out.println("3. Main Menu");
		System.out.println("0. Quit");
		int choice = input.nextInt();
		switch(choice) {
			case 1:
				createEmployeePortal();
				break;
			case 2:
				employeeLoginPortal();
				break;
			case 3:
				SystemMenu.showMenu();
				break;
			case 0:
				System.out.println("Bye Bye! Come back soon!");
				System.exit(0);
			default:
				System.out.println("Invalid selection, please try again");
				choice = input.nextInt();
		}
	}
	
	public static void createEmployeePortal() {
		Scanner input = new Scanner(System.in);
		boolean isManager = false;
		System.out.println();
		System.out.println("Employee Account Creation");
		System.out.println("=========================");
		System.out.println();
		
		//ask y/n if manager account
		// if y, ask for MANAGER_PIN;
		System.out.println("Are you creating a manager account? y/n");
		String yesNo = input.nextLine();
		if(yesNo.equals("y")) {
			System.out.println("Please input the PIN that was given too you.");
			int managerPin = input.nextInt();
			if(managerPin != MANAGER_PIN) {
				while(managerTries > 0) {
					System.out.println("Incorrect PIN, " + managerTries + " left" );
					managerPin = input.nextInt();
					managerTries--;
				}
			} else {
				isManager = true;
			}
		}
		
		System.out.println("Username");
		String username = input.nextLine();
		
		System.out.println("Password");
		String password = input.nextLine();
		
		System.out.println("First Name");
		String firstName = input.nextLine();
		
		System.out.println("Last Name");
		String lastName = input.nextLine();
		
		System.out.println("Please input the PIN given to you by your manager");
		int employeePin = input.nextInt();
		
		if(employeePin != PIN) {
			tries--;
			System.out.println("PIN incorrect, " + tries + " left");
			if(tries < 0) {
				SystemMenu.showMenu();
			}
		} else {
			Employee e = new Employee(firstName, lastName, username, password, isManager);
			ec.create(e);
		}
	}
	
	public static void employeeLoginPortal() {
		Employee e = null;
		Scanner input = new Scanner(System.in);
		
		System.out.println();
		System.out.println("Employee Login Portal");
		System.out.println("=====================");
		System.out.println();
		
		System.out.println("Enter username");
		String username = input.nextLine();
		
		System.out.println("Enter password");
		String password = input.nextLine();
		
		// find employee
		 e = ec.findByUsername(username, password);
		 user.setLoggedInUser(e);
		 if(e != null) {
			 employeeLoggedInMenu();
		 } else {
			 System.out.println();
			 System.out.println("Please try again");
			 System.out.println();
			 employeeLoginPortal();
		 }
	}
	
	public static void employeeLoggedInMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println();
		System.out.println("Your Account");
		System.out.println("============");
		System.out.println();
		
		System.out.println("Where would you like to go?");
		System.out.println("1. Show Room");
		System.out.println("2. Offers");
		System.out.println("3. Accept Customer Offer");
		System.out.println("4. Order Vehicle");
		System.out.println("5. Purge Owned Vehicles from Lot");
		System.out.println("0. Quit");
		String choice = input.nextLine();
		
		switch(choice) {
			case "1":
				showAllAutos();
				employeeLoggedInMenu();
				break;
			case "2":
				showAllOffers();
				employeeLoggedInMenu();
				break;
			case "3":
				acceptCustomerOffer();
				break;
			case "4":
				addVehicle();
				break;
			case "5":
				purge();
				break;
			case "0":
				System.out.println("Bye Bye! Come back soon!");
				System.exit(0);
			default:
				System.out.println("Invalid selection, please try again");
				choice = input.nextLine();
				break;
		}
		
	}
	
	public static void showAllAutos() {
		for(int i = 0; i < SystemMenu.showLot().size(); i++) {
			System.out.println();
			System.out.println((i + 1) + ". " + SystemMenu.showLot().get(i));
		}
	}
		
	public static void acceptCustomerOffer() {
		Scanner input = new Scanner(System.in);
		Offer offer = null;
		
		if(SystemMenu.showOffers().size() == 0) {
			System.out.println("No offers available");
		} else {
			for(Offer element : SystemMenu.showOffers()) {
				System.out.println();
				System.out.println(element.toString());
			}
			
			System.out.println();
			System.out.println("Which offer do you want to accept? please input the offer's id number, the username of the customer wishing to purchase the vehicle, and the vehicle id");
			String offerId = input.nextLine();
			System.out.println(offerId);
			String customerUsername = input.nextLine();
			System.out.println(customerUsername);
			String vId = input.nextLine();
			System.out.println(vId);
			
			for(Offer element : SystemMenu.showOffers()) {
				if(
					Integer.parseInt(offerId) == element.getId()
					&& customerUsername.equals(element.getBelongsToCustomer())
					&&  Integer.parseInt(vId) == element.getBelongsToVehicle()
				) {
					offer = element;
					offer.setAccepted(true);
					oc.update(offer);
					oc.deleteAllRejected(offer.getBelongsToVehicle());
					System.out.println("Offer updated!");
				} 
			}
		}
		employeeLoggedInMenu();
	}
	
	public static void showAllOffers() {
		if(SystemMenu.showOffers().size() == 0) {
			System.out.println("No offers available");
		} else {
			for(Offer element : SystemMenu.showOffers()) {
				if(!element.isAccepted()) {
					System.out.println();
					System.out.println(element.toString());
				}
			}
		}
	}
	
	public static void addVehicle() {
		Scanner input = new Scanner(System.in);
		System.out.println();
		System.out.println("Vehicle Factory");
		System.out.println("===============");
		System.out.println();
		System.out.println("Type? 1. SUV, 2. Sedan");
		String type = input.nextLine();
		System.out.println("Color?");
		String color = input.nextLine();
		System.out.println("Price?");
		String price = input.nextLine();
		
		switch(type) {
			case "1":
				type = "SUV";
				break;
			case "2":
				type = "Sedan";
				break;
			default:
				System.out.println("Invalid type selection, please try again");
				type = input.nextLine();
				break;
		}
		// error checking needed here too.
		Vehicle v = new Vehicle(type, color, Double.parseDouble(price));
		vc.create(v);
	}
	
	public static void purge() {
		oc.deleteAllAccepted();
		vc.deleteAllOwned();
		System.out.println("Lot Purged");
	}
}
