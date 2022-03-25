package com.rodrigues.rodrigues.serial.controller;

import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;


public class SerialController{
	private ReadController readController;

    int end = 2;

    public void stopCommunication(){
    	readController.threadCancel();
    }
    
    public void startCommunication(){
    	instanciates();
    	
    	try {
			readController.read();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    private void instanciates() {
		if(readController==null)readController = DependencyInjection.getReadController();
	}
}
