package com.alessiopriore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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
    public static void main( String[] args ) throws NumberFormatException, IOException
    {
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    	String nome, cognome,data_nascita,nro,citta,provincia,mail,modello,marca,dim;
		int peso;
    	String menu = "MENU\n1.Inserisci utente e suo telefono\n2.Visualizza informazione utente e telefono\n"
                + "3.Aggiornamento di un utente e telefono\n4.Cancella un utente\n5.Info telefono\n"
                + "6.Aggiorna info telefono\n7.Inserisci nuovo modello telefono\n0.Esci";
    	UtenteModel utenteModel = new UtenteModel();
    	TipoTelModel tel = null;

    	UtenteView utenteView = new UtenteView(utenteModel);
    	UtenteController utenteController = new UtenteController(utenteModel, utenteView);
		
    	TipoTelModel tipoTelModel = new TipoTelModel();
    	TipoTelView tipoTelView = new TipoTelView(tipoTelModel);
    	TipoTelController telController = new TipoTelController(tipoTelModel, tipoTelView);	
    	
    	int scelta = -1;
    	
    	while(scelta != 0){
    		System.out.println(menu);
    		try {
    			scelta = Integer.parseInt(input.readLine());
    		}catch (NumberFormatException e){
    			System.out.println("il valore da inserire deve essere un intero");
    		}
        	
    		switch(scelta){
    		   case 1:
    			   System.out.println("Inserisci");
    			   System.out.println("nome: ");
    			   nome = input.readLine();
    			   utenteModel.setNome(nome);
    			   System.out.println("cognome: ");
    			   cognome = input.readLine();
    			   utenteModel.setCognome(cognome);
    			   System.out.println("data di nascita: ");
    			   data_nascita = input.readLine();
    		       utenteModel.setData_nascita(data_nascita);
    		       System.out.println("numero: ");
    		       nro = input.readLine();
    		       utenteModel.setNum_tel(nro);
    		       System.out.println("citta: ");
    		       citta = input.readLine();
    		       utenteModel.setCitta(citta);
    		       System.out.println("provincia: ");
    		       provincia = input.readLine();
    		       utenteModel.setProvincia(provincia);
    		       System.out.println("email: ");
    		       mail = input.readLine();
    		       utenteModel.setEmail(mail);
    		       System.out.println("Modello telefono:: ");
    		       modello = input.readLine();
    		       tel = getTelefonoFromDB(modello);
    		       if (tel != null){
    		    	  utenteModel.setId_modello(tel.getId());
    		    	   if(getInsertUtenteTODB(utenteModel,modello))
    		    		   System.out.println("Inserimento andato a buon fine");
    		    	   else
    		    		   System.out.println("Inserimento fallito");
    		      }else{
    		    	  System.out.println("Telefono non presente");
    		    	  System.out.println("Inserire prima il modello di telefono e le caratteristiche nel db");
    		      }
    			   break;
    		   case 2:
    			   System.out.println("email: ");
    			   mail = input.readLine();
    			   utenteModel = getUtenteFromDB(mail);
    			   if (utenteModel != null){
    				   utenteController.setUtenteModel(utenteModel);
        			   utenteView.setUtente(utenteModel); 
        			   utenteView.printInfo();
    			   }else {
    				   System.out.println("Utente non esiste");
    			   }
    			   break;
    		   case 3:
    			   System.out.println("Inserici la citta che vive ora: ");
    			   citta = input.readLine();
    			   System.out.println("Inserici il modello di telefono che possiede: ");
    			   modello = input.readLine();
    			   System.out.println("Inserici la mail dell'utente da aggiornare: ");
    			   mail = input.readLine();
    			   if(getUpdateUtenteTODB(citta,modello,mail)){
    				   utenteModel = getUtenteFromDB(mail);
    				   utenteController.setUtenteModel(utenteModel);
    				   utenteView.setUtente(utenteModel);
    				   System.out.println("Stampa utente aggiornato: ");
    				   utenteView.printInfo();
    			   }else{
    				   System.out.println("Non è presente un utente con questa mail");
    			   }	   
    			   break;
    		   case 4:
    			   System.out.println("email: ");
    			   mail = input.readLine();
    			   if(getDeletetUtenteTODB(mail)){
    				   System.out.println("Utente cancellato");
    			   }else{
    				   System.out.println("Utente non presente");
    			   }
    			   break;
    		   case 5:
    			   System.out.println("Inserisci il nome del modello di cui vuoi le info: ");
    			   modello = input.readLine();
    			   tipoTelModel = getTelefonoFromDB(modello);
    			   if (tipoTelModel != null){
    				   telController.setTipoTelModel(tipoTelModel);
    				   tipoTelView.setTipoTel(tipoTelModel);
    				   tipoTelView.printInfo();
  
    			   }else{
    				   System.out.println("Modello di cellulare non presente.");
    				   System.out.println("Inserire prima il modello.");
    			   }
    				   
    			   break;
    		   case 6:
    			   System.out.println("Inserisci la marca del modello di cui aggiornare le info: ");
    			   marca = input.readLine();
    			   System.out.println("Inserisci il nome del modello: ");
    			   modello = input.readLine();
    			   if (getUpdateTelTODB(modello,marca)){
    				   tipoTelModel = getTelefonoFromDB(modello);
    				   telController.setTipoTelModel(tipoTelModel);
    				   tipoTelView.setTipoTel(tipoTelModel);
    				   System.out.println("aggiornamento riuscito con successo");
    				   tipoTelView.printInfo();
    			   }else {
    				   System.out.println("Non Esite un telefono con la marca inserita");
    			   }
    			   break;
    		   case 7:
    			   System.out.println("Inserisci la marca: ");
    			   marca = input.readLine();
    			   tipoTelModel.setMarca(marca);
    			   System.out.println("Inserisci il modello: ");
    			   modello = input.readLine();
    			   tipoTelModel.setModello(modello);
    			   System.out.println("Inserisci la dimensione: ");
    			   dim = input.readLine();
    			   tipoTelModel.setSize(dim);
    			   System.out.println("Inserisci il peso: ");
    				try {
    					peso = Integer.parseInt(input.readLine());
    	    		}catch (NumberFormatException e){
    	    			System.err.println("il valore da inserire deve essere un intero");
    	    			System.out.println("Reinserire il peso: ");
    	    			peso = Integer.parseInt(input.readLine());
    	    		}
    			   tipoTelModel.setWeight(peso);
    			   if (getInsertTelTODB(tipoTelModel)){
    				   System.out.println("inserimento andato a buon fine");
    			   }else{
    				   System.out.println("Telefono non inserito");
    			   }
    			   break; 
    		   case 0:
    			   System.out.println("bye bye");
    			   break;
    		   default:
    				System.out.println("Scelta errata!");
    				break;
    		}
    	}
    }
    
	private static UtenteModel getUtenteFromDB(String email) {
		
		UtenteDAO utenteDAO = new UtenteDAOImpl();		
		return utenteDAO.getUtenteInfo(email);
	}
	
	private static boolean getUpdateUtenteTODB(String citta, String tel, String mail) {
		
		UtenteDAO utenteDAO = new UtenteDAOImpl();		
		return utenteDAO.updateUtenteInfo(citta,tel,mail);
	}
	
	private static boolean getInsertUtenteTODB(UtenteModel utenteModel,String telNome) {
		
		UtenteDAO utenteDAO = new UtenteDAOImpl();		
		return utenteDAO.insertUtente(utenteModel, telNome);
	}
	
	private static boolean getDeletetUtenteTODB (String email){
		UtenteDAO utenteDAO = new UtenteDAOImpl();		
		return utenteDAO.deleteUtenteInfo(email);
	}
	
	
	private static TipoTelModel getTelefonoFromDB(String modello) {
		
		TipoTelDAO telDAO = new TipoTelDAOImpl();		
		return telDAO.getTipoTelInfo(modello);
	}
	
	private static boolean getInsertTelTODB(TipoTelModel tipoTelModel) {
		
		TipoTelDAO telDAO = new TipoTelDAOImpl();		
		return telDAO.insertTipoTel(tipoTelModel);
	}
	private static boolean getUpdateTelTODB(String modello, String marca) {
		
		TipoTelDAO telDAO = new TipoTelDAOImpl();		
		return telDAO.updateTipoTelInfo(modello,marca);
	}
	
}
