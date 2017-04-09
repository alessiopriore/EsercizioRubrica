package com.alessiopriore.interfaces.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.apache.commons.dbutils.DbUtils;

import com.alessiopriore.interfaces.UtenteDAO;
import com.alessiopriore.model.TipoTelModel;
import com.alessiopriore.model.UtenteModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class UtenteDAOImpl implements UtenteDAO{

	final static String INSERT_INFO = "INSERT INTO utente (nome,cognome,data_nascita,num_tel,id_modello,citta,provincia,email)VALUES (?,?,?,?,?,?,?,?)"; 
	final static String TEL_INFO = "SELECT id FROM tipotel WHERE modello = ?;";
	final static String UPDATE_INFO = "UPDATE utente SET citta = ? , id_modello = ? WHERE email = ? ;"; 
	final static String USER_INFO = "SELECT U.nome,U.cognome,U.data_nascita,U.num_tel,U.citta,U.provincia,U.email,T.modello "
			+ "FROM utente U JOIN tipotel T ON (U.id_modello = T.id) where U.email = ?;"; 
	final static String DELETE = "DELETE  FROM utente WHERE email = ?;";
	
	public UtenteModel getUtenteInfo(String email) {
		UtenteModel utenteModel = new UtenteModel();
		TipoTelModel tipoTelModel = new TipoTelModel ();
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
		
		
		Connection conn = null;
			
		try {
			  conn = (Connection) dataSource.getConnection();
			 
			  PreparedStatement stmtUtenteInfo =  (PreparedStatement) conn.prepareStatement(USER_INFO);
			  
			  stmtUtenteInfo.setString(1, email);
			 
			  ResultSet rsUtenteInfoSet = stmtUtenteInfo.executeQuery();
				
			  if(rsUtenteInfoSet.first()){
				  utenteModel.setNome(rsUtenteInfoSet.getString("nome"));
				  utenteModel.setCognome(rsUtenteInfoSet.getString("cognome"));
				  utenteModel.setData_nascita(rsUtenteInfoSet.getString("data_nascita"));
				  utenteModel.setNum_tel(rsUtenteInfoSet.getString("num_tel"));
				  utenteModel.setCitta(rsUtenteInfoSet.getString("citta"));
				  utenteModel.setProvincia(rsUtenteInfoSet.getString("provincia"));
				  utenteModel.setEmail(email);
				  tipoTelModel.setModello(rsUtenteInfoSet.getString("modello"));
				  utenteModel.setTipocell(tipoTelModel);
			  }else{
				  utenteModel = null;
			  }
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally{
			    DbUtils.closeQuietly(conn); 
			}
				
		return utenteModel;
	}

	public boolean updateUtenteInfo(String citta, String tel, String mail) {
		// TODO Auto-generated method stub
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
				
		Connection conn = null;
				
		try {
			    conn = (Connection) dataSource.getConnection();
			    PreparedStatement stmtTelInfo =  (PreparedStatement) conn.prepareStatement(TEL_INFO);
				PreparedStatement stmtUtenteUpdate =  (PreparedStatement) conn.prepareStatement(UPDATE_INFO);
				
				stmtTelInfo.setString(1, tel);
			    ResultSet res = stmtTelInfo.executeQuery();
			    
			    if (res.first()){
			    	stmtUtenteUpdate.setString(1,citta);
					stmtUtenteUpdate.setInt(2,res.getInt("id"));
					stmtUtenteUpdate.setString(3,mail);
					if (stmtUtenteUpdate.executeUpdate() > 0){
						return true;
					}
			    }else{
			    	System.out.println("Telefono non presente");
			    	System.out.println("Inserire prima il modello del telefono");
			    }
				
			}		
		catch(SQLException e) {	
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(conn); 
		}
		return false;
	}

	public boolean insertUtente(UtenteModel utenteModel,String telNome) {
		// TODO Auto-generated method stub
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
		boolean esito = false;		
		Connection conn = null;
				
		try {
			    conn = (Connection) dataSource.getConnection();
			    int id;
			    PreparedStatement stmtTelSelect =  (PreparedStatement) conn.prepareStatement(TEL_INFO);
			    stmtTelSelect.setString(1, telNome);
			    ResultSet res = stmtTelSelect.executeQuery();
			
			    if(res.first()){
			    	id = res.getInt("id");
			    	PreparedStatement stmtUtenteUpdate =  (PreparedStatement) conn.prepareStatement(INSERT_INFO);
			    	
					stmtUtenteUpdate.setString(1,utenteModel.getNome());
					stmtUtenteUpdate.setString(2,utenteModel.getCognome());
					stmtUtenteUpdate.setString(3,utenteModel.getData_nascita());
					stmtUtenteUpdate.setString(4,utenteModel.getNum_tel());
					stmtUtenteUpdate.setInt(5,id);
					stmtUtenteUpdate.setString(6,utenteModel.getCitta());
					stmtUtenteUpdate.setString(7,utenteModel.getProvincia());
					stmtUtenteUpdate.setString(8,utenteModel.getEmail());
					
					if (stmtUtenteUpdate.executeUpdate() > 0){	
						esito = true;
					}
						
			    }
			}		
		catch(SQLException e) {	
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(conn); 
		}
		return esito;
	}

	public boolean deleteUtenteInfo(String email) {
		// TODO Auto-generated method stub
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
				
		Connection conn = null;
				
		try {
			    conn = (Connection) dataSource.getConnection();

			    PreparedStatement stmtTelDelete =  (PreparedStatement) conn.prepareStatement(DELETE);
			    stmtTelDelete.setString(1, email);
			    
			    if(stmtTelDelete.executeUpdate() > 0){
			    	return true;
			    }
			}		
		catch(SQLException e) {	
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(conn); 
		}
		return false;
	}


	
}
