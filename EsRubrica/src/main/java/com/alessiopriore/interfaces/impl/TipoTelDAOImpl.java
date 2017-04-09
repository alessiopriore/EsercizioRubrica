package com.alessiopriore.interfaces.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import com.alessiopriore.interfaces.TipoTelDAO;
import com.alessiopriore.model.TipoTelModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class TipoTelDAOImpl implements TipoTelDAO{
	
	final static String INSERT_INFO = "INSERT INTO tipotel (modello,marca,size,weight)VALUES (?,?,?,?)"; 
	final static String UPDATE_INFO = "UPDATE tipotel SET modello = ? WHERE marca = ? ;"; 
	final static String USER_INFO = "SELECT modello,marca,size,weight FROM tipotel where modello = ?;"; 
	
	public TipoTelModel getTipoTelInfo(String modello) {
		
		
		TipoTelModel tipoTelModel = new TipoTelModel();
			
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
			
			
		Connection conn = null;
				
		try {
			    conn = (Connection) dataSource.getConnection();
				PreparedStatement stmtTelInfo =  (PreparedStatement) conn.prepareStatement(USER_INFO);
				stmtTelInfo.setString(1, modello);
				 
				ResultSet rsTelInfoSet = stmtTelInfo.executeQuery();
				
				if(rsTelInfoSet.first()){
					tipoTelModel.setModello(rsTelInfoSet.getString("modello"));
					tipoTelModel.setMarca(rsTelInfoSet.getString("marca"));
					tipoTelModel.setSize(rsTelInfoSet.getString("size"));
					tipoTelModel.setWeight(rsTelInfoSet.getInt("weight"));
				  }else{
						tipoTelModel = null;
					}
				}
				
				catch(SQLException e) {
					e.printStackTrace();
				}
				finally{
				    DbUtils.closeQuietly(conn); 
				}
				
			return tipoTelModel;
	}

	public boolean updateTipoTelInfo(String modello, String marca) {
		// TODO Auto-generated method stub
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
				
		Connection conn = null;
				
		try {
			    conn = (Connection) dataSource.getConnection();
				PreparedStatement stmtTelUpdate =  (PreparedStatement) conn.prepareStatement(UPDATE_INFO);

				stmtTelUpdate.setString(1,modello);
				stmtTelUpdate.setString(2,marca);
				
				if (stmtTelUpdate.executeUpdate() > 0){
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

	public boolean insertTipoTel(TipoTelModel tipoTelModel ) {
		// TODO Auto-generated method stub	
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/rubrica");
				
		Connection conn = null;
				
		try {
			    conn = (Connection) dataSource.getConnection();
				PreparedStatement stmtTelInsert =  (PreparedStatement) conn.prepareStatement(INSERT_INFO);

				stmtTelInsert.setString(1,tipoTelModel.getModello());
				stmtTelInsert.setString(2,tipoTelModel.getMarca());
				stmtTelInsert.setString(3,tipoTelModel.getSize());
				stmtTelInsert.setInt(4,tipoTelModel.getWeight());
				
				if(stmtTelInsert.executeUpdate() > 0){
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
