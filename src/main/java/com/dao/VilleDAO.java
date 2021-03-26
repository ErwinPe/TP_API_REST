package com.dao;

import java.util.ArrayList;

import beans.Ville;

public interface VilleDAO {
	public ArrayList<Ville> getVille(String value);
	public void deleteVille(String codeCommune);
	public void addVille(String codeCommune,String nom, String cp,String libelle,String ligne, String latitude,String longitude);
	public void updateVille(String codeCommune,String nom, String cp,String libelle,String ligne, String latitude,String longitude);
}
