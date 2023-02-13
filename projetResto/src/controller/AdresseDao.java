package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connetion.ConnectionSql;
import model.Adresse;
import model.Client;

public class AdresseDao implements IDao<Adresse>{
	Client client = new Client();
	Adresse adresse = new Adresse();
	Connection conn= ConnectionSql.myConnection();
	PreparedStatement sql = null;
	ResultSet rs = null;
	@Override
	public Boolean create(Adresse object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Adresse> read(String txt) {
		ArrayList<Adresse> list=new ArrayList<>();
		try {
			sql = conn.prepareStatement("SELECT ad.id, ad.id_client, ad.rue, ad.cod_postal, ad.ville, ad.complement  FROM adresse as ad INNER JOIN user ON ad.id_client = user.id WHERE user.id=?");
			sql.setInt(1, Integer.parseInt(txt));
			System.out.println("SQL Adresse R: "+sql);
			rs=sql.executeQuery();
			while (rs.next()) {
				list.add(new Adresse(rs.getInt("id"), rs.getInt("id_client"), rs.getString("rue"), rs.getString("cod_postal"), rs.getString("ville"), rs.getString("complement")));
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
	public Boolean update(Adresse object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Delete(Adresse object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean activer(Adresse object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean desactiver(Adresse object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isExist(String txt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return 0;
	}

}
