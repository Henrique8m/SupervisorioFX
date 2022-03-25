package com.rodrigues.rodrigues.serial.controller;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.serial.dao.WriteSetPoints;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.service.FormatData;
import com.rodrigues.rodrigues.serial.service.SerialService;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;
import com.rodrigues.rodrigues.serial.utilitary.calc.CalculatorData;

public class ReadController implements Runnable{

    private SerialProperties serialProperties;
    private PrimaryViewController primaryViewController;
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
     
    private String display;
    private String[] displayVetor = new String[29];
    
    private Thread thread = new Thread(this);
    
    private Boolean whileRead = false;

    
    private Boolean teste;
    
    
    
    public ReadController() {
		// TODO Auto-generated constructor stub
	}

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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	teste = true;
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
    }

    private void sweep(int i) {
        try{
        	
            serialService.setPortName(serialProperties.getPorta());
            serialService.setBaudRate(serialProperties.getBaud());
            serialService.setTimeout(serialProperties.getTimeout());
            serialService.setStopBits(serialProperties.getStopBits());
            
            if(i>=19)
            	bufferWrite = CalculatorData.addressReadAlfa(i,80,6);            
            else if(i==15)
            	bufferWrite = CalculatorData.addressRead(i,2);            
            else bufferWrite = CalculatorData.addressRead(i,1);
            
            if (teste) 
            if(serialService.getPortIdentifier()){
            	//serialService.openPort();
            	//serialService.writeDataAlfa(writeSetPoints.seletroraWrite(19));
            	//Thread.sleep(500);
            	//serialService.getPortIdentifier();
            	serialService.openPort();
            	serialService.writeDataAlfa(writeSetPoints.Write(19));
            	teste = false;
            	Thread.sleep(5000);
            }
            	
            if(serialService.getPortIdentifier()) {
                serialService.openPort();
                serialService.writeData(bufferWrite);
                
				if(i>=19)
	            	bufferReadAlfa = serialService.readDataAlfa();
	            else bufferRead = serialService.readData();
	                
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
	                indicadores(i, display);
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

    //Ate desenvolver algo melhor
    //essa vai ser a varredura dos aparelhos
    private void indicadores(int i, String display) {
    	try {        
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
            
            if(i==15){ primaryViewController.pcoroa.setText(display);}
            if(i==15){ primaryViewController.pcoroa1.setText(display);}
            
            if(i==17){ primaryViewController.vazao.setText(display);}
            if(i==17){ primaryViewController.vazao1.setText(display);}
            
            if(i==18){ primaryViewController.psm.setText(display);}
            if(i==18){ primaryViewController.psm1.setText(display);}
            
            if(i==16){ primaryViewController.pirometro.setText(display);}
            if(i==16){ primaryViewController.pirometro1.setText(display);}
            
            if(i==19){ primaryViewController.Balanca01.setText(display);}
            
            if(i==20){ primaryViewController.Balanca02.setText(display);}
            
            if(i==21){ primaryViewController.Balanca03.setText(display);}
            
            if(i==22){ primaryViewController.Balanca04.setText(display);}
            
            if(i==23){ primaryViewController.Balanca05.setText(display);}
            
            if(i==24){ primaryViewController.Balanca06.setText(display);}
            
            if(i==25){ primaryViewController.Balanca07.setText(display);}
            
            if(i==26){ primaryViewController.Balanca08.setText(display);}
            
            if(i==27){ primaryViewController.Balanca09.setText(display);}
            
            if(i==28){ primaryViewController.Balanca10.setText(display);}
            
            

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
		if(writeSetPoints==null)writeSetPoints = DependencyInjection.getWritesetpoints();
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
