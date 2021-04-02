package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.*;
import com.model.*;



public class MembreServiceImpl implements MembreService {
	
	
	private static MembreServiceImpl INSTANCE;    
    /** Point d'accès pour l'instance unique du singleton */
    public static MembreServiceImpl getInstance()
    {           
        if (INSTANCE == null){
        	INSTANCE = new MembreServiceImpl(); 
        }
        return INSTANCE;
    }    
    
    // creation of our singleton 
    private MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();   
    private EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();     
    
    
    
	
	
	public List<Membre> getList() throws ServiceException {
		
		try {
 			return membreDaoImpl.getList();		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
	}
	
	
	public Membre getById(int id) throws ServiceException {
		
		if (id < 0) {
			throw new ServiceException("Mauvais ID");			
		}
		
		try {
 			return membreDaoImpl.getById(id) ;		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
	}
	
	
 	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException {
		if (nom.length()*prenom.length() == 0)  {
			throw new ServiceException("Données incomplètes");			
		}
		
		try {
 			return membreDaoImpl.create(nom.toUpperCase() , prenom,  adresse, email, telephone);	
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
	}
 	
 	
 	
 	public void update(Membre membre) throws ServiceException {
		
		try {
			membreDaoImpl.update( membre );	
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
	}
 	
 	
 	public void delete(int id) throws ServiceException {
 		if ( id < 0 ) {
 			throw new ServiceException("Mauvais ID");			
		}
		
		try {
			membreDaoImpl.delete(id );	
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
	}
 	
 	
 	public int count() throws ServiceException {
		
		try {
 			return membreDaoImpl.count();	
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
	}
 	
	
	
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		
		try {
			// get list of members 
 			List<Membre> good_membres = new  ArrayList<Membre>(0);			
			
			// remove those impossible 
			for( Membre membre : membreDaoImpl.getList() ) { 
 				if (empruntServiceImpl.isEmpruntPossible(membre) ) good_membres.add(membre);
			}
			
			return good_membres;
		}
		
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
		
	}
 	
	
}
