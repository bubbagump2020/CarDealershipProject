package com.application.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.application.controllers.CustomerController;
import com.application.controllers.VehicleController;
import com.application.models.Customer;
import com.application.models.Offer;
import com.application.models.Vehicle;

public class CustomerMenu {
	private static CustomerController cc = new CustomerController();
	private static VehicleController vc = new VehicleController();
	private static Customer user = new Customer();
	
	public CustomerMenu() {
		this(new CustomerController());
	}

	private CustomerMenu(CustomerController cc) {
		// TODO Auto-generated constructor stub
		super();
		this.cc = cc;
	}
	
	public static void mainCustomerMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("==========================");
		System.out.println();
		System.out.println("1. Create new customer account // <-- Implemented!");
		System.out.println("2. Login to existing account  // <-- Implemented!");
		System.out.println("3. Back to main program menu");
		System.out.println("0. Quit Program");
		int choice = input.nextInt();
		switch(choice) {
			case 1:
				createCustomerPortal();
				break;
			case 2:
				customerLoginPortal();
				break;
			case 3:
				SystemMenu.showMenu();
			case 0:
				System.out.println("Bye Bye! Come back soon!");
				System.exit(0);
			default:
				System.out.println("Invalid selection, please try again");
				choice = input.nextInt();
		}
	}
	public static void createCustomerPortal() {
		Scanner input = new Scanner(System.in);
		System.out.println("Customer Account Creation");
		System.out.println("=========================");
		System.out.println();
		
		System.out.println("Please enter your username");
		String username = input.nextLine();
		
		System.out.println("Please enter your password");
		String password = input.nextLine();
		
		System.out.println("Please enter your first name");
		String firstName = input.nextLine();
		
		System.out.println("Please enter your last name");
		String lastName = input.nextLine();
		
		Customer c = new Customer(firstName, lastName, username, password);
		cc.create(c);
	}
	public static void customerLoginPortal() {
		Customer c = null;
		Scanner input = new Scanner(System.in);
		System.out.println("Customer Login Portal");
		System.out.println("=====================");
		System.out.println();
		System.out.println("Enter your username");
		String username = input.nextLine();
		System.out.println("Enter your password");
		String password = input.nextLine();
		
		// finding user
		c = cc.findByUsername(username, password);
		user.setLoggedInUser(c);
		if(c != null) {
			customerLoggedInMenu();
		} else {
			customerLoginPortal();
		}
	}
	public static void customerLoggedInMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("Your Account");
		System.out.println("============");
		System.out.println();
		System.out.println("Where would you like to go?");
		System.out.println("1. Show Room");
		System.out.println("2. My offers");
		System.out.println("3. Make offer");
		System.out.println("4. My Vehicles");
		System.out.println("0. Quit");
		int choice = input.nextInt();
		switch(choice) {
			case 1:
				showAllAutos();
				customerLoggedInMenu();
				break;
			case 2:
				System.out.println();
				showCustomerOffers();
				System.out.println();
				customerLoggedInMenu();
			case 3:
				chooseAutoFromLot();
			case 4:
				showMyAutos();
			case 0:
				System.out.println("Bye Bye! Come back soon!");
				System.exit(0);
			default:
				System.out.println("Invalid selection, please try again");
				choice = input.nextInt();
		}
	}
	public static void chooseAutoFromLot() {
		System.out.println(user.toString());
		Scanner input = new Scanner(System.in);
		List<Vehicle> autos = SystemMenu.showLot();
		int count = 0;
		for(int i = 0; i < autos.size(); i++) {
			System.out.println();
			System.out.println((i+1) +". " + autos.get(i).toString());
			System.out.println();
			System.out.println("Make offer on this vehicle? y/n");
			String yesNo = input.nextLine();
			switch(yesNo) {
				case "y":
					OfferMenu.makeOfferMenu(user, autos.get(i));
					break;
				case "n":
					count++;
					if(count == autos.size()) {
						customerLoggedInMenu();
					}
					break;
				default:
					System.out.println("Invalid response, please try again");
					yesNo = input.nextLine();
					break;
			}
		}
	}
	public static void showMyAutos() {
		List<Vehicle> myAutos = vc.findAllOwnedByParticularCustomer(user);
		for(Vehicle element : myAutos) {
			System.out.println();
			System.out.println("Id: " + element.getId() + "\n"
						+ "Type: " + element.getVehicleType() + "\n"
						+ "Color: " + element.getVehicleColor()
					);
			System.out.println();
		}
	}
	
	public static void showAllAutos() {
		for(int i = 0; i < SystemMenu.showLot().size(); i++) {
			System.out.println();
			System.out.println((i + 1) + ". " + SystemMenu.showLot().get(i));
		}
	}
	
	public static void showCustomerOffers() {
		System.out.println("Showing offers");
		List<Offer> userOffers = new ArrayList<>();
		for(int i = 0; i < SystemMenu.showOffers().size(); i++) {
			if(SystemMenu.showOffers().get(i).getBelongsToCustomer().equals(user.getUsername())) {
				userOffers.add(SystemMenu.showOffers().get(i));
			}
		}
		if(userOffers.size() == 0) {
			System.out.println("You have no offers");
		} else {
			for(Offer element : userOffers) {
				System.out.println();
				System.out.println(element.toString());
			}
		}
	}
	
}
