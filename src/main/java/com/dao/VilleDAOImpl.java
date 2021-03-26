package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Ville;

public class VilleDAOImpl implements VilleDAO{
	public ArrayList<Ville> getVille(String value){
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Ville> listeVille=new ArrayList<>();
		try {
			connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/maven", "root", "root");
		    stmt = connexion.createStatement();
		    if(value==null) {
		    	 rs = stmt.executeQuery("SELECT * FROM ville_france");
		    }else {
		    	String req="SELECT * FROM ville_france WHERE Code_postal="+value;
		    	 rs = stmt.executeQuery(req);
		    }
		   
		    while(rs.next()) {
		    	Ville a=new Ville(rs.getString("Code_commune_INSEE"),rs.getString("Nom_Commune"),rs.getString("Code_postal"),rs.getString("Libelle_acheminement"),rs.getString("Ligne_5"),rs.getString("Latitude"),rs.getString("Longitude"));
		    	listeVille.add(a);
		    }
		    
		    
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    // Fermeture de la connexion
		    try {
		    	closeAllSQLFiles(rs,stmt,connexion);
		    } catch (SQLException ignore) {
		    	ignore.printStackTrace();
		    }
		}
		return listeVille;
	}
	
	private static void closeAllSQLFiles(ResultSet rs,Statement st,Connection con) throws SQLException{
		if(rs!=null) {
			rs.close();
		}
		if(st!=null) {
			st.close();
		}
		if(con!=null) {
			con.close();
		}
	}

	public void deleteVille(String codeCommune) {
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/maven", "root", "root");
		    stmt = connexion.createStatement();
		    if(codeCommune==null) {
		    	 System.out.println("Erreur suppression, commune inconnue");
		    }else {
		    	String req="DELETE FROM ville_france WHERE Code_commune_INSEE=?";
		    	 PreparedStatement preparedStmt = connexion.prepareStatement(req);
		    	 preparedStmt.setString (1, codeCommune);
		    	 preparedStmt.execute();
		    }
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    // Fermeture de la connexion
		    try {
		    	closeAllSQLFiles(rs,stmt,connexion);
		    } catch (SQLException ignore) {
		    	ignore.printStackTrace();
		    }
		}
		
	}
	
	public void addVille(String codeCommune,String nom, String cp,String libelle,String ligne, String latitude,String longitude) {
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/maven", "root", "root");
		    stmt = connexion.createStatement();
		    if(codeCommune==null || nom==null || cp==null || libelle==null || ligne==null || latitude==null || longitude ==null ) {
		    	 System.out.println("Erreur ajout, une des valeurs nulles");
		    }else {;
		    	String req = " insert into ville_france (Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5,Latitude,Longitude)"
		    	        + " values (?, ?, ?, ?, ?, ?, ?)";
		    	 PreparedStatement preparedStmt = connexion.prepareStatement(req);
		    	 preparedStmt.setString (1, codeCommune);
		    	 preparedStmt.setString (2, nom);
		    	 preparedStmt.setString (3, cp);
		    	 preparedStmt.setString (4, libelle);
		    	 preparedStmt.setString (5, ligne);
		    	 preparedStmt.setString (6, latitude);
		    	 preparedStmt.setString (7, longitude);
		    	 preparedStmt.execute();
		    	 
		    }
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    // Fermeture de la connexion
		    try {
		    	closeAllSQLFiles(rs,stmt,connexion);
		    } catch (SQLException ignore) {
		    	ignore.printStackTrace();
		    }
		}
		
	}
	
	public void updateVille(String codeCommune,String nom, String cp,String libelle,String ligne, String latitude,String longitude) {
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/maven", "root", "root");
		    stmt = connexion.createStatement();
		    if(codeCommune==null || nom==null || cp==null || libelle==null || ligne==null || latitude==null || longitude ==null ) {
		    	 System.out.println("Erreur modification, une des valeurs nulles");
		    }else {
		    	String req = " UPDATE ville_france SET Nom_commune=?, Code_postal=?, Libelle_acheminement=?, Ligne_5=?,Latitude=?,Longitude=? WHERE Code_commune_INSEE=?";
		    	 PreparedStatement preparedStmt = connexion.prepareStatement(req);
		    	 preparedStmt.setString (1, nom);
		    	 preparedStmt.setString (2, cp);
		    	 preparedStmt.setString (3, libelle);
		    	 preparedStmt.setString (4, ligne);
		    	 preparedStmt.setString (5, latitude);
		    	 preparedStmt.setString (6, longitude);
		    	 preparedStmt.setString (7, codeCommune);
		    	 preparedStmt.execute();
		    }
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    // Fermeture de la connexion
		    try {
		    	closeAllSQLFiles(rs,stmt,connexion);
		    } catch (SQLException ignore) {
		    	ignore.printStackTrace();
		    }
		}
		
	}
}


