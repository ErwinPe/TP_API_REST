package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DAOFactory;

import beans.Ville;

@RestController
public class VilleController {
	@RequestMapping(value="/ville",method= RequestMethod.GET)
	
	@ResponseBody
	public ArrayList<Ville> appelGET(@RequestParam(required=false,value="codePostal") String codePostal) {
		System.out.println("Appel GET");
		DAOFactory fact=new DAOFactory();
		System.out.println(codePostal);
		
		ArrayList<Ville> allVilles=getVille(codePostal);
		return allVilles;
	
	}
	
	
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
	
}
