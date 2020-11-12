package com.application.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;

import com.application.config.PlainTextConnectionUtil;
import com.application.models.Customer;

public class CustomerConn implements UserDao<Customer, Integer> {
//	private static final Logger logger = LogManager.getLogger("CustomerConnection");
//	private static final 
	public CustomerConn() {
		super();
	}
	
	// pass
	@Override
	public List<Customer> findAll() {
		List<Customer> customers = new LinkedList<>();
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			Statement s = conn.createStatement();
			String sqlQuery = "select * from customer;";
			ResultSet rs = s.executeQuery(sqlQuery);
			while(rs.next()) {
				customers.add(
							new Customer(
										rs.getString(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4)
									)
						);
			}
			rs.close();
			s.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}
	// pass
	@Override
	public Customer findByUsername(String s, String p) {
		Customer c = null;
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String sql = "select * from customer where username = ? AND user_password=?"; // this will sanitize the input.
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, p);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c = new Customer(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
					);
				System.out.println("Welcome " + c.getFirstName());
			}	
			ps.close();
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Customer update(Customer t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// pass
	public boolean unique(Customer t) {
		List<Customer> customers = new LinkedList<>();
		boolean taken = false;
		String customerUserName = t.getUsername();
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String userNameQuery = "select * from customer";
			PreparedStatement ps = conn.prepareStatement(userNameQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				customers.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			for(Customer element : customers) {
				if(customerUserName.equals(element.getUsername())) {
					taken = true;
				}
			}
			ps.close();
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return taken;
	}
	
	@Override
	public Customer create(Customer t) {
		boolean taken = unique(t);
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			if(!taken) {
				String sql = "insert into customer values(?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, t.getFirstName());
				ps.setString(2, t.getLastName());
				ps.setString(3, t.getUsername());
				ps.setString(4, t.getPassword());
				ps.executeUpdate();
				conn.commit();
				System.out.println();
				System.out.println("Account created!");
				System.out.println();
				ps.close();
			} else {
				System.out.println();
				System.out.println("Sorry, that username is taken");
				System.out.println();
			}
		} catch(SQLException e) {
			
		}
		return t;
	}
	//passes test
	@Override
	public int delete(Integer i) {
		int row = 0;
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String sql = "delete from customer where id = ?"; // this will sanitize the input.
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			row = ps.executeUpdate();
			System.out.println(row);
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public Customer findById(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}

}
