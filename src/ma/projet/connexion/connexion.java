package ma.projet.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexion {
	private static String url = "jdbc:mysql://localhost:3300/demojdbc";
	private static String utilisateur = "root";
	private static String mot_de_passe = "";
	
	public static Connection getConnection() {
		Connection connexion = null ;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion = DriverManager.getConnection(url, utilisateur, mot_de_passe);
			System.out.println("connexion bien effectuer");
		} catch (ClassNotFoundException e) {
			System.out.println("classNotFoundException");
		} catch (SQLException e) {
			System.out.println("SQLException");
		}
		
		return connexion;
	}
	/*
	public static void main(String [] args) {
		Connection connexion = connexion.getConnection();
	}*/
}
