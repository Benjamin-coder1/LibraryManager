package com.model;
import java.time.LocalDate;
	
	
public class Emprunt {
	
	private int id;
	private Membre membre;
	private Livre livre;
	private LocalDate dateEmprunt;
	private LocalDate dateRetour;
	
	
	
	public int getId() {return id;	}
	public Membre getMembre() {	return membre;	}
	public Livre getLivre() {return livre;}
	public LocalDate getDateEmprunt() {	return dateEmprunt;}
	public LocalDate getDateRetour() {return dateRetour;}


	public void setId(int id) {	this.id = id;}
	public void setMembre(Membre membre) {this.membre = membre;	}
	public void setLivre(Livre livre) {	this.livre = livre;	}
	public void setDateEmprunt(LocalDate dateEmprunt) {	this.dateEmprunt = dateEmprunt;	}
	public void setDateRetour(LocalDate dateRetour) {this.dateRetour = dateRetour;	}
	
	
	
	public Emprunt(int id, Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetour) {
		this.id = id;
		this.membre = membre;
		this.livre = livre;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}


	@Override
	public String toString() {
		return "id : " + id + " membre : " + membre.getNom() + " livre : " + livre.getTitre() + " dateEmprunt : " + dateEmprunt.toString() + " dateRetour : " + dateRetour.toString(); 
		
	}
	
	
	
	 
	

}