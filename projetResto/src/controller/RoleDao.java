package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connetion.ConnectionSql;
import model.Role;



public class RoleDao implements IDao<Role>{
 Connection conn= ConnectionSql.myConnection();
 PreparedStatement sql = null;
 ResultSet rs = null;
	@Override
	public Boolean create(Role object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Role> read(String txt) {
		ArrayList<Role> list=new ArrayList<>();
		try {
		sql = conn.prepareStatement("SELECT * FROM role WHERE nom LIKE ?");
		sql.setString(1, "%"+txt+"%");
		System.out.println(sql);
		rs=sql.executeQuery();
		
		while (rs.next()) {
			list.add(new Role(rs.getInt("id"),rs.getString("nom"),rs.getString("description"),rs.getString("statut")));
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return list;
	}

	@Override
	public Object findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Update(Role object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Delete(Role object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean activer(Role role) {
		try {
			sql = conn.prepareStatement("UPDATE role SET statut = 'Active' WHERE id=?");
			sql.setInt(1,role.getId() );
			if(sql.executeUpdate()>0) {
				return true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
				return false;
	}

	@Override
	public Boolean desactiver(Role role) {
		try {
			sql = conn.prepareStatement("UPDATE role SET statut = 'Inactive' WHERE id=?");
			sql.setInt(1,role.getId() );
			if(sql.executeUpdate()>0) {
				return true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
				return false;
		
	}

	@Override
	public Boolean isExist(String txt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int total() {
		int total =0;
		try {
			sql = conn.prepareStatement("SELECT COUNT(*) as total FROM role");
			rs=sql.executeQuery();
			while (rs.next()) {
			total =	rs.getInt("total");
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		return total;
	}



}
