package com.alessiopriore.view;

import com.alessiopriore.model.UtenteModel;

public class UtenteView {
    
	private UtenteModel utente;
	
	public UtenteView(UtenteModel utente){
		this.utente = utente;
	}
	public UtenteModel getUtente() {
		return utente;
	}

	public void setUtente(UtenteModel utente) {
		this.utente = utente;
	}
	
	public void printInfo(){
		System.out.println(utente.toString());
	}
}
