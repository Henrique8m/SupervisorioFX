package com.rodrigues.rodrigues.serial.controller;

import java.util.Timer;
import java.util.TimerTask;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;


public class SerialController{

    public SerialController() {	}
    
    private ReadController readController;
	private PrimaryViewController primaryViewController;
	
    private Timer timer = new Timer(true);
    private TimerTask tarefa;


    int end = 2;

    public void startCommunication(){
    	instanciates();
    	if(tarefa == null) {    		
	    	primaryViewController.txLog.setText("Starting comunication...");
	    	primaryViewController.txLog1.setText("Starting comunication...");
	        try {
				timerInstantiated();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
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
        if(tarefa != null) {
        	tarefa.cancel();
        	readController.threadCancel();
        	tarefa = null;
        }else
        readController.threadCancel();
    }
    
    public ReadController getReadControler() {
    	return readController;
    }  
    
    private void instanciates() {
		if(readController==null)readController = DependencyInjection.getReadController();
		if(primaryViewController==null)primaryViewController = DependencyInjection.getPrimaryViewController();
	}
}
