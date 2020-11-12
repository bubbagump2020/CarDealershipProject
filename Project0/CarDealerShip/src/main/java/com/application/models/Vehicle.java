package com.application.models;

public class Vehicle {
	private int id;
	private String vehicleType;
	private String vehicleColor;
	private double vehiclePrice;
	private boolean owned;
	private String customer;
	
	public Vehicle() {
		super();
	}
	
	// what customer/employee enters
	public Vehicle(String vType, String vColor, double vPrice) {
		this.vehicleType = vType;
		this.vehicleColor = vColor;
		this.vehiclePrice = vPrice;
	}
	
	public Vehicle(int id, String vType, String vColor) {
		this.id = id;
		this.vehicleColor = vColor;
		this.vehicleType = vType;
	}
	
	// what is returned from the database
	public Vehicle(int id, String vType, String vColor, double vPrice, boolean owned, String customer) {
		this.id = id;
		this.vehicleType = vType;
		this.vehicleColor = vColor;
		this.vehiclePrice = vPrice;
		this.customer = customer;
		this.owned = owned;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public double getVehiclePrice() {
		return vehiclePrice;
	}

	public void setVehiclePrice(double vehiclePrice) {
		this.vehiclePrice = vehiclePrice;
	}
	
	public boolean isOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Id: " + getId() +"\n"
				+ "Type: " + getVehicleType() + "\n"
				+ "Color: " + getVehicleColor() + "\n"
				+ "Price: $" + getVehiclePrice();
				
	}
	
}
