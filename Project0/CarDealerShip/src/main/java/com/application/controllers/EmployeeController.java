package com.application.controllers;

import java.util.List;

import com.application.models.Employee;
import com.application.service.EmployeeService;

public class EmployeeController {
	private EmployeeService es = new EmployeeService();
	
	public EmployeeController() {}
	
	public List<Employee> findAll(){
		return es.findAll();
	}
	
	public Employee create(Employee t) {
		return es.create(t);
	}
	
	public Employee findByUsername(String s, String p) {
		return es.findByUsername(s, p);
	}
	
}
