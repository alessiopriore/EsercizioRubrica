package com.alessiopriore.model;

public class TipoTelModel {
	private int id;
	private String modello;
	private String marca;
	private String size;
	private String weight;
	
	public TipoTelModel(int id, String modello, String marca, String size, String weight) {
		this.id = id;
		this.modello = modello;
		this.marca = marca;
		this.size = size;
		this.weight = weight;
	}
	
	@Override
	public String toString() {
				
		StringBuilder builder = new StringBuilder();
		
		builder.append("Id = ").append(id).append("\n")
		.append("modello = ").append(modello).append("\n")
		.append("marca = ").append(marca).append("\n")
		.append("size = ").append(size).append("\n")
		.append("weight = ").append(weight).append("\n");
			
		return builder.toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
}
