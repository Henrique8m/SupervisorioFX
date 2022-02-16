package com.rodrigues.rodrigues.serial.controller;

import java.util.Timer;
import java.util.TimerTask;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.gui.util.Alerts;

import javafx.scene.control.Alert.AlertType;


public class SerialController{
    public SerialController(PrimaryViewController fxmlController){
        this.fxmlController  = fxmlController;
    }

    private PrimaryViewController fxmlController;
   


    private int numGadgets=11;
    private Timer timer = new Timer(true);
    private TimerTask tarefa;
    private ReadController readController = new ReadController(numGadgets,fxmlController, this);

    int end = 2;

    public Boolean startCommunication(){
 
        try {
			timerInstantiated();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return true;
    }

    private void timerInstantiated(){
        tarefa = new TimerTask() {
            @Override
            public void run() {
				try {
					readController.read();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        timer.scheduleAtFixedRate(tarefa, 3000, 7000);
    }
    public void timerCancel(){
        if(tarefa != null)tarefa.cancel();
    }
    
    public ReadController getReadControler() {
    	return readController;
    }
}
