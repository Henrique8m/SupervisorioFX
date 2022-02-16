package com.rodrigues.rodrigues.serial.properties;

public class SerialProperties {
    private String porta;
    private int baud = 9600;
    private int timeout = 500;
    private int stopBits = 1;
    private String paridade = "None";
    
    public SerialProperties(String porta){
        this.porta = porta;
    }

    public String getPorta() {
        return porta;
    }

    public int getBaud() {
        return baud;
    }

    public int getTimeout() {
        return timeout;
    }
    
    public int getStopBits() {
        return stopBits;
    }

	public String getParidade() {
		return paridade;
	}
}
