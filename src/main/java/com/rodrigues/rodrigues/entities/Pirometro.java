package com.rodrigues.rodrigues.entities;

public class Pirometro {
	private String temp;
	private String dataTime;
	
	public Pirometro(String temp, String dataTime) {
		this.temp = temp;
		this.dataTime = dataTime;
	}
	
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	
	

}
