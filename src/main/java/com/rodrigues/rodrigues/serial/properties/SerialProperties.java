package com.rodrigues.rodrigues.serial.properties;

public class SerialProperties {
    String porta = "COM4";
    int baud = 9600;
    int timeout = 500;

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
