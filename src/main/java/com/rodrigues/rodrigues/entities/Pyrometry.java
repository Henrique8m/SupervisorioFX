package com.rodrigues.rodrigues.entities;

public class Pyrometry {
	private String timeStartFinish;
	private String pressaoCoroa;
	private String pressaoTopo;
	private String tempCoroa;
	private String tempTopo;
	private String vazaoAr;
	private String secador;

	public Pyrometry(String timeStartFinish, String pressaoCoroa, String pressaoTopo, String tempCoroa, String tempTopo,
			String vazaoAr, String secador) {
		super();
		this.timeStartFinish = timeStartFinish;
		this.pressaoCoroa = pressaoCoroa;
		this.pressaoTopo = pressaoTopo;
		this.tempCoroa = tempCoroa;
		this.tempTopo = tempTopo;
		this.vazaoAr = vazaoAr;
		this.secador = secador;
	}

	public Pyrometry() {}
	
	public String getTimeStartFinish() {
		return timeStartFinish;
	}

	public void setTimeStartFinish(String timeStartFinish) {
		this.timeStartFinish = timeStartFinish;
	}

	public String getPressaoCoroa() {
		return pressaoCoroa;
	}

	public void setPressaoCoroa(String pressaoCoroa) {
		this.pressaoCoroa = pressaoCoroa;
	}

	public String getPressaoTopo() {
		return pressaoTopo;
	}

	public void setPressaoTopo(String pressaoTopo) {
		this.pressaoTopo = pressaoTopo;
	}

	public String getTempCoroa() {
		return tempCoroa;
	}

	public void setTempCoroa(String tempCoroa) {
		this.tempCoroa = tempCoroa;
	}

	public String getTempTopo() {
		return tempTopo;
	}

	public void setTempTopo(String tempTopo) {
		this.tempTopo = tempTopo;
	}

	public String getVazaoAr() {
		return vazaoAr;
	}

	public void setVazaoAr(String vazaoAr) {
		this.vazaoAr = vazaoAr;
	}

	public String getSecador() {
		return secador;
	}

	public void setSecador(String secador) {
		this.secador = secador;
	}

	
	
	
}
