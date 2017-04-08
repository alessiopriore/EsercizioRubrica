package com.alessiopriore.interfaces;

import com.alessiopriore.model.UtenteModel;

public interface UtenteDAO {
	public UtenteModel getUtenteInfo(String email);
	public boolean updateUtenteInfo(String citta, int tel, String mail);
	public boolean deleteUtenteInfo(String email);
	public boolean insertUtente(UtenteModel utenteModel,String modello);
	
}