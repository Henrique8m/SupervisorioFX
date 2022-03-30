package com.rodrigues.rodrigues.entities;

public class Balancas {
	private String name;
	private Integer peso;
		
	public Balancas(String name, Integer peso) {
		super();
		this.name = name;
		this.peso = peso;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	

}
