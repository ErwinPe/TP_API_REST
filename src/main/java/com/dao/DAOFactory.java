package com.dao;

public class DAOFactory {
	VilleDAO villeDAO;
	
	public void DAOFactory() {
		this.villeDAO=getVilleDao();
	}
	
	public VilleDAO getVilleDao() {
        return new VilleDAOImpl();
    }
}
