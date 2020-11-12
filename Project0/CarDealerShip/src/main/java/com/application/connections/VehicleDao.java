package com.application.connections;

import java.util.List;

import com.application.models.Customer;
import com.application.models.Vehicle;

public interface VehicleDao<T, I> extends MasterDao<T, I>{
	public int deleteAllOwned();
	public List<Vehicle> findAllOwnedByParticularCustomer(Customer c);
}
