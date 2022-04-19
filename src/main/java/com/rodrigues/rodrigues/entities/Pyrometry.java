package com.rodrigues.rodrigues.entities;

public class Pyrometry {
	private String timeStartFinish;
	private String vazaoAr;
	private String pressaoCoroa;
	private String tempCoroa;
	private String tempTopo;
		
	public Pyrometry(String timeStartFinish, String vazaoAr, String pressaoCoroa, String tempCoroa, String tempTopo) {
		super();
		this.timeStartFinish = timeStartFinish;
		this.vazaoAr = vazaoAr;
		this.pressaoCoroa = pressaoCoroa;
		this.tempCoroa = tempCoroa;
		this.tempTopo = tempTopo;
	}

	public Pyrometry() {
	}

	public String getTimeStartFinish() {
		return timeStartFinish;
	}

	public void setTimeStartFinish(String timeStartFinish) {
		this.timeStartFinish = timeStartFinish;
	}

	public String getVazaoAr() {
		return vazaoAr;
	}

	public void setVazaoAr(String vazaoAr) {
		this.vazaoAr = vazaoAr;
	}

	public String getPressaoCoroa() {
		return pressaoCoroa;
	}

	public void setPressaoCoroa(String pressaoCoroa) {
		this.pressaoCoroa = pressaoCoroa;
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
	
}
