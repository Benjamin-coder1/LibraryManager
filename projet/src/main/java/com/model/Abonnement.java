package com.model;

public enum Abonnement {
	BASIC(2), PREMIUM(5), VIP(20);
	
	private int nombreEmprunts;
	
	private Abonnement(int nb_emprunts) {
		this.nombreEmprunts = nb_emprunts;
	}
	
	public int getNombreEmprunts() {
		return nombreEmprunts;
	}

}
