package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connetion.ConnectionSql;
import model.Role;
import model.User;



public class UserDao implements IDao<User>{
	/*
	 * Connection a la bdd
	 * instanciation des class plusieurs fois utilisées
	 */
 Connection conn= ConnectionSql.myConnection();
 PreparedStatement sql = null;
 ResultSet rs = null;
 /*
  * methode Create du CRUD
  */
	@Override
	public Boolean create(User user) {
		try {
//			sql = conn.prepareStatement("INSERT INTO role (nom,description) VALUES (?,?)");
//			sql.setString(1, role.getNom());
//			sql.setString(2, role.getDescription());
//			sql.execute();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return false;
	}
	/*
	  * methode Read du CRUD, qui peut devenir FindByName grace au parametre txt
	  */
	@Override
	public ArrayList<User> read(String txt) {
		ArrayList<User> list = new ArrayList<>();
		try {
		sql = conn.prepareStatement("SELECT *, role.id as 'idRol', role.nom as 'nomRol', role.statut as 'statutRol'  FROM User INNER JOIN role ON user.id_role = role.id WHERE user.nom LIKE ?");
		sql.setString(1, "%"+txt+"%");
		System.out.println(sql);
		rs=sql.executeQuery();
		
		while (rs.next()) {
			
			Role role = new Role(rs.getInt("idRol"), rs.getString("nomRol"), rs.getString("description"), rs.getString("statuts"));
			list.add(new User(rs.getInt("id"), role ,rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("url"), rs.getString("statut")));
			
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
/*
 * méthode update du CRUD
 */
	@Override
	public Boolean update(User user) {
		try {
//			sql = conn.prepareStatement("UPDATE role SET nom = ?,description = ? WHERE id=?");
//			sql.setString(1, role.getNom());
//			sql.setString(2, role.getDescription());
//			sql.setInt(3,role.getId() );
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
	/*
	 * méthode delete du CRUD
	 */
	@Override
	public Boolean Delete(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * méthode pour rendre le role actif
	 */
	@Override
	public Boolean activer(User user) {
		try {
//			sql = conn.prepareStatement("UPDATE role SET statut = 'Active' WHERE id=?");
//			sql.setInt(1,role.getId() );
//			if(sql.executeUpdate()>0) {
//				return true;
//			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
				return false;
	}
	/*
	 * méthode pour rendre le role inactif
	 */
	@Override
	public Boolean desactiver(User user) {
		try {
//			sql = conn.prepareStatement("UPDATE role SET statut = 'Inactive' WHERE id=?");
//			sql.setInt(1,role.getId() );
//			if(sql.executeUpdate()>0) {
//				return true;
//			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
				return false;
		
	}
	/*
	 * méthode pour vérifier l'existence d'un objet role avant INSERT INTO
	 */
	@Override
	public Boolean isExist(String txt) {
		try {
			sql = conn.prepareStatement("SELECT nom FROM user WHERE nom=?");
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
	/*
	 * méthode pour compte le nombre de registe total de la bdd role
	 */
	@Override
	public int total() {
		int total =0;
		try {
			sql = conn.prepareStatement("SELECT COUNT(*) as total FROM user");
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
	
	public Object findRolUser(int idUser) {
		Role role = null;
		try {
		sql = conn.prepareStatement("SELECT  role.id as 'idRol', role.description, role.nom as 'nomRol', role.statut as 'statutRol'  FROM role INNER JOIN user ON role.id = user.id_role WHERE user.id = ?");
		sql.setInt(1, idUser);
		System.out.println(sql);
		rs=sql.executeQuery();
		
		while (rs.next()) {
			
			role = new Role(rs.getInt("idRol"), rs.getString("nomRol"), rs.getString("description"), rs.getString("statutRol"));
		
		}
		return role;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return role;
	}



}
