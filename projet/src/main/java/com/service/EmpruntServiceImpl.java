package com.service;

 
import java.time.LocalDate;
import java.util.List;

import com.dao.*;
import com.model.*;
 

public class EmpruntServiceImpl implements EmpruntService {
	
	
	
	private static EmpruntServiceImpl INSTANCE;    
    /** Point d'accès pour l'instance unique du singleton */
    public static EmpruntServiceImpl getInstance()
    {           
        if (INSTANCE == null){
        	INSTANCE = new EmpruntServiceImpl(); 
        }
        return INSTANCE;
    }    
    
    // creation of our singleton 
    private EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();    
    
	
	
	public List<Emprunt> getList() throws ServiceException{
		try {
			return empruntDaoImpl.getList();		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
		
	}
	
	
	public List<Emprunt> getListCurrent() throws ServiceException{
		try {
			return empruntDaoImpl.getListCurrent();		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
		
	}
	
	
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException{
		
		if (idMembre < 0) {
			throw new ServiceException("Mauvaise Id de membre");
		}
		try {
 			return empruntDaoImpl.getListCurrentByMembre(idMembre );		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
		
	}
	
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException{
		
		if (idLivre < 0) {
			throw new ServiceException("Mauvaise IDd de livre");
		}
		try {
 			return empruntDaoImpl.getListCurrentByLivre(idLivre );		
		}
		catch( DaoException e ) {
			e.printStackTrace();
			throw new ServiceException("Impossible de lire la bdd");
		}
		
	} 
	
	public Emprunt getById(int id) throws ServiceException{
		
		if (id < 0) {
			throw new ServiceException("Mauvaise Id");
		}
		try {
 			return empruntDaoImpl.getById(id );		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
		
	} 
	
	
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		
		if (idMembre < 0 || idLivre < 0) {
			throw new ServiceException("Mauvaise ID");
		}
		try {
			empruntDaoImpl.create(idMembre, idLivre, dateEmprunt) ;		
		}
		catch( DaoException e ) {
			e.printStackTrace();
			throw new ServiceException("Impossible de lire la bdd");
		}
		
	} 
	
	
	public int count() throws ServiceException {
		try {
 			return empruntDaoImpl.getListCurrent().size() ;		
		}
		catch( DaoException e ) {
			throw new ServiceException("Impossible de lire la bdd");
		}
		
 	}
	
	

	public void returnBook(int id) throws ServiceException {	
		
		try {
 			Emprunt emprunt = empruntDaoImpl.getById(id);
			emprunt.setDateRetour( LocalDate.now() );
			empruntDaoImpl.update(emprunt);	
		}
		
		catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void update(Emprunt emprunt) throws ServiceException {
		
		if ( emprunt.getId()*emprunt.getLivre().getId()*emprunt.getMembre().getId() == 0 ){
			throw new ServiceException("Mauvaise ID");
		}
		
		try {
			EmpruntDaoImpl.getInstance().update(emprunt); 
		}
		
		catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		// Here we call a method of service thus the inputs will be check next		
		return this.getListCurrentByLivre(idLivre).size() == 0;		
	}
	
		
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		// Here we call a method of service thus the inputs will be check next			
		return this.getListCurrentByMembre( membre.getId() ).size() < membre.getAbonnement().getNombreEmprunts() ;
	}

}
























