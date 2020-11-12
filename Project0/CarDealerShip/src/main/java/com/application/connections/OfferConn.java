package com.application.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.application.config.PlainTextConnectionUtil;
import com.application.models.Offer;
import com.application.models.Vehicle;

public class OfferConn implements OfferDao<Offer, Integer>{
	@Override
	public List<Offer> findAll() {
		List<Offer> offers = new LinkedList<>();
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			Statement s = conn.createStatement();
			String query = "select * from offer;";
			ResultSet rs = s.executeQuery(query);
			while(rs.next()) {
				offers.add(new Offer(
						rs.getInt(1),
						rs.getDouble(2),
						rs.getDouble(3),
						rs.getFloat(4),
						rs.getBoolean(5),
						rs.getString(6),
						rs.getString(8),
						rs.getInt(7)
					));
			}
			rs.close();
		} catch(SQLException e) {
			
		}
		return offers;
	}

	@Override
	public Offer findById(Integer i) {
		System.out.println("The id is " + i);
		return null;
	}

	public List<Offer> findByUsername(String s) {
		List<Offer> offers = new LinkedList<>();
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String query = "select * from offer where belongs_to_customer=?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(7).equals(s)) {
					offers.add(new Offer(
								rs.getInt(1),
								rs.getDouble(2),
								rs.getDouble(3),
								rs.getFloat(4),
								rs.getBoolean(5),
								rs.getString(6),
								rs.getString(7),
								rs.getInt(8)
							));
				}
			}
			ps.close();
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return offers;
	}
	
	public Offer findByUsernameAndVehicleId(String s, Integer i) {
		Offer offer = null;
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String query = "select * from offer where belongs_to_customer=? and belongs_to_vehicle=?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, s);
			ps.setInt(2, i);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(7).equals(s)) {
					offer = new Offer(
								rs.getInt(1),
								rs.getDouble(2),
								rs.getDouble(3),
								rs.getFloat(4),
								rs.getBoolean(5),
								rs.getString(6),
								rs.getString(7),
								rs.getInt(8)
							);
				}
			}
			ps.close();
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return offer;
	}
	
	@Override
	public Offer update(Offer t) {
		Vehicle v = new Vehicle();
		System.out.println(t.toString());
		System.out.println(t.getBelongsToVehicle());
		try (Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String query = "update offer "
					+ "set accepted=? "
					+ "where (belongs_to_customer= ?) "
					+ "and belongs_to_vehicle= ?;";
			PreparedStatement ps1 = conn.prepareStatement(query);
			ps1.setBoolean(1, t.isAccepted());
			ps1.setString(2, t.getBelongsToCustomer());
			ps1.setInt(3, t.getBelongsToVehicle());
			ps1.executeUpdate();
			
			String query2 = "update vehicle set is_owned=?, v_owner=? where id=?;";
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps2.setBoolean(1, true);
			ps2.setString(2, t.getBelongsToCustomer());
			ps2.setInt(3, t.getBelongsToVehicle());
			ps2.executeUpdate();
			
			String query3 = "select * from vehicle where id=?;";
			PreparedStatement ps3 = conn.prepareStatement(query3);
			ps3.setInt(1, t.getBelongsToVehicle());
			ResultSet rs = ps3.executeQuery();
			while(rs.next()) {
				v = new Vehicle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5), rs.getString(6));
			}
			
			String query4 = "insert into owned_vehicle values(?, ?, ?, ?);";
			PreparedStatement ps4 = conn.prepareStatement(query4);
			ps4.setInt(1, t.getBelongsToVehicle());
			ps4.setString(2, v.getVehicleType());
			ps4.setString(3, v.getVehicleColor());
			ps4.setString(4, t.getBelongsToCustomer());
			ps4.executeUpdate();
			
			ps1.close();
			ps2.close();
			ps3.close();
			ps4.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public Offer create(Offer t) {
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String sql = "insert into offer values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setDouble(2, t.getDownPayment());
			ps.setDouble(3, t.getMonthlyPayment());
			ps.setDouble(4, t.getInterestRate());
			ps.setBoolean(5, false);
			ps.setString(6, t.getBelongsToCustomer());
			ps.setInt(7, t.getBelongsToVehicle());
			ps.setString(8, t.getBank());
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public int delete(Integer i) {
		return 0;
	}
	
	public int deleteAllAccepted() {
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String query = "delete from offer where accepted=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBoolean(1, true);
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	public int deleteAllRejected(Integer i) {
		try(Connection conn = PlainTextConnectionUtil.getInstance().getConnection()){
			String query = "delete from offer where accepted=? and belongs_to_vehicle=?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setInt(2, i);
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
