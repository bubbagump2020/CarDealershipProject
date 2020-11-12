package com.application.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.application.config.PlainTextConnectionUtil;
import com.application.models.Employee;

public class EmployeeConn implements UserDao<Employee, Integer> {

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = new LinkedList<>();
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			Statement s = conn.createStatement();
			String query = "select * from employee;";
			ResultSet rs = s.executeQuery(query);
			while(rs.next()) {
				employees.add(
							new Employee(
										rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getBoolean(6)
									)
						);
			}
			rs.close();
			s.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee findById(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee update(Employee t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee create(Employee t) {
		boolean taken = unique(t);
		System.out.println(t.toString());
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			if(!taken) {
				String query = "insert into employee values(?, ?, ?, ?, ?, ?);";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, 0);
				ps.setString(2, t.getFirstName());
				ps.setString(3, t.getLastName());
				ps.setString(4, t.getUsername());
				ps.setString(5, t.getPassword());
				ps.setBoolean(6, t.isManager());
				ps.executeUpdate();
				ps.close();
			} else {
				System.out.println();
				System.out.println("Sorry that username is taken");
				System.out.println();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int delete(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Employee findByUsername(String s, String p) {
		Employee em = null;
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String query = "select * from employee where username=? and employee_password=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,  s);
			ps.setString(2, p);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				em = new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getBoolean(6)
					);
				System.out.println();
				System.out.println("Welcome" + em.getFirstName());
				System.out.println();
			}
			rs.close();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return em;
	}
	
	public boolean unique(Employee t) {
		boolean taken = false;
		List<Employee> employees = new LinkedList();
		String employeeUserName = t.getUsername();
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String query = "select * from employee";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				employees.add(
							new Employee(
										rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getBoolean(6)
									)
						);
			}
			for(Employee element : employees) {
				if(employeeUserName.equals(element.getUsername())) {
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

}
