package com.rodrigues.rodrigues.temperaruras;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CoroaTemperatura implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private int temperatura;
	
	public CoroaTemperatura(int temperatura) {
		super();
		this.temperatura = temperatura;
	}

	public CoroaTemperatura() {
		// TODO Auto-generated constructor stub
	}

	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}
	

}
