package com.rodrigues.rodrigues.serial.controller;

import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.service.SerialService;

import java.util.Timer;
import java.util.TimerTask;


public class SerialController{
    public SerialController(){}
    
    private SerialService service;
    private ReadControler readControler;
    private final SerialProperties properties = new SerialProperties("COM4");

    private int numGadgets=2;
    private Timer timer = new Timer(true);
    private TimerTask tarefa;

    int end = 2;

    public Boolean startCommunication() throws InterruptedException {
        if (service==null) service = new SerialService(properties.getPorta(), properties.getBaud(),properties.getTimeout());
        if (readControler==null) readControler = new ReadControler(service, numGadgets);
        timerInstantiated();
        return true;
    }

    private void timerInstantiated() {
        tarefa = new TimerTask() {
            @Override
            public void run() {
                try {
                    readControler.read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, 0, 10000);
    }
}
