package com.rodrigues.rodrigues.serial.properties;

public class SerialProperties {
    String porta;
    int baud = 9600;
    int timeout = 500;

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
}
