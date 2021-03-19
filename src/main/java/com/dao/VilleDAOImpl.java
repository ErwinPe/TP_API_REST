package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Ville;

public class VilleDAOImpl implements VilleDAO{
	public ArrayList<Ville> getVille(){
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Ville> listeVille=new ArrayList<>();
		try {
			connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/maven", "root", "root");
		    stmt = connexion.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM ville_france");
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
}


