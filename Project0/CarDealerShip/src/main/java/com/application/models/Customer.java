package com.application.models;

public class Customer{
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Customer loggedInUser;
	
	public Customer(String firstName, String lastName, String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(Customer t) {
		this.username = t.getUsername();
		this.password = t.getPassword();
		this.firstName = t.getFirstName();
		this.lastName = t.getLastName();
	}
	// getters & setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getLoggedInUser() {
		return loggedInUser;
	}
	public void setLoggedInUser(Customer loggedInUser) {
		if(loggedInUser == null) {
			System.out.println("Username or password incorrect, please try again");
		} else {
			this.username = loggedInUser.getUsername();
			this.firstName = loggedInUser.getFirstName();
			this.lastName = loggedInUser.getLastName();
			this.loggedInUser = loggedInUser;
		}
		
	}
	// human readable;
	@Override
	public String toString() {
		return  "Customer Username: " + username + "\n"
				+ "Customer Name: " + firstName + " " + lastName;
	}
}
