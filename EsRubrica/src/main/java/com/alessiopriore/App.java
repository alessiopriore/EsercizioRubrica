package com.alessiopriore;

import com.alessiopriore.controllers.TipoTelController;
import com.alessiopriore.controllers.UtenteController;
import com.alessiopriore.interfaces.TipoTelDAO;
import com.alessiopriore.interfaces.UtenteDAO;
import com.alessiopriore.interfaces.impl.TipoTelDAOImpl;
import com.alessiopriore.interfaces.impl.UtenteDAOImpl;
import com.alessiopriore.model.TipoTelModel;
import com.alessiopriore.model.UtenteModel;
import com.alessiopriore.view.TipoTelView;
import com.alessiopriore.view.UtenteView;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	UtenteModel utenteModel = getUtenteFromDB(args[0]); //TODO qualche check su args[0]
    	UtenteView utenteView = new UtenteView(utenteModel);
    	UtenteController utenteController = new UtenteController(utenteModel, utenteView);
		//utenteView.printInfo();
    	
    	TipoTelModel tipoTelModel = getTelefonoFromDB("8S"); //TODO qualche check su args[0]
    	TipoTelView tipoTelView = new TipoTelView(tipoTelModel);
    	TipoTelController telController = new TipoTelController(tipoTelModel, tipoTelView);	
    	//tipoTelView.printInfo();
    	
    	tipoTelModel.setModello("Xiaomi Redmi 4");
    	tipoTelModel.setMarca("Xiaomi");
    	tipoTelModel.setSize("135x70x30");
    	tipoTelModel.setWeight(200);
    	telController.updateView(tipoTelModel);
    	//getInsertTelTODB(tipoTelModel);
    	
    	getUpdateTelTODB("nuovo Xiaomi Redmi 4","Xiaomi");
    	
    	getUpdateUtenteTODB("grottaminarda",20,"pasquale.rossi@gmail.com");
    	
    	utenteModel.setNome("antonio");
    	utenteModel.setCognome("romualdo");
    	utenteModel.setData_nascita("12/06/1987");
    	utenteModel.setNum_tel("3335689741");
    	utenteModel.setCitta("Marano");
    	utenteModel.setProvincia("Napoli");
    	utenteModel.setEmail("antonio.romualdo@gmail.com");

    	//getInsertUtenteTODB(utenteModel,"HTC One M8");
    	
    	getDeletetUtenteTODB("antonio.romualdo@gmail.com");
    }
    
	private static UtenteModel getUtenteFromDB(String email) {
		
		UtenteDAO utenteDAO = new UtenteDAOImpl();		
		return utenteDAO.getUtenteInfo(email);
	}
	
	private static void getUpdateUtenteTODB(String citta, int tel, String mail) {
		
		UtenteDAO utenteDAO = new UtenteDAOImpl();		
		utenteDAO.updateUtenteInfo(citta,tel,mail);
	}
	
	private static void getInsertUtenteTODB(UtenteModel utenteModel,String telNome) {
		
		UtenteDAO utenteDAO = new UtenteDAOImpl();		
		utenteDAO.insertUtente(utenteModel, telNome);
	}
	
	private static void getDeletetUtenteTODB (String email){
		UtenteDAO utenteDAO = new UtenteDAOImpl();		
		utenteDAO.deleteUtenteInfo(email);;
	}
	
	
	private static TipoTelModel getTelefonoFromDB(String modello) {
		
		TipoTelDAO telDAO = new TipoTelDAOImpl();		
		return telDAO.getTipoTelInfo(modello);
	}
	
	private static void getInsertTelTODB(TipoTelModel tipoTelModel) {
		
		TipoTelDAO telDAO = new TipoTelDAOImpl();		
		telDAO.insertTipoTel(tipoTelModel);;
	}
	private static void getUpdateTelTODB(String modello, String marca) {
		
		TipoTelDAO telDAO = new TipoTelDAOImpl();		
		telDAO.updateTipoTelInfo(modello,marca);
	}
	
}
