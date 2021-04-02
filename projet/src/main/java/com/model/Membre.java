package com.model;
	
	
public class Membre {
	
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String telephone;
	private Abonnement abonnement;
	
	
	public Membre(int id, String nom, String prenom, String adresse, String email, String telephone,Abonnement abonnement) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.abonnement = abonnement;
	}


	@Override
	public String toString() {
		return "id : " + id + " nom : " + nom + " prenom : " + prenom + " adresse : " + adresse + " email : " + email + " telephone : " + telephone + " abonnement : " + abonnement;
		
	}
	
	
	public int getId() {return id;}
	public String getNom() {return nom;	}
	public String getPrenom() {	return prenom;}
	public String getAdresse() {return adresse;	}
	public String getEmail() {return email;	}
	public String getTelephone() {return telephone;	}
	public Abonnement getAbonnement() { return abonnement;}



	public void setId(int id) {this.id = id;}
	public void setNom(String nom) {this.nom = nom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public void setAdresse(String adresse) {this.adresse = adresse;	}
	public void setEmail(String email) {this.email = email;	}
	public void setTelephone(String telephone) {this.telephone = telephone;	}
	public void setAbonnement(Abonnement abonnement) {this.abonnement = abonnement;	}
	
	
	
	
	
	 
	

}