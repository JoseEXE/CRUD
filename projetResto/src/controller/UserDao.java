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
			System.out.println("Entra Insert: ");
			sql = conn.prepareStatement("INSERT INTO user (id_role, nom, prenom, email, url, password) VALUES (?,?,?,?,?,PASSWORD(?))");
			sql.setInt(1, user.getId_role().getId());
			sql.setString(2, user.getNom());
			sql.setString(3, user.getPrenom());
			sql.setString(4, user.getEmail());
			sql.setString(5, user.getUrl());
			sql.setString(6, user.getPassword());
			System.out.println("Insert: "+sql);
			sql.execute();
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
			
			Role role = new Role(rs.getInt("idRol"), rs.getString("nomRol"), rs.getString("description"), rs.getString("statutRol"));
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
			sql = conn.prepareStatement("UPDATE user SET id_role = ?, nom = ?, prenom = ?, email = ?, url = ? WHERE id=?");

			sql.setObject(1, user.getId_role());
			sql.setString(2, user.getNom());
			sql.setString(3, user.getPrenom());
			sql.setString(4, user.getEmail());
			sql.setString(5, user.getUrl());
			sql.setObject(6, user.getId());
			
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
			sql = conn.prepareStatement("UPDATE user SET statut = 'Active' WHERE id=?");
			sql.setInt(1,user.getId() );
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
	 * méthode pour rendre le role inactif
	 */
	@Override
	public Boolean desactiver(User user) {
		try {
			sql = conn.prepareStatement("UPDATE user SET statut = 'Inactive' WHERE id=?");
			sql.setInt(1,user.getId() );
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
	 * méthode pour vérifier l'existence d'un objet role avant INSERT INTO
	 */
	@Override
	public Boolean isExist(String txt) {
		try {
			sql = conn.prepareStatement("SELECT email FROM user WHERE email=?");
			sql.setString(1, txt);
			System.out.println("Emailsql: "+sql);
			rs=sql.executeQuery();
			while (rs.next()) {
				if(rs.getString("email").equalsIgnoreCase(txt)) {
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
	
	//VAlidation password Login
	public Boolean isExistOk(String txt, int id) {
		try {
			sql = conn.prepareStatement("SELECT password FROM user WHERE id=? and password=PASSWORD(?) and statut='Active'");
			sql.setInt(1, id);
			sql.setString(2, txt);
			rs=sql.executeQuery();
			while (rs.next()) {
				if(rs.getString("password").equalsIgnoreCase(txt)) {
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
	
	public Boolean changePass(String txt, int id) {
		try {
			sql = conn.prepareStatement("INSERT INTO user SET password=PASSWORD(?) WHERE id=?");
			sql.setInt(1, id);
			sql.setString(2, txt);
			rs=sql.executeQuery();
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


}
