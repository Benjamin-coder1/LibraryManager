package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.ensta.librarymanager.persistence.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.model.*;




public class LivreDaoImpl implements LivreDao {
	
	private String getList = "FROM livre SELECT *";
	private String getById = "FROM livre SELECT * WHERE id = ?";
	private String create = "INSERT INTO livre(titre,auteur,isbn)  VALUES(?,?,?)";
	private String update = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?" ;
	private String delete = "DELETE FROM livre WHERE id = ?";
	private String count = "SELECT COUNT(id) AS count FROM livre";
	
	
	private static LivreDaoImpl INSTANCE;    
    /** Point d'accès pour l'instance unique du singleton */
    public static LivreDaoImpl getInstance()
    {           
        if (INSTANCE == null){
        	INSTANCE = new LivreDaoImpl(); 
        }
        return INSTANCE;
    }
	
	
	public List<Livre> getList() throws DaoException {	
		
 		List<Livre> livres = new ArrayList<Livre>(0);
		
		try(Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(getList); ){			
 
			ResultSet rs = myPreparedStatement.executeQuery();			
			
			while (rs.next() ) {
				Livre livre = new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("isbn")  );
				livres.add(livre);	
 			}
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
 			throw new DaoException("Impossible de récupérer les données");
		} 	
		
		return livres;
	};
	
	
	public Livre getById(int id ) throws DaoException {
		
		try (Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement   = connection.prepareStatement(getById); ) {
			
			myPreparedStatement.setString(1, String.valueOf(id) );
			ResultSet rs = myPreparedStatement.executeQuery();
			

			if ( !rs.next() ) {
				Livre livre = null;
				return livre;
			}
			
			else {
				Livre livre = new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("isbn")  );
				return livre;
			}
			
		}
		
		catch( SQLException e ) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de récupérer les données");
		}
	
	};
	
	
	
	public int create(String titre, String auteur, String isbn) throws DaoException{
		
		try (Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS); ) {
			
			myPreparedStatement.setString(1, titre );
			myPreparedStatement.setString(2, auteur );
			myPreparedStatement.setString(3, isbn );
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
	
	
	
	
	public void update(Livre livre) throws DaoException{
		
		try(Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(update ); ) {
			
			myPreparedStatement.setString(1, livre.getTitre()  );
			myPreparedStatement.setString(2, livre.getAuteur() );
			myPreparedStatement.setString(3, livre.getIsbn()   );
			myPreparedStatement.setString(4, String.valueOf( livre.getId() )  );
			myPreparedStatement.executeUpdate();		

		}
		
		catch( SQLException e ) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible de mettre a jour les données");
		}
	
	};
	
	
	public void delete (int id) throws DaoException{
		
		try  (Connection connection = ConnectionManager.getConnection();  
			PreparedStatement myPreparedStatement = connection.prepareStatement(delete );) {
			 
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
