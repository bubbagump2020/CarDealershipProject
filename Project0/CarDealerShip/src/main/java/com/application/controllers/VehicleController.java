package com.application.controllers;

import java.util.List;

import com.application.models.Customer;
import com.application.models.Vehicle;
import com.application.service.VehicleService;

public class VehicleController {
	private VehicleService vs = new VehicleService();
	public VehicleController() {}
	public List<Vehicle> all(){
		return vs.findAll();
	}
	
	public int deleteAllOwned() {
		return vs.deleteAllOwned();
	}
	public List<Vehicle> findAllOwnedByParticularCustomer(Customer c){
		return vs.findAllOwnedByParticularCustomer(c);
	}
	public Vehicle create(Vehicle v) {
		return vs.create(v);
	}
}
