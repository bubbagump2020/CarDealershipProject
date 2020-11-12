package com.application.menus;

import java.util.Scanner;

import com.application.controllers.OfferController;
import com.application.controllers.VehicleController;
import com.application.models.Customer;
import com.application.models.Offer;
import com.application.models.Vehicle;

public class OfferMenu {
	private static OfferController oc = new OfferController();
	private static VehicleController vc = new VehicleController();
	
	public static void makeOfferMenu(Customer c, Vehicle v) {
		
		Offer offer = null;
		Scanner input = new Scanner(System.in);
		String loanLengthChoice ="";
		double payments = 0;
		
		// TBLSL down payments and interest
		String tblName = "TBLSL";
		double oneLegDownPayment = (v.getVehiclePrice() * .2);
		double twoLegDownPayment = (v.getVehiclePrice() * .15);
		final double ONE_LEG_INTEREST = .15;
		final double TWO_LEG_INTEREST = .10;
		
		// BBCR down payment and interest
		String bbcrName = "BBCR";
		double bbcrDownPayment = (v.getVehiclePrice() * .15);
		final double BBCR_INTEREST = .30;
		
		// MFS down payment and interest
		String mfsName = "MFS";
		double mfsDownPayment = (v.getVehiclePrice() * .1);
		final double MFS_INTEREST = .75;
		
		System.out.println("What financial Service would you like to use to finance your purchase?");
		System.out.println("======================================================================");
		System.out.println();
		System.out.println("1. Two Broken Legs Savings and Loan");
		System.out.println("\tThe down payment needed for one broken leg is " + oneLegDownPayment + " and the interest rate is " + ONE_LEG_INTEREST * 100 + "%");
		System.out.println("\tThe down payment needed for two borken legs is " + twoLegDownPayment + " and the interest rate is " + TWO_LEG_INTEREST * 100 + "%");
		System.out.println("2. Blackbeard's Corporate Raiders");
		System.out.println("\tThe down payment is " + bbcrDownPayment + " and the interest rate is " + BBCR_INTEREST * 100 + "%");
		System.out.println("3. Madoff Financial Services");
		System.out.println("\tThe down payment is " + mfsDownPayment + " and the interest rate is " + MFS_INTEREST * 100 + "%");
		String loanChoice = input.nextLine();
		
		switch(loanChoice) {
			case "1":
				System.out.println("Break 1 leg or 2? Enter the number");
				String brokenLegs = input.nextLine();
				switch(brokenLegs) {
					case "1":
						System.out.println("Length of the loan?");
						System.out.println("===================");
						System.out.println();
						System.out.println("1. 36 months");
						System.out.println("2. 48 months");
						System.out.println("3. 60 months");
						loanLengthChoice = input.nextLine();
						switch(loanLengthChoice) {
							case "1":
								payments = SystemMenu.calculatePayment(oneLegDownPayment, v.getVehiclePrice(), 36);
								offer = new Offer(oneLegDownPayment, payments, ONE_LEG_INTEREST,
										c.getUsername(), v.getId(), tblName);
								oc.create(offer);
								break;
							case "2":
								payments = SystemMenu.calculatePayment(oneLegDownPayment, v.getVehiclePrice(), 48);
								offer = new Offer(oneLegDownPayment, payments, ONE_LEG_INTEREST,
										c.getUsername(), v.getId(), tblName);
								oc.create(offer);
								break;
							case "3":
								payments = SystemMenu.calculatePayment(oneLegDownPayment, v.getVehiclePrice(), 60);
								offer = new Offer(oneLegDownPayment, payments, ONE_LEG_INTEREST,
										c.getUsername(), v.getId(), tblName);
								oc.create(offer);
								break;
							default:
								System.out.println("Invalid selection, please try again");
								loanLengthChoice = input.nextLine();
						}
						break;
					case "2":
						System.out.println("Length of the loan?");
						System.out.println("===================");
						System.out.println();
						System.out.println("1. 36 months");
						System.out.println("2. 48 months");
						System.out.println("3. 60 months");
						loanLengthChoice = input.nextLine();
						switch(loanLengthChoice) {
							case "1":
								payments = SystemMenu.calculatePayment(twoLegDownPayment, v.getVehiclePrice(), 36);
								offer = new Offer(twoLegDownPayment, payments, TWO_LEG_INTEREST,
										c.getUsername(), v.getId(), tblName);
								oc.create(offer);
								break;
							case "2":
								payments = SystemMenu.calculatePayment(twoLegDownPayment, v.getVehiclePrice(), 36);
								offer = new Offer(twoLegDownPayment, payments, TWO_LEG_INTEREST,
										c.getUsername(), v.getId(), tblName);
								oc.create(offer);
								break;
							case "3":
								payments = SystemMenu.calculatePayment(twoLegDownPayment, v.getVehiclePrice(), 36);
								offer = new Offer(twoLegDownPayment, payments, TWO_LEG_INTEREST,
										c.getUsername(), v.getId(), tblName);
								oc.create(offer);
								break;
							default:
								System.out.println("Invalid selection, please try again");
								loanLengthChoice = input.nextLine();
						break;
						}
				}
				break;
			case"2":
				System.out.println("Length of the loan?");
				System.out.println("===================");
				System.out.println();
				System.out.println("1. 36 months");
				System.out.println("2. 48 months");
				System.out.println("3. 60 months");
				loanLengthChoice = input.nextLine();
				switch(loanLengthChoice) {
					case "1":
						payments = SystemMenu.calculatePayment(bbcrDownPayment, v.getVehiclePrice(), 36);
						offer = new Offer(bbcrDownPayment, payments, BBCR_INTEREST,
								c.getUsername(), v.getId(), bbcrName);
						oc.create(offer);
						break;
					case "2":
						payments = SystemMenu.calculatePayment(bbcrDownPayment, v.getVehiclePrice(), 36);
						offer = new Offer(bbcrDownPayment, payments, BBCR_INTEREST,
								c.getUsername(), v.getId(), bbcrName);
						oc.create(offer);
						break;
					case "3":
						payments = SystemMenu.calculatePayment(bbcrDownPayment, v.getVehiclePrice(), 36);
						offer = new Offer(bbcrDownPayment, payments, BBCR_INTEREST,
								c.getUsername(), v.getId(), bbcrName);
						oc.create(offer);
						break;
					default:
						System.out.println("Invalid selection, please try again");
						loanLengthChoice = input.nextLine();
				}
				break;
			case"3":
				System.out.println("Length of the loan?");
				System.out.println("===================");
				System.out.println();
				System.out.println("1. 36 months");
				System.out.println("2. 48 months");
				System.out.println("3. 60 months");
				loanLengthChoice = input.nextLine();
				switch(loanLengthChoice) {
					case "1":
						payments = SystemMenu.calculatePayment(mfsDownPayment, v.getVehiclePrice(), 36);
						offer = new Offer(mfsDownPayment, payments, MFS_INTEREST,
								c.getUsername(), v.getId(), mfsName);
						oc.create(offer);
						break;
					case "2":
						payments = SystemMenu.calculatePayment(mfsDownPayment, v.getVehiclePrice(), 36);
						offer = new Offer(mfsDownPayment, payments, MFS_INTEREST,
								c.getUsername(), v.getId(), mfsName);
						oc.create(offer);
						break;
					case "3":
						payments = SystemMenu.calculatePayment(mfsDownPayment, v.getVehiclePrice(), 36);
						offer = new Offer(mfsDownPayment, payments, MFS_INTEREST,
								c.getUsername(), v.getId(), mfsName);
						oc.create(offer);
						break;
					default:
						System.out.println("Invalid selection, please try again");
						loanLengthChoice = input.nextLine();
				}
				break;
			default:
				System.out.println("Invalid selection, please try again");
				makeOfferMenu(c, v);
		}
		
		System.out.println();
		System.out.println("Offer made!");
		System.out.println();
		CustomerMenu.customerLoggedInMenu();
	}
}
