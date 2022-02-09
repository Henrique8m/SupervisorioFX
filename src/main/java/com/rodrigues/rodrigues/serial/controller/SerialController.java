package com.rodrigues.rodrigues.serial.controller;

import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.service.SerialService;

import java.util.Timer;
import java.util.TimerTask;


public class SerialController{
    public SerialController(){}
    
    private SerialService service;
    private final SerialProperties properties = new SerialProperties();
    
    private Timer timer;
    private TimerTask tarefa;

    int end = 2;

    public Boolean startCommunication() throws InterruptedException {
        service = new SerialService(properties.getPorta(), properties.getBaud(),properties.getTimeout());
        if(service.getPortIdentifier()){
            if(service.AbrirPorta()){
                if(timer == null){
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
                    System.out.println("Teste ");
                    //irei chamar uma nova comunicação por aqui, iniciando uma varredura por todos aparelhos
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, 0, 2000);        
    }
}
