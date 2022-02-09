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

    private int numGadgets=1;
    private Timer timer;
    private TimerTask tarefa;

    int end = 2;

    public Boolean startCommunication() throws InterruptedException {
        service = new SerialService(properties.getPorta(), properties.getBaud(),properties.getTimeout());
        if(service.getPortIdentifier()){
            if(service.AbrirPorta()){
                if(timer == null){
                    readControler = new ReadControler(service, numGadgets);
                    timerInstantiated();
                }
            }
        }
        return true;
    }

    private void timerInstantiated() {
        timer = new Timer();
        tarefa = new TimerTask() {
            @Override
            public void run() {
                try {
                    readControler.read();
                } catch (Exception e) {
                    e.printStackTrace();
                    tarefa.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, 0, 5000);
    }
}
