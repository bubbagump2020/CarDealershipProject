package com.application.menus;

import java.util.List;
import java.util.Scanner;

import com.application.controllers.CustomerController;
import com.application.controllers.OfferController;
import com.application.controllers.VehicleController;
import com.application.models.Customer;
import com.application.models.Offer;
import com.application.models.Vehicle;

public class SystemMenu {
	private static VehicleController vc = new VehicleController();
	private static CustomerController cc = new CustomerController();
	private static OfferController oc = new OfferController();
	private static Customer c = new Customer();
	
	public SystemMenu(){}
	
	public static String showMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Shady Used Autos");
		System.out.println("===========================");
		System.out.println("What would you like to do?");
		System.out.println("1. Customer Menu");
		System.out.println("2. Employee Menu");
		System.out.println("0. Quit Program");
		String choice = input.nextLine();
		return choice;
		
	}
	public static List<Vehicle> showLot() {
			return vc.all();
	}
	public static List<Customer> showCustomers(){
		return cc.findAll();
	}
	public static List<Offer> showOffers(){
		return oc.findAll();
	}
	
	public static double calculatePayment(double downPayment, double price, int lengthOfLoan) {
		double amountToFinance = price - downPayment;
		switch(lengthOfLoan) {
			case 36:
				return amountToFinance / lengthOfLoan;
			case 48:
				return amountToFinance / lengthOfLoan;
			case 60:
				return amountToFinance / lengthOfLoan;
			default:
				return 0;
		}
	}
	
}
