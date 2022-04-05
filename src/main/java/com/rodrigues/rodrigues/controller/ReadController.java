package com.rodrigues.rodrigues.controller;

import javax.comm.SerialPort;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.serial.dao.WriteSetPoints;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;
import com.rodrigues.rodrigues.serial.utilitary.Gadgets;
import com.rodrigues.rodrigues.serial.utilitary.calc.CalculatorByteInt;
import com.rodrigues.rodrigues.serial.utilitary.calc.CalculatorData;
import com.rodrigues.rodrigues.service.FormatData;
import com.rodrigues.rodrigues.service.PrimaryViewService;
import com.rodrigues.rodrigues.service.SerialService;

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
    
    public static String[] displayVetor = new String[28];
    
    private int bufferSizeRead;
    private int bufferSizeWrite;
     
    private String display;

    private Thread thread = new Thread(this);
    
    
    private Boolean whileRead = false;
    private Boolean readSetPoints = false;
    private Boolean writeSetPoint = false;
    private int readSetPointsEnd;
	private String[] setPoints = new String[4];

    
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
	    		int cont = 0;

	    		
	    		for(int i = 0; i < 9; i++) {
	    			
	    			if(i<8) {	cont = 18;
	    			
	    			}else cont = 0;
	    			
	    		
		            for(; cont < numGadgets.length; cont++){
		
		            	if(thread.isInterrupted()) {
		                	primaryViewController.txLog.setText("Conection Lost");
		                	primaryViewController.txLog1.setText("Conection Lost");
		                	//primaryViewController.chartStop();
		            		return;
		            	}
		            	//System.out.println(Teste.valueOf("ALFA").getDivisao() + "  " + Teste.ALFA.getBufferWrite() + "   "  +  Teste.ALFA.getBufferRead());
		                if(readSetPoints) {
		                	readSetPoints();
		                	readSetPoints = false;
		                }
		                if(writeSetPoint) {
		                	writeSetPointsView();
		                	writeSetPoint = false;
		                }
		            	
		            	sweep(cont+1, serialService.enablePortCom());
		           }
	            }
	    	}
	    	
	    	
    	} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    
    private void sweep(int i, SerialPort serial) {

    	
    	try{
 
    		
        	if(serial != null) {               
                
				if(i>=19) {
					bufferWrite = CalculatorData.addressReadAlfa(i,Gadgets.ALFA.getRegistrador(),Gadgets.ALFA.getTotalRegistradores()); 
					bufferSizeRead = Gadgets.ALFA.getBufferRead();
					bufferSizeWrite = Gadgets.ALFA.getBufferWrite();
					serialService.writeData(bufferWrite, serial, bufferSizeWrite);
	            	bufferReadAlfa = serialService.readData(serial, bufferSizeRead);
	            	
	            	
				}  
				else {
					if(i==15)
						bufferWrite = CalculatorData.addressRead(i,2);
				
		            else 
		            	bufferWrite = CalculatorData.addressRead(i,1);
		            
					
	            	bufferSizeRead = Gadgets.N1500.getBufferRead();
	            	bufferSizeWrite = Gadgets.N1500.getBufferWrite();
	            	serialService.writeData(bufferWrite, serial, bufferSizeWrite);
	            	bufferRead = serialService.readData(serial, bufferSizeRead);
	            	
		        }
	                
                if(bufferRead != null) {                  	
                	
	                if((i >= 1 )&&(i<=11)||(i==13 || i==14)) {	                	
	                	display = formatData.formatData(bufferRead, "N1540", "int");
	                	displayVetor[i-1] = display;
	                			
                }
	                else if (i==12 || i==17||i==18) {	                	
	                	display= formatData.formatData(bufferRead, "N1540_4_a_20", "double");
	                	displayVetor[i-1] = display;
	                }
	                else if (i==15||i==16) {
	                	
	                	display	= formatData.formatData(bufferRead, "N2000", "int");
	                	displayVetor[i-1] = display;
	                }
                }else if(bufferReadAlfa != null){
                	if(i>=19)
                	{
	                	display	= formatData.formatDataAlfa(bufferReadAlfa);
                		displayVetor[i-1] = display;
                	}
                }else {
                	displayVetor[i-1]= "Error";
                	display	= "Error";
	            }
                display = displayVetor[i-1];
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
		if(writeSetPoints==null)writeSetPoints = DependencyInjection.getWritesetpoints();
	}

	@SuppressWarnings("deprecation")
	public void threadCancel() {
		
    	primaryViewController.txLog.setText("Conection Lost");
    	primaryViewController.txLog1.setText("Conection Lost");
    	primaryViewController.chartStop();
    	
		if(!thread.isInterrupted()) {
			thread.suspend();
		}	
		whileRead = false;
	}
	
	public Boolean getWhileRead() {
		return this.whileRead;
	}
	
	public void setReadSetPoints(Boolean readSetPoints) {
		this.readSetPoints = readSetPoints;
	}
	
	public void setEndReadSetPoints(int readSetPointsEnd) {		
		this.readSetPointsEnd = readSetPointsEnd;	
		
		
	}
	
	public void setWriteSetPoints(Boolean writeSetPoint, String[] setPoints) {		
		this.writeSetPoint = writeSetPoint;
		this.setPoints  = setPoints;
	}
			
	private void readSetPoints() {
		SerialPort serial = serialService.enablePortCom();
    	String[] setPoints = new String[4];
		
		bufferWrite = CalculatorData.addressReadAlfa(readSetPointsEnd,Gadgets.ALFA_LEI_SETPOINTS.getRegistrador(),Gadgets.ALFA_LEI_SETPOINTS.getTotalRegistradores()); 
    	bufferSizeRead = Gadgets.ALFA_LEI_SETPOINTS.getBufferRead();
    	bufferSizeWrite = Gadgets.ALFA_LEI_SETPOINTS.getBufferWrite();
    	serialService.writeData(bufferWrite, serial, bufferSizeWrite);
    	bufferReadAlfa = serialService.readData(serial, bufferSizeRead);
    	
    	
    	setPoints[0] = formatData.formatDataAlfaGeneric(
    			"ALFA_LEI_SETPOINTS", bufferReadAlfa, 
    			Gadgets.ALFA_FORMAT_SETPOINTS_VAZIA.getFormatDataLs(),
    			Gadgets.ALFA_FORMAT_SETPOINTS_VAZIA.getFormatDataMs());
    	setPoints[1] =
    			formatData.formatDataAlfaGeneric(
    			"ALFA_LEI_SETPOINTS", bufferReadAlfa, 
    			Gadgets.ALFA_FORMAT_SETPOINTS_1_4.getFormatDataLs(),
    			Gadgets.ALFA_FORMAT_SETPOINTS_1_4.getFormatDataMs());
    	setPoints[2] =
    			formatData.formatDataAlfaGeneric(
    			"ALFA_LEI_SETPOINTS", bufferReadAlfa, 
    			Gadgets.ALFA_FORMAT_SETPOINTS_2_5.getFormatDataLs(),
    			Gadgets.ALFA_FORMAT_SETPOINTS_2_5.getFormatDataMs());
    	setPoints[3] =
    			formatData.formatDataAlfaGeneric(
    			"ALFA_LEI_SETPOINTS", bufferReadAlfa, 
    			Gadgets.ALFA_FORMAT_SETPOINTS_3_6.getFormatDataLs(),
    			Gadgets.ALFA_FORMAT_SETPOINTS_3_6.getFormatDataMs());
    	
    	viewService.writeTextSetPoints(readSetPointsEnd, setPoints);

	}
	
	public void writeSetPointsView() {
		System.out.println(this.readSetPointsEnd);
		SerialPort serial = serialService.enablePortCom();
		byte[] buferr = new byte[Gadgets.ALFA_ESC_SETPOINTS.getBufferWrite()];
		try {
			Integer vazia = Integer.parseInt(this.setPoints[0]);
			Integer sp_1 = Integer.parseInt(this.setPoints[1]);
			Integer sp_2 = Integer.parseInt(this.setPoints[2]);
			Integer sp_3 = Integer.parseInt(this.setPoints[3]);
			//System.out.println(vazia +""+ sp_1 +""+sp_2+""+ sp_3);
			buferr = writeSetPoints.Write(
				this.readSetPointsEnd, 
				CalculatorByteInt.intToByteWord16(sp_1), 
				CalculatorByteInt.intToByteWord16(sp_2),
				CalculatorByteInt.intToByteWord16(sp_3),
				CalculatorByteInt.intToByteWord16(vazia));

			
			serialService.writeData(buferr, serial, Gadgets.ALFA_ESC_SETPOINTS.getBufferWrite());
		}catch(NumberFormatException e) {
		System.out.println("Peso do set point em formato incorreto");
		}
		serial.close();
	}

	public int getEndReadSetPoints() {		
		return this.readSetPointsEnd;
	}
}
