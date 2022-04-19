package com.rodrigues.rodrigues.entities;

public class Balancas {	
	private String date;
	private String hInicio;
	private String hFim;	
	private String balanca01;
	private String balanca02;
	private String balanca03;
	private String balanca04;
	private String balanca05;
	private String balanca06;
	private String balanca07;
	private String balanca08;
	private String balanca09;
	private String balanca10;

	
	public Balancas(String date,String hInicio, String hFim, String balanca01, String balanca02, String balanca03, String balanca04, String balanca05,
			String balanca06, String balanca07, String balanca08, String balanca09, String balanca10 ) {
		this.date = date;
		this.balanca01 = balanca01;
		this.balanca02 = balanca02;
		this.balanca03 = balanca03;
		this.balanca04 = balanca04;
		this.balanca05 = balanca05;
		this.balanca06 = balanca06;
		this.balanca07 = balanca07;
		this.balanca08 = balanca08;
		this.balanca09 = balanca09;
		this.balanca10 = balanca10;
		this.hInicio = hInicio;
		this.hFim = hFim;
	}
	
	public Balancas() {
		
	}

	public String getBalanca01() {
		return balanca01;
	}
	public void setBalanca01(String balanca01) {
		this.balanca01 = balanca01;
	}
	public String getBalanca02() {
		return balanca02;
	}
	public void setBalanca02(String balanca02) {
		this.balanca02 = balanca02;
	}
	public String getBalanca03() {
		return balanca03;
	}
	public void setBalanca03(String balanca03) {
		this.balanca03 = balanca03;
	}
	public String getBalanca04() {
		return balanca04;
	}
	public void setBalanca04(String balanca04) {
		this.balanca04 = balanca04;
	}
	public String getBalanca05() {
		return balanca05;
	}
	public void setBalanca05(String balanca05) {
		this.balanca05 = balanca05;
	}
	public String getBalanca06() {
		return balanca06;
	}
	public void setBalanca06(String balanca06) {
		this.balanca06 = balanca06;
	}
	public String getBalanca07() {
		return balanca07;
	}
	public void setBalanca07(String balanca07) {
		this.balanca07 = balanca07;
	}
	public String getBalanca08() {
		return balanca08;
	}
	public void setBalanca08(String balanca08) {
		this.balanca08 = balanca08;
	}
	public String getBalanca09() {
		return balanca09;
	}
	public void setBalanca09(String balanca09) {
		this.balanca09 = balanca09;
	}
	public String getBalanca10() {
		return balanca10;
	}
	public void setBalanca10(String balanca10) {
		this.balanca10 = balanca10;
	}
	public String getHInicio() {
		return hInicio;
	}
	public void setHInicio(String horario) {
		this.hInicio = horario;
	}

	public String getHFim() {
		return hFim;
	}

	public void setHFim(String horarioFim) {
		this.hFim = horarioFim;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
