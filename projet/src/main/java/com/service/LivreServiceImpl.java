package com.service;
import java.util.ArrayList;
import java.util.List;

import com.dao.*;
import com.model.*;

public class LivreServiceImpl implements LivreService {
	
	
	private static LivreServiceImpl INSTANCE;    
    /** Point d'accès pour l'instance unique du singleton */
    public static LivreServiceImpl getInstance()
    {           
        if (INSTANCE == null){
        	INSTANCE = new LivreServiceImpl(); 
        }
        return INSTANCE;
    }    
    
    // creation of our singleton 
    private LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();   
    private EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();   
    
    
    
	
	public List<Livre> getList() throws ServiceException{
		
		try {
 			return livreDaoImpl.getList();		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
		
	}
	
	
	
	public Livre getById(int id) throws ServiceException{
		
		if (id < 0) {
			throw new ServiceException("Mauvaise Id");			
		}
		
		try {
 			return livreDaoImpl.getById( id );		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
		
	}
	
	
	
 	public int create(String titre, String auteur, String isbn) throws ServiceException {
 		
 		if ( titre.length() == 0 ) {
 			throw new ServiceException("Titre Vide");	
  		}
 		
 		try {
 			return livreDaoImpl.create(titre, auteur, isbn);		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}	
 		
 	}
 	
 	
	public void update(Livre livre) throws ServiceException {
		
		if ( livre.getTitre().length() == 0 ) {
 			throw new ServiceException("Titre vide");	
  		}
 		
 		try {
 			livreDaoImpl.update(livre);	
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
 		
	}
	
	
	
	public void delete(int id) throws ServiceException {
		
		if (id < 0) {
			throw new ServiceException("Mauvaise Id");			
		}
		
		try {
			livreDaoImpl.delete( id );		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
	}
	
	
	
	public int count() throws ServiceException {
		
		try {
 			return livreDaoImpl.count();		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
	}
	
	
	
	public List<Livre> getListDispo() throws ServiceException {
		
		try {
			// list of available books 
 			List<Livre> livresDispo =  new ArrayList<Livre>(0);	
			for( Livre livre : livreDaoImpl.getList() ) {
				if ( empruntServiceImpl.isLivreDispo(livre.getId()) ) livresDispo.add(livre); 
			}
			
			return livresDispo;
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
		
	}
	
	
	
	

	
	

}
