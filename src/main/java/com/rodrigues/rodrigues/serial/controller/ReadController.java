package com.rodrigues.rodrigues.serial.controller;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.service.FormatData;
import com.rodrigues.rodrigues.serial.service.SerialService;
import com.rodrigues.rodrigues.serial.utilitary.CalculatorData;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;

public class ReadController implements Runnable{

    private SerialProperties serialProperties;
    private PrimaryViewController primaryViewController;
    private SerialService serialService;
    private SerialController serialController;
    private FormatData formatData;

    private int lostConection = 0;
    private int attemptToReconnect = 5;
    private byte[] numGadgets = new byte[18];
    private byte[] bufferWrite= new byte[8];
    private byte[] bufferRead = new byte[7];
    
    private String display;
    
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
    	try {
			thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(numGadgets!=null){
            for(int i=0; i < numGadgets.length; i++){

            	if(thread.isInterrupted()) {
                	primaryViewController.txLog.setText("Conection Lost");
                	primaryViewController.txLog1.setText("Conection Lost");
            		return;
            	}                	
                sweep(i+1);
                
            }
        }else threadCancel();        
    }

    private void sweep(int i) {
        try{
        	
            serialService.setPortName(serialProperties.getPorta());
            serialService.setBaudRate(serialProperties.getBaud());
            serialService.setTimeout(serialProperties.getTimeout());
            
            bufferWrite = CalculatorData.addressRead(i);

            if(serialService.getPortIdentifier()) {
                serialService.openPort();
                serialService.writeData(bufferWrite);
                Thread.sleep(100);
                bufferRead = serialService.readData();
	                if(bufferRead != null) {                
	                if((i >= 1 )&&(i<=11)||(i==13 || i==14))
	                	display = formatData.formatData(bufferRead, "N1540", "int");
	                else if (i==12 || i==17||i==18)
	                	display = formatData.formatData(bufferRead, "N1540_4_a_20", "double");
	                else if (i==15||i==16)
	                	display = formatData.formatData(bufferRead, "N2000", "int");
	                }else {
	                	display = "Error";
	                }
	                indicadores(i);
	                System.out.println("Aparelho end = " + i + " Pv = " + display);
                Thread.sleep(300);
                indicadores(i);
            	primaryViewController.txLog.setText("Conection OK");
            	primaryViewController.txLog1.setText("Conection OK");
            }
            else{
            	lostConection++;
            	Thread.sleep(300); 
            	
            	if(lostConection <= attemptToReconnect) {
            		thread.interrupt();
            	}else {
                	serialController.timerCancel();
            	}
            } 

        }catch(Exception e) {
        	e.printStackTrace();
        	threadCancel();
        }
		
    }

    //Ate desenvolver algo melhor
    //essa vai ser a varredura dos aparelhos
    private void indicadores(int i) {
    	try {        
            System.out.println(i + "  =  " + display);
            if(i==1){primaryViewController.cq1.setText(display);}
            if(i==2){ primaryViewController.cq2.setText(display);}
            if(i==3){ primaryViewController.cq3.setText(display);}
            
            if(i==4){ primaryViewController.cm1.setText(display);}
            if(i==5){ primaryViewController.cm2.setText(display);}
            if(i==6){ primaryViewController.cm3.setText(display);}
            
            if(i==7){ primaryViewController.s1.setText(display);}
            if(i==8){ primaryViewController.s2.setText(display);}
            if(i==9){ primaryViewController.s3.setText(display);}
            
            if(i==10){ primaryViewController.topoE.setText(display);}
            if(i==11){ primaryViewController.topoD.setText(display);}
            
            if(i==13){ primaryViewController.coroaE.setText(display);}
            if(i==14){ primaryViewController.coroaD.setText(display);}
            
            if(i==10){ primaryViewController.topoE1.setText(display);}
            if(i==11){ primaryViewController.topoD1.setText(display);}
            
            if(i==13){ primaryViewController.coroaE1.setText(display);}
            if(i==14){ primaryViewController.coroaD1.setText(display);}
            
            if(i==12){ primaryViewController.ptopo.setText(display);}
            if(i==12){ primaryViewController.ptopo1.setText(display);}
            
            if(i==17){ primaryViewController.vazao.setText(display);}
            if(i==17){ primaryViewController.vazao1.setText(display);}
            
            if(i==18){ primaryViewController.psm.setText(display);}
            if(i==18){ primaryViewController.psm1.setText(display);}
            
            if(i==16){ primaryViewController.pirometro.setText(display);}
            if(i==16){ primaryViewController.pirometro1.setText(display);}

    	}catch(Exception e) {
    		e.printStackTrace();
    	}

    }
    
    private void instanciates() {
    	if(serialService==null)serialService = DependencyInjection.getSerialService();
		if(serialController==null)serialController = DependencyInjection.getSerialController();
		if(primaryViewController==null)primaryViewController = DependencyInjection.getPrimaryViewController();
		if(serialProperties==null)serialProperties = DependencyInjection.getSerialProperties();
		if(formatData==null)formatData = DependencyInjection.getFormatData();
	}

	@SuppressWarnings("deprecation")
	public void threadCancel() {
		
    	primaryViewController.txLog.setText("Conection Lost");
    	primaryViewController.txLog1.setText("Conection Lost");
    	
		if(!thread.isInterrupted()) {
			thread.interrupt();
		}	
	}
}
