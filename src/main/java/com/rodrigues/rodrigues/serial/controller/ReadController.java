package com.rodrigues.rodrigues.serial.controller;

import javax.comm.SerialPort;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.gui.service.PrimaryViewService;
import com.rodrigues.rodrigues.serial.dao.WriteSetPoints;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.service.FormatData;
import com.rodrigues.rodrigues.serial.service.SerialService;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;
import com.rodrigues.rodrigues.serial.utilitary.calc.CalculatorData;

public class ReadController implements Runnable{

    private SerialProperties serialProperties;
    private PrimaryViewController primaryViewController;
    private PrimaryViewService viewService;
    private SerialService serialService;
    private SerialController serialController;
    private FormatData formatData;
    private WriteSetPoints writeSetPoints;

    private int lostConection = 0;
    private int attemptToReconnect = 5;
    private byte[] numGadgets = new byte[28];
    private byte[] bufferWrite= new byte[8];
    private byte[] bufferRead = new byte[7];
    private byte[] bufferReadAlfa = new byte[17];
    
    private int bufferSizeRead;
    private int bufferSizeWrite;
     
    private String display;
    private String[] displayVetor = new String[29];
    
    private Thread thread = new Thread(this);
    
    private Boolean whileRead = false;

    
    public ReadController() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("removal")
	public void read() throws InterruptedException {
		
		instanciates();
		if(!thread.isAlive()){
			whileRead = true;
			lostConection = 0;
			thread.start();
			}	
			else {
				whileRead = true;
				thread.resume();
			}
			
		}

    @Override
    public void run() {
    	try {
			Thread.sleep(1000);
	    	while(whileRead) {
	            for(int i=0; i < numGadgets.length; i++){
	
	            	if(thread.isInterrupted()) {
	                	primaryViewController.txLog.setText("Conection Lost");
	                	primaryViewController.txLog1.setText("Conection Lost");
	            		return;
	            	}                	
	                sweep(i+1);
	           }
	    	}
	    	
	    	
    	} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    private void sweep(int i) {
        SerialPort serial;
    	
    	try{
        	
        	serial = serialService.enablePortCom(serialProperties.getPorta(), serialProperties.getBaud(), serialProperties.getTimeout(), serialProperties.getStopBits());
        	
            
   	           
           
 
            
            	
            if(serial != null) {               
                
				if(i>=19) {
					bufferWrite = CalculatorData.addressReadAlfa(i,80,6); 
					bufferSizeRead = 17;
					bufferSizeWrite = 27;
					serialService.writeData(bufferWrite, serial, bufferSizeWrite);
	            	bufferReadAlfa = serialService.readData(serial, bufferSizeRead);
	            	
				}  
				else {
					if(i==15)
						bufferWrite = CalculatorData.addressRead(i,2);
				
		            else 
		            	bufferWrite = CalculatorData.addressRead(i,1);
		            
					
	            	bufferSizeRead = 7;
	            	bufferSizeWrite = 8;
	            	serialService.writeData(bufferWrite, serial, bufferSizeWrite);
	            	bufferRead = serialService.readData(serial, bufferSizeRead);
	            	
		        }
	                
                if(bufferRead != null) {                
	                if((i >= 1 )&&(i<=11)||(i==13 || i==14))
	                	//displayVetor[i-1] 
	                	display = formatData.formatData(bufferRead, "N1540", "int");
	                else if (i==12 || i==17||i==18)
	                	//displayVetor[i-1]
	                	display= formatData.formatData(bufferRead, "N1540_4_a_20", "double");
	                else if (i==15||i==16)
	                	//displayVetor[i-1]
	                	display	= formatData.formatData(bufferRead, "N2000", "int");
                }else if(bufferReadAlfa != null){
                	if(i>=19)
	                	//displayVetor[i-1]
	                	display	= formatData.formatDataAlfa(bufferReadAlfa);
                }else {
                	//displayVetor[i-1]
                	display	= "Error";
	            }
                //display = displayVetor[i-1];
                viewService.writeText(i, display);
                
                
                bufferRead = null;
                bufferReadAlfa = null;
            	primaryViewController.txLog.setText("Conection OK");
            	primaryViewController.txLog1.setText("Conection OK");
            }
            else{
            	lostConection++;
            	Thread.sleep(300); 
            	
            	if(lostConection <= attemptToReconnect) {
            		thread.interrupt();
            	}else {
                	//serialController.timerCancel();
            		threadCancel();
            	}
            } 

        }catch(Exception e) {
        	e.printStackTrace();
        	threadCancel();
        }
		
    }
    
    private void instanciates() {
    	if(serialService==null)serialService = DependencyInjection.getSerialService();
		if(serialController==null)serialController = DependencyInjection.getSerialController();
		if(primaryViewController==null)primaryViewController = DependencyInjection.getPrimaryViewController();
		if(serialProperties==null)serialProperties = DependencyInjection.getSerialProperties();
		if(formatData==null)formatData = DependencyInjection.getFormatData();
		if(viewService==null)viewService = DependencyInjection.getPrimaryViewService();
	}

	public void threadCancel() {
		
    	primaryViewController.txLog.setText("Conection Lost");
    	primaryViewController.txLog1.setText("Conection Lost");
    	
		if(!thread.isInterrupted()) {
			thread.suspend();
		}	
		whileRead = false;
	}
	
	public Boolean getWhileRead() {
		return this.whileRead;
	}
}
