package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.persistence.ConnectionManager;
import com.model.Abonnement;
import com.model.Membre;

public class MembreDaoImpl implements MembreDao {	
	
	private String getList = "FROM membre SELECT * ORDER BY nom ASC ";
	private String getById = "FROM membre SELECT * WHERE id = ?";
	private String create = "INSERT INTO membre(nom, prenom, adresse, email, telephone,  abonnement) VALUES (?, ?, ?, ?, ?, ?)";
	private String update = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?,  abonnement = ? WHERE id = ?";
	private String delete = "DELETE FROM membre WHERE id = ?";
	private String count = " SELECT COUNT(id) AS count FROM membre";
	
	
	private static MembreDaoImpl INSTANCE;    
    /** Point d'accès pour l'instance unique du singleton */
    public static MembreDaoImpl getInstance()
    {           
        if (INSTANCE == null){
        	INSTANCE = new MembreDaoImpl(); 
        }
        return INSTANCE;
    }
    
    
	public List<Membre> getList() throws DaoException {	
		
 		List<Membre> membres = new ArrayList<Membre>();
		
		try (Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(getList); ) {
			
 
			ResultSet rs = myPreparedStatement.executeQuery();			
			
			while (rs.next() ) {
				Membre membre = new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf( rs.getString("abonnement") ) );
				membres.add(membre);			
			}
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de récupérer les données");
		} 
		
		return membres;
	};
	
	
	public Membre getById(int id ) throws DaoException {
		
		try(Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement =  connection.prepareStatement(getById); ) {
			
			myPreparedStatement.setString(1, String.valueOf(id) );
			ResultSet rs = myPreparedStatement.executeQuery();
			

			if ( !rs.next() ) {
				return null;
			}
			
			else {
				Membre membre = new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf( rs.getString("abonnement") ) );
				return membre;
			}
			
		}
		
		catch( SQLException e ) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de récupérer les données");
		}
	
	};
	
	
	
	public int create(String nom, String prenom, String adresse, String email, String telephone)  throws DaoException{
		
		try(Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS); ){
			
			myPreparedStatement.setString(1, nom );
			myPreparedStatement.setString(2, prenom );
			myPreparedStatement.setString(3, adresse );
			myPreparedStatement.setString(4, email );
			myPreparedStatement.setString(5,  telephone );
			myPreparedStatement.setString(6,  "BASIC" );
			
			
			myPreparedStatement.executeUpdate();
			ResultSet rs = myPreparedStatement.getGeneratedKeys();			

			if ( !rs.next() ) return 0;		
			else return rs.getInt(1);	
			
		}
		
		catch( SQLException e ) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible d'ajouter les données");
		}
	
	};
	
	
	
	
	public void update(Membre membre) throws DaoException{
		
		try(Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(update); ) {
			
			myPreparedStatement.setString(1, membre.getNom()  );
			myPreparedStatement.setString(2, membre.getPrenom() );
			myPreparedStatement.setString(3, membre.getAdresse()  );
			myPreparedStatement.setString(4, membre.getEmail()  );
			myPreparedStatement.setString(5, membre.getTelephone()  );
			myPreparedStatement.setString(6, membre.getAbonnement().toString()  );
			myPreparedStatement.setString(7, String.valueOf( membre.getId() )  );
			myPreparedStatement.executeUpdate();		

		}
		
		catch( SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("Impossible de mettre a jour les données");
		}
	
	};
	
	
	public void delete (int id) throws DaoException{
		
		try (Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(delete);) {
			 
			myPreparedStatement.setString(1, String.valueOf(id) );
			myPreparedStatement.executeUpdate();		

		}
		
		catch( SQLException e ) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de supprimer les données");
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
