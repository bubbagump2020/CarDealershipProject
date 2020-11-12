package com.application.models;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean isManager;
	private Employee loggedInUser;
	// no arg constructor
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	// db constructor
	public Employee(int id, String firstName, String lastName, String username, String password, boolean isManager) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.isManager = isManager;
	}
	// user constructor
	public Employee(String firstName, String lastName, String username, String password, boolean isManager) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.isManager = isManager;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	public String toString() {
		return "Employee Name: " + getFirstName() + " " + getLastName() + "\n"
				+ "Employee Username: " + getUsername() + "\n"
				+ "Manager? " + isManager();
	}
	public void setLoggedInUser(Employee e) {
		this.username = e.getUsername();
		this.firstName = e.getFirstName();
		this.lastName = e.getLastName();
		this.loggedInUser = e;
	}
	
	
}
