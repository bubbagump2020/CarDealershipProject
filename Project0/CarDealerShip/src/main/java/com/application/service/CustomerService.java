package com.application.service;

import java.util.List;

import com.application.connections.CustomerConn;
import com.application.models.Customer;

public class CustomerService {
	private CustomerConn cc = new CustomerConn();

	public CustomerService() {
	}

	private CustomerService(CustomerConn cc) {
		super();
		this.cc = cc;
	}
	
	public List<Customer> findAll(){
		return cc.findAll();
	}
	
	public Customer create(Customer t) {
		return cc.create(t);
	}
	public Customer findByUsername(String s, String p) {
		return cc.findByUsername(s, p);
	}
}
