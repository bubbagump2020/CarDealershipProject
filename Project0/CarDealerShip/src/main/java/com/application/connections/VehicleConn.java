package com.application.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.application.config.PlainTextConnectionUtil;
import com.application.models.Customer;
import com.application.models.Vehicle;

public class VehicleConn implements VehicleDao<Vehicle, Integer>{

	@Override
	public List<Vehicle> findAll() {
		List<Vehicle> autos = new LinkedList<>();
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String query = "select * from vehicle;";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				autos.add(new Vehicle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5), rs.getString(6)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return autos;
	}

	@Override
	public Vehicle findById(Integer i) {
		Vehicle v = null;
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String sql = "select * from vehicle where id = ?"; // this will sanitize the input.
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				v = new Vehicle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5), rs.getString(6));
			}	
			ps.close();
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

	public List<Vehicle> findAllOwnedByParticularCustomer(Customer c){
		List<Vehicle> vehicles= new LinkedList<>();
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String sql = "select * from owned_vehicle where v_owner = ?"; // this will sanitize the input.
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getUsername());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vehicles.add(new Vehicle (rs.getInt(1), rs.getString(2), rs.getString(3)));
			}	
			ps.close();
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return vehicles;
	}
	
	@Override
	public Vehicle create(Vehicle v) {
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
				//TODO add id field to customer table
				String sql = "insert into vehicle values(?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, 0);
				ps.setString(2, v.getVehicleType());
				ps.setString(3, v.getVehicleColor());
				ps.setDouble(4, v.getVehiclePrice());
				ps.setBoolean(5, false);
				ps.setString(6, null);
				
				ps.executeUpdate();
				ps.close();
			
		} catch(SQLException e) {
			
		}
		return v;
	}

	@Override
	public Vehicle update(Vehicle v) {
		return null;
	}
	
	public int deleteAllOwned() {
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String query = "delete from vehicle where is_owned=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBoolean(1, true);
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Integer i) {
		return 0;
	}
	
}
