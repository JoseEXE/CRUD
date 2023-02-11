package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connetion.ConnectionSql;
import model.Client;

public class ClientDao implements IDao<Client>{
Connection conn= ConnectionSql.myConnection();
	PreparedStatement sql = null;
	ResultSet rs = null;
	@Override
	public Boolean create(Client client) {
		try {
			sql=conn.prepareStatement("INSERT INTO client (nom,prenom,numtel) VALUES (?,?,?)");
			sql.setString(1, client.getNom());
			sql.setString(2, client.getPrenom());
			sql.setString(3, client.getNumtel());
			sql.execute();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Client> read(String txt) {
		ArrayList<Client> list=new ArrayList<>();
		try {
			sql = conn.prepareStatement("SELECT * FROM client Where nom Like ?");
			sql.setString(1, "%"+txt+"%");
			rs=sql.executeQuery();
			while (rs.next()) {
				list.add(new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("numtel"),rs.getString("statut")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Object findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Client client) {
		try {
			sql = conn.prepareStatement("UPDATE client SET nom = ?,prenom = ?, WHERE id=?");
			sql.setString(1, client.getNom());
			sql.setString(2, client.getPrenom());
			sql.setString(3, client.getNumtel());
			sql.setInt(3,client.getId() );
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
	public Boolean Delete(Client object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean activer(Client client) {
		try {
			sql = conn.prepareStatement("UPDATE client SET statut = 'Actif' WHERE id=?");
			sql.setInt(1,client.getId() );
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
	public Boolean desactiver(Client client) {
		try {
			sql = conn.prepareStatement("UPDATE client SET statut = 'Inactif' WHERE id=?");
			sql.setInt(1,client.getId() );
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
		try {
			sql = conn.prepareStatement("SELECT nom FROM client WHERE numtel=?");
			sql.setString(1, txt);
			rs=sql.executeQuery();
			while (rs.next()) {
				if(rs.getString("nom").equalsIgnoreCase(txt)) {
					return true;
				}
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		return false;
	}

	@Override
	public int total() {
		int total =0;
		try {
			sql = conn.prepareStatement("SELECT COUNT(*) as total FROM client");
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
