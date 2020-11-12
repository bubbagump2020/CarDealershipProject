package com.application.service;

import java.util.List;

import com.application.connections.VehicleConn;
import com.application.models.Customer;
import com.application.models.Vehicle;

public class VehicleService {
	private VehicleConn vc = new VehicleConn();
	
	public VehicleService() {}
	public List<Vehicle> findAll(){
		return vc.findAll();
	}
	
	public int deleteAllOwned() {
		return vc.deleteAllOwned();
	}
	
	public List<Vehicle> findAllOwnedByParticularCustomer(Customer c){
		return vc.findAllOwnedByParticularCustomer(c);
	}
	
	public Vehicle create(Vehicle v) {
		return vc.create(v);
	}
}