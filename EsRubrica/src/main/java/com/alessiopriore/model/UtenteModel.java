package com.alessiopriore.model;

public class UtenteModel {
	
	private int id;
	private String nome;
	private String cognome;
	private String data_nascita;
	private String num_tel;
	private int id_modello;
	private String citta;
	private String provincia;
	private String email;
	
	public UtenteModel(int id, String nome, String cognome, String data_nascita, String num_tel, int id_modello,
			String citta, String provincia, String email) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.data_nascita = data_nascita;
		this.num_tel = num_tel;
		this.id_modello = id_modello;
		this.citta = citta;
		this.provincia = provincia;
		this.email = email;
	}
	
	
	@Override
	public String toString() {
				
		StringBuilder builder = new StringBuilder();
		
		builder.append("Id = ").append(id).append("\n")
		.append("nome = ").append(nome).append("\n")
		.append("cognome = ").append(cognome).append("\n")
		.append("data_nascita = ").append(data_nascita).append("\n")
		.append("num_tel = ").append(num_tel).append("\n")
		.append("id_modello = ").append(id_modello).append("\n")
		.append("citta = ").append(citta).append("\n")
		.append("provincia = ").append(provincia).append("\n")
		.append("email = ").append(email).append("\n");
			
		return builder.toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getData_nascita() {
		return data_nascita;
	}
	public void setData_nascita(String data_nascita) {
		this.data_nascita = data_nascita;
	}
	public String getNum_tel() {
		return num_tel;
	}
	public void setNum_tel(String num_tel) {
		this.num_tel = num_tel;
	}
	public int getId_modello() {
		return id_modello;
	}
	public void setId_modello(int id_modello) {
		this.id_modello = id_modello;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
