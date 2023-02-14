package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connetion.ConnectionSql;
import model.Detail_commande;

public class DetailDao implements IDao<Detail_commande>{
	Connection conn=ConnectionSql.myConnection();
	PreparedStatement sql = null;
	ResultSet rs = null;
	@Override
	public Boolean create(Detail_commande detail) {
			try {
				sql=conn.prepareStatement("INSERT INTO detail_commande (id_commande,id_produit,quantite,prix_unitaire) VALUES (?,?,?,?)");
				sql.setInt(1, detail.getId_commande().getId());
				sql.setInt(2, detail.getId_produit().getId());
				sql.setInt(3, detail.getQuantite());
				sql.setDouble(4, detail.getPrix_unitaire());
				sql.execute();

				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}

	@Override
	public ArrayList<Detail_commande> read(String txt) {
				try {
					sql=conn.prepareStatement("SELECT *,  FROM detail_commande dc INNER JOIN commande c ON id_commande=c.id INNER JOIN produit p "
							+ "ON id_produit=p.id WHERE c.id =?");
					sql.setInt(1, Integer.parseInt(txt));
					rs=sql.executeQuery();
					while (rs.next()) {
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return null;
	}

	@Override
	public Object findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Detail_commande object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Delete(Detail_commande object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean activer(Detail_commande object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean desactiver(Detail_commande object) {
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
