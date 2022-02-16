package com.rodrigues.rodrigues.serial.controller;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.service.SerialService;
import com.rodrigues.rodrigues.serial.utilitary.CalculatorData;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;

public class ReadController implements Runnable{

    private final SerialProperties properties = new SerialProperties("COM4");
    private PrimaryViewController primaryViewController;
    private SerialService serialService;
    private SerialController serialController;

    private int lostConection = 0;
    private int attemptToReconnect = 5;
    private byte[] numGadgets = new byte[11];
    private byte[] bufferRead= new byte[8];

    private Thread thread = new Thread(this);

    public ReadController() {
		// TODO Auto-generated constructor stub
	}

	public void read() throws InterruptedException {
		
		instanciates();
		if(!thread.isAlive()){
			thread.run();
			lostConection = 0;
			}
		}

    @Override
    public void run() {
        if(numGadgets!=null){
            for(int i=0; i < numGadgets.length; i++){
                if(sweep(i+1))
                	return;
            }
        }
        thread.interrupt();
    }

    private Boolean sweep(int i) {
        try{
        	
            serialService.setPortName(properties.getPorta());
            serialService.setBaudRate(properties.getBaud());
            serialService.setTimeout(properties.getTimeout());
            
            bufferRead = CalculatorData.addressRead(i);

            if(serialService.getPortIdentifier()) {
                serialService.openPort();
                serialService.writeData(bufferRead);
                Thread.sleep(100);
                serialService.readData();
                Thread.sleep(300);
                indicadores(i);
            	primaryViewController.txLog.setText("Conection OK");
            	primaryViewController.txLog1.setText("Conection OK");
                return false;
            }
            else{
            	lostConection++;
            	Thread.sleep(300); 
            	
            	if(lostConection <= attemptToReconnect) {
            		thread.interrupt();
               		return false;
            	}else {
                	serialController.timerCancel();
                	primaryViewController.txLog.setText("Conection Lost");
                	primaryViewController.txLog1.setText("Conection Lost");
                	return true;
            	}
            } 

        }catch(Exception e) {
        	e.printStackTrace();
        	return true;
        }
		
    }

    //Ate desenvolver algo melhor
    //essa vai ser a varredura dos aparelhos
    private void indicadores(int i) {
    	try {
            if(serialService.getDisplay()!=null){
                String display = serialService.getDisplay();
                if(i==1){primaryViewController.cq1.setText(display);}
                if(i==2){ primaryViewController.cq2.setText(display);}
                if(i==3){ primaryViewController.cq3.setText(display);}
                if(i==4){ primaryViewController.s1.setText(display);}
                if(i==5){ primaryViewController.s2.setText(display);}
                if(i==6){ primaryViewController.s3.setText(display);}
                if(i==7){ primaryViewController.topoE.setText(display);}
                if(i==7){ primaryViewController.topoD.setText(display);}
                if(i==8){ primaryViewController.coroaE.setText(display);}
                if(i==8){ primaryViewController.coroaD.setText(display);}
                if(i==9){ primaryViewController.ptopo.setText(display);}
                if(i==10){ primaryViewController.vazao.setText(display);}
                if(i==11){ primaryViewController.psm.setText(display);}
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}

    }
    public SerialProperties getSerialProperties() {
    	return properties;
    }
    
    private void instanciates() {
    	if(serialService==null)serialService = DependencyInjection.getSerialService();
		if(serialController==null)serialController = DependencyInjection.getSerialController();
		if(primaryViewController==null)primaryViewController = DependencyInjection.getPrimaryViewController();
	}
}
