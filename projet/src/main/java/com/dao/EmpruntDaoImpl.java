package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.persistence.ConnectionManager;
import com.model.*;

public class EmpruntDaoImpl implements EmpruntDao {
	
	
	private String getList = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateEmprunt DESC";
	private String getListCurrent = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL ORDER BY dateEmprunt DESC";
	private String getListCurrentByMembre = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?";
	private String getListCurrentByLivre = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?";
	private String getById = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?";
	private String create = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, null)";
	private String update= "UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?";
	private String count = "SELECT COUNT(id) AS count FROM emprunt";
	
	
	private static EmpruntDaoImpl INSTANCE;	    
    /** Point d'accès pour l'instance unique du singleton */
    public static EmpruntDaoImpl getInstance()
    {           
        if (INSTANCE == null){
        	INSTANCE = new EmpruntDaoImpl(); 
        }
        return INSTANCE;
    }
	    
    
	public List<Emprunt> getList() throws DaoException {	
		
		List<Emprunt> emprunts = new ArrayList<Emprunt>();
		
		try (Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(getList); ) {
			 
			ResultSet rs = myPreparedStatement.executeQuery();		
 			
			while (rs.next() ) {				
 
				Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre") , rs.getString("auteur"), rs.getString("isbn") );
				Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf( rs.getString("abonnement") ) );
				Emprunt emprunt = new Emprunt(rs.getInt("id"), membre, livre , rs.getDate("dateEmprunt").toLocalDate(), ( (rs.getDate("dateRetour") == null) ? null:rs.getDate("dateRetour").toLocalDate()  ) );
								
				emprunts.add(emprunt);					
			}
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de récupérer les données");
		} 
		
		return emprunts;
	};
	
	
	
	
	public List<Emprunt> getListCurrent() throws DaoException {	
		
 		List<Emprunt> emprunts = new ArrayList<Emprunt>();
		
		try (Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(getListCurrent); ) {
			 
			ResultSet rs = myPreparedStatement.executeQuery();		
 			
			while (rs.next() ) {			
 
				Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"), rs.getString("isbn")  );
				Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf( rs.getString("abonnement") ) );
				Emprunt emprunt = new Emprunt(rs.getInt("id"), membre, livre , rs.getDate("dateEmprunt").toLocalDate(), null   );
								
				emprunts.add(emprunt);					
			}
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de récupérer les données");
		} 
		
		return emprunts;
	};
	
	
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {	
		
 		List<Emprunt> emprunts = new ArrayList<Emprunt>(0);
		
		try(Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(getListCurrentByMembre);) {
			 
			myPreparedStatement.setString(1, String.valueOf(idMembre) );
			ResultSet rs = myPreparedStatement.executeQuery();			
			
			while (rs.next() ) { 			
					
				Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre") , rs.getString("auteur"), rs.getString("isbn") );
				Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf( rs.getString("abonnement") ) );
				Emprunt emprunt = new Emprunt(rs.getInt("id"), membre, livre , rs.getDate("dateEmprunt").toLocalDate(), null   );
								
				emprunts.add(emprunt);
											
			}
			
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de récupérer les données");
		} 
		
		return emprunts;
		
		
	};
	
	
	
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {	
		
 		List<Emprunt> emprunts = new ArrayList<Emprunt>(0);
		
		try(Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(getListCurrentByLivre); ) {
			
			myPreparedStatement.setString(1, String.valueOf(idLivre) ); 
			ResultSet rs = myPreparedStatement.executeQuery();	
			
			 			
			// fill 
			while (rs.next() ) {
				Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre") , rs.getString("auteur"), rs.getString("isbn") );
 				Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf( rs.getString("abonnement") ) );
 				Emprunt emprunt = new Emprunt(rs.getInt("id"), membre, livre , rs.getDate("dateEmprunt").toLocalDate(), null  );
				
 				emprunts.add(emprunt);
			}
			
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de récupérer les données");
		} 
 		
		return emprunts;
	};
	
	
	
	public Emprunt getById(int id) throws DaoException {	
		
 		
		try (Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement =  connection.prepareStatement(getById);) {
			 
			myPreparedStatement.setString(1, String.valueOf(id) );
			ResultSet rs = myPreparedStatement.executeQuery();	
			rs.next();
			
			Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre") , rs.getString("auteur"), rs.getString("isbn") );
			Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf( rs.getString("abonnement") ) );
			Emprunt emprunt = new Emprunt(rs.getInt("id"), membre, livre , rs.getDate("dateEmprunt").toLocalDate(), ( (rs.getDate("dateRetour") == null) ? null:rs.getDate("dateRetour").toLocalDate()  ) );
			return emprunt;
						
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de récupérer les données");
		} 
		
 		
		
	};
	
	
	
	public int create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		
		try (Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement =  connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS); ){
			
			myPreparedStatement.setString(1, String.valueOf(idMembre) );
			myPreparedStatement.setString(2, String.valueOf(idLivre) );
			myPreparedStatement.setString(3, String.valueOf(dateEmprunt) );
 			myPreparedStatement.executeUpdate();
 			

			ResultSet rs = myPreparedStatement.getGeneratedKeys();
			
			if ( !rs.next() ) return 0;		
			else return rs.getInt(1);	
 			
		}
		
		catch( SQLException e ) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible d'ajouter les données");
		}
	}
	
	
	public void update(Emprunt emprunt) throws DaoException{
		
		try (Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(update ); ) {			
			
			myPreparedStatement.setString(1, String.valueOf( emprunt.getMembre().getId() ) );
			myPreparedStatement.setString(2, String.valueOf( emprunt.getLivre().getId() )   );
			myPreparedStatement.setString(3, String.valueOf( emprunt.getDateEmprunt() )  );
			myPreparedStatement.setString(4, String.valueOf( emprunt.getDateRetour() )  );			
			myPreparedStatement.setString(5, String.valueOf( emprunt.getId() ) );
			myPreparedStatement.executeUpdate();		

		}
		
		catch( SQLException e ) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de mettre a jour les données");
		}
	
	};
	
	
	
	
	
	public int count() throws DaoException {
		
		try(Connection connection = ConnectionManager.getConnection();  
				PreparedStatement myPreparedStatement = connection.prepareStatement(count); ) {
			
  			myPreparedStatement.executeQuery();
 			ResultSet rs = myPreparedStatement.executeQuery();
 			

			if ( !rs.next() )  return 0;
			else return rs.getInt("count");		
			
		}
		
		catch( SQLException e ) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de récupérer les données");
		}
	
	};
	
	
	
	
	
	
	
	
	
	
	
	
	

}
