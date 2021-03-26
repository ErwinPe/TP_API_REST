package com.controller;


import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DAOFactory;
import com.dao.VilleDAO;

import beans.Ville;

@RestController
public class VilleController {
	@RequestMapping(value="/ville",method= RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> appelGET(@RequestParam(required=false,value="codePostal") String codePostal) {
		System.out.println("Appel GET");
		DAOFactory fact=new DAOFactory();
		VilleDAO villeDAO=fact.getVilleDao();
		ArrayList<Ville> allVilles=villeDAO.getVille(codePostal);
		return allVilles;
	
	}
	
	@RequestMapping(value="/ville",method= RequestMethod.DELETE)
	@ResponseBody
	public void appelDelete(@RequestParam(required=false,value="codeCommune") String codeCommune) {
		DAOFactory fact=new DAOFactory();
		VilleDAO villeDAO=fact.getVilleDao();
		System.out.println("DELETE : "+codeCommune);
		villeDAO.deleteVille(codeCommune);
	}
	
	
	@RequestMapping(value="/ville",method= RequestMethod.PUT)
	@ResponseBody
	public void appelPut(@RequestParam(required=false,value="codeCommune") String codeCommune,@RequestParam(required=false,value="nom") String nom,@RequestParam(required=false,value="cp") String codePostal,@RequestParam(required=false,value="libelle") String libelle,@RequestParam(required=false,value="ligne") String ligne,@RequestParam(required=false,value="latitude") String latitude,@RequestParam(required=false,value="longitude") String longitude) {
		DAOFactory fact=new DAOFactory();
		VilleDAO villeDAO=fact.getVilleDao();
		villeDAO.updateVille(codePostal,nom,codePostal,libelle,ligne,latitude,longitude);
	}
	
	@RequestMapping(value="/ville",method= RequestMethod.POST)
	@ResponseBody
	public void appelPost(@RequestParam(required=false,value="codeCommune") String codeCommune,@RequestParam(required=false,value="nom") String nom,@RequestParam(required=false,value="cp") String codePostal,@RequestParam(required=false,value="libelle") String libelle,@RequestParam(required=false,value="ligne") String ligne,@RequestParam(required=false,value="latitude") String latitude,@RequestParam(required=false,value="longitude") String longitude) {
		DAOFactory fact=new DAOFactory();
		VilleDAO villeDAO=fact.getVilleDao();
		villeDAO.addVille(codeCommune,nom,codePostal,libelle,ligne,latitude,longitude);
	}
	
	
	
}
