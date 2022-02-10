package com.rodrigues.rodrigues.serial.controller;

import com.rodrigues.rodrigues.gui.FXMLController;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.service.SerialService;
import com.rodrigues.rodrigues.serial.utilitary.CalculatorData;

public class ReadControler implements Runnable{
    private FXMLController fxmlController;
    private SerialService service;
    private byte[] numGadgets;
    private byte[] bufferRead= new byte[8];
    private Thread thread = new Thread(this);
    private final SerialProperties properties = new SerialProperties("COM4");


    public ReadControler(int num, FXMLController fxmlController){
        this.numGadgets = new byte[num];
        this.fxmlController = fxmlController;
    }

    public void read() throws InterruptedException {if(!thread.isAlive()){thread.run();}}

    @Override
    public void run() {
        if(numGadgets!=null){
            for(int i=0; i < numGadgets.length; i++){
                try{
                    service = new SerialService(properties.getPorta(), properties.getBaud(),properties.getTimeout());
                    bufferRead = CalculatorData.addressRead(i+1);
                    service.getPortIdentifier();
                    service.openPort();
                    service.writeData(bufferRead);
                    Thread.sleep(100);
                    service.readData();
                    Thread.sleep(300);
                    indicadores(i+1);
                }catch(Exception ex) {System.out.println("Erro na leitura do aparelho de endereço = " + (i+1) + "STATUS: ");
                ex.printStackTrace();}
            }
        }
        thread.stop();
    }

    private void indicadores(int i) {
        if(service.getDisplay()!=null){
            String display = service.getDisplay();
            if(i==1){fxmlController.cq1.setText(display);}
            if(i==2){ fxmlController.cq2.setText(display);}
            if(i==3){ fxmlController.cq3.setText(display);}
            if(i==4){ fxmlController.s1.setText(display);}
            if(i==5){ fxmlController.s2.setText(display);}
            if(i==6){ fxmlController.s3.setText(display);}
            if(i==7){ fxmlController.topo.setText(display);}
            if(i==8){ fxmlController.coroa.setText(display);}
            if(i==9){ fxmlController.ptopo.setText(display);}
            if(i==10){ fxmlController.vazao.setText(display);}
            if(i==11){ fxmlController.psm.setText(display);}
        }
    }
}
