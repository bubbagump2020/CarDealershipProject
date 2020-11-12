package com.application.controllers;

import java.util.List;

import com.application.models.Customer;
import com.application.service.CustomerService;

public class CustomerController {
	private CustomerService cs = new CustomerService();
	
	public CustomerController() {
		super();
	}
	public List<Customer> findAll(){
		return cs.findAll();
	}
	
	public Customer create(Customer t) {
		return cs.create(t);
	}
	
	public Customer findByUsername(String s, String p) {
		return cs.findByUsername(s, p);
	}
}
