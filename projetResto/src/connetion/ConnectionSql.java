package connetion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSql {
	
	public static Connection myConnection() {
		Connection conn = null;
		
		String url = "jdbc:mysql://localhost/";
		String dbName = "restaurant";
		String user = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url+dbName,user,password);
			System.out.println("ok connexion");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}

}
