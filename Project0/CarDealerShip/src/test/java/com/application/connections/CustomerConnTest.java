package com.application.connections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.application.models.Customer;


public class CustomerConnTest {
	private CustomerConn cc;
	
	@Before
	public void setup() {
		cc = new CustomerConn();
	}
	
	@Test
	public void findAllTest() {
		List<Customer> customers = cc.findAll();
		assertNotNull(customers);
	}
	
	@Test
	public void findById() {
		Customer c = cc.findById(1);
		assertNotNull(c);
	}
	
	@Test
	public void createTest() {
		Customer c = cc.create(new Customer("john", "jameson", "kbagnall", "password"));
		assertNotNull(c);
	}
	
	@Test
	public void deleteTest() {
		int i = cc.delete(2);
		assertEquals(1, i);
	}
	
}
