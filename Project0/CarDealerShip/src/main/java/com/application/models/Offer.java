package com.application.models;

public class Offer {
	private int id;
	private double downPayment;
	private double monthlyPayment;
	private double interestRate;
	
	private boolean accepted;
	private String belongsToCustomer;
	private String bank;
	private int belongsToVehicle;
	
	// database constructor
	public Offer(int id, double downPayment, double monthlyPayment, double interestRate, 
			boolean accepted, String belongsToCustomer, String bank, int belongsToVehicle) {
		super();
		this.id = id;
		this.downPayment = downPayment;
		this.monthlyPayment = monthlyPayment;
		this.interestRate = interestRate;
		this.accepted = accepted;
		this.belongsToCustomer = belongsToCustomer;
		this.bank = bank;
		this.belongsToVehicle = belongsToVehicle;
	}
	
		// program constructor
	public Offer(double downPayment, double monthlyPayment, double interestRate,
			String belongsToCustomer, int belongsToVehicle, String bank) {
		this.downPayment = downPayment;
		this.monthlyPayment = monthlyPayment;
		this.interestRate = interestRate;
		this.bank = bank;
		this.belongsToCustomer = belongsToCustomer;
		this.belongsToVehicle = belongsToVehicle;
	}
	// No Arg Constructor
	public Offer() {}

	
	
	// ToString for human readability
//	@Override
	public String toString() {
		return  "Id: " + getId() + "\n"
				+ "Bank: " + getBank() + "\n"
				+ "Down Payment: " + getDownPayment() + "\n"
				+ "Monthly Payment: " + getMonthlyPayment() + "\n"
				+ "Interest Rate: " + String.format("%.2f", getInterestRate()  * 100) + "%" +"\n"
				+ "Customer: " + getBelongsToCustomer() + "\n"
				+ "Loan Status: " + (isAccepted() ? "Approved" : "Not Approved");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getBelongsToCustomer() {
		return belongsToCustomer;
	}

	public void setBelongsToCustomer(String belongsToCustomer) {
		this.belongsToCustomer = belongsToCustomer;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public int getBelongsToVehicle() {
		return belongsToVehicle;
	}

	public void setBelongsToVehicle(int belongsToVehicle) {
		this.belongsToVehicle = belongsToVehicle;
	}
	
	
}
