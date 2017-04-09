package com.alessiopriore.controllers;

import com.alessiopriore.model.UtenteModel;
import com.alessiopriore.view.UtenteView;

public class UtenteController {
	
	UtenteModel utenteModel;
	UtenteView  utenteView;
	
	public UtenteController(UtenteModel utenteModel, UtenteView utenteView) {
		this.utenteModel = utenteModel;
		this.utenteView = utenteView;
	}
	
	public void updateView(UtenteModel utenteModel){
		utenteView.setUtente(utenteModel);
	}
	
	public void updateModel(UtenteView utenteView){
		
		this.utenteModel = utenteView.getUtente();		
	}
	
	UtenteModel getUtenteModel() {
		return utenteModel;
	}

	public void setUtenteModel(UtenteModel utenteModel) {
		this.utenteModel = utenteModel;
	}

	public UtenteView getUtenteView() {
		return utenteView;
	}

	public void setUtenteView(UtenteView utenteView) {
		this.utenteView = utenteView;
	}
}
