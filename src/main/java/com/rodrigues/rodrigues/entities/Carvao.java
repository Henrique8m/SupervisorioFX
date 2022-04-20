package com.rodrigues.rodrigues.entities;

import java.sql.Date;

import com.rodrigues.rodrigues.serial.utilitary.Format;

public class Carvao {

	public Carvao() {}
	
	public String getDataCarvao() {
		return dataCarvao;
	}
	public void setDataCarvao(String dataCarvao) {
		this.dataCarvao = dataCarvao;
	}
	public String getHoraCarvao() {
		return horaCarvao;
	}
	public void setHoraCarvao(String horaCarvao) {
		this.horaCarvao = horaCarvao;
	}
	public String getPesoCarvao() {
		return pesoCarvao;
	}
	public void setPesoCarvao(String pesoCarvao) {
		this.pesoCarvao = pesoCarvao;
	}
	public String getUmidadeCarvao() {
		return umidadeCarvao;
	}
	public void setUmidadeCarvao(String umidadeCarvao) {
		this.umidadeCarvao = umidadeCarvao;
	}
	
	private String dataCarvao;
	private String horaCarvao;
	private String pesoCarvao;
	private String umidadeCarvao;
	
	/*public Carvao(String dataCarvao, String horaCarvao, String pesoCarvao, String umidadeCarvao) {
		
		this.dataCarvao = dataCarvao;
		this.horaCarvao = horaCarvao;
		this.pesoCarvao = pesoCarvao;
		this.umidadeCarvao = umidadeCarvao;
	}*/
	
	public Carvao(Date dateCarvao, Double pesoCarvao, Double umidadeCarvao) {
		
		this.dataCarvao = Format.formatData.format(dateCarvao);
		this.horaCarvao = Format.formataTimeString.format(dateCarvao);
		try {
			this.pesoCarvao = Double.toString(pesoCarvao) + " Kg";
			this.umidadeCarvao = Double.toString(umidadeCarvao);
		}catch(NumberFormatException e ) {
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		String print = dataCarvao + "  " + horaCarvao + "  " + pesoCarvao;
		return print;
	}

	
}
