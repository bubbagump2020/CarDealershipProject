package com.application.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PlainTextConnectionUtil {
	private static PlainTextConnectionUtil instance;
	
	private final String URL = "jdbc:postgresql://revature-db-1.cidcg5bmrsjg.us-east-2.rds.amazonaws.com:5432/project0?currentSchema=car_dealer_ship_schema";
	private final String USERNAME = "car_dealer_ship_db_user";
	private final String PASSWORD = "wasspord";
	
	private PlainTextConnectionUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static PlainTextConnectionUtil getInstance() {
		if(instance == null) {
			instance = new PlainTextConnectionUtil();
		}
		return instance;
	}
	
	// to create a connection to the db
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
}
