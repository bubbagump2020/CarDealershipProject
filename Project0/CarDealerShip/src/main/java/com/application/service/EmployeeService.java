package com.application.service;

import java.util.List;

import com.application.connections.EmployeeConn;
import com.application.models.Employee;

public class EmployeeService {
	private EmployeeConn ec = new EmployeeConn();
	
	public EmployeeService() {}
	
	public List<Employee> findAll(){
		return ec.findAll();
	}
	public Employee create(Employee t) {
		return ec.create(t);
	}
	public Employee findByUsername(String s, String p) {
		return ec.findByUsername(s, p);
	}
}
