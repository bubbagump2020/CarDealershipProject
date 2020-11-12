package com.application.connections;

import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.ds.common.BaseDataSource;

public class ConnectionFactory {
	private interface Singleton{
		ConnectionFactory instance = new ConnectionFactory();
	}
	
	private BaseDataSource dataSource;
	
	
	public static Connection getConnection() throws SQLException {
		return null;
//		return Singleton.instance.dataSource.getConnection();
	}
	
}
